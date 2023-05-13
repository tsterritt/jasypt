package io.sterritt.jasypt.spring4.java;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

/**
 * Jasypt requires that the properties file with the encrypted values be added to the PropertyComponent instead
 * of as a PropertySource, otherwise the encrypted properties won't decrypt.
 *
 * THe PropertySource can be used for normal unencrypted properties files in addition to the Jasypt config.
 */
@Configuration
@ComponentScan
//TODO - why doesn't encryption/decryption work when the location is defined here vs adding a location?
@PropertySource("classpath:other-properties-file.properties")
public class AppConfiguration {

    public static final String ENCRYPTION_PASSWORD="NeverABreathYouCouldAffordToWaste";
    public static final String ENCRYPTION_ALGORITHM="PBEWithSHAAnd3-KeyTripleDES-CBC";

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfig(@Autowired JasyptPropertiesConfiguration jasyptPropertiesConfiguration) {
        return jasyptPropertiesConfiguration.propertyConfig();
    }


}
