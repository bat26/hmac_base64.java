/**
 * 
 */
package com;



import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;



/**
 * @author Bruce
 * 
 * @date 27 Nov 2014
 * 
 */
public class Hmac
{

	String value;
	SecretKeySpec sKey;
	byte[] hash;
	
	
	/*
	 * Accepted algorithms are: HmacMD5, HmacSHA1, HmacSHA256
	 * As per Java doc - https://docs.oracle.com/javase/7/docs/api/javax/crypto/Mac.html
	 */
	public enum HashAlgorithms
	{
		HmacMD5,
		HmacSHA1,
		HmacSHA256,
	}
	
	
	/*
	 * Accepted algorithms are: HmacMD5, HmacSHA1, HmacSHA256
	 */
	public Hmac(String val, byte[] keyString, String algo) throws InvalidKeyException, NoSuchAlgorithmException
	{
		this.sKey = new SecretKeySpec(keyString, algo);
		this.value = val;
		this.generateHash();
	}

	private void generateHash() throws InvalidKeyException, NoSuchAlgorithmException
	{
		Mac mac = Mac.getInstance(sKey.getAlgorithm());
		mac.init(sKey);
		this.hash = mac.doFinal(value.getBytes());

	}
	
	/*
	 * Does what it says on the 'tin
	 */
	public byte[] getHash()
	{
		return this.hash;
	}

}
