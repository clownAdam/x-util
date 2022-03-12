package cn.clown.util;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class HdfsUtilTest {

    @Before
    public void before(){
        HadoopConf conf = new HadoopConf("cdh2",8020);
        HdfsUtil.setConf(conf);
    }
    @Test
    public void mkdirs() throws IOException {
        HdfsUtil.mkdirs("/clown/test");
    }
    @Test
    public void copyFromLocalFile(){
        HdfsUtil.copyFromLocalFile("D:\\BaiduNetdiskDownload\\badou_project_data","/clown/");
    }
    @Test
    public void delete(){
        HdfsUtil.delete("/clown/test");
    }
    @Test
    public void copyFromLocalFile2(){
        HdfsUtil.copyFromLocalFile(
                "D:\\BaiduNetdiskDownload\\badou_project_data\\project1\\order_products__train.csv",
                "/clown/badou_project_data/project1/order_products__train.csv");
    }

    @Test
    public void copyToLocalFile(){
        HdfsUtil.copyToLocalFile("/clown/badou_project_data/project1/aisles.csv","/aisles.csv");
    }
}