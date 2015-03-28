/**
 * 
 */
package app;



import helper.Base;
import helper.HMAC;
import helper.IOHandler;
import helper.Posix;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.ParseException;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;



/**
 * @author Bruce Taylor
 * 
 */
public class Main
{

	// one instantiation of log4j per class
	private static Logger log = Logger.getLogger(Main.class.getName());
	private static CommandLine cl;
	private static IOHandler io = new IOHandler();;

	/*
	 * Current methods
	 */
	private static enum methods
	{
		BASE, HMAC_BASE
	}

	/*
	 *  If the log flag has been specified then set the level to debug 
	 */
	public static void setLogLevel()
	{
		if (cl.hasOption("debug"))
		{
			log.info("Debugging has been enabled.");
			log.setLevel(Level.DEBUG);
		}
		else
			log.setLevel(Level.OFF);
	}

	/*
	 * Perform HMAC operation on passed in string
	 */
	public static byte[] doHMAC(String str) throws Exception
	{
		HMAC hmac = null;
		log.info("Input string for HMAC : " + str);
		if (cl.hasOption("hash") && cl.hasOption("key"))
		{
			String hashKey = cl.getOptionValue("key");
			String hashMethod = cl.getOptionValue("hash");
			log.info("Hashing algorithm being used is : " + hashMethod);
			log.info("Secret key is : " + hashKey);
			hmac = new HMAC(hashKey.getBytes(), hashMethod);
			hmac.generateHash(str);
		}
		else
		{
			if (cl.hasOption("hash"))
			{
				log.error("No key given for HMAC.");
				throw new Exception("No hashing key given for HMAC to use.");
			}
			if (cl.hasOption("key"))
			{
				log.error("No hashing algorithm given for HMAC.");
				throw new Exception("No hashing algorithm given for HMAC, refer to readme for accepted HMAC methods");
			}

		}
		return hmac.getHash();
	}

	/*
	 * @param str Perform Base operation
	 */
	public static byte[] doBase(byte[] bs) throws Exception
	{
		log.info("Input string for Base : " + bs);

		if (cl.hasOption("encode"))
		{
			String baseMethod = cl.getOptionValue("encode");
			log.info("Doing encode in " + baseMethod);
			if (Base.isBaseMethodValid(baseMethod) == true)
				return Base.encode(bs, baseMethod);
			else
				throw new UnsupportedEncodingException("Unsupported encoder : " + baseMethod);

		}
		else if (cl.hasOption("decode"))
		{
			String baseMethod = cl.getOptionValue("encode");
			log.info("Doing decode");
			if (Base.isBaseMethodValid(baseMethod) == true)
				return Base.decode(bs, baseMethod);
			else
				throw new UnsupportedEncodingException("Unsupported encoder : " + baseMethod);
		}
		else if (cl.hasOption("decode") && cl.hasOption("encode"))
		{
			log.error("Both encode and decode methods have been called, remove one method.");
			throw new Exception("Both encode and decode methods have been called, remove one method.");
		}

		else
		{
			log.error("No base operation specified.");
			throw new Exception("No base encoding operation specified.");
		}
	}

	/*
	 * Retrieves input arg and passes it to IOHandler
	 */
	public static String handleInput() throws IOException
	{
		log.info("Checking input.");

		if (cl.hasOption("input"))
		{
			String input = cl.getOptionValue("input");
			log.info("An input of : " + input + " was specified.");

			io.interpretInput(input);

			// user wants to remove whitespace from input
			if (cl.hasOption("cleanseWs"))
				io.cleanseWhiteSpace();
			// user wants to remove new lines from input
			if (cl.hasOption("cleanseNl"))
				io.cleanseNewLine();
			// user wants to remove characters from input using custom regex
			if (cl.hasOption("cleanse"))
				io.cleansePattern(cl.getOptionValue("cleanse"));

		}
		else
		{
			log.error("No input specified, exiting.");
			System.exit(-1);
		}
		log.info("Final input is : " + io.getString());
		return io.getString();
	}

	/*
	 * Produces output either to the command line or to a specified file
	 * @param output
	 */
	public static void handleOutput(byte[] output) throws IOException
	{
		log.info("Preparing output");

		if (cl.hasOption("file"))
		{
			String filename = cl.getOptionValue("file");
			log.info("Value to be written to file : " + filename);
			io.outputToFile(filename, output);
		}
		else
		{
			log.info("Outputting using standard output.");
			System.out.println(new String(output, "UTF-8"));
		}
	}

	/**
	 * The main function starts off with parsing the arguments from command line, if sanity checking is passed on these arguments then it will
	 * carry out whatever instructions specified from the shell.
	 * @param args
	 */
	public static void main(String[] args)
	{
		try
		{
			// initialise Posix object and parse arguments from command line
			cl = new Posix(args).parseArgs();

			// set loglevel depending on presence of -debug argument
			setLogLevel();

			log.info("Parsing command line arguments.");
			// check mimimum arguments given
			if (cl.getOptions().length > 1)
			{
				// can't use string switch in Java 1.6

				// do base only
				if (cl.hasOption("base"))
				{
					try
					{
						log.info("A BASE operation will only be performed in this execution.");
						byte[] output = doBase(handleInput().getBytes());
						log.info("Final output is : " + new String(output, "UTF-8"));
						handleOutput(output);
					}
					catch (UnsupportedEncodingException e)
					{
						System.out.println(e.getLocalizedMessage() + ", only the following BASE encoding methods " + java.util.Arrays.asList(Base.validBaseMethods.values()) + " are currently accepted.");
						System.exit(-1);
					}
					catch (IOException e)
					{
						System.out.println(e.getLocalizedMessage() + ", please ensure file and current working directory is writable.");
						System.exit(-1);
					}
					catch (Exception e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
				// do hmac and base
				else if (cl.hasOption("hmac_base"))
				{
					log.info("Both HMAC and BASE operations will be performed in this execution.");

					try
					{
						byte[] output = doBase(doHMAC(handleInput()));
						log.info("Final output is : " + new String(output, "UTF-8"));
						handleOutput(output);
					}
					catch (InvalidKeyException e)
					{
						System.out.println(e.getLocalizedMessage() + ", the HMAC key provided is invalid.");
						System.exit(-1);
					}
					catch (NoSuchAlgorithmException e)
					{
						System.out.println(e.getLocalizedMessage() + ", this really shouldn't come up as an error message.");
						System.exit(-1);
					}
					catch (InvalidAlgorithmParameterException e)
					{
						System.out.println(e.getLocalizedMessage() + ", only the following HMAC methods " + java.util.Arrays.asList(HMAC.validHmacMethods.values()) + " are accepted.");
						System.exit(-1);
					}
					catch (UnsupportedEncodingException e)
					{
						System.out.println(e.getLocalizedMessage() + ", only the following BASE encoding methods " + java.util.Arrays.asList(Base.validBaseMethods.values()) + " are currently accepted.");
						System.exit(-1);
					}
					catch (IOException e)
					{
						System.out.println(e.getLocalizedMessage() + ", please ensure file and current working directory is writable.");
						System.exit(-1);
					}
					catch (Exception e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				// default
				else
				{
					log.error("No operation method specific. Expected either \"hmac_base\" or \"base\" .");
					System.exit(-1);
					// TODO
				}

			}
			else
			{
				log.error("The minimum amount of arguments required were not given. Expected " + methods.values());
				System.exit(-1);
			}

		}

		// end of posix try block
		catch (ParseException e)
		{
			// occurs with invalid posix options given
			System.out.println(e.getLocalizedMessage() + ", please check the user manual for all accepted command arguments.");
			System.exit(-1);
		}

	}
}

// the spice must flow