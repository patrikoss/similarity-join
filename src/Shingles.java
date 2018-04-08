import java.util.HashSet;
import java.util.Set;

public class Shingles {
	public static HashSet<Integer> getShingles(String text, int shingleLength){
		HashSet<Integer> shingles = new HashSet<Integer>();
		for(int i=0; i < text.length() - shingleLength; i++) {
			shingles.add(text.substring(i, i+shingleLength).hashCode());
		}
		return shingles;
	}
}
