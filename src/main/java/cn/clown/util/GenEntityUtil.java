package cn.clown.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.sql.*;

/**
 * 自动生成实体类工具
 *
 * @author clown
 * @Date 2022/3/5 0005 11:53
 */
public class GenEntityUtil {
    private static String packageOutPath;
    private static String table;
    private static String[] colNames;
    private static String[] colTypes;
    private static int[] colSizes;
    private static boolean is_import_util = false;
    private static boolean is_import_sql = false;
    private static Logger logger = LoggerFactory.getLogger(GenEntityUtil.class);

    /**
     * 生成实体类
     *
     * @param conn           连接
     * @param tableName      表
     * @param packageOutPath 实体类生成所在的包路径
     * @throws SQLException
     */
    public static void generateFromMysql(Connection conn, String tableName, String packageOutPath) throws SQLException {
        GenEntityUtil.packageOutPath = packageOutPath;
        GenEntityUtil.table = tableName;
        String sql = "select * from "+tableName;
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSetMetaData metaData = ps.getMetaData();
        int size = metaData.getColumnCount();
        colNames = new String[size];
        colTypes = new String[size];
        colSizes = new int[size];
        for (int i = 0; i < size; i++) {
            colNames[i] = metaData.getColumnName(i + 1);
            colTypes[i] = metaData.getColumnTypeName(i + 1);
            colSizes[i] = metaData.getColumnDisplaySize(i + 1);
            if ("datetime".equalsIgnoreCase(colTypes[i])) {
                is_import_util = true;
            }
            if ("image".equalsIgnoreCase(colTypes[i]) || "text".equalsIgnoreCase(colTypes[i])) {
                is_import_sql = true;
            }
        }
        String content = createEntityCode(colNames, colTypes, colSizes);
        //开始生成文件
        try {
            File directory = new File("");
            // String path = GenEntityUtil.class.getResource("").getPath();
            String outputPath = directory.getAbsolutePath() + "/src/main/java/" + packageOutPath.replace(".", "/") + "/" + StringUtil.titleCase(table) + ".java";
            FileWriter fw = new FileWriter(outputPath);
            PrintWriter pw = new PrintWriter(fw);
            pw.println(content);
            pw.flush();
            pw.close();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            ps.close();
        }
    }

    private static String createEntityCode(String[] colNames, String[] colTypes, int[] colSizes) {
        StringBuffer sb = new StringBuffer();
        //导包部分
        sb.append("package " + packageOutPath + ";\r\n");
        sb.append("\r\n");
        if (is_import_util) {
            sb.append("import java.util.*;\r\n");
        }
        if (is_import_sql) {
            sb.append("import java.sql.*;\r\n");
        }
        sb.append("\r\n");
        //注释部分
        sb.append("/**\r\n");
        sb.append(" *\r\n");
        sb.append(" * " + table + " 实体类\r\n");
        sb.append(" * @author clown\r\n");
        sb.append(" */ \r\n");
        //实体部分
        sb.append("public class " + StringUtil.titleCase(table) + " {\r\n");
        //属性
        createEntityAttributeCode(sb);
        //方法
        createEntityMethodCode(sb);
        sb.append("}\r\n");
        return sb.toString();
    }

    /**
     * 生成所有属性
     *
     * @param sb 存放的集合
     */
    private static void createEntityAttributeCode(StringBuffer sb) {
        for (int i = 0; i < colNames.length; i++) {
            sb.append("\tprivate " + DataTypeUtil.mysqlTypeConvertJava(colTypes[i]) + " " + colNames[i] + ";\r\n");
        }
    }

    /**
     * 生成所有方法【getter setter】
     *
     * @param sb 存放的集合
     */
    private static void createEntityMethodCode(StringBuffer sb) {
        for (int i = 0; i < colNames.length; i++) {
            sb.append("\r\n");
            sb.append("\tpublic void set" + StringUtil.titleCase(colNames[i]) + " (" + DataTypeUtil.mysqlTypeConvertJava(colTypes[i]) + " " + colNames[i] + ") {\r\n");
            sb.append("\t\tthis." + colNames[i] + " = " + colNames[i] + ";\r\n");
            sb.append("\t}\r\n");
            sb.append("\tpublic " + DataTypeUtil.mysqlTypeConvertJava(colTypes[i]) + " get" + StringUtil.titleCase(colNames[i]) + " () {\r\n");
            sb.append("\t\treturn " + colNames[i] + ";\r\n");
            sb.append("\t}\r\n");
        }
    }
}
