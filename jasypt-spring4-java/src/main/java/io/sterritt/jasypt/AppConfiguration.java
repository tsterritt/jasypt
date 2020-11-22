package io.sterritt.jasypt;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.EnvironmentStringPBEConfig;
import org.jasypt.spring4.properties.EncryptablePropertySourcesPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;

@Configuration
@ComponentScan
public class AppConfiguration {

    public static final String ENCRYPTION_PASSWORD="NeverABreathYouCouldAffordToWaste";
    public static final String ENCRYPTION_ALGORITHM="PBEWithSHAAnd3-KeyTripleDES-CBC";

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfig() {
        EnvironmentStringPBEConfig pbeConfig = new EnvironmentStringPBEConfig();
        pbeConfig.setPassword(ENCRYPTION_PASSWORD);
        pbeConfig.setAlgorithm(ENCRYPTION_ALGORITHM);
        pbeConfig.setProvider(new BouncyCastleProvider());

        StandardPBEStringEncryptor se = new StandardPBEStringEncryptor();
        se.setConfig(pbeConfig);

        EncryptablePropertySourcesPlaceholderConfigurer pc = new EncryptablePropertySourcesPlaceholderConfigurer(se);

        //is this a bug in Jasypt that the @PropertySource annotation doesn't work? Using @PropertySource("classpath:encrypted.properties")
        //reads the properties from the file but doesn't trigger the decryption. However, setting the location
        //explicitly triggers the decryption. This also seems to be just a 'feature' of Java config; it all works as expected
        //when using spring XML.
        pc.setLocation(new ClassPathResource("encrypted.properties"));

        return pc;

    }


}
