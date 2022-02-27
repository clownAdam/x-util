package cn.clown.util;


import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * properties配置文件读取工具类
 *
 * @author clown
 * @Date 2022/2/27 0027 16:37
 */
public class PropertiesUtil {
    /**
     * 将properties文件加载到Properties对象中
     * @param propertiesPath
     * @return
     */
    public static Properties read(String propertiesPath){
        Properties prop = new Properties();
        try {
            InputStream resourceAsStream = PropertiesUtil.class.getResourceAsStream(propertiesPath);
            prop.load(resourceAsStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop;
    }
}
