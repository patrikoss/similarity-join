
public class Main {

	public static void main(String[] args) throws Exception {

		if (args.length != 2) {
			System.err.println("Enter valid number of arguments <Inputdirectory>  <Outputlocation>");
			System.exit(1);
		}
		System.exit(LSH.run(args[0], args[1]) ? 0:1);
	}

}