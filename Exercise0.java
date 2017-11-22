package nl.ru.ai.exercise0;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Exercise0
{ 
  private static final int MAX_NR_OF_TRACKS = 5000;
  /**
   * Program main entry point
   * @param args arguments to the program (ignored)
   */
  public static void main(String [] args)
  {
	  //Length length1=fromString("12:34");
	  //Length length2=fromString("23:45");
	  //Length sum=add(length1,length2);
	  //System.out.println(toString(sum));
	  Track [] database = new Track[MAX_NR_OF_TRACKS];
	  int nr = readDatabase(database);
	  System.out.println(nr + " tracks read");
  }
  /**
   * Calculate the sum of the specified length arguments
   * @param a
   * @param b
   * @return sum
   */
  static Length add(Length a, Length b)
  {
    Length sum = new Length();
    int totalSeconds = (a.minutes * 60) + (b.minutes * 60) + a.seconds + b.seconds;
    sum.minutes = totalSeconds / 60;
    sum.seconds = totalSeconds % 60;
    return sum;
  }
  /**
   * Converts the given string in the format m..m:ss to a Length object
   * @param string
   * @return corresponding length object
   */
  static Length fromString(String string)
  {
    Length translatedString = new Length();
    String minutes = "";
    int i;
    for (i = 0; string.charAt(i) != ':'; i++) {
    	minutes += (string.charAt(i));
    }
    translatedString.minutes = Integer.parseInt(minutes); //Using Integer.parseInt().... better method?
    translatedString.seconds = (int)(string.charAt(i + 1) + string.charAt(i + 2));
    return translatedString;
  }
  /**
   * Converts a given length object into a string in the format m..m:ss
   * @param length
   * @return string representation
   */
  static String toString(Length length)
  {
    return length.minutes + ":" + length.seconds;
  }
  /**
   * Reads the cd database from the file 'Nummers.txt' into the specified track array
   * @param database
   * @return number of tracks read
   */
  static int readDatabase(Track[] database)
  {
	int i = 0;
    try {
		InputStreamReader reader = new InputStreamReader(new FileInputStream("songs.txt"));
		Scanner scanner = new Scanner(reader);
		for (i = 0; scanner.hasNext() == true; i++) {
			database[i].artist = scanner.nextLine(); //Null Pointer Exception?!
			database[i].cd = scanner.nextLine();
			database[i].year = scanner.nextInt();
			scanner.nextLine();
			database[i].track = scanner.nextInt();
			scanner.nextLine();
			database[i].title = scanner.nextLine();
			database[i].tags = scanner.nextLine();
			database[i].time = fromString(scanner.nextLine());
			database[i].country = scanner.nextLine();
		}
		scanner.close();
	} catch (FileNotFoundException e) {
		System.out.println("File not found");
		e.printStackTrace();
	}
    return i;
  }
}
