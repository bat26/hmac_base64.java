/**
 * Posix Class
 * This class allows the application to parse POSIX compliant arguments, more info : http://en.wikipedia.org/wiki/POSIX
 * 
 * Example java -jar foobar.jar -a foo -b bar
 * 
 * Would be parsed as { a => foo, b => bar}
 * 
 */
package helper;



import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.PosixParser;
import org.apache.log4j.Logger;
import app.CommandOptions;
import app.Main;



/**
 * @author Bruce
 * 
 * @date 27 Nov 2014
 * 
 */
public class Posix
{

	private Options comOptions;
	private String[] args;
	private static Logger log = Logger.getLogger(Main.class.getName());
	
	public Posix(String[] args)
	{
		this.comOptions = new CommandOptions().getCommandOptions();
		this.args = args;
	}

	/*
	 * Parses command argument string array with preset Options object
	 * Not tested with GNU style -- delims
	 */
	public CommandLine parseArgs() throws ParseException
	{
		return new PosixParser().parse(comOptions, args);
	}

}


// the line must be drawn here ------>