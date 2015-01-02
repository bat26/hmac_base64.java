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
		
		comArg = new Options();
		//add option : command line name, internal name, does it have an argument true/false, human friendly description 
		
		//hmac base
		comArg.addOption("hmac_base", "hmac_base", false, "HMAC and Base");
			//hmac options
			comArg.addOption("k", "key", true, "Hashing key");
			comArg.addOption("h","hash", true, "Hashing algorithm");
		
		//base only
		comArg.addOption("base", "base", false, "Base only");
			//base options
			comArg.addOption("enc", "encode", true, "Base Encode");
			comArg.addOption("dec", "decode", true, "Base Decode");
			
		
		//general options
		comArg.addOption("i", "input", true, "Input");
		comArg.addOption("d", "debug", false, "Enable Debug Mode");
		comArg.addOption("c", "cleanse", true, "Cleanse using pattern");
		comArg.addOption("w", "cleanseWs", false, "Cleanse whitespace");
		comArg.addOption("l", "cleanseNl", false, "Cleanse newlines");
		comArg.addOption("f", "file", true, "Output to file");
				
	}
	
	//these aren't the comments you're looking for 
	public Options getCommandOptions()
	{
		return comArg;
	}
}


// they mostly come at night......mostly...
