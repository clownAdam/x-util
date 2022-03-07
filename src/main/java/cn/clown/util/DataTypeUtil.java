package cn.clown.util;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 数据类型工具类
 *
 * @author clown
 * @Date 2022/3/5 0005 14:48
 */
public class DataTypeUtil {
    private static Logger logger = LoggerFactory.getLogger(DataTypeUtil.class);

    /**
     * 将mysql的数据类型转换为java的数据类型
     *
     * @param mysqlType mysql的数据类型
     */
    public static String mysqlTypeConvertJava(String mysqlType) {
        if ("bit".equalsIgnoreCase(mysqlType)) {
            return "boolean";
        } else if ("tinyint".equalsIgnoreCase(mysqlType)) {
            return "byte";
        } else if ("smallint".equalsIgnoreCase(mysqlType)) {
            return "short";
        } else if ("int".equalsIgnoreCase(mysqlType)) {
            return "int";
        } else if ("bigint".equalsIgnoreCase(mysqlType)) {
            return "long";
        } else if ("float".equalsIgnoreCase(mysqlType)) {
            return "float";
        } else if (
                "decimal".equalsIgnoreCase(mysqlType) ||
                        "numeric".equalsIgnoreCase(mysqlType) ||
                        "money".equalsIgnoreCase(mysqlType) ||
                        "real".equalsIgnoreCase(mysqlType) ||
                        "smallmoney".equalsIgnoreCase(mysqlType)
        ) {
            return "double";
        } else if (
                "char".equalsIgnoreCase(mysqlType) ||
                        "nchar".equalsIgnoreCase(mysqlType) ||
                        "varchar".equalsIgnoreCase(mysqlType) ||
                        "nvarchar".equalsIgnoreCase(mysqlType) ||
                        "text".equalsIgnoreCase(mysqlType)
        ) {
            return "String";
        } else if ("datetime".equalsIgnoreCase(mysqlType)) {
            return "Date";
        } else {
            logger.error("mysql数据类型:{}无法匹配java数据类型", mysqlType);
        }
        return null;
    }
}
