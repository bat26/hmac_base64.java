/**
 * 
 */
package com;



import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.PosixParser;



/**
 * @author Bruce
 * 
 * @date 27 Nov 2014
 * 
 */
public class Posix
{

	Options comOptions;
	String[] args;

	/**
	 * 
	 */
	public Posix(String[] args)
	{

		this.comOptions = new CommandOptions().getCommandOptions();
		this.args = args;
	}

	public CommandLine parseArgs()
	{
		PosixParser posPar = new PosixParser();
		CommandLine cl = null;

		try
		{
			cl = posPar.parse(this.comOptions, this.args);
		}
		catch (ParseException e)
		{
			// TODO
		}
		return cl;
	}

}
