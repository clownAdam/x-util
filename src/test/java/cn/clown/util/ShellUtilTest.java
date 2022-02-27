package cn.clown.util;


public class ShellUtilTest {

    @org.junit.Test
    public void getConnection() {
        ShellUtil.getConnection("bd-101",22,"root","root");
        ShellUtil.close();
    }

    @org.junit.Test
    public void testGetConnection() {
        ShellUtil.getConnection("bd-101","root","root");
        ShellUtil.close();
    }

    @org.junit.Test
    public void exec() {
        ShellUtil.getConnection("bd-101","root","root");
        ShellUtil.exec("ps -ef | grep java");
        ShellUtil.close();
    }

    @org.junit.Test
    public void close() {
        ShellUtil.close();
    }
}