package cn.clown.util;

import org.junit.Test;

import java.util.Properties;

public class PropertiesUtilTest {

    @Test
    public void read() {
        Properties properties = PropertiesUtil.read("/test.properties");
        System.out.println("properties = " + properties);
        System.out.println("d = " + properties.get("a"));
    }
}