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
	  musicDatabase();
  }
  private static void musicDatabase() {
	  Scanner scanner = new Scanner(System.in);
	  Track [] database = new Track[MAX_NR_OF_TRACKS];
	  int nr = readDatabase(database);
	  System.out.println(nr + " tracks read from songs.txt.");
	  System.out.println("Enter a command:");
	  String command = scanner.next();
	  String input = scanner.next();
	  switch (command) {
	  	case "track": //track command
	  		title(database, input);
	  	break;
		case "artist": //artist command
		break;
		case "cds": //cds command
			cds(database, input);
		break;
		case "#cds": //#cds command
		break;
		case "time": //time command
		break;
		case "stop": //stop command
		break;
		case "help": //help command
			help();
		break;
		default:
			System.out.println("Unrecognised command. Type 'help' to see available commands.");
	  }
  }
  
  private static void title(Track[] database, String input) {
	  int i;
	  int tracksFound = 0;
	  for (i = 0; i < database.length; i++)
		  if(input.equals(database[i].title)) {
			  System.out.println(database[i].artist + ", "
					  + database[i].cd + ", "
					  + database[i].year + ", "
					  + database[i].track + ", "
					  + database[i].title + ", "
					  + database[i].time + ", "
					  + database[i].tags + ", "
					  + database[i].country + ".");
			  tracksFound += 1;
			  }
	  System.out.println(tracksFound + " tracks found.");
  }
  private static void help() {
	  System.out.println("track *title* - Finds every track of which the track *title* matches track. Displays the artist, CD title, year of\n" + 
	  		"recording, track number, track title, track length, tags, and country. At the end, shows the\n" + 
	  		"number of found tracks.");
	  System.out.println("artist *name* - Finds every artist whose *name* matches artist. Displays the artist. Each artist is displayed at\n" + 
	  		"most once. At the end, shows the number of found artists.");
	  System.out.println("cds *name* - Finds every CD title of artists of which the artists *name* matches artist. Displays the artist\n" + 
	  		"name, CD title, and year of recording. Each CD title is displayed at most once. Terminates\n" + 
	  		"the output with the number of found CD titles.");
	  System.out.println("#cds - Display the total number of CDs");
	  System.out.println("time - Display the total running time of all tracks in “mm:ss” format");
	  System.out.println("stop - Terminates the program");
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
	Track newTrack = new Track();
	int i = 0;
    try {
		InputStreamReader reader = new InputStreamReader(new FileInputStream("songs.txt"));
		Scanner scanner = new Scanner(reader);
		for (i = 0; scanner.hasNext() == true; i++) {
			newTrack.artist = scanner.nextLine(); //Null Pointer Exception?!
			newTrack.cd = scanner.nextLine();
			newTrack.year = scanner.nextInt();
			scanner.nextLine();
			newTrack.track = scanner.nextInt();
			scanner.nextLine();
			newTrack.title = scanner.nextLine();
			newTrack.tags = scanner.nextLine();
			newTrack.time = fromString(scanner.nextLine());
			newTrack.country = scanner.nextLine();
			database[i] = newTrack;
		}
		scanner.close();
	} catch (FileNotFoundException e) {
		System.out.println("File not found");
		e.printStackTrace();
	}
    return i;
  }
}
