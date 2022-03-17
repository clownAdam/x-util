package cn.clown.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Closeable;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * jdbc工具类
 *
 * @author clown
 * @Date 2022/3/18 0018 0:00
 */
public class JDBCUtil {
    private static Logger logger = LoggerFactory.getLogger(JDBCUtil.class);

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

    public static List<Map<String, Object>> executeQuery(Connection connection, String sql) {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            ResultSetMetaData metaData = resultSet.getMetaData();
            List<Map<String, Object>> result = new ArrayList<>();
            while (resultSet.next()) {
                Map<String, Object> row = new HashMap<>(metaData.getColumnCount());
                for (int i = 0; i < metaData.getColumnCount(); i++) {
                    row.put(metaData.getColumnName(i), resultSet.getObject(metaData.getColumnName(i)));
                }
                result.add(row);
            }
            return result;
        } catch (SQLException e) {
            logger.error("sql:{}", sql, e);
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
