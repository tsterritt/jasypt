package io.sterritt.jasypt.camel;


import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class EncryptedPropertiesRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        from("direct:start")
                .setProperty("unencrypted.property",constant("{{unencrypted.property}}"))
                .setProperty("decrypted.property",constant("{{encrypted.property}}"))
                .to("mock:result");

    }
}
