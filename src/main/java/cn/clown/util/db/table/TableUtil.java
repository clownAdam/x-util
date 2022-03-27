package cn.clown.util.db.table;

import cn.clown.util.db.DBUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * jdbc table util
 *
 * @author clown
 * @Date 2022/3/19 0019 23:41
 */
public final class TableUtil {
    private static Logger logger = LoggerFactory.getLogger(TableUtil.class);

    public static List<Map<String, Object>> executeQuery(Connection connection, String sql) {
        try {
            logger.info("execute query sql:{}", sql);
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
            logger.error("error sql:{}", sql, e);
        }
        return null;
    }

    public static int executeUpdate(Connection connection, String sql) {
        try {
            logger.info("execute update sql:{}", sql);
            Statement statement = connection.createStatement();
            int result = statement.executeUpdate(sql);
            return result;
        } catch (SQLException e) {
            logger.error("error sql:{}", sql, e);
        }
        return -1;
    }
}
