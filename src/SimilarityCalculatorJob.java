import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

public class SimilarityCalculatorJob {

	public static boolean run(String tweetsFilepath, String inputPath, String outputPath, 
			String similarityMeasure, int shingleLength) throws Exception {

		Configuration conf = new Configuration();
		JaccardSimilarity s = new JaccardSimilarity(5);
		
		similarityMeasure = similarityMeasure.toLowerCase();
		if(similarityMeasure.equals("jaccard_similarity")) {
			conf.set("similarityMeasure", similarityMeasure);
			conf.setInt("shingleLength", shingleLength);
		} else if(similarityMeasure.equals("hamming_distance")) {
			conf.set("similarityMeasure", similarityMeasure);
		}
		
		conf.set("tweetsFilepath", tweetsFilepath);
		FileSystem fs = FileSystem.get(conf);
		Job job = Job.getInstance(conf, "Computing similarity measure on candidate pairs");
		
		job.setJarByClass(SimilarityCalculatorJob.class);
		job.setMapperClass(IdentityMapper.class);
		job.setReducerClass(SimilarityCalculatorReducer.class);
  
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(NullWritable.class);
		
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(DoubleWritable.class);

		job.setInputFormatClass(TextInputFormat.class);
		job.setOutputFormatClass(TextOutputFormat.class);
  
		TextInputFormat.addInputPath(job, new Path(inputPath));
		TextOutputFormat.setOutputPath(job, new Path(outputPath));

		return job.waitForCompletion(true);
	}

}
