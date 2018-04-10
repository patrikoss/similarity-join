import java.io.IOException;
import java.util.Random;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.Reducer.Context;

public class SimilarityCalculatorReducer extends Reducer<Text, NullWritable, Text, DoubleWritable> {
	private HdfsTweetsFileReader tweets;
	private String[] candidatePair;
	String tweet1Content, tweet2Content;
	private DoubleWritable outDuble = new DoubleWritable();
	private Text outText = new Text();
	private SimilarityMeasure similarityMeasure;
	
	@Override
	protected void setup(Context context) throws IOException, InterruptedException{
		Configuration conf = context.getConfiguration();
		// if the similarity is not set, use hamming_distance
		String sim = conf.get("similarityMeasure", "hamming_distance");
		if(sim.equals("hamming_distance")) {
			similarityMeasure = new HammingDistance();
		}else if (sim.equals("jaccard_similarity")) {
			int shingleLength = conf.getInt("shingleLength", 5);
			similarityMeasure = new JaccardSimilarity(shingleLength);
		}
		this.tweets = new HdfsTweetsFileReader(conf);
	}
	
	@Override
	public void reduce(Text candidatePair, Iterable<NullWritable> dummies,
			Context context) 
			throws IOException, InterruptedException {
		
		this.candidatePair = candidatePair.toString().split(",");
		tweet1Content = this.tweets.getTweetContent(Integer.parseInt(this.candidatePair[0]));
		tweet2Content = this.tweets.getTweetContent(Integer.parseInt(this.candidatePair[1]));
		double js = similarityMeasure.calculate(tweet1Content, tweet2Content);
		outText.set(tweet1Content + "," + tweet2Content);
		outDuble.set(js);
		context.write(outText, outDuble);
	}
}
