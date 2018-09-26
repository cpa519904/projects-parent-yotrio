package com.yotrio.common.utils;


import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * 一些常用的加密方法,包含URL\MD5\DES\BASE64
 * 
 * @author lujun
 */
public class CryptUtils {
	/**
	 * 如果系统中存在旧版本的数据，则此值不能修改，否则在进行密码解析的时候出错
	 */
	private final static String KEY = "_Cai_MaO_"; // 系统默认密匙8个长度
	private final static String DES = "DES"; // 采用DES算法，不是密匙

	// 算法名称
	public static final String KEY_ALGORITHM = "DESede";
	// 算法名称/加密模式/填充方式
	public static final String CIPHER_ALGORITHM_CBC = "DESede/CBC/PKCS5Padding";
	private final static String UTF_ENCODE = "UTF-8";
	/**
	 * Description 根据键值进行加密
	 * 
	 * @param data
	 * @param key
	 *            加密键byte数组
	 * @return
	 * @throws Exception
	 */
	public static String encryptByDESedeBASE64Encode(String data, String key) throws Exception {
		byte[] bt = encryptByDESede(data.getBytes(UTF_ENCODE), key.getBytes(UTF_ENCODE));
		return Base64.encodeBase64String(bt);//使用apache的base64去除换号空格符
	}

	/**
	 * Description 根据键值进行解密
	 * 
	 * @param data
	 * @param key
	 *            加密键byte数组
	 * @return
	 * @throws IOException
	 * @throws Exception
	 */
	public static String decryptByDESedeBASE64Decode(String data, String key) throws Exception {
		if (data == null)
			return null;
		byte[] buf = new Base64().decodeBase64(data);//使用apache的base64去除换号空格符
		byte[] bt = decryptByDESede(buf, key.getBytes(UTF_ENCODE));
		return new String(bt, UTF_ENCODE);
	}
	/**
	 * iv向量
	 * @return
	 */
	public static byte[] getIV() {
		return "12345678".getBytes();
	}
	/**
	 * 加密
	 * 
	 * @param data
	 *            原文
	 * @param key
	 * @return 密文
	 * @throws Exception
	 */
	public static byte[] encryptByDESede(byte[] data, byte[] key) throws Exception {
		SecretKey deskey = new SecretKeySpec(key, KEY_ALGORITHM);
		Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM_CBC);
		cipher.init(Cipher.ENCRYPT_MODE, deskey, new IvParameterSpec(getIV()));
		return cipher.doFinal(data);
	}

	/**
	 * 解密
	 * 
	 * @param data
	 *            密文
	 * @param key
	 * @return 明文、原文
	 * @throws Exception
	 */
	public static byte[] decryptByDESede(byte[] data, byte[] key) throws Exception {
		SecretKey deskey = new SecretKeySpec(key, KEY_ALGORITHM);
		Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM_CBC);
		cipher.init(Cipher.DECRYPT_MODE, deskey, new IvParameterSpec(getIV()));
		return cipher.doFinal(data);
	}
	/** URL加密 **/
	public static String urlEncode(String str) {
		try {
			return java.net.URLEncoder.encode(str, "UTF-8");
		} catch (UnsupportedEncodingException ex) {
			return "";
		}
	}

	/** URL解密 **/
	public static String urlDecode(String str) {
		try {
			return java.net.URLDecoder.decode(str, "UTF-8");
		} catch (UnsupportedEncodingException ex) {
			return "";
		}
	}

	/**
	 * sun系统自带的MD5加密
	 * 
	 * @param inbuf
	 * @return
	 */
	public static String md5(String inbuf) {
		try {
			MessageDigest dg = MessageDigest.getInstance("MD5");
			byte[] result = dg.digest(inbuf.getBytes());
			StringBuilder sb = new StringBuilder();
			for (byte bit : result) {
				sb.append(String.format("%02x", bit));
			}
			return sb.toString();
		} catch (NoSuchAlgorithmException e) {
			return null;
		}
	}

	/**
	 * BASE64 编码
	 */
	public static String base64Encode(String src) {
		sun.misc.BASE64Encoder encoder = new sun.misc.BASE64Encoder();
		return encoder.encode(src.getBytes());
	}

	/**
	 * BASE64 解码
	 */
	public static String base64Decode(String src) {
		sun.misc.BASE64Decoder decoder = new sun.misc.BASE64Decoder();
		try {
			return new String(decoder.decodeBuffer(src));
		} catch (Exception ex) {
			return "";
		}
	}

	/**
	 * 默认加密方式
	 * 
	 * @param data
	 * @return
	 */
	public final static String encrypt(String data) {
		return encrypt(data, KEY);
	}

	/**
	 * 默认解密方式
	 * 
	 * @param data
	 * @return
	 */
	public final static String decrypt(String data) {
		return decrypt(data, KEY);
	}

	/**
	 * 数据加密
	 * 
	 * @param data
	 * @param key
	 *            密钥
	 * @return
	 * @throws Exception
	 */
	public final static String encrypt(String data, String key) {
		if (data != null)
			try {
				return byte2hex(encrypt(data.getBytes(), key.getBytes()));
			} catch (Exception e) {
				e.printStackTrace();
			}
		return null;
	}

	/**
	 * 数据解密
	 * 
	 * @param data
	 * @param key
	 *            密钥
	 * @return
	 * @throws Exception
	 */
	public final static String decrypt(String data, String key) {
		if (data != null)
			try {
				return new String(decrypt(hex2byte(data.getBytes()), key.getBytes()));
			} catch (Exception e) {
				e.printStackTrace();
			}
		return null;
	}

	/**
	 * 加密
	 * 
	 * @param src
	 *            数据源
	 * @param key
	 *            密钥，长度必须是8的倍数
	 * @return 返回加密后的数据
	 * @throws Exception
	 */
	private static byte[] encrypt(byte[] src, byte[] key) throws Exception {
		// DES算法要求有一个可信任的随机数源
		SecureRandom sr = new SecureRandom();
		// 从原始密匙数据创建DESKeySpec对象
		DESKeySpec dks = new DESKeySpec(key);
		// 创建一个密匙工厂，然后用它把DESKeySpec转换成
		// 一个SecretKey对象
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
		SecretKey securekey = keyFactory.generateSecret(dks);
		// Cipher对象实际完成加密操作
		Cipher cipher = Cipher.getInstance(DES);
		// 用密匙初始化Cipher对象
		cipher.init(Cipher.ENCRYPT_MODE, securekey, sr);
		// 现在，获取数据并加密
		// 正式执行加密操作
		return cipher.doFinal(src);
	}

	/**
	 * 解密
	 * 
	 * @param src
	 *            数据源
	 * @param key
	 *            密钥，长度必须是8的倍数
	 * @return 返回解密后的原始数据
	 * @throws Exception
	 */
	private static byte[] decrypt(byte[] src, byte[] key) throws Exception {
		// DES算法要求有一个可信任的随机数源
		SecureRandom sr = new SecureRandom();
		// 从原始密匙数据创建一个DESKeySpec对象
		DESKeySpec dks = new DESKeySpec(key);
		// 创建一个密匙工厂，然后用它把DESKeySpec对象转换成
		// 一个SecretKey对象
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
		SecretKey securekey = keyFactory.generateSecret(dks);
		// Cipher对象实际完成解密操作
		Cipher cipher = Cipher.getInstance(DES);
		// 用密匙初始化Cipher对象
		cipher.init(Cipher.DECRYPT_MODE, securekey, sr);
		// 现在，获取数据并解密
		// 正式执行解密操作
		return cipher.doFinal(src);
	}

	/**
	 * 二行制转字符串
	 * 
	 * @param b
	 * @return
	 */
	private static String byte2hex(byte[] b) {
		String hs = "";
		String stmp = "";
		for (int n = 0; b != null && n < b.length; n++) {
			stmp = (Integer.toHexString(b[n] & 0XFF));
			if (stmp.length() == 1)
				hs = hs + "0" + stmp;
			else
				hs = hs + stmp;
		}
		return hs.toUpperCase();
	}

	public static byte[] hex2byte(byte[] b) {
		if ((b.length % 2) != 0)
			throw new IllegalArgumentException("长度不是偶数");
		byte[] b2 = new byte[b.length / 2];
		for (int n = 0; n < b.length; n += 2) {
			String item = new String(b, n, 2);
			b2[n / 2] = (byte) Integer.parseInt(item, 16);
		}
		return b2;
	}

	/**
	 * 该方法返回一个字符的DBCS编码值
	 * 
	 * @param cc
	 * @return int
	 */
	public static int getCode(char cc) {
		byte[] bs = String.valueOf(cc).getBytes();
		int code = (bs[0] << 8) | (bs[1] & 0x00FF);
		if (bs.length < 2)
			code = (int) cc;
		bs = null;
		return code;
	}

	public static boolean isAscii(char ch) {
		return (ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z') || (ch >= '0' && ch <= '9');
	}
}