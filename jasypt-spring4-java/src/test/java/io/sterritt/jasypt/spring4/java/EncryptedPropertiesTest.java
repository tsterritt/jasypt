package io.sterritt.jasypt.spring4.java;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * Uses Bouncy Castle encrypted values to demo using Jasypt and Bouncy Castle with Spring Java Config
 *
 * Properties are in the encrypted.properties file and Autowired into the EncryptedProperties class.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={AppConfiguration.class})
public class EncryptedPropertiesTest {

    @Autowired
    private EncryptedProperties properties;

    @Test
    public void getUnencryptedProperty() {
        assertEquals("ItsJustThePowerToCharm", properties.getUnencryptedProperty());
    }

    @Test
    public void getEncryptedProperty() {
        assertEquals("IHaveSeenTheFlamingSwordsThereOverEastOfEden", properties.getEncryptedProperty());
    }
}