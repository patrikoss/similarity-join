import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.Reducer.Context;

public class UniqueCandidatePairReducer extends Reducer<Text, NullWritable, Text, NullWritable> {
	@Override
	public void reduce(Text candidatePair, Iterable<NullWritable> dummies,
			Context context) 
			throws IOException, InterruptedException {
		context.write(candidatePair, NullWritable.get());
	}
	
	
}
