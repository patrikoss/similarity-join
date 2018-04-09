import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;

import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.join.TupleWritable;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;


public class UniqueCandidatePairJob {

	public static boolean run(String inputPath, String outputPath) throws Exception {

		Configuration conf = new Configuration();
		FileSystem fs = FileSystem.get(conf);
		Job job = Job.getInstance(conf, "Removing duplicate candidate pairs");
		
		job.setJarByClass(UniqueCandidatePairJob.class);
		job.setMapperClass(IdentityMapper.class);
		job.setReducerClass(UniqueCandidatePairReducer.class);
  
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(NullWritable.class);
		
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(NullWritable.class);

		job.setInputFormatClass(TextInputFormat.class);
		job.setOutputFormatClass(TextOutputFormat.class);
  
		TextInputFormat.addInputPath(job, new Path(inputPath));
		TextOutputFormat.setOutputPath(job, new Path(outputPath));

		return job.waitForCompletion(true);
	}

}
