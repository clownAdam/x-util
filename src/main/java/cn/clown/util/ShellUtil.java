package cn.clown.util;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
    private static Connection connection = null;
    private static final int DEFAULT_PORT = 22;
    private static String charset = Charset.defaultCharset().toString();
    private static Logger logger = LoggerFactory.getLogger(ShellUtil.class);

    /**
     * 获取连接
     * @param ip ip
     * @param port port
     * @param user user
     * @param password password
     */
    public static void getConnection(String ip, int port, String user, String password) {
        connection = new Connection(ip, port);
        try {
            connection.connect();
            boolean conn_status = connection.authenticateWithPassword(user, password);
            if (conn_status) {
                logger.info("连接成功[ip:{},port:{},user:{},password:{}].", ip, port, user, password);
            }
        } catch (IOException e) {
            logger.error("code:101,连接失败：{}.", e.getMessage());
        }
    }

    /**
     * 获取连接
     * @param ip ip
     * @param user user
     * @param password password
     */
    public static void getConnection(String ip, String user, String password) {
        getConnection(ip, DEFAULT_PORT, user, password);
    }

    /**
     * 执行command命令
     *
     * @param command shell
     * @return 执行返回状态码
     */
    public static int exec(String command) {
        Session session;
        try {
            session = connection.openSession();
        } catch (IOException e) {
            logger.error("code:201,session shell连接失败{}.", e.getMessage());
            return 201;
        }
        try {
            logger.info("执行shell:[{}]......", command);
            session.execCommand(command);
        } catch (IOException e) {
            logger.error("code:202,command:[{}]执行失败{}", command, e.getMessage());
            return 202;
        }
        try {
            String stdout = processStream(session.getStdout());
            String stderr = processStream(session.getStderr());
            logger.info("shell command:[{}] stdout:{}", command, "".equals(stdout) ? "{}" : "\n" + stdout);
            logger.info("shell command:[{}] stderr:{}", command, "".equals(stderr) ? "{}" : "\n" + stderr);
        } catch (IOException e) {
            logger.error("读取shell command:[{}]Stdout信息失败.{}", command, e.getMessage());
            logger.error("读取shell command:[{}]Stderr信息失败.{}", command, e.getMessage());
            return 203;
        }
        session.close();
        return 0;
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
        in.close();
        return sb.toString();
    }

    /**
     * 关闭连接
     */
    public static void close() {
        if (connection != null) {
            logger.info("关闭connection shell");
            connection.close();
        }
    }
}
