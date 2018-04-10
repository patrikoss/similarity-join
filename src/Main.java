
public class Main {

	public static void main(String[] args) throws Exception {

		if (args.length < 5 || args.length > 6) {
			System.err.println(
				"Enter valid number of arguments:" +
				"<Input-File>" +
				"<Tmpdirectory1>" +
				"<TmpDirectory2>" +
				"<Outputlocation>" +
				"<similarity_measyre>" +
				"[optional: shingleLength]"
			);
			System.exit(1);
		}

		int shingleLength = 5;
		if (args.length == 6) {
			shingleLength = Integer.parseInt(args[5]);
		}

		boolean firstJob = LSHJob.run(args[0], args[1], shingleLength);
		if (!firstJob) {
			System.exit(-1);
		}

		boolean secondJob = UniqueCandidatePairJob.run(args[1], args[2]);
		if(!secondJob) {
			System.exit(-1);
		}
		boolean thirdJob = SimilarityCalculatorJob.run(args[0], args[2], args[3], args[4], shingleLength);
		if(!thirdJob) {
			System.exit(-1);
		}
		
	}

}