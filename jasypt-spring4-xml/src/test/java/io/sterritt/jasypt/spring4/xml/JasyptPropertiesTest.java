package io.sterritt.jasypt.spring4.xml;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-config.xml")

public class JasyptPropertiesTest
{

    @Autowired
    EncryptedProperties properties;

    @Test
    public void getUnencryptedProperty() {
        assertEquals("ItsJustThePowerToCharm", properties.getUnencryptedProperty());
    }

    @Test
    public void getEncryptedProperty() {
        assertEquals("IHaveSeenTheFlamingSwordsThereOverEastOfEden", properties.getEncryptedProperty());
    }

    @Test
    public void testGetSimpleProperty() {
        assertEquals("LetThereBeSongsToFillTheAir", properties.getSimpleProperty());
    }
}