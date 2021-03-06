package io.sterritt.jasypt.camel;


import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class EncryptedPropertiesRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        from("direct:start_encrypted_properties_route")
                .setProperty("unencrypted.property",constant("{{unencrypted.property}}"))
                .setProperty("encrypted.property",constant("{{encrypted.property}}"))
                .to("mock:result");

    }
}
