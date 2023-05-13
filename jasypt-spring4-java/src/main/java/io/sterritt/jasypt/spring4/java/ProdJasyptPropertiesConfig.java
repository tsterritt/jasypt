package io.sterritt.jasypt.spring4.java;

import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

@Component
@Profile("prod")
public class ProdJasyptPropertiesConfig extends JasyptPropertiesConfiguration {
    @Override
    public ClassPathResource getPropertiesLocation() {
        return new ClassPathResource("encrypted-prod.properties");
    }
}
