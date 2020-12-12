package io.sterritt.jasypt.camel;


import org.apache.camel.*;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.spring.CamelSpringRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.Assert.assertEquals;

/**
 *
 */
@RunWith(CamelSpringRunner.class)
@ContextConfiguration(classes = {EncryptedPropertiesConfiguration.class})
public class PropertiesSourcePropertyTest {

    //inside camel context, need to use PropertyInject instead of Value annotation
    @PropertyInject("properties.source.property")
    private String propertiesSourceProperty;

    @Autowired
    CamelContext camelContext;

    @Produce("direct:start_simplepropertiesroute")
    ProducerTemplate template;

    @EndpointInject("mock:end_simplepropertiesroute")
    private MockEndpoint end;

    @Test
    public void testUsingValueAnnotation() {
        assertEquals("LetThereBeSongsToFillTheAir",propertiesSourceProperty);
    }

    @Test
    public void testUsingCamelRoute() throws Exception {
        end.expectedPropertyReceived("properties.source.property","LetThereBeSongsToFillTheAir");
        template.sendBody("Fake body");
        end.assertIsSatisfied();
    }

}
