package Kata1;

public class App {

	public static void main(String[] args) {
		final String filePath = args[0];
		final int numberOfOutputLines = 3;
		final String delimiter = ";";
		CSVreadAndPrint.printCsv(filePath, numberOfOutputLines, delimiter);
	}
}
