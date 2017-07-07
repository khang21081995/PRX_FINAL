
package encrypt;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;
import java.util.ResourceBundle;

public class MD5Library {

    public static String md5Encrypt(String str) {
        try {
            ResourceBundle rb = ResourceBundle.getBundle("encrypt.salt", Locale.getDefault());
            str += rb.getString("salt");
        } catch (Exception e) {
        }
        String result = "";
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("MD5");
            digest.update(str.getBytes());
            BigInteger bigInteger = new BigInteger(1, digest.digest());
            result = bigInteger.toString(16);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return result;
    }
}
