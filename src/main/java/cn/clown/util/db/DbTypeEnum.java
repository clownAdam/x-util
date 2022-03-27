package cn.clown.util.db;

/**
 * 数据库类型的枚举类
 *
 * @author clown
 */
public enum DbTypeEnum {
    /*mysql数据库类型*/
    MYSQL("mysql"),
    /*clickhouse数据库类型*/
    CLICKHOUSE("clickhouse"),
    /*oracle数据库类型*/
    ORACLE("oracle"),
    /*doris数据库类型*/
    DORIS("doris"),
    /*hbase数据库类型*/
    HBASE("hbase");
    private String dbType;

    DbTypeEnum(String dbType) {
        this.dbType = dbType;
    }

    public String getDbType() {
        return dbType;
    }
}
