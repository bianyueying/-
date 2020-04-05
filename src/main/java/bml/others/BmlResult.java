package bml.others;

import lombok.Data;

/**
 * @author 月影
 * Date 2020/3/7 18:36
 * 自定义返回结果封装类
 */
@Data
public class BmlResult {

    /**
     * 返回状态码
     */
    private Integer status;

    /**
     * 返回自定义信息
     */
    private String msg;

    public BmlResult(Integer status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public static BmlResult ok(String msg){
        return new BmlResult(200,msg);
    }

    public static BmlResult error(String msg){
        return new BmlResult(400,msg);
    }



}
