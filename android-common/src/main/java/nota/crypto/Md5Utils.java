package nota.crypto;

public class Md5Utils {
    /**
     * Return the hex string of MD5 encryption.
     *
     * @param data The data.
     * @return the hex string of MD5 encryption
     */
    public static String encryptMD5ToString(final String data) {
        if (data == null || data.isEmpty()) return "";
        return encryptMD5ToString(data.getBytes());
    }

    /**
     * Return the hex string of MD5 encryption.
     *
     * @param data The data.
     * @param salt The salt.
     * @return the hex string of MD5 encryption
     */
    public static String encryptMD5ToString(final String data, final String salt) {
        if (data == null && salt == null) return "";
        if (salt == null) return EncryptUtils.bytes2HexString(encryptMD5(data.getBytes()));
        if (data == null) return EncryptUtils.bytes2HexString(encryptMD5(salt.getBytes()));
        return EncryptUtils.bytes2HexString(encryptMD5((data + salt).getBytes()));
    }

    /**
     * Return the hex string of MD5 encryption.
     *
     * @param data The data.
     * @return the hex string of MD5 encryption
     */
    public static String encryptMD5ToString(final byte[] data) {
        return EncryptUtils.bytes2HexString(encryptMD5(data));
    }

    /**
     * Return the hex string of MD5 encryption.
     *
     * @param data The data.
     * @param salt The salt.
     * @return the hex string of MD5 encryption
     */
    public static String encryptMD5ToString(final byte[] data, final byte[] salt) {
        if (data == null && salt == null) return "";
        if (salt == null) return EncryptUtils.bytes2HexString(encryptMD5(data));
        if (data == null) return EncryptUtils.bytes2HexString(encryptMD5(salt));
        byte[] dataSalt = new byte[data.length + salt.length];
        System.arraycopy(data, 0, dataSalt, 0, data.length);
        System.arraycopy(salt, 0, dataSalt, data.length, salt.length);
        return EncryptUtils.bytes2HexString(encryptMD5(dataSalt));
    }

    /**
     * Return the bytes of MD5 encryption.
     *
     * @param data The data.
     * @return the bytes of MD5 encryption
     */
    public static byte[] encryptMD5(final byte[] data) {
        return EncryptUtils.hashTemplate(data, "MD5");
    }

    /**
     * Return the hex string of HmacMD5 encryption.
     *
     * @param data The data.
     * @param key  The key.
     * @return the hex string of HmacMD5 encryption
     */
    public static String encryptHmacMD5ToString(final String data, final String key) {
        if (data == null || data.length() == 0 || key == null || key.length() == 0) return "";
        return encryptHmacMD5ToString(data.getBytes(), key.getBytes());
    }

    /**
     * Return the hex string of HmacMD5 encryption.
     *
     * @param data The data.
     * @param key  The key.
     * @return the hex string of HmacMD5 encryption
     */
    public static String encryptHmacMD5ToString(final byte[] data, final byte[] key) {
        return EncryptUtils.bytes2HexString(encryptHmacMD5(data, key));
    }

    /**
     * Return the bytes of HmacMD5 encryption.
     *
     * @param data The data.
     * @param key  The key.
     * @return the bytes of HmacMD5 encryption
     */
    public static byte[] encryptHmacMD5(final byte[] data, final byte[] key) {
        return EncryptUtils.hmacTemplate(data, key, "HmacMD5");
    }
}
