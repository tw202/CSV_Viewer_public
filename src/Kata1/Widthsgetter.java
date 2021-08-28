package Kata1;

import java.util.ArrayList;
import java.util.List;

public class Widthsgetter {
	
	public static int [] getWidthPerColoumn(List<String[]> separatedLinesWithoutHeader, List<String[]> separatedHeader) {
		int widthsOfLinesWoHeader [] = new int [separatedLinesWithoutHeader.get(0).length];
		separatedLinesWithoutHeader.add(separatedHeader.get(0)); //merge header and body to scan as a whole for the needed widths
		
		for (String[] array : separatedLinesWithoutHeader) {
// TODO foreach
			for (int index = 0; index < array.length; index++) {
				
				int width = array[index].length();				
				if ( width > widthsOfLinesWoHeader[index]) {
					widthsOfLinesWoHeader[index] = width;
				}
			}
		}
		separatedLinesWithoutHeader.remove(separatedLinesWithoutHeader.size()-1); //separate header and body again for further prosessing
		return widthsOfLinesWoHeader;
	}
}


