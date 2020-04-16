package bml.others;

import lombok.Data;

/**
 * @author 月影
 * Date 2020/3/7 18:36
 * 自定义返回结果封装类
 */
@Data
public class BmlResult<T> {

    /**
     * 封装数据
     */
    private T data;

    /**
     * 返回状态码
     */
    private Integer status;

    /**
     * 返回自定义信息
     */
    private String msg;


    /**
     * 如没有数据返回，则可以自定义指定状态码和提示信息
     */
    public BmlResult(Integer status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    /**
     * 用于返回查询的集合
     * @param data 封装数据
     * @param status 状态码
     */
    public BmlResult(T data,Integer status) {
        this.data = data;
        this.status = status;
    }

}
