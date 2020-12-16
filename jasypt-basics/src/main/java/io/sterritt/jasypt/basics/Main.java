package io.sterritt.jasypt.basics;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.util.text.BasicTextEncryptor;

/**
 * Utility class for getting encrypted values using various algorithms
 */
public class Main {

    public static void main(String[] args) {
        //String encryptionPassword="NeverABreathYouCouldAffordToWaste";
        String encryptionPassword = "ToBoldlyGo";
        //String textToEncrypt="IHaveSeenTheFlamingSwordsThereOverEastOfEden";
        String textToEncrypt="Success";
        System.out.println("BouncyCastle:" + bouncyCastle(encryptionPassword, textToEncrypt));
        System.out.println("Basic:" + basicEncryption(encryptionPassword, textToEncrypt));
        System.out.println("Strong:" + strongEncryption(encryptionPassword, textToEncrypt));
    }

    private static String bouncyCastle(String password, String toEncrypt ) {
        StandardPBEStringEncryptor bcEncryptor = new StandardPBEStringEncryptor();
        bcEncryptor.setProvider(new BouncyCastleProvider());
        bcEncryptor.setAlgorithm("PBEWithSHAAnd3-KeyTripleDES-CBC");
        bcEncryptor.setPassword(password);
        return bcEncryptor.encrypt(toEncrypt);
    }

    private static String basicEncryption(String password, String toEncrypt ) {
        BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
        textEncryptor.setPassword(password);
        return textEncryptor.encrypt(toEncrypt);
    }

    private static String strongEncryption(String password, String toEncrypt ) {
        BasicTextEncryptor strongEncryptor = new BasicTextEncryptor();
        strongEncryptor.setPassword(password);
        return strongEncryptor.encrypt(toEncrypt);
    }
}
