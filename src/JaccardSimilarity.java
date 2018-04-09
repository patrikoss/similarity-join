import java.util.HashSet;
import java.util.Set;

public class JaccardSimilarity implements SimilarityMeasure {
	private int shingleLength;
	
	public JaccardSimilarity(int shingleLength) {
		this.shingleLength = shingleLength;
	}
	
	@Override
	public double calculate(String s1, String s2) {
		Set<Integer> shingles1 = Shingles.getShingles(s1, this.shingleLength);
		Set<Integer> shingles2 = Shingles.getShingles(s2, this.shingleLength);
		
		Set<Integer> intersection = new HashSet<Integer>(shingles1);
		intersection.retainAll(shingles2);
		
		Set<Integer> sum = new HashSet<Integer>(shingles1);
		sum.addAll(shingles2);
		
		if(sum.size() == 0) {
			return 1.0;
		}else {
			return intersection.size() / (double) sum.size();
		}
	}
}
