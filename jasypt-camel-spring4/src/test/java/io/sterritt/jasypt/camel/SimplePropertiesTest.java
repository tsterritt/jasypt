package io.sterritt.jasypt.camel;


import org.apache.camel.CamelContext;
import org.apache.camel.EndpointInject;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.spring.CamelSpringRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

@RunWith(CamelSpringRunner.class)
@ContextConfiguration(classes = {AppConfiguration.class})
public class SimplePropertiesTest {

    @Autowired
    protected CamelContext camelContext;

    @Produce("direct:start_simplepropertiesroute")
    ProducerTemplate template;

    @EndpointInject("mock:end_simplepropertiesroute")
    private MockEndpoint end;


    @Test
    public void testUsingCamelRoute() throws Exception {
        end.expectedPropertyReceived("simple.property.one","LetThereBeSongsToFillTheAir");
        end.expectedPropertyReceived("simple.property.two","RippleInClearWater");
        end.expectedPropertyReceived("environment.variable",System.getenv("SHELL"));
        template.sendBody("Fake body");
        end.assertIsSatisfied();
    }

}
