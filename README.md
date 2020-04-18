# 后台管理系统（第2.5版）

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
├─系统设置
│  ├─系统日志
│  ├─Druid监控
│  ├─Swagger2文档

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
- [MySQL8](https://dev.mysql.com/downloads/mysql/8.x.html#downloads), [Druid](http://druid.apache.org/)
- [Shiro](http://shiro.apache.org/)
- [Docker](https://www.docker.com/)
- [Redis](https://redis.io/)

#### 前端
- [LayuiAdmin](https://www.layui.com/)
- [eleTree 树组件](https://layuiextend.hsianglee.cn/eletree/)
- [Echarts 图表](https://www.echartsjs.com/zh/index.html)
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

   ####      7.个人信息编辑页面（第二版修复页面bug，并完善了一点点功能）

![](https://s1.ax1x.com/2020/04/05/GrDLOx.png)


### 特别说明：

1.前端有一些页面完全照搬了别人的页面(作者写的很好看，我写的太丑了，就直接拿来用了)，但后端为独立实现(其实是我看不懂他们的后端，他们写的前端JS我也是一脸蒙蔽，尴尬)。

2.第二版修复了第一版本Subject无法实时更新的问题；并发现修复了一个form表单的隐藏错误...

3.最后就是感觉写的太差了，不知道能不能行，后面打算在剩下的时间里能再好好的补充一些功能。

4.后续会添加redis，应该在4月20号就搞定了...


### 第2.5版说明：
> + 1.集成redis；这个项目里我只是简单的用了一下，如果有人下载了不想用，只需要把控制层LoginController类里面的@Cacheable(cacheNames = "usernameCheck")这一行去掉就行。（话说shiro集成redis真XX的是一个大坑，不过还好，爬了两天我终于爬出来了......）
> + 2.接下来就要专注补充一些功能了（个人打算再添加三个页面这毕设就完结了......）
   
   
### [这是我参考的大佬项目，点我去膜拜一下吧！](https://shiro.mrbird.cn:8080/login)
