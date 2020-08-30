## admin_client
##前言
此项目是我接私活进行的项目开发，项目预览拥有全部功能，开源代码禁止全部商用，给客服开发的正式项目并没有权限系统，由于网上基本没有react的权限系统，因此我决定开发出来并且前后端开源（此权限系统并不像网上vue的管理系统模版那么复杂且完善，但是一般也满足小中型公司的业务需求了）。

此项目也是我在业余时间学习react后开发的第一个react管理项目，使用了他人的开源基本模版，我在此开源模版上进行的项目开发。

[react前端部分](https://gitee.com/Explore_Mr_Pei/admin_manager_master)

[体验地址](http://www.xypsp.com/admin/)

### `java后端部分`
后端使用的技术栈：springboot、 jwt、 security 、mysql、mybatis、liqiubase、gradle、七牛云等

服务器采用ubuntu16.4，nginx，shell脚本的形式启动iar包
### `项目介绍`
项目采用liquibase自动创建数据表结构，采用多数据源连接admin权限相关数据库和admin_client用户业务相关数据库，
admin权限相关数据库和相关代码可作为模版进行二次开发，security进行权限的校验，jwt实现账号的鉴权
### `项目准备`
1. 创建xypsp_admin数据库和xypsp数据库，字符集utf8mb4
2. 运行admin_init.sql文件
3. 项目采用liqiubase自动创建数据库表结构，只需要创建好数据库名，运行项目，表结构就自动创建好了。liqiubase相关数据结构在resource/db下配置
[参考springboot+liquibase多数据源管理配置](https://www.jianshu.com/p/1d42731dc28b)
4. 修改配置文件，根据实际情况修改数据库配置和七牛云配置
5. 运行项目
### `本地部署`
1. 第一种方式直接运行入口类 SecurityAdminApplication
2. 第二种方式
    >
        点击右上角乌龟🐢图标 Edit Configurations
        点击左边➕加号，选择gradle
        填写name 随意
        填写Gradle project: 点击右边文件夹，直接选择项目名称
        填写Tasks:  clean bootRun
        点击 Apply,ok
        点击右上角乌龟🐢图标，选择刚刚自定义的name，点击右边的运行/debug按钮
    
### `线上部署`
1. 根据实际情况修改线上配置文件
2. 点击idea窗口右边gradle
3. 点击build，点击bootJar
4. 将生成的jar包放服务器
关于如何在线上部署，[请参考我这边文章](https://www.jianshu.com/p/7542d76f1ba5)