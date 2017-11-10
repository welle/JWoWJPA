package aka.jwowjpa.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;

/**
 * A utility class to handle Security.
 */
public class CryptoUtils {

    /**
     * Get the hash key from the given passwordToHash and salt.
     *
     * @param passwordToHash
     * @param salt
     * @return hash key
     */
    @Nullable
    public static String getSHA512SecurePassword(@NonNull final String passwordToHash, @NonNull final String salt) {
        String generatedPassword = null;
        try {
            final MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(salt.getBytes("UTF-8"));
            final byte[] bytes = md.digest(passwordToHash.getBytes("UTF-8"));
            final StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return generatedPassword;
    }

    private CryptoUtils() {
        // Singleton
    }
}
