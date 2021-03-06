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
public class EncryptedPropertiesRouteTest {

    @Autowired
    CamelContext camelContext;

    @Produce("direct:start_encrypted_properties_route")
    ProducerTemplate template;

    @EndpointInject("mock:result")
    private MockEndpoint end;

    @Test
    public void testEncryptedPropertiesRoute() throws Exception {
        end.expectedPropertyReceived("encrypted.property","IHaveSeenTheFlamingSwordsThereOverEastOfEden");
        template.sendBody("Fake body");
        end.assertIsSatisfied();
    }

    @Test
    public void testUnencryptedPropertiesRoute() throws Exception {
        end.expectedPropertyReceived("unencrypted.property","ItsJustThePowerToCharm");
        template.sendBody("Fake body");
        end.assertIsSatisfied();
    }
}