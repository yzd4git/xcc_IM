package com.zdh.core.util;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class DesUtil {
	private static final Log log = LogFactory.getLog(DateJsonValueProcessor.class);
	public static void main(String[] args) throws Exception {
//        byte[] key=new BASE64Decoder().decodeBuffer("Z08Z866H268DR84L6ZRRNR68");
//        byte[] keyiv = { 1, 2, 3, 4, 5, 6, 7, 8 };
//        byte[] data="中国ABCabc123".getBytes("UTF-8");
//        byte[] str5 = ees3DecodeECB("Z08Z866H268DR84L6ZRRNR68".getBytes(),  new BASE64Decoder().decodeBuffer("vxHGORSTDjdL+FOPWylwcg=="));
//        System.err.println(new String (str5));
		//String test = "DqkCBwXfmvyu/JYYTFCZi6X+IvPFHb/+tX3p+rtntI4uKIQpuVIu9g==" ;
//		String test = "xExR1MC7wM/01R2VOJaflx21j79TJVBLzLgMjPtuj9i1OCdzAlir3wRBpWwFgJrEThuKo55ROQ04qvzaOYfzIQ==" ;
//		String debase = new String (new BASE64Decoder().decodeBuffer(test)) ;
//		log.error(new String(DesUtil.ees3DecodeECB(
//				"0RPV6ZT6V6ZBVVVX84JN28JL".toString().getBytes(),
//				new BASE64Decoder().decodeBuffer(test))) );
		JSONObject obj = new JSONObject() ;
		obj.put("zhangdahui","sdfadfa") ;
		
		String resultString = new String(new BASE64Encoder().encode(DesUtil.des3EncodeECB("Z08Z866H268DR84L6ZRRNR68".getBytes(), JsonUtil.toJSONString(obj).getBytes())));
		log.error(resultString) ;
		String userInfo = new String(DesUtil.ees3DecodeECB("Z08Z866H268DR84L6ZRRNR68".getBytes(),new BASE64Decoder().decodeBuffer(resultString.toString() ) ) );
		log.error(userInfo) ;
//		System.out.println("key:" + encryptThreeDESECB("张大辉", "F86LX088ZZ4HXF6TH82D4HP6")) ;
//		System.out.println(decryptThreeDESECB("C6XuK7WVhXC4JIF3pLJCj+APuA98rZW38qU2CkX9zqDFl9UPzj8C+FNmEQODzF6q9ey+LS4kiylfV9I5KiRh3Z+akHG+/BaRKCqol4YvSh8epgQ7lm15Wc/tvUUxUlRe/zh+7Y54kdVyoJkQOsIvyzEy+WPKd/Z8qLCugWEl+H8ClF33edgWxH7WIAZW63tt1PPGD3/ThPtO4ik82Z/HFz1jqN5kCU0w","YWJjZGVmZ2gxMjM0NTY3OGlqa2xtbm9w"));
    }
    /**
     * ECB加密,不要IV
     * @param key 密钥
     * @param data 明文
     * @return Base64编码的密文
     * @throws Exception
     */
    public static byte[] des3EncodeECB(byte[] key, byte[] data)
            throws Exception {
        Key deskey = null;
        DESedeKeySpec spec = new DESedeKeySpec(key);
        SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
        deskey = keyfactory.generateSecret(spec);
        Cipher cipher = Cipher.getInstance("desede" + "/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, deskey);
        byte[] bOut = cipher.doFinal(data);
        return bOut;
    }
    /**
     * ECB解密,不要IV
     * @param key 密钥
     * @param data Base64编码的密文
     * @return 明文
     * @throws Exception
     */
    public static byte[] ees3DecodeECB(byte[] key, byte[] data)
            throws Exception {
        Key deskey = null;
        DESedeKeySpec spec = new DESedeKeySpec(key);
        SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
        deskey = keyfactory.generateSecret(spec);
        Cipher cipher = Cipher.getInstance("desede" + "/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, deskey);
        byte[] bOut = cipher.doFinal(data);
        return bOut;
    }
    /**
     * CBC加密
     * @param key 密钥
     * @param keyiv IV
     * @param data 明文
     * @return Base64编码的密文
     * @throws Exception
     */
    public static byte[] des3EncodeCBC(byte[] key, byte[] keyiv, byte[] data)
            throws Exception {
        Key deskey = null;
        DESedeKeySpec spec = new DESedeKeySpec(key);
        SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
        deskey = keyfactory.generateSecret(spec);
        Cipher cipher = Cipher.getInstance("desede" + "/CBC/PKCS5Padding");
        IvParameterSpec ips = new IvParameterSpec(keyiv);
        cipher.init(Cipher.ENCRYPT_MODE, deskey, ips);
        byte[] bOut = cipher.doFinal(data);
        return bOut;
    }
    /**
     * CBC解密
     * @param key 密钥
     * @param keyiv IV
     * @param data Base64编码的密文
     * @return 明文
     * @throws Exception
     */
    public static byte[] des3DecodeCBC(byte[] key, byte[] keyiv, byte[] data)
            throws Exception {
        Key deskey = null;
        DESedeKeySpec spec = new DESedeKeySpec(key);
        SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
        deskey = keyfactory.generateSecret(spec);
        Cipher cipher = Cipher.getInstance("desede" + "/CBC/PKCS5Padding");
        IvParameterSpec ips = new IvParameterSpec(keyiv);
        cipher.init(Cipher.DECRYPT_MODE, deskey, ips);
        byte[] bOut = cipher.doFinal(data);
        return bOut;
    }
    
    /**
     * 3des加密数据
     * @param src 表示要加密的字符串
     * @param key 用于加密的密钥
     * @return 
     * @throws Exception
     */
    public static String encryptThreeDESECB(String src,String key) throws Exception {
        DESedeKeySpec dks = new DESedeKeySpec(key.getBytes("UTF-8"));
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DESede");
        SecretKey securekey = keyFactory.generateSecret(dks);
        Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, securekey);
        byte[] b=cipher.doFinal(src.getBytes());
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(b).replaceAll("r", "").replaceAll("n", "");
    }
    /**
     * @param src 表示要加密的字符串
     * @param key 用于加密的密钥
     * @return
     * @throws Exception
     */
    //3DESECB解密,key必须是长度大于等于 3*8 = 24 位
    public static String decryptThreeDESECB(String src,String key) throws Exception {
        //--通过base64,将字符串转成byte数组
        BASE64Decoder decoder = new BASE64Decoder();
        byte[] bytesrc = decoder.decodeBuffer(src);
        //--解密的key
        DESedeKeySpec dks = new DESedeKeySpec(key.getBytes("UTF-8"));
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DESede");
        SecretKey securekey = keyFactory.generateSecret(dks);
        //--Chipher对象解密
        Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, securekey);
        byte[] retByte = cipher.doFinal(bytesrc);
        return new String(retByte);
    }


}
