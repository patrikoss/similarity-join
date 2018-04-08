
public class HammingDistance {

	public static int calculate(String s1, String s2) {
		int minLen = Math.min(s1.length(), s2.length());
		int maxLen = Math.max(s1.length(), s2.length());
		int difference = maxLen - minLen;
		for(int i = 0; i < minLen; i++) {
			if(s1.charAt(i) != s2.charAt(i)) difference++;
		}
		return difference;
	}

}
