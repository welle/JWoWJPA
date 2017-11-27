package aka.jwowjpa.test.utils;

import org.junit.Assert;
import org.junit.Test;

import aka.jwowjpa.utils.CryptoUtils;

/**
 * Test for CryptoUtils.
 *
 * @author charlottew
 */
public class CryptoUtils_Test {

    /**
     * Test getSHA512SecurePassword method.
     */
    @Test
    public void Test_getSHA512SecurePassword1() {
        final String fakePassWord = "gxmwgvwxvzebtygu";
        final String salt = "saltingcrypto";

        final String generatedHash = CryptoUtils.getSHA512SecurePassword(fakePassWord, salt);
        Assert.assertEquals("0ae29b3ee870d58005182d34b72f3c9b1afb8c4c6102a76d46ffb53557bbf5cca30873a95fecbcecc7a1aaceb18bdad11c3ecd291bf86484849dd7d26f319d68", generatedHash);
    }

}
