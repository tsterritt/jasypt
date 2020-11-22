package io.sterritt.jasypt;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:jasypt-context.xml")

public class JasyptPropertiesTest
{

    @Autowired
    XmlConfiguredEncryptedProperties properties;

    @Test
    public void getUnencryptedProperty() {
        assertEquals("ItsJustThePowerToCharm", properties.getUnencryptedProperty());
    }

    @Test
    public void getEncryptedProperty() {
        assertEquals("IHaveSeenTheFlamingSwordsThereOverEastOfEden", properties.getEncryptedProperty());
    }
}