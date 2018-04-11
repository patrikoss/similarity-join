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

public class LSHJob {

	public static boolean run(String inputPath, String outputPath, int shingleLength) throws Exception {

		Configuration conf = new Configuration();
		conf.setInt("shingleLength", shingleLength);
		FileSystem fs = FileSystem.get(conf);
		Job job = Job.getInstance(conf, "Local sensitivity hashing");
		
		job.setJarByClass(LSHJob.class);
		job.setMapperClass(MapperLSH.class);
		job.setReducerClass(ReducerLSH.class);
  
		job.setMapOutputKeyClass(IntWritable.class);
		job.setMapOutputValueClass(IntWritable.class);
		
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(NullWritable.class);

		job.setInputFormatClass(TextInputFormat.class);
		job.setOutputFormatClass(TextOutputFormat.class);
  
		TextInputFormat.addInputPath(job, new Path(inputPath));
		TextOutputFormat.setOutputPath(job, new Path(outputPath));

		return job.waitForCompletion(true);
	}

}
