package Kata1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CSVreadAndPrint {
	/**
	 * Prints the CSV file.
	 * 
	 * @param filepath            the path of the CSV file.
	 * @param numberofOutputlines defines how many lines of the table are shown per
	 *                            page.
	 * @delimiter delimiter that separates the values in the CSV file.
	 * 
	 */
	public static void printCsv(String filePath, int numberOfOutputLines, String delimiter) {
		List<String> unseparatedLines = readLines(filePath);
		List<String[]> separatedHeader = separateHeader(unseparatedLines, delimiter);
		List<String[]> separatedLinesWithoutHeader = separateLinesWithoutHeader(unseparatedLines, delimiter);
		int[] widths = Widthsgetter.getWidthPerColoumn(separatedLinesWithoutHeader, separatedHeader);
		ConsoleOutput.print(separatedHeader,separatedLinesWithoutHeader, widths, numberOfOutputLines);
	}

	private static List<String> readLines(String filePath) {
		try {
			Stream<String> rows = Files.lines(Paths.get(filePath));
			List<String> unseparatedLines = rows.collect(Collectors.toList());
			rows.close();
			return unseparatedLines;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	private static List<String[]> separateHeader(List<String> unseparatedLines, String delimiter) {

		List<String> manipulatedLines = addExtraColumnToHeader(unseparatedLines, delimiter);
		List<String[]> separatedHeader = manipulatedLines
				.stream()
				.limit(1)
				.map(x -> x.split(delimiter))
				.collect(Collectors.toList());

		return separatedHeader;
	}

	private static List<String[]> separateLinesWithoutHeader(List<String> unseparatedLines, String delimiter) {
		List<String> manipulatedLines = addCountingNumberToLines(unseparatedLines, delimiter);
		List<String[]> separatedValuesPerLine = manipulatedLines
				.stream()
				.map(x -> x.split(delimiter))
				.collect(Collectors.toList());
		return separatedValuesPerLine;
	}

	private static List<String> addExtraColumnToHeader(List<String> unseparatedLines, String delimiter) {
		List<String> manipulatedHeader = unseparatedLines.stream()
				.limit(1)
				.map(x -> "No." + delimiter + x)
				.collect(Collectors.toList());
		return manipulatedHeader;
	}
	
	private static List<String> addCountingNumberToLines(List<String> unseparatedLines, String delimiter){
		AtomicInteger row = new AtomicInteger(1);
		List<String> manipulatedLines = unseparatedLines
				.stream()
				.skip(1)
				.map(x -> {
					int rowInt = row.intValue();
					String rowString = Integer.toString(rowInt);
					String modifiedRow = rowString + "."+ delimiter + x;
					row.addAndGet(1);
					return modifiedRow;		})
				.collect(Collectors.toList());
		return manipulatedLines;
	}
}
