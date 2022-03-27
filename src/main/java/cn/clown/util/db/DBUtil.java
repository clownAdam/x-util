package cn.clown.util.db;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * db工具类
 *
 * @author clown
 * @Date 2022/3/18 0018 0:00
 */
public final class DBUtil {
    private static Logger logger = LoggerFactory.getLogger(DBUtil.class);

    public static Connection getConnection(String driver, String url, String username, String password) {
        try {
            Class.forName(driver);
            return DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException e) {
            logger.error("加载驱动失败:{}", driver, e);
        } catch (SQLException e) {
            logger.error("获取连接失败.", e);
        }
        return null;
    }

    private static void close(Connection connection, Statement statement, ResultSet resultSet) throws SQLException {
        if (resultSet != null) {
            resultSet.close();
        }
        if (statement != null) {
            statement.close();
        }
        if (connection != null) {
            connection.close();
        }
    }

}
