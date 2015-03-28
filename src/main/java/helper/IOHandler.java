package helper;



import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import org.apache.log4j.Logger;
import app.Main;



/**
 * @author Bruce
 * 
 * @date 27 Nov 2014
 * 
 */

public class IOHandler
{
	// TODO

	private String value;
	private static Logger log = Logger.getLogger(Main.class.getName());

	/*
	  *  
	 */
	public IOHandler()
	{
		// TODO Auto-generated constructor stub
	}

	public void readFile(File input) throws IOException
	{
		BufferedReader br = new BufferedReader(new FileReader(input));
		log.info("Reading from file : " + input);
		StringBuilder sb = new StringBuilder();
		String line = br.readLine();

		while (line != null)
		{
			sb.append(line);
			line = br.readLine();
		}
		br.close();
		log.info("Finished reading file.");
		this.value = sb.toString();

	}

	/*
	 * Uses Regex to find and remove all newline chars Works for UNIX/Windows
	 */
	public void cleanseNewLine()
	{
		log.info("Cleansing string of Newline characters.");
		this.value = this.value.replaceAll("\\r\\n|\\r|\\n", "");
	}

	/*
	 * Cleanses whitespace from input
	 */
	public void cleanseWhiteSpace()
	{
		log.info("Cleansing string of Whitespaces.");
		this.value = this.value.replaceAll(" ", "");
	}

	/*
	 * Cleanses input using user specified Regex pattern, removing all chars
	 * 
	 * @note may refactor to allow user specified substitution.
	 */
	public void cleansePattern(String regex)
	{
		log.info("Cleansing string using regex pattern : " + regex);
		this.value = this.value.replaceAll(regex, "");
	}

	/*
	 * Determines if input string is path to a file or is a literal string If no file path found, a string is assumed
	 */
	public void interpretInput(String input) throws IOException
	{

		log.info("Checking if input : " + input + " is a file.");

		File inputFile = new File(input);

		if (inputFile.isFile())
		{
			log.info("Found a file from input.");
			readFile(inputFile);
		}
		else
		{
			log.info("Did not find a file from input : " + input);
			log.info("Interpreting as string literal.");
			this.value = input;
		}
	}

	public void outputToFile(String filename, byte[] output) throws IOException
	{
		Writer writer = null;
		writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filename), "utf-8"));
		String outputString = new String(output, "UTF-8");
		writer.write(outputString);
		writer.close();
	}

	/*
	 * Retrieve string
	 */
	public String getString()
	{
		return this.value;
	}
}

// no fate but what we make
