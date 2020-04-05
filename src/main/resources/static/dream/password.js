
/*个人密码修改模态框*/
function updatePassword() {
    layui.use(['layer','form','jquery'],function () {
        var layer = layui.layer;
        var $ = layui.jquery;
        /*必须有下面这一行代码 不然复选框无法显示*/
        var form = layui.form;
        //打开弹出层
        var frame = layer.open({
            type:1,
            area:["400px","280"],
            resize:true,
            content: $('#user-password-update'),
            title:'更改密码',
            btn:['确定','取消'],
            success:function () {
                $(':focus').blur();
                form.render();
            },
            btn1: function () {
                const data = form.val('update-form');
                if (data.newPassword !== data.confirmPassword) {
                    $('#newPassword').val("");
                    $('#confirmPassword').val("");
                    layer.msg("新密码与确认码不一致！");
                    return false;
                }
                $.ajax({
                    url: "http://localhost:8080/admin/user/password",
                    type: 'put',
                    data: JSON.stringify(data),
                    dataType: "json",
                    contentType: "application/json;charset=utf-8",
                    success: function (result) {
                        if (result.status === 200) {
                            layer.msg(result.msg);
                            setTimeout("location.reload()",1000);
                        } else if (result.status === 201) {
                            $('#oldPassword').val("");
                            $('#newPassword').val("");
                            $('#confirmPassword').val("");
                            layer.msg(result.msg);
                        } else if (result.status === 202) {
                            $('#oldPassword').val("");
                            layer.msg(result.msg);
                        }
                    }
                });
            }
        });
    })
}

// 退出确认框 已确定 谨慎更改
function exitConfirm() {
    layui.use(['layer'],function () {
        const layer = layui.layer;
        layer.open({
            type: 1,
            title:'退出确定信息框',
            //15s后自动关闭
            time: 15000,
            skin: 'layui-layer-molv',
            anim: 5,
            resize:false,
            area:["300px","200px"],
            content: '<div style="text-align: center;line-height: 100px">确定要退出吗？客官不再继续快活了吗?</div>',
            btn: ['取消','退出'],
            btn2: function () {
                window.location.href="/logout";
            }
        })
    })
}