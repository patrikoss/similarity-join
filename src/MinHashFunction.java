
public class MinHashFunction {
	private int seed;
	
	public MinHashFunction(int seed) {
		this.seed = seed;
	}
	
	public int hash(int val) {
		return Integer.hashCode(val) ^ this.seed;
	}
	
	public int hash(String val) {
		return val.hashCode() ^ this.seed;
	}
}
