import java.io.IOException;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Mapper.Context;

public class IdentityMapper extends Mapper<Object, Text, Text, NullWritable> {

	@Override
	public void map(Object dummy, Text candidatePair, Context context) 
			throws IOException, InterruptedException {
		
		context.write(candidatePair, NullWritable.get());
	}
}
