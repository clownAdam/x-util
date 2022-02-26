package cn.clown.util;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

/**
 * java与shell的工具类
 *
 * @author clown
 * @Date 2022/2/26 0026 20:05
 */
public class ShellUtil {
    private static String charset = Charset.defaultCharset().toString();
    private static Connection connection;

    public static boolean getConnection(String ip, int port, String user, String password) {
        connection = new Connection(ip, port);
        return false;
    }

    public static boolean getConnection(String ip, String user, String password) {
        connection = new Connection(ip);
        return false;
    }

    public static void main(String[] args) throws IOException {
        Connection connection = new Connection("192.168.233.11");
        connection.connect();
        boolean flag = connection.authenticateWithPassword("root", "root");
        System.out.println("flag = " + flag);
        Session session = connection.openSession();

        session.execCommand("ps -ef | grep click");
        System.out.println(processStream(session.getStdout()));


        session.close();
        connection.close();
    }

    /**
     * 将inputStream转换成string
     *
     * @param in InputStream
     * @return String
     * @throws IOException
     */
    private static String processStream(InputStream in) throws IOException {
        byte[] buf = new byte[1024];
        StringBuilder sb = new StringBuilder();
        while (in.read(buf) != -1) {
            sb.append(new String(buf, charset));
        }
        return sb.toString();
    }
}
