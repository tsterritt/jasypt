package io.sterritt.jasypt.spring4.xml;

import org.springframework.beans.factory.annotation.Value;

public class EncryptedProperties {

    @Value("${unencrypted.property}")
    private String unencryptedProperty;

    @Value("${encrypted.property}")
    private String encryptedProperty;

    @Value("${simple.property}")
    private String simpleProperty;

    public String getUnencryptedProperty() {
        return unencryptedProperty;
    }

    public String getEncryptedProperty() {
        return encryptedProperty;
    }

    public String getSimpleProperty() {
        return simpleProperty;
    }
}
