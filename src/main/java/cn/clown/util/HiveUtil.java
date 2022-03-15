package cn.clown.util;

import org.apache.logging.log4j.core.net.server.UdpSocketServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * hive相关操作连接工具类
 *
 * @author clown
 */
public class HiveUtil {
    private static final String driverName = "org.apache.hive.jdbc.HiveDriver";
    private static final String HIVE_JDBC_PROTOCOL = "jdbc:hive2://";
    private static Logger logger = LoggerFactory.getLogger(HiveUtil.class);

    static {
        try {
            Class.forName(driverName);
        } catch (ClassNotFoundException e) {
            logger.error("hive driver:{}驱动类加载失败", driverName, e);
        }
    }

    public static Connection getConnection(String host, int port) throws SQLException, ClassNotFoundException {
        String url = HIVE_JDBC_PROTOCOL + host + ":" + port;
        Connection connection = DriverManager.getConnection(url, "hive", "hive");
        return connection;
    }
}
