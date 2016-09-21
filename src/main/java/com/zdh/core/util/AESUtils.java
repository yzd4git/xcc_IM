/**
 * 
 */
package com.zdh.core.util;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

import com.stec.deviceapi.exception.CPKCryptographyException;
import com.stec.deviceapi.software.Algorithm;
import com.stec.deviceapi.software.CPKSoftwareApi;

/**
 * @author root
 * 
 */
public final class AESUtils {
	
	private final char keySeeds[] = { 'a', 'A', 'b', 'B', 'c', 'C', 'd', 'D',
				'e', 'E', 'f', 'F', 'g', 'G', 'h', 'H', 'i', 'I', 'j', 'J', //12
				'k', 'K', 'l', 'L', 'm', 'M', 'n', 'N', 'o', 'O', 'p', 'P', 'q', 'Q',
				'r', 'R', 's', 'S', 't', 'T', 'u', 'U', 'v', 'V', 'w', 'W',
				'x', 'X', 'y', 'Y', 'z', 'Z', '0', '1', '2', '3', '4', '5',
				'6', '7', '8', '9' };
	
	private final Random random = new Random();
	
	private final int SEED_LENGTH = keySeeds.length; //62;
	private final int KEY_LENGTH = 32;
	
	private static 	AESUtils instance = null;
	
	private static  CPKSoftwareApi cpkInstance = CPKSoftwareApi.getInstance(); 
	
	private AESUtils() {
		
	}
	
	public static AESUtils newInstance() {
		if(null == instance) {
			return new AESUtils();
		} else {
			return instance;
		}
	}
	
	public String generateAESRandom() {
		String aseKey = "";
		int index = -1;
		
		for(int i=0; i<KEY_LENGTH; i++) {
			index = random.nextInt(SEED_LENGTH) % SEED_LENGTH;
			aseKey += keySeeds[index];
		}
		
		return aseKey;
	}	
	
	public Map<String, String> aesEncrypt(byte[] plaintext, byte[] key) throws CPKCryptographyException {
		Map<String, String> map = new ConcurrentHashMap<String, String>();
		
		byte[] cipher = null;
		
		cipher = cpkInstance.symEncrypt(Algorithm.AES_256_CBC, plaintext, key);		
		String base64Cipher = cpkInstance.base64Encode(cipher);
		
		map.put("cipher", base64Cipher);
		map.put("key", new String(key));
		map.put("plaintext", new String(plaintext));
		
		return map;
	}
	
	/**
	 * 
	 * @param cipher - Encoded based on base64
	 * @param key
	 * @return
	 * @throws CPKCryptographyException 
	 */
	public Map<String, String> aesDescrypt(String base64Cipher, byte[] key) throws CPKCryptographyException {
		Map<String, String> map = new ConcurrentHashMap<String, String>();
		
		byte[] cipher = cpkInstance.base64Decode(base64Cipher);
		byte[] plaintext = cpkInstance.symDecrypt(Algorithm.AES_256_CBC, cipher, key);
		
		map.put("cipher", base64Cipher);
		map.put("key", new String(key));
		map.put("plaintext", new String(plaintext));
		
		return map;
	}

	
	/**
	 * @param args
	 * @throws CPKCryptographyException 
	 */
	public static void main(String[] args)
			throws CPKCryptographyException {
		// TODO Auto-generated method stub
		AESUtils testing = AESUtils.newInstance();
		String testKey = testing.generateAESRandom();
		String plaintext = "15285390435";
		Map<String, String> map = null;
		map = testing.aesEncrypt(plaintext.getBytes(), testKey.getBytes());
		
		testing.printMap(map);
		
		map = testing.aesDescrypt(map.get("cipher"), testKey.getBytes());
		
		testing.printMap(map);
	}
	
	public void printMap(Map<String, String> map) {
		System.out.println(map.get("cipher"));
		System.out.println(map.get("key"));
		System.out.println(map.get("plaintext"));
		System.out.println("=================================================");
	}

}
