package nota.android.content.pm;

import android.content.pm.PackageInfo;
import android.content.pm.Signature;
import android.text.TextUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

public class SignatureUtils {

    public static boolean isSignatureSame(PackageInfo packageInfo, PackageInfo packageInfo2) {
        return TextUtils.equals(SignatureUtils.getCertMD5(packageInfo), SignatureUtils.getCertMD5(packageInfo2));
    }

    /**
     * need {@link android.Manifest.permission#GET_SIGNATURES}
     *
     * @param packageInfo
     * @return
     */
    public static String getCertMD5(PackageInfo packageInfo) {
        try {
            Signature[] signs = packageInfo.signatures;
            if (signs.length > 0) {
                return getCertMD5(signs[0].toByteArray());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String getCertMD5(byte[] signature) {
        try {
            CertificateFactory certFactory = CertificateFactory.getInstance("X.509");
            X509Certificate cert = (X509Certificate) certFactory.generateCertificate(new ByteArrayInputStream(signature));
            return md5Digest(cert.getEncoded());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String md5Digest(byte[] input) throws IOException {
        MessageDigest digest = getDigest("Md5");
        digest.update(input);
        return getHexString(digest.digest());
    }

    public static String getHexString(byte[] digest) {
        BigInteger bi = new BigInteger(1, digest);
        return String.format("%032x", bi);
    }

    public static MessageDigest getDigest(String algorithm) throws IOException {
        try {
            return MessageDigest.getInstance(algorithm);
        } catch (NoSuchAlgorithmException e) {
            throw new IOException(e.getMessage());
        }
    }
}
