/**
 * 
 */
package com;



import org.apache.commons.cli.CommandLine;



/**
 * @author Bruce
 * 
 */
public class Main
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub

		CommandLine cl = new Posix(args).parseArgs();

		System.out.println(cl.getOptionValue("key"));

		//
		// if(cl.hasOption("url"))
		// splitUrl = cl.getOptionValue("url").split(",");
		// if(cl.hasOption("user"))
		// splitUser = cl.getOptionValue("user").split(",");
		// if(cl.hasOption("pass"))
		// splitPass = cl.getOptionValue("pass").split(",");
		// if(cl.hasOption("host"))
		// splitHost = cl.getOptionValue("host").split(",");
		// if(cl.hasOption("port"))
		// splitPort = cl.getOptionValue("port").split(",");

	}

}
