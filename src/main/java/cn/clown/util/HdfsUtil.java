package cn.clown.util;

import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

/**
 * Hdfs工具类
 * hdfs相关操作的工具类，包括hdfs系统文件的创建，与本地系统文件的交互等
 * readme:https://github.com/clownAdam/FocusBigData.git
 *
 * @author clown
 */
public class HdfsUtil {
    private static FileSystem fs;
    private static Logger logger = LoggerFactory.getLogger(HdfsUtil.class);

    public static void setConf(HadoopConf hadoopConf) {
        try {
            HdfsUtil.fs = FileSystem.get(hadoopConf.configuration);
        } catch (IOException e) {
            logger.error("获取hdfs FileSystem 失败：{}", e.getMessage(), e);
        }
    }

    /**
     * 将本地目录src上传到hdfs系统dst目录之下
     *
     * @param src 本地路径
     * @param dst hdfs目录
     */
    public static void copyFromLocalFile(String src, String dst) {
        try {
            fs.copyFromLocalFile(new Path(src), new Path(dst));
            logger.info("复制本地目录{}到hdfs目录{}下", src, dst);
        } catch (IOException e) {
            logger.error("复制本地目录{}到hdfs目录{}下失败", src, dst, e);
        }
    }

    /**
     * 将hdfs系统path路径删除
     *
     * @param path hdfs路径
     */
    public static boolean delete(String path) {
        try {
            boolean flag = fs.delete(new Path(path));
            if (flag) {
                logger.info("删除hdfs路径{}成功", path);
            } else {
                logger.warn("删除hdfs路径{}失败", path);
            }
            return flag;
        } catch (IOException e) {
            logger.error("删除hdfs路径{}失败", path, e);
            return false;
        }
    }

    /**
     * 创建hdfs目录路径path
     *
     * @param path hdfs路径
     * @return 创建状态, 成功返回true, 失败返回false
     */
    public static boolean mkdirs(String path) {
        try {
            try {
                boolean flag = fs.mkdirs(new Path(path));
                if (flag) {
                    logger.info("path:{}创建成功", path);
                } else {
                    logger.warn("path:{}创建失败", path);
                }
                return flag;
            } catch (IOException e) {
                logger.error("path:{}创建失败", path, e);
                return false;
            }
        } catch (NullPointerException e) {
            logger.error("你需要先设置conf");
            return false;
        }
    }

    /**
     * 将文件从hdfs下载到本地目录
     *
     * @param src
     * @param dest
     * @return
     */
    public static void copyToLocalFile(String src, String dest) {
        try {
            try {
                fs.copyToLocalFile(new Path(src), new Path(dest));
                logger.info("复制hdfs目录{}到本地目录{}下", src, new File(dest).getAbsolutePath());
            } catch (IOException e) {
                logger.error("复制hdfs目录{}到本地目录{}下失败", src, new File(dest).getAbsolutePath(), e);
            }
        } catch (NullPointerException e) {
            logger.error("你需要先设置conf");
        }
    }
}
