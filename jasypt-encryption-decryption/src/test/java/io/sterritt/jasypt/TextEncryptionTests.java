package io.sterritt.jasypt;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.util.text.BasicTextEncryptor;
import org.jasypt.util.text.StrongTextEncryptor;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Tests basic text based encryption and decryption
 *
 * http://www.jasypt.org/easy-usage.html
 */

public class TextEncryptionTests {

    private static final String PLAIN_TEXT = "SpiritOfTheWest";
    private static final String ENCRYPTION_PASSWORD = "AMillionMilesAway";

    /**
     * PBEWithMD5AndDES
     *
     * @throws Exception
     */
    @Test
    public void testBasicTextEncryption() throws Exception {
        BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
        textEncryptor.setPassword(ENCRYPTION_PASSWORD);

        String encryptedValue = textEncryptor.encrypt(PLAIN_TEXT);
        assertNotEquals(PLAIN_TEXT, encryptedValue);

        String decryptedValue = textEncryptor.decrypt(encryptedValue);
        assertEquals(PLAIN_TEXT, decryptedValue);
    }

    /**
     * PBEWithMD5AndTripleDES
     *
     * @throws Exception
     */
    @Test
    public void testStrongTextBasedEncryption() throws Exception {
        StrongTextEncryptor strongTextEncryptor = new StrongTextEncryptor();
        strongTextEncryptor.setPassword(ENCRYPTION_PASSWORD);

        String encryptedValue = strongTextEncryptor.encrypt(PLAIN_TEXT);
        assertNotEquals(PLAIN_TEXT, encryptedValue);

        String decryptedValue = strongTextEncryptor.decrypt(encryptedValue);
        assertEquals(PLAIN_TEXT, decryptedValue);
    }

    /**
     * Demos using a custom JCE provider (Legion of The Bouncy Castle),
     * Algorithms supported by BC provider:
     *
     * https://www.bouncycastle.org/specifications.html
     * @throws Exception
     */
    @Test
    public void testWithCustomProvider() throws Exception {
        StandardPBEStringEncryptor bcEncryptor = new StandardPBEStringEncryptor();
        bcEncryptor.setProvider(new BouncyCastleProvider());
        bcEncryptor.setAlgorithm("PBEWithSHAAnd3-KeyTripleDES-CBC");
        bcEncryptor.setPassword(ENCRYPTION_PASSWORD);

        String encryptedValue = bcEncryptor.encrypt(PLAIN_TEXT);
        assertNotEquals(PLAIN_TEXT, encryptedValue);

        String decryptedValue = bcEncryptor.decrypt(encryptedValue);
        assertEquals(PLAIN_TEXT, decryptedValue);

    }
}
