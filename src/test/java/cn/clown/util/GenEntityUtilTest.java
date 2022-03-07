package cn.clown.util;

import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.Assert.*;

public class GenEntityUtilTest {

    @Test
    public void generateFromMysql() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bigdata?serverTimezone=UTC&useSSL=false", "root", "root");
        GenEntityUtil.generateFromMysql(connection,"function", "cn.clown");
    }
}