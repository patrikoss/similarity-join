import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.mapreduce.lib.join.TupleWritable;

public class PairWritableComparable extends TupleWritable implements WritableComparable<PairWritableComparable> {

	public PairWritableComparable(WritableComparable arr[]) {
		super(arr);
	}
	
	@Override
	public int compareTo(PairWritableComparable other) {
		int firstCmp = ((WritableComparable) this.get(0)).compareTo((WritableComparable) other.get(0));
		int sndCmp = ((WritableComparable) this.get(1)).compareTo((WritableComparable) other.get(1));
		return firstCmp != 0 ? firstCmp : sndCmp;
	}
	
}
