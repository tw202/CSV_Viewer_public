package Kata1;

import java.util.ArrayList;

public class Page {
	public ArrayList<String[]> lines;

	public Page(ArrayList<String[]> lines) {
		this.lines = lines;
	}

	public void printPage(int[] widths) {
		for (String[] currentLine : lines) {
			System.out.println("");
			for (int indexOfColoumn = 0; indexOfColoumn < currentLine.length; indexOfColoumn++) {

				String value = String.format("%-" + widths[indexOfColoumn] + "s", currentLine[indexOfColoumn]);
				System.out.print(value + "|");
			}
		}
	}
}
