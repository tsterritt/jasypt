package io.sterritt.jasypt.camel;

import org.apache.camel.PropertyInject;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class SimplePropertiesRoute extends RouteBuilder {

    @PropertyInject("env:SHELL")
    private String environmentVariable;

    @Override
    public void configure() throws Exception {
        from("direct:start_simplepropertiesroute")
                .setProperty("properties.source.property",constant("{{properties.source.property}}"))
                .setProperty("environment.variable",constant(environmentVariable))
                .to("mock:end_simplepropertiesroute");
    }
}
