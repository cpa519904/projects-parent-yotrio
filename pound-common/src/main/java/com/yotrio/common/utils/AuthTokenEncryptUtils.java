//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.yotrio.common.utils;

import org.apache.log4j.Logger;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class AuthTokenEncryptUtils {
    private static Logger logger = Logger.getLogger(AuthTokenEncryptUtils.class);
    private static Key key;

    public AuthTokenEncryptUtils() {
    }

    public static void getKey(String strKey) {
        try {
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            secureRandom.setSeed(strKey.getBytes());
            KeyGenerator _generator = KeyGenerator.getInstance("DES");
            _generator.init(secureRandom);
            key = _generator.generateKey();
        } catch (Exception var3) {
            logger.error(var3.getMessage());
        }

    }

    public static String getEncString(String strMing) {
        byte[] byteMi = null;
        byte[] byteMing = null;
        String strMi = "";
        BASE64Encoder base64en = new BASE64Encoder();

        try {
            byteMing = strMing.getBytes("UTF8");
            byteMi = getEncCode(byteMing);
            strMi = base64en.encode(byteMi);
            strMi = strMi.replace('+', '*');
            strMi = strMi.replace('/', '_');
            strMi = strMi.replaceAll("=", ".");
        } catch (Exception var6) {
            logger.error(var6.getMessage());
        }

        return strMi;
    }

    public static String getDesString(String strMi) {
        BASE64Decoder base64De = new BASE64Decoder();
        byte[] byteMing = null;
        byte[] byteMi = null;
        String strMing = "";

        try {
            String base64Str = strMi.replace('*', '+');
            base64Str = base64Str.replace('_', '/');
            base64Str = base64Str.replace('.', '=');
            byteMi = base64De.decodeBuffer(base64Str);
            byteMing = getDesCode(byteMi);
            strMing = new String(byteMing, "UTF-8");
        } catch (Exception var6) {
            logger.error(var6.getMessage());
        }

        return strMing;
    }

    private static byte[] getEncCode(byte[] byteS) {
        byte[] byteFina = null;

        try {
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(1, key);
            byteFina = cipher.doFinal(byteS);
        } catch (Exception var4) {
            logger.error(var4.getMessage());
        }

        return byteFina;
    }

    private static byte[] getDesCode(byte[] byteD) {
        byte[] byteFina = null;

        try {
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(2, key);
            byteFina = cipher.doFinal(byteD);
        } catch (Exception var4) {
            logger.error(var4.getMessage());
        }

        return byteFina;
    }

    public static String getEncryptString(String strMing) {
        BASE64Encoder base64en = new BASE64Encoder();
        return base64en.encode(getEncCode(strMing.getBytes()));
    }

    public static String getDecryptString(String strMi) {
        strMi = new String(getDesString(strMi).getBytes());
        return strMi;
    }

    public static byte[] getSHA(String s) {
        MessageDigest md = null;

        try {
            md = MessageDigest.getInstance("SHA-1");
        } catch (NoSuchAlgorithmException var3) {
            logger.error(var3.getMessage());
        }

        md.update(s.getBytes());
        return md.digest();
    }

    public static String getBASE64(byte[] s) {
        return s == null ? null : (new BASE64Encoder()).encode(s);
    }

    public static String getFromBASE64(String s) {
        if (s == null) {
            return null;
        } else {
            BASE64Decoder decoder = new BASE64Decoder();

            try {
                byte[] b = decoder.decodeBuffer(s);
                return new String(b);
            } catch (Exception var3) {
                return null;
            }
        }
    }

    public static String safeUrlBase64Encode(byte[] data) {
        String encodeBase64 = (new BASE64Encoder()).encode(data);
        String safeBase64Str = encodeBase64.replace('+', '*');
        safeBase64Str = safeBase64Str.replace('/', '_');
        safeBase64Str = safeBase64Str.replaceAll("=", ".");
        return safeBase64Str;
    }

    public static byte[] safeUrlBase64Decode(String safeBase64Str) {
        String base64Str = safeBase64Str.replace('*', '+');
        base64Str = base64Str.replace('_', '/');
        base64Str = base64Str.replace('.', '=');
        int mod4 = base64Str.length() % 4;
        if (mod4 > 0) {
            base64Str = base64Str + "====".substring(mod4);
        }

        try {
            return (new BASE64Decoder()).decodeBuffer(base64Str);
        } catch (Exception var4) {
            return null;
        }
    }

    public static void main(String[] args) {
        String strDes = getDecryptString("Q+p8l0Hxy1UtnycubAH1YPWyC7tEBu9sppEFkyUieMLQ0nGc3aBWorSgPYXFYJNl5VKQy4gHSwk");
        System.out.println(strDes);
    }

    static {
        getKey("83AB1361198A57161C3262B080E34AA4E55BBC80129476BF");
    }
}
