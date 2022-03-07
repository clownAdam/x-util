package cn.clown.util;



/**
 * @author clown
 * @Date 2022/3/5 0005 13:26
 */
public class StringUtil {

    /**
     * 将传入的字符串首字母大写，其余字母小写并输出
     *
     * @param str 传入的字符串
     * @return
     */
    public static String titleCase(String str) {
        return titleCase(str, true);
    }

    /**
     * 将传入的字符串首字母大写并输出
     *
     * @param str 传入的字符串
     * @param raw 如果为true:传入的字符串首字母大写,其余字符小写;false:传入的字符串首字母大写,其余部分原样输出
     * @return
     */
    public static String titleCase(String str, boolean raw) {
        return separatorTitleCase(str, "", raw);
    }

    /**
     * 将传入的字符串按照指定的分隔符为界限，分隔符之间首字母大写，最后分隔符去除并返回
     *
     * @param str 输入的字符串
     * @param sep 分隔符.如果sep isEmpty 不作处理
     * @param raw 如果为true:传入的字符串中分隔符之间的字符首字母大写,其余字符小写;false:传入的字符串中分隔符之间的字符首字母大写,其余部分原样输出。分隔符也去除
     * @return
     */
    public static String separatorTitleCase(String str, String sep, boolean raw) {
        str = raw ? str.toLowerCase() : str;
        String[] strs = sep.isEmpty() ? new String[]{str} : str.split(sep);
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < strs.length; i++) {
            char[] ch = strs[i].toCharArray();
            if (ch[0] >= 'a' && ch[0] <= 'z') {
                ch[0] = (char) (ch[0] - 32);
            }
            sb.append(new String(ch));
        }
        return sb.toString();
    }

}
