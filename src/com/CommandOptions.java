/**
 * 
 */
package com;

import org.apache.commons.cli.Options;

/**
 * @author Bruce
 * 
 * @date 27 Nov 2014
 *
 */
@SuppressWarnings("serial")
public class CommandOptions
{

	private Options comArg;
	/**
	 * 
	 */
	public CommandOptions()
	{
		// TODO Auto-generated constructor stub
		
		this.comArg = new Options();
				
		
		this.comArg.addOption("k", "key", true, "Hashing key");
		this.comArg.addOption("m", "method", true, "Encrypt or Decrypt hash");
		this.comArg.addOption("i", "input", true, "Input string");
		this.comArg.addOption("f", "file", true, "Input file");
		this.comArg.addOption("d", "debug", false, "Enable debug output");
		this.comArg.addOption("h", "hash", false, "Hash method");
		this.comArg.addOption("b", "base", true, "Base encoded");
				
	}
	
	//these aren't the comments you're looking for 
	public Options getCommandOptions()
	{
		return this.comArg;
	}
}
