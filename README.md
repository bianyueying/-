# 后台管理系统（第一版）

### IDEA需要安装lombok插件
### 系统模块
系统功能模块组成如下所示：
```
├─用户管理
│  ├─用户管理
│  ├─角色管理
│  ├─权限管理
├─职位管理
│  ├─分类管理
│  ├─我的职位
│  ├─职位添加
├─系统日志
├─Swagger2文档

头部导航栏：
├─个人中心
│  ├─更换头像
│  ├─编辑资料
│  ├─修改密码

```
### 系统特点

1. 前后端分离

2. 前端UI使用LayuiIframe框架，主题多样

3. 实时统计系统日志

4. 多Tab页面

5. 用户数据动态刷新

6. 代码简单，结构清晰（水平太差，代码就简单了）

### 技术选型

#### 后端
- [Spring Boot](http://spring.io/projects/spring-boot/)
- [Mybatis-Plus](https://mp.baomidou.com/guide/)
- [MySQL 8.x.x](https://dev.mysql.com/downloads/mysql/8.x.html#downloads),
- [Shiro 1.4.2](http://shiro.apache.org/)
- [Docker](https://www.docker.com/)

#### 前端
- [Layui 2.5.5](https://www.layui.com/)
- [LayuiIframe](https://www.layui.com/admin/)
- [eleTree 树组件](https://layuiextend.hsianglee.cn/eletree/)
- [Echarts图表](https://www.echartsjs.com/zh/index.html)
- [Editor.md](https://pandao.github.io/editor.md/)
- [Thymeleaf](https://www.thymeleaf.org/)
- [EasyCaptcha](https://gitee.com/whvse/EasyCaptcha)(很好看的验证码)

### 系统截图

   ####      1.登录与注册共用一个页面

![登录页面](https://s1.ax1x.com/2020/04/05/GrDq61.png)

   ####      2.系统首页，右下部分暂时还不知道写点什么，表格是充数的

![系统首页](https://s1.ax1x.com/2020/04/05/GrDXm6.png)

   ####      3.用户管理页面 权限不足则不能操作

![](https://s1.ax1x.com/2020/04/05/GrrpfH.png)

   ####      4.角色编辑页面，权限不足无法更改，右侧新增角色与编辑角色可以动态切换

![](https://s1.ax1x.com/2020/04/05/GrrSte.png)

   ####      5.权限编辑页面，权限不足无法更改，右侧新增菜单与编辑菜单可以智能切换

![](https://s1.ax1x.com/2020/04/05/GrDblR.png)

   ####      6.职位编辑页面

![](https://s1.ax1x.com/2020/04/05/GrrP1A.png)

   ####      7.个人信息编辑页面（目前有bug，更新后数据库能更改成功，但前端页面不能实时展示，刷新也不能，重新登录就可以，不知道为什么。后续应该会死磕这个）

![](https://s1.ax1x.com/2020/04/05/GrDLOx.png)


### 特别说明：

​	1.前端有一些页面完全照搬了别人的页面(作者写的很好看，我写的太丑了，就直接拿来用了)，但后端为独立实现(其实是我看不懂他们的后端，他们写的前端JS我也是一脸蒙蔽，尴尬)。

​	2.目前第一版有一个功能一直无法实现：用户更改资料后一直无法实时显示，但是数据库里已经更改成功了；我写的demo里就能更改，但这里一直无法搞定，我感觉可能是Layui框架父子页面的问题。对Layui不太熟练。。

​	3.最后就是感觉写的太差了，不知道能不能行，后面打算在剩下的时间里能再好好的补充一些功能。

### [点我去膜拜大佬们的项目吧！](https://shiro.mrbird.cn:8080/login)
