import java.io.IOException;
import java.util.HashSet;
import java.util.Random;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Mapper.Context;


public class MapperLSH
	extends Mapper<Object, Text, IntWritable, IntWritable>{	

	private static int shingleLength;
	private static int signatureLength;
	private static int bands;
	private static MinHashFunction minHashFunction[];
	
	private Integer signature[];
	private String tweet[] = new String[2];
	private HashSet<Integer> shingles = new HashSet<Integer>();
	
	// variables defined for hadoop output
	private IntWritable outInt1 = new IntWritable(-1);
	private IntWritable outInt2 = new IntWritable(-1);
	private Text outString = new Text();
	
	@Override
	protected void setup(Context context) throws IOException, InterruptedException{
		Configuration conf = context.getConfiguration();
		
		int initialSeed = conf.getInt("initialSeed", 1000);
		Random random = new Random(initialSeed);
		
		this.shingleLength = conf.getInt("shingleLength", 5);
		this.signatureLength = conf.getInt("signatureLength", 100);
		this.bands = conf.getInt("bands", 20);
		this.minHashFunction = new MinHashFunction[this.signatureLength];
		this.signature = new Integer[this.signatureLength];
		
		for(int i = 0; i < this.signatureLength; i++) {
			int seedi = random.nextInt();
			// initialize hash function for signature generation
			this.minHashFunction[i] = new MinHashFunction(seedi);
		}
		
	}
	
	@Override
	public void map(Object dummy, Text tweet, Context context) 
			throws IOException, InterruptedException {
		
		// split the tweet into an array of [tweetId, tweetContent]
		this.tweet = tweet.toString().split(",", 2);
		// remove all non-digits characters from tweet id
		this.tweet[0] = this.tweet[0].replaceAll("[^\\d]", "");
		// remove all non-letter characters from tweet content
		this.tweet[1] = this.tweet[1].replaceAll("[^a-zA-Z]", "");
		
		// if the tweet has no id(e.g csv header), then stop processing it
		if (this.tweet[0].length() == 0) {
			return;
		}
		
		//reset the signature array
		for(int i = 0; i < this.signatureLength; i++) {
			// initialize the signature values
			this.signature[i] = Integer.MAX_VALUE;
		}
		
		shingles = Shingles.getShingles(this.tweet[1], shingleLength);
		// calculate the signature composing of this.signatureLength integer values
		for(int hashFunNr = 0; hashFunNr < this.signatureLength; hashFunNr++) {
			for(Integer shingle : shingles) {
				this.signature[hashFunNr] = Math.min(
					this.minHashFunction[hashFunNr].hash(shingle),
					this.signature[hashFunNr]);
				
			}
		}
		
		String bandHash;
		// divide the minhashing signature into predefined number of bands
		// and output them
		// the last band may have less than bandLength elements
		int bandLength = (int) Math.ceil(this.signatureLength / (double) this.bands);
		for(int bandNr = 0; bandNr < this.bands; bandNr++) {
			bandHash = "";
			for(int i=bandNr*bandLength; 
				i < (bandNr+1)*bandLength && i < this.signatureLength; i++) {
				
				bandHash = bandHash.concat(Integer.toString(this.signature[i]));
				bandHash.concat(",");
			}
			
			// set hash of the band as output key
			outInt1.set(bandHash.hashCode());
			// set id of the tweet as output value
			outInt2.set(Integer.parseInt(this.tweet[0]));
			// output key-value for further processing
			context.write(outInt1, outInt2);
		}
		
    }
}