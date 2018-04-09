import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
 
public class HdfsTweetsFileReader {
	private BufferedReader br;
	private ArrayList<String> tweets = new ArrayList<String>();
	
	public HdfsTweetsFileReader(Configuration conf) throws IOException {
		String tweetsFilepath = conf.get("tweetsFilepath");
		this.open(tweetsFilepath, conf);
		this.readTweets();
		this.close();
	}
	
	private void open(String filepath, Configuration conf) throws IOException {
		Path path = new Path(filepath);
		FileSystem fs = FileSystem.get(conf);
		this.br = new BufferedReader(new InputStreamReader(fs.open(path)));
	}
 
	private void close() throws IOException {
		this.br.close();
	}
	
	private void readTweets() throws IOException {
		// read header of tweets file
		String line = br.readLine();
		while (line != null) {
			// remove all non-letter characters from line leaving only tweet content
			line = line.replaceAll("[^a-zA-Z]", "");
			tweets.add(line);
			//read next tweet
			line = br.readLine();
		}
	}
	
	public String getTweetContent(int index) {
		if (0 <= index && index < this.tweets.size()) {
			return this.tweets.get(index);
		}
		throw new IndexOutOfBoundsException("Invalid tweet identifier");
	}
	
}
