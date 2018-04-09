
public class Main {

	public static void main(String[] args) throws Exception {

		if (args.length != 4) {
			System.err.println("Enter valid number of arguments <Input-File> <Tmpdirectory1> <TmpDirectory2> <Outputlocation>");
			System.exit(1);
		}
		boolean firstJob = LSH.run(args[0], args[1]);
		if (!firstJob) {
			System.exit(-1);
		}
		boolean secondJob = UniqueCandidatePairJob.run(args[1], args[2]);
		if(!secondJob) {
			System.exit(-1);
		}
		boolean thirdJob = SimilarityCalculatorJob.run(args[0], args[2], args[3]);
		if(!thirdJob) {
			System.exit(-1);
		}
		
	}

}