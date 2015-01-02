/**
 * 
 */
package helper;



import java.io.UnsupportedEncodingException;
import org.apache.commons.codec.binary.Base32;
import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;



/**
 * @author Bruce
 * 
 * @date 3 Dec 2014
 * 
 */
public class Base
{

	private static Logger log = Logger.getLogger(Base.class.getName());
	private static byte[] bytesEncoded;
	private static byte[] bytesDecoded;

	public enum validBaseMethods
	{
		base64, base32
	}

	public Base()
	{
	}

	/*
	 * @param str
	 * 
	 * @param encodeType Decided which setter to use depending on what encodeType is given and returns byte array
	 */
	public static byte[] encode(byte[] str, String baseMethod) throws UnsupportedEncodingException
	{
		log.info("Encode method : " + baseMethod);
		if (baseMethod.toLowerCase().equals("base64"))
		{
			log.info("Call base64Encode(" + str + ")");
			base64Encode(str);
		}

		else if (baseMethod.toLowerCase().equals("base32"))
		{
			log.info("Call base32Encode(" + str + ")");
			base32Encode(str);
		}

		return bytesEncoded;
	}

	/*
	 * @param str
	 * 
	 * @param encodeType Decided which setter to use depending on what encodeType is given and returns byte array
	 */
	public static byte[] decode(byte[] str, String baseMethod) throws UnsupportedEncodingException
	{
		log.info("Decode method.");
		if (baseMethod.toLowerCase().equals("base64"))
		{
			// log.info("Call base64Decode(" + str + ")");
			base64Decode(str);
		}

		else if (baseMethod.toLowerCase().equals("Base32"))
		{
			// log.info("Call base32Decode(" + str + ")");
			base32Decode(str);
		}
		return bytesDecoded;
	}

	/*
	 * @param str
	 */
	public static void base64Encode(byte[] str) throws UnsupportedEncodingException
	{
		log.info("Encoding message in Base64");
		bytesEncoded = Base64.encodeBase64(str);
		log.debug("Encoded byte value is : " + new String(bytesEncoded, "UTF-8"));
	}

	/*
	 * @param str
	 */
	public static void base32Encode(byte[] str) throws UnsupportedEncodingException
	{
		log.info("Encoding string in Base32");
		Base32 base32 = new Base32();
		bytesEncoded = base32.encode(str);
		log.debug("Encoded byte value is : " + new String(bytesEncoded, "UTF-8"));
	}

	/*
	 * @param str
	 */
	public static void base64Decode(byte[] str) throws UnsupportedEncodingException
	{
		log.info("Decoding Base64 string");
		bytesDecoded = Base64.decodeBase64(str);
		log.debug("Decoded value is : " + new String(bytesEncoded, "UTF-8"));
	}

	/*
	 * @param str
	 */
	public static void base32Decode(byte[] str) throws UnsupportedEncodingException
	{
		log.info("Decoding Base32 string");
		Base32 base32 = new Base32();
		bytesDecoded = base32.decode(str);
		log.debug("Decoded value is : " + new String(bytesEncoded, "UTF-8"));
	}

	/*
	 * @param str
	 * 
	 * Checks using regex pattern in Base64 class to determine if argument is in Base64 format Unfortunately the same cannot be done for Base32/16 encoded strings.
	 */
	public static boolean isBase64String(String str)
	{
		if (Base64.isBase64(str))
		{
			log.info("String is in Base64 format.");
			return true;
		}
		else
		{
			log.info("String is not Base64 format.");
			return false;
		}
	}

	/*
	 * Checks if Base method in argument is valid
	 */
	public static boolean isBaseMethodValid(String method)
	{
		if (method.equals("") || method == null)
		{
			log.error("Base method not specified or null.");
			return false;
		}

		else
		{
			// in case user messes up case formatting
			method = method.toLowerCase();

			for (validBaseMethods vBM : validBaseMethods.values())
			{

				if (vBM.name().toLowerCase().equals(method))
				{
					log.info("Base method : " + method + " is an accepted method.");
					return true;
				}
			}
			log.error("Base method : " + method + " is not in the accepted methods.");
			return false;
		}

	}

}

// I know kung fu
