package io.sterritt.jasypt.camel;

import org.apache.camel.CamelContext;
import org.apache.camel.component.jasypt.JasyptPropertiesParser;
import org.apache.camel.component.properties.PropertiesComponent;
import org.apache.camel.component.properties.PropertiesParser;
import org.apache.camel.spring.javaconfig.CamelConfiguration;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.EnvironmentStringPBEConfig;
import org.jasypt.encryption.pbe.config.StringPBEConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Configures Jasypt using the JasyptPropertiesParser from camel-jasypt. Requires adding the locations of the properties
 * files using addLocation - @PropertySource annotations won't work.
 */
@Configuration
@ComponentScan
public class AppConfiguration extends CamelConfiguration {
   //in a real application, these should be pulled from environment rather than hardcoded
    public static final String ENCRYPTION_PASSWORD="NeverABreathYouCouldAffordToWaste";
    public static final String ENCRYPTION_ALGORITHM="PBEWithSHAAnd3-KeyTripleDES-CBC";

    @Override
    protected void setupCamelContext(CamelContext camelContext) throws Exception {
        camelContext.setPropertiesComponent(configurePropertiesComponent());
    }

    public PropertiesComponent configurePropertiesComponent() throws Exception {
        PropertiesComponent pc = new PropertiesComponent();
        pc.setPropertiesParser(configureJasyptPropertiesParser());
        pc.addLocation("classpath:encrypted.properties");
        pc.addLocation("classpath:simple.properties");
        return pc;
    }

    private PropertiesParser configureJasyptPropertiesParser() throws Exception {
        JasyptPropertiesParser propertiesParser = new JasyptPropertiesParser();
        propertiesParser.setEncryptor(configureStringEncryptor());
        return propertiesParser;
    }

    private StringEncryptor configureStringEncryptor() throws Exception {
        StandardPBEStringEncryptor stringEncryptor = new StandardPBEStringEncryptor();
        stringEncryptor.setConfig(configureStringPBEConfig());
        return stringEncryptor;
    }

    private StringPBEConfig configureStringPBEConfig() throws Exception {
        EnvironmentStringPBEConfig pbeConfig = new EnvironmentStringPBEConfig();
        pbeConfig.setPassword(ENCRYPTION_PASSWORD);
        pbeConfig.setAlgorithm(ENCRYPTION_ALGORITHM);
        pbeConfig.setProvider(new BouncyCastleProvider());
        return pbeConfig;
    }
}
