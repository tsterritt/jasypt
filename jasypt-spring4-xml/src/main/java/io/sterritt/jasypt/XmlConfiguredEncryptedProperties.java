package io.sterritt.jasypt;

import org.springframework.beans.factory.annotation.Value;

public class XmlConfiguredEncryptedProperties {

    @Value("${unencrypted.property}")
    private String unencryptedProperty;

    @Value("${encrypted.property}")
    private String encryptedProperty;

    public String getUnencryptedProperty() {
        return unencryptedProperty;
    }

    public String getEncryptedProperty() {
        return encryptedProperty;
    }
}
