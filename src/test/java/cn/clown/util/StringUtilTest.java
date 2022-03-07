package cn.clown.util;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class StringUtilTest {

    @Test
    public void initCap() {
        String str = null;
        if(str==null || str.isEmpty()){
            System.out.println("1");
        }
    }
    @Test
    public void initCap2() {
        String str = "a_b_c";
        if(str==null || str.isEmpty()){
            System.out.println("1");
        }
        System.out.println("res : " + Arrays.toString(str.split("_")));
    }

    @Test
    public void titleCase() {
        String str = StringUtil.titleCase("S_S_S", false);
        System.out.println("str = " + str);
    }

    @Test
    public void separatorTitleCase() {
        String str = StringUtil.separatorTitleCase("S_S_S", "_",false);
        System.out.println("str = " + str);
    }

    @Test
    public void testTitleCase() {
        String str = StringUtil.titleCase("S_S_S");
        System.out.println("str = " + str);
    }
}