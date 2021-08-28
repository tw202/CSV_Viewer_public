package Kata1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ConsoleOutput {

	private final static String NEXT_PAGE = "N";
	private final static String PREVIOUS_PAGE = "P";
	private final static String FIRST_PAGE = "F";
	private final static String EXIT = "X";
	private final static String LAST_PAGE = "L";

	public static void print(List<String[]> separatedHeader, List<String[]> separatedLinesWithoutHeader, int[] widths, int numberOfLinesPerPage) {
	
		String[]header = separatedHeader.get(0);
		final ArrayList<Page> pages = createPages(separatedLinesWithoutHeader, numberOfLinesPerPage);
		int currentPageIndex = 0;
		int lastPageIndex = pages.size()-1;
		boolean isRunning = true;

		while (isRunning) {
	
			Page currentPage = pages.get(currentPageIndex);
			if (currentPageIndex == 0) {
				printHeader(header, widths);
			}
			currentPage.printPage(widths);
			int numberOfPages = pages.size();
			int currentPageNumber = currentPageIndex + 1;
			String userInput = askUserForAction(numberOfLinesPerPage, currentPageNumber, numberOfPages);
			if (userInput.equals(NEXT_PAGE)) {
				currentPageIndex++;
			} else if (userInput.equals(PREVIOUS_PAGE)) {
				currentPageIndex--;
			} else if (userInput.equals(FIRST_PAGE)) {
				currentPageIndex=0;
			} else if (userInput.equals(EXIT)) {
				break;
			} else if (userInput.equals(LAST_PAGE)) {
				currentPageIndex=lastPageIndex;
			}
		}
	}

	private static ArrayList<Page> createPages(List<String[]> separatedValuesPerLine, int numberOfLinesPerPage) {
		final int numberOfPages = (int) Math.ceil(separatedValuesPerLine.size() / numberOfLinesPerPage);
		ArrayList<Page> pages = new ArrayList<>(numberOfPages);
	
		for (int currentLineIndex = 0; currentLineIndex < separatedValuesPerLine.size();) {
			ArrayList<String[]> linesForOnePage = new ArrayList<>(numberOfLinesPerPage);

			for (int i = 0; i < numberOfLinesPerPage; i++) {
				try {
					linesForOnePage.add(separatedValuesPerLine.get(currentLineIndex));
					currentLineIndex++;
				} catch (IndexOutOfBoundsException e) {
					// happens when last page is not full -> do nothing
				}
			}
			pages.add(new Page(linesForOnePage));
		}
		return pages;
	}

	private static void printHeader(String[] headerLine, int[] widths) {
		int indexOfColoumn = 0;
		for (String headervalue : headerLine) {
			String valueToPrint = String.format("%-" + widths[indexOfColoumn] + "s", headervalue);
			System.out.print(valueToPrint + "|");
			indexOfColoumn++;
		}
		System.out.println("");
		for (int coloumn = 0; coloumn < widths.length; coloumn++) {
			String headerFormatting = String.format("%" + (widths[coloumn] + 1) + "s", "+").replace(" ", "-");
			System.out.print(headerFormatting);
		}
	}

	private static String askUserForAction(int numberOfOutputLines, int currentPage, int totalPages) {
		System.out.println("");
		System.out.println("Page "+ currentPage +" of " + totalPages);
		System.out.println("N(ext page, P(revious page, F(irst page, L(ast page, eX(it");
		BufferedReader userInputReader = new BufferedReader(new InputStreamReader(System.in));
		try {
			return userInputReader.readLine();
			
		} catch (IOException e) {
			e.printStackTrace();
			// Logger.log(LoggingLevel.CRITICAL, e.getMessage());
		}
		return "";
	}
}
