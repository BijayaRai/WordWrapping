import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * acquired and modified by Bijaya Rai
 * @author Bijaya Rai
 */
public class TextwrapperMain {

	/**
	 * Run this method to run this program.
	 */
 

	public static void main(String[] args) {

		/* this program requires 2 parameters to run */
		if (args.length >= 2) {
			try {
				TextwrapperMain textWrapper = new TextwrapperMain();
				String text = textWrapper.readText(new BufferedReader(new FileReader(args[0])));
				int colWidth = Integer.parseInt(args[1]);
				textWrapper.wrapText(text, colWidth);
			} catch (FileNotFoundException e) {
				System.err.println("Could not find file '" + args[0] + "'");
			} catch (NumberFormatException e) {
				System.err.println("'" + args[1] + "' is not a number");
			}
		} else {
			System.out.println(
					"This program requires 2 parameters to run: " + "a filename (string) and a column width (integer)");
		}
	}

	/**
	 * Reads from the BufferedReader and returns the contents as a String.
	 * 
	 * @param br
	 *            - the BufferedReader to read from.
	 * @return the text as a String.
	 */
	public String readText(BufferedReader br) {
		StringBuffer sb = new StringBuffer();
		try {
			String line;
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
			br.close();
		} catch (IOException e) {
			System.err.println("An IOException occurred: " + e);
		}
		return sb.toString();
	}

	/**
	 * Prints the text to the screen, wrapping each line at the 'width'.
	 * 
	 * @param text
	 *            - the text to wrap and output.
	 * @param width
	 *            - the column width for outputting the text.
	 * @return 
	 */
	public List<String> wrapText(String text, int width) {
		/* this method needs to be improved - it currently does not wrap text */

		List<String> splitWordsList = new ArrayList<>();//strings within the user defined value
		List<String> uniqueCharacters = new ArrayList<>();//any string over the user defined width
		String cropText = "";
		StringBuffer buff = new StringBuffer(text);
		int bufferPoint = 0;
		
		//run the loop till all the characters in the buffers have been assigned to a list
		while ((buff.length() != 0)) {

			//run if the buff size is bigger then the threshold +1
			//+1 because its can used for checking for " ", blankspace; to check if the word is complete
			if (buff.length() > width + 1) {

				cropText = buff.substring(0, width+1);
				bufferPoint = width;

				//check for spelling gaps in a sentence to identify different words
				if (cropText.contains(" ")) {
					int indexofSpace = cropText.lastIndexOf(" ");
					cropText = buff.substring(0, indexofSpace);
					bufferPoint = indexofSpace + 1;
					splitWordsList.add(cropText);

					// System.out.println(cropText+" , IOS:"+indexofSpace);
				} else {
					cropText = buff.substring(0, buff.indexOf(" "));
					bufferPoint = buff.indexOf(" ") + 1;
					uniqueCharacters.add(cropText);
					// System.out.println("UniqueL:"+buff.indexOf(" ")+
					// "length"+cropText);
				}

			} else {
				bufferPoint = buff.length();
				cropText = buff.toString();
				splitWordsList.add(cropText);

			}

			buff.delete(0, bufferPoint);//remove the categorised characters from buffer
		//remove it for testing
		}
		
		//print out both the list
	//	System.out.println("Words within defined threshold:" +"\n");
		for(String a:splitWordsList)
		{
			//System.out.println(a);
		}
		
		//System.out.println("\n"+"Words over defined threshold:" +"\n");
		for(String a:uniqueCharacters)
		{
			//System.out.println(a);
		}
	
		List<String> finalList= new ArrayList<String>(splitWordsList);
		finalList.addAll(uniqueCharacters);
		
		
		return finalList;
	}

}
