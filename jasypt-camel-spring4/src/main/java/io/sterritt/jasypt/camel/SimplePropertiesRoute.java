package io.sterritt.jasypt.camel;

import org.apache.camel.PropertyInject;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class SimplePropertiesRoute extends RouteBuilder {

    @PropertyInject("env:SHELL")
    private String environmentVariable;


    //@PropertyInject("simple.property.one")
    //private String simplePropertyOne;

    @Override
    public void configure() throws Exception {
        from("direct:start_simplepropertiesroute")
                .setProperty("simple.property.one",constant("{{simple.property.one}}"))
                .setProperty("simple.property.two",constant("{{simple.property.two}}"))
                .setProperty("environment.variable",constant(environmentVariable))
                .to("mock:end_simplepropertiesroute");
    }
}
