package cn.clown.util.db.sql;

import cn.clown.util.db.DbTypeEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * sql 工具类
 *
 * @author clown
 * @Date 2022/3/19 0019 23:33
 */
public final class JdbcSqlUtil {
    private static Logger logger = LoggerFactory.getLogger(JdbcSqlUtil.class);

    private static String generateSql(DbTypeEnum dbType, SqlOptionTypeEnum sqlOptionType, List<Map<String, Object>> kvData) {
        logger.error("该方法暂不支持");
        return null;
    }

    /**
     * 生成sql语句
     *
     * @param dbType        数据库类型
     * @param sqlOptionType sql类型
     * @param kvData        字段-字段值
     * @return 返回生成完成的sql
     */
    public static String generateSql(DbTypeEnum dbType, SqlOptionTypeEnum sqlOptionType, String table, Map<String, Object> kvData) {
        logger.info("sql db type:{},option:{},table:{}", dbType, sqlOptionType, table);
        String sql = null;
        switch (dbType) {
            case MYSQL:
                switch (sqlOptionType) {
                    case INSERT:
                        Iterator<Map.Entry<String, Object>> kv = kvData.entrySet().iterator();
                        StringBuffer keyBuffer = new StringBuffer();
                        StringBuffer valueBuffer = new StringBuffer();
                        while (kv.hasNext()) {
                            Map.Entry<String, Object> entry = kv.next();
                            String key = entry.getKey();
                            keyBuffer.append(key + ",");
                            Object value = entry.getValue();
                            valueBuffer.append(value + ",");
                        }
                        keyBuffer = keyBuffer.deleteCharAt(keyBuffer.length() - 1);
                        valueBuffer = valueBuffer.deleteCharAt(valueBuffer.length() - 1);
                        sql = String.format("insert into %s (%s) values (%s)", table, keyBuffer, valueBuffer);
                        break;
                    case DELETE:
//                        break;
                    case SELECT:
//                        break;
                    case UPDATE:
//                        break;
                    default:
                        logger.error("目前不支持{}数据库生成{}sql", dbType, sqlOptionType);
                        break;
                }
                break;
            case CLICKHOUSE:
                switch (sqlOptionType) {
                    case INSERT:
                        Iterator<Map.Entry<String, Object>> kv = kvData.entrySet().iterator();
                        StringBuffer keyBuffer = new StringBuffer();
                        StringBuffer valueBuffer = new StringBuffer();
                        while (kv.hasNext()) {
                            Map.Entry<String, Object> entry = kv.next();
                            String key = entry.getKey();
                            keyBuffer.append(key + ",");
                            Object value = entry.getValue();
                            valueBuffer.append(value + ",");
                        }
                        keyBuffer = keyBuffer.deleteCharAt(keyBuffer.length() - 1);
                        valueBuffer = valueBuffer.deleteCharAt(valueBuffer.length() - 1);
                        sql = String.format("insert into %s (%s) values (%s)", table, keyBuffer, valueBuffer);
                        break;
                    case DELETE:
//                        break;
                    case SELECT:
//                        break;
                    case UPDATE:
//                        break;
                    default:
                        logger.error("目前不支持{}数据库生成{}sql", dbType, sqlOptionType);
                        break;
                }
                break;
            default:
                logger.error("目前不支持{}数据库生成sql", dbType);
                break;
        }
        logger.info("generate db:{} sql:{}", dbType, sql);
        return sql;
    }
}
