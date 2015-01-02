/**
 * Handles the Hashing of strings 
 */
package helper;



import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import org.apache.log4j.Logger;
import org.apache.commons.codec.binary.Hex;


/**
 * @author Bruce
 * 
 * @date 27 Nov 2014
 * 
 */
public class HMAC
{
	private static Logger log = Logger.getLogger(HMAC.class.getName());
	private SecretKeySpec sKey;
	private static byte[] hash;
	
	
	/*
	 * Accepted algorithms are: HmacMD5, HmacSHA1, HmacSHA256
	 * As per Java doc - https://docs.oracle.com/javase/7/docs/api/javax/crypto/Mac.html
	 */
	public enum validHmacMethods
	{
		HmacMD5,
		HmacSHA1,
		HmacSHA256,
	}
	
	
	/*
	 * Accepted algorithms are: HmacMD5, HmacSHA1, HmacSHA256
	 * @param keyString
	 * @param method
	 */
	public HMAC(byte[] keyString, String method) throws InvalidKeyException, NoSuchAlgorithmException, InvalidAlgorithmParameterException
	{
		log.info("Initialising HMAC class.");
		if(isHmacMethodValid(method))
		{
			sKey = new SecretKeySpec(keyString, method);
			log.info("Initialised with keyString : " + keyString + " and using " + method + " method.");
		}
		else
		{
			log.error("Failed to initialise HMAC class, the algorithm specified was invalid.");
			throw new InvalidAlgorithmParameterException("The HMAC aglorithm : " + method + " is invalid ");
		}
		
	}
	
	/*
	 * Generate HMAC hash from input string
	 * @param str
	 */
	@SuppressWarnings("static-access")
	public void generateHash(String str) throws InvalidKeyException, NoSuchAlgorithmException, UnsupportedEncodingException
	{
		log.info("Generating hash with string : " + str);
		Mac mac = Mac.getInstance(sKey.getAlgorithm());
		mac.init(sKey);
		hash = mac.doFinal(str.getBytes());
		new Hex();
		log.info("Successfully generated hash, hexadecimal value is : " + Hex.encodeHexString(hash));

	}
	
	/*
	 * Does what it says on the 'tin
	 */
	public byte[] getHash()
	{
		return hash;
	}
	
	
	/*
	 * Checks if Base method in argument is valid
	 */
	public static boolean isHmacMethodValid(String method)
	{
		if (method.equals("") || method == null)
		{
			log.error("HMAC method not specified or null.");
			return false;
		}

		else
		{
			// in case user messes up case formatting
			method = method.toLowerCase();

			for (validHmacMethods vHMAC : validHmacMethods.values())
			{

				if (vHMAC.name().toLowerCase().equals(method))
				{
					log.info("HMAC method : " + method + " is an accepted method.");
					return true;
				}
			}
			log.error("HMAC method : " + method + " is not in the accepted methods.");
			return false;
		}

	}
	
	
	/*
	 * Print byte array into string
	 */
	public String hashToString() throws UnsupportedEncodingException
	{
		return new String(hash, "UTF-8");
	}

}


//i am a leaf on the wind