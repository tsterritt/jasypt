package io.sterritt.jasypt.spring4.java;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class EncryptedProperties {

    @Value("${unencrypted.property}")
    private String unencryptedProperty;

    @Value("${encrypted.property}")
    private String encryptedProperty;

    @Value("${other.file.property}")
    private String otherProperty;

    public String getUnencryptedProperty() {
        return unencryptedProperty;
    }

    public String getEncryptedProperty() {
        return encryptedProperty;
    }

    public String getOtherProperty() {
        return otherProperty;
    }
}
