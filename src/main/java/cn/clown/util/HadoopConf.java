package cn.clown.util;

import org.apache.hadoop.conf.Configuration;

/**
 * hadoop 配置
 *
 * @author clown
 */
public class HadoopConf {
    public static final String DEFAULT_FS = "fs.defaultFS";
    protected Configuration configuration = new Configuration();

    public HadoopConf(String host, int port) {
        String url = "hdfs://" + host + ":" + port;
        configuration.set(HadoopConf.DEFAULT_FS, url);
    }

    public void set(String key, String value) {
        configuration.set(key, value);
    }
}
