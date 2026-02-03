package nota.crypto;


import android.util.Base64;

import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/**
 */

public final class EncryptUtils {

    private EncryptUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    ///////////////////////////////////////////////////////////////////////////
    // hash encryption
    ///////////////////////////////////////////////////////////////////////////


    /**
     * Return the bytes of hash encryption.
     *
     * @param data      The data.
     * @param algorithm The name of hash encryption.
     * @return the bytes of hash encryption
     */
    static byte[] hashTemplate(final byte[] data, final String algorithm) {
        if (data == null || data.length <= 0) return null;
        try {
            MessageDigest md = MessageDigest.getInstance(algorithm);
            md.update(data);
            return md.digest();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    ///////////////////////////////////////////////////////////////////////////
    // hmac encryption
    ///////////////////////////////////////////////////////////////////////////

    /**
     * Return the bytes of hmac encryption.
     *
     * @param data      The data.
     * @param key       The key.
     * @param algorithm The name of hmac encryption.
     * @return the bytes of hmac encryption
     */
    static byte[] hmacTemplate(final byte[] data,
                               final byte[] key,
                               final String algorithm) {
        if (data == null || data.length == 0 || key == null || key.length == 0) return null;
        try {
            SecretKeySpec secretKey = new SecretKeySpec(key, algorithm);
            Mac mac = Mac.getInstance(algorithm);
            mac.init(secretKey);
            return mac.doFinal(data);
        } catch (InvalidKeyException | NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }


    private static final char[] HEX_DIGITS =
            {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    static String bytes2HexString(final byte[] bytes) {
        if (bytes == null) return "";
        int len = bytes.length;
        if (len <= 0) return "";
        char[] ret = new char[len << 1];
        for (int i = 0, j = 0; i < len; i++) {
            ret[j++] = HEX_DIGITS[bytes[i] >> 4 & 0x0f];
            ret[j++] = HEX_DIGITS[bytes[i] & 0x0f];
        }
        return new String(ret);
    }

    /**
     * AES, key length must be 16
     * @param input
     * @param key key length must be 16
     * @return
     */
    public static String encryptAES(String input, String key){
        return encryptBySymmetrical("AES", input, key);
    }
    /**
     * AES，key length must be 16
     * @param input
     * @param key key length must be 16
     * @return
     */
    public static String decryptAES(String input, String key){
        return decryptBySymmetrical("AES", input, key);
    }

    /**
     * DES, key length must be 8
     * @param input
     * @param key key length must be 8
     * @return
     */
    public static String encryptDES(String input, String key){
        return encryptBySymmetrical("DES", input, key);
    }

    /**
     * DES, key length must be 8
     * @param input
     * @param key key length must be 8
     * @return
     */
    public static String decryptDES(String input, String key){
        return decryptBySymmetrical("DES", input, key);
    }
    private static String encryptBySymmetrical(String name, String input, String key){
        try {
            Cipher cipher = Cipher.getInstance(name);
            SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), name);
            cipher.init(Cipher.ENCRYPT_MODE,  secretKeySpec);
            byte[] bytes = cipher.doFinal(input.getBytes());
            return Base64.encodeToString(bytes, Base64.DEFAULT);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    private static String decryptBySymmetrical(String name, String input, String key){
        try {
            Cipher cipher = Cipher.getInstance(name);
            SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), name);
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
            byte[] bytes = cipher.doFinal(Base64.decode(input, Base64.DEFAULT));
            return new String(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}