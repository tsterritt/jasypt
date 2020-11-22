package io.sterritt.jasypt;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.jasypt.util.password.BasicPasswordEncryptor;
import org.jasypt.util.password.ConfigurablePasswordEncryptor;
import org.jasypt.util.password.StrongPasswordEncryptor;
import org.junit.Test;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

/**
 * Demonstrates using jasypt for password based encryption
 *
 * http://www.jasypt.org/easy-usage.html
 */
public class PasswordEncryptionTests {

    public static final String PLAIN_TEXT_PASSWORD = "WindmillWindmillForTheNow";

    /**
     * MD5 with 8 bytes salt
     * @throws Exception
     */
    @Test
    public void testPasswordBasedEncryption() throws Exception {
        BasicPasswordEncryptor encryptor = new BasicPasswordEncryptor();
        String encryptPassword = encryptor.encryptPassword(PLAIN_TEXT_PASSWORD);
        assertNotEquals(PLAIN_TEXT_PASSWORD, encryptPassword);
        assertTrue(encryptor.checkPassword(PLAIN_TEXT_PASSWORD, encryptPassword));
    }


    /**
     * Tests SHA-256 PBE with 16 byte salt
     * @throws Exception
     */
    @Test
    public void testStrongPasswordBasedEncryption() throws Exception {
        StrongPasswordEncryptor encryptor = new StrongPasswordEncryptor();
        String encryptPassword = encryptor.encryptPassword(PLAIN_TEXT_PASSWORD);
        assertNotEquals(PLAIN_TEXT_PASSWORD, encryptPassword);
        assertTrue(encryptor.checkPassword(PLAIN_TEXT_PASSWORD, encryptPassword));
    }

    /**
     * Demos BE using a custom JCE provider (Legion of The Bouncy Castle) Algoirthms supported by BC provider
     * https://www.bouncycastle.org/specifications.html
     * @throws Exception
     */
    @Test
    public void testWithCustomProvider() throws Exception {
        ConfigurablePasswordEncryptor encryptor = new ConfigurablePasswordEncryptor();
        encryptor.setProvider(new BouncyCastleProvider());
        encryptor.setAlgorithm("TIGER");
        String encryptedPassword = encryptor.encryptPassword(PLAIN_TEXT_PASSWORD);
        assertNotEquals(PLAIN_TEXT_PASSWORD, encryptedPassword);
        assertTrue(encryptor.checkPassword(PLAIN_TEXT_PASSWORD, encryptedPassword));
    }

}