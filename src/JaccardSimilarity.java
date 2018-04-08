import java.util.HashSet;
import java.util.Set;

public class JaccardSimilarity {
	public static double calculate(String s1, String s2, int shingleLength) {
		Set<Integer> shingles1 = Shingles.getShingles(s1, shingleLength);
		Set<Integer> shingles2 = Shingles.getShingles(s2, shingleLength);
		
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
