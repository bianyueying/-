package bml.others;

import lombok.Data;

import java.io.Serializable;


/**
 * @author 月影
 * Date 2020/3/25 20:18
 * 接收密码的中间类
 */
@Data
public class UserPasswordDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String oldPassword;

    private String newPassword;


}
