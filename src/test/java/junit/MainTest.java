/**
 * 
 */
package junit;



import static org.junit.Assert.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Collection;
import org.apache.commons.codec.binary.Hex;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import app.Main;



/**
 * @author Bruce
 * 
 * @date 9 Dec 2014
 * 
 */
public class MainTest
{

	/**
	 * 
	 */
	private static Logger log = Logger.getLogger(Main.class.getName());
	private Level logLevel;
	
	
	@Before
	public void init()
	{
		
	}
	
	
	public MainTest()
	{
	}

	@Parameterized.Parameters
	/*
	 * This runs with a series of command line arguments
	 */
	public static Collection primeNumbers()
	{
		return Arrays.asList(new Object[][] { { "-hmac_base -k LukeSkywalker -h HmacSHA256 -i Xwing -encode base64 -debug", Level.DEBUG }, { "-hmac_base -k MalcolmReynolds -h HmacMD5 -i Serenity -encode base64", Level.OFF } });
	}

	/**
	 * Test method for {@link app.Main#getLogLevel(java.lang.String)}.
	 */
	@Test
	public void testSetLogLevel()
	{
		// Test that log level has been set successfully
		Main.setLogLevel();
		assertNotEquals(log.getLevel(), null);

		// Test that debug is off
		Main.setLogLevel();
		assertEquals(log.getLevel(), Level.OFF);
	}

	/**
	 * Test method for {@link app.Main#doHMAC(java.lang.String)}.
	 * @throws Exception 
	 */
	@Test
	public void testDoHMAC() throws Exception
	{
		try
		{
			// Test HMACSHA256
			byte[] result = Main.doHMAC(Main.handleInput());
			assertEquals(Hex.encodeHexString(result), "7c14f88e84a0bdc683fa0d44e0c4fee2e26baf2181b4e651d823550baf89e362");

			// Test HMACSHA1
			result = Main.doHMAC(Main.handleInput());
			assertEquals(Hex.encodeHexString(result), "d98af60210564ac1af16d79974c4e7a441a455fe");

			// Test HMACMD5
			result = Main.doHMAC(Main.handleInput());
			assertEquals(Hex.encodeHexString(result), "b35dcd8c8996f43bc9a7fc42d317adbadb9ad4bf");
		}
		catch (InvalidKeyException e)
		{
			// TODO Auto-generated catch block
			fail("Invalid key");
			e.printStackTrace();
		}
		catch (NoSuchAlgorithmException e)
		{
			// TODO Auto-generated catch block
			fail("Invalid HMAC Algorithm");
			e.printStackTrace();
		}
		catch (InvalidAlgorithmParameterException e)
		{
			// TODO Auto-generated catch block
			fail("Invalid HMAC Parameter");
			e.printStackTrace();
		}
		catch (UnsupportedEncodingException e)
		{
			fail("Unsupported encoding");
			e.printStackTrace();
		}

	}

	/**
	 * Test method for {@link app.Main#doBase(byte[])}.
	 */
	@Test
	public void testDoBase()
	{
	}

	/**
	 * Test method for {@link app.Main#handleInput()}.
	 */
	@Test
	public void testHandleInput()
	{
	}

	/**
	 * Test method for {@link app.Main#handleOutput()}.
	 */
	@Test
	public void testHandleOutput()
	{
	}

	/**
	 * Test method for {@link app.Main#main(java.lang.String[])}.
	 */
	@Test
	public void testMain()
	{

	}

}
