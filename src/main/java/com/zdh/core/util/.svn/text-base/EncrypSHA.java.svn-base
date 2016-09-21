package com.zdh.core.util ;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncrypSHA {
	
	public byte[] eccrypt(String info) throws NoSuchAlgorithmException{
		MessageDigest md5 = MessageDigest.getInstance("SHA");
		byte[] srcBytes = info.getBytes();
		//使用srcBytes更新摘要
		md5.update(srcBytes);
		//完成哈希计算，得到result
		byte[] resultBytes = md5.digest();
		return resultBytes;
	}
	
	public static String SHA1(String decript) {
        try {
            MessageDigest digest = java.security.MessageDigest
                    .getInstance("SHA-1");
            digest.update(decript.getBytes());
            byte messageDigest[] = digest.digest();
            // Create Hex String
            StringBuffer hexString = new StringBuffer();
            // 字节数组转换为 十六进制 数
            for (int i = 0; i < messageDigest.length; i++) {
                String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
                if (shaHex.length() < 2) {
                    hexString.append(0);
                }
                hexString.append(shaHex);
            }
            return hexString.toString();
 
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

	/**
	 * @param args
	 * @throws NoSuchAlgorithmException 
	 */
	public static void main(String[] args) throws NoSuchAlgorithmException {
		String msg = "郭XX-精品相声技术";
		EncrypSHA sha = new EncrypSHA();
		byte[] resultBytes = sha.eccrypt(msg);
		System.out.println("明文是：" + msg);
		System.out.println("密文是：" + new String(resultBytes));
		
	}

}
