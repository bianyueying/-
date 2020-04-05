package bml.util;


/**
 * @author 月影
 * Date 2020/2/29 19:50
 * 检查用户注册时输入的账号密码是否合法 用户名和密码都只限字母和数字
 */
public class StringUtil {

    public StringUtil() {

    }

    private static final String REGEX = "^[a-z0-9A-Z]+$";

    public static boolean checkUsername(String username) {
        return username.length() >= 3 && username.length() <= 12 && username.matches(REGEX);
    }

    public static boolean checkPassword(String password){
        return password.length() >= 6 && password.length() <= 12 && password.matches(REGEX);
    }

    /**
     * 此方法用于处理数据库字段与前端展示不匹配问题
     * 比如 20200101 截取拼装后变成01-01 字符更短 便与展示
     */
    public static String getFitDate(String string){
        String month = string.substring(4,6);
        String day = string.substring(6,8);
        return month+"-"+day;
    }

    public static void main(String[] args) {

    }
}
