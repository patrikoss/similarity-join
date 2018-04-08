import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.Reducer.Context;
import org.apache.hadoop.mapreduce.lib.join.TupleWritable;

public class ReducerLSH
	extends Reducer<IntWritable,IntWritable,TupleWritable,NullWritable> {

	private static int bands;
	private IntWritable outInts[] = new IntWritable[] {new IntWritable(), new IntWritable()};
	private ArrayList<Integer> oddTweets = new ArrayList<Integer>();
	private ArrayList<Integer> evenTweets = new ArrayList<Integer>();
	
	@Override
	public void reduce(IntWritable bandHash, Iterable<IntWritable> tweetsId,
			Context context) 
			throws IOException, InterruptedException {
		
		// distribute tweets across two lists: 
		// those with odd id, and those with even id
		for(IntWritable tweetId : tweetsId) {
			if ((tweetId.get() & 1) == 1) {
				oddTweets.add(tweetId.get());
			}else {
				evenTweets.add(tweetId.get());
			}
		}
		// save the tweets' candidate pairs
		for(Integer oddTweet : oddTweets) {
			for (Integer evenTweet : evenTweets) {
				outInts[0].set(oddTweet.intValue());
				outInts[1].set(evenTweet.intValue());
				context.write(new TupleWritable(outInts), NullWritable.get());
			}
		}
		
		// clear the lists after the method finishes
		oddTweets.clear();
		evenTweets.clear();
	}
}