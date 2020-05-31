# Vue + SSM 的校园二手交易平台
## 既然已经点进来了，不如随手点个 Star 谢谢！
## 技术点
#### Vue.js + Spring + SpringMVC + Mybatis + Mysql
**项目需求中有 Redis、支付宝沙箱支付、Echarts 或 V-charts 图表等技术，待加入**
## 对应后台项目
[SchoolCampusUsedBack JSP校园二手交易系统后台，使用AdminLTE模板](https://github.com/jdassd/SchoolCampusUsedBack)
**需要用到后台的话，可以额外部署这个后台项目，后台的前后端使用的是 JSP + Servlet 持久层使用 Mybatis 数据库为 Mysql**
## 展示
#### 首页
![](https://github.com/jdassd/imgRepository/blob/master/TIM%E6%88%AA%E5%9B%BE20191220203515.png)
![](https://github.com/jdassd/imgRepository/blob/master/TIM%E6%88%AA%E5%9B%BE20191220203535.png)
#### 首页商品展示效果
![](https://github.com/jdassd/imgRepository/blob/master/TIM%E6%88%AA%E5%9B%BE20191220203553.png)
#### 查看商品详情效果
![](https://github.com/jdassd/imgRepository/blob/master/TIM%E6%88%AA%E5%9B%BE20191220203608.png)
#### 购物车
![](https://github.com/jdassd/imgRepository/blob/master/TIM%E6%88%AA%E5%9B%BE20191220203637.png)
#### 上传二手（闲置卖钱）
![](https://github.com/jdassd/imgRepository/blob/master/TIM%E6%88%AA%E5%9B%BE20191220203720.png)
##### 查看商品大图
![](https://github.com/jdassd/imgRepository/blob/master/InkedTIM%E6%88%AA%E5%9B%BE20191220203831_LI.jpg)
#### 个人信息展示与更改
![](https://github.com/jdassd/imgRepository/blob/master/TIM%E6%88%AA%E5%9B%BE20191220203750.png)
#### 查看订单与闲置
![](https://github.com/jdassd/imgRepository/blob/master/TIM%E6%88%AA%E5%9B%BE20191220203919.png)
## 部署
1. 项目由 Maven 构建，建议使用 IDEA 打开项目引入依赖，如果需要和对应的后台项目一起使用：[SchoolCampusUsedBack JSP校园二手交易系统后台，使用AdminLTE模板](https://github.com/jdassd/SchoolCampusUsedBack)，请在编译运行时注意调整 Tomcat 的端口。**该项目默认 Vue 端口 8080，Tomcat 端口 8083<font color="red">请不要更改该项目的 Tomcat 端口，否则将无法发送和收到对应的 Ajax 请求。</font>**
2. 导入项目后请不要着急运行项目，该项目上传图片需要一个单独的图片服务器。**代码中写入的调试服务器地址为 localhost:9090 ，请使用 Node.js 安装 Serve，再通过下面的方法映射本地文件夹到 localhost:9090 用来作为本地图片服务器。**
- 1. 全局安装 Serve
```
npm install -g serve
```
- 2. 将项目提供的 9090.bat 文件放入要映射的文件夹，右键编辑，将第一行的 ``` H: ``` 修改为你的对应盘符，注意 ``` : ``` 符号不要省略，右键管理员运行它，如图片所示即表示映射成功。
![](https://github.com/jdassd/imgRepository/blob/master/TIM%E6%88%AA%E5%9B%BE20191220210632.png)
3. 设置编译运行环境，找到对应的 package.json 文件，如图
![](https://github.com/jdassd/imgRepository/blob/master/TIM%E6%88%AA%E5%9B%BE20191220211011.png)
4. 将 sql.sql 导入到你的数据库
5. 修改 src/main/resources/jdbc.properties 为你的数据库配置信息，将用户名和密码修改为你的用户名和密码**如果你使用的不是 Mysql 数据库，请自行修改 applicationContext.xml 文件里的数据库配置，和 pom.xml 中引入的依赖**
6. 在 Terminal 控制台 cd 进入 CourseDesionSpring 工程下的 vue_web 文件夹，并执行指令 ``` npm install ``` 安装 Vue.js 的依赖。**如果依赖安装总是出错，请尝试全局科学上网，或者在下面找到我的联系方式，或在 Issues 里提交问题。**
7. 先确保第 2 步已经映射成功，再启动名为 spring 的 tomcat，最后启动 dev
8. 如果在控制台输出中无误，可以打开 [这里](http://localhost:8080) 查看效果。**如果你在第 1 步修改了 Vue 的端口，那么点击这里将无效。**
## FAQ
#### 为什么这个项目跑起来报错？
如果你的项目已经跑起来了，而不能访问到首页，你可以尝试切换 Tomcat 版本，这个项目构建时的版本为 Tomcat-8.5.46
#### 我发现这个项目有个 BUG ？
这个项目目前发现而没有解决的 BUG 有：
1. 未登录但可以通过链接访问到个人中心，以及登录后可以通过链接访问到登录和注册页面。
2. 其他一些 BUG ，但基本不影响使用。
## 捐赠
|捐赠人|金额(RMB)|渠道|时间|留言|
|:-:|:-:|:-:|:-:|:-:|
|MH2319916121（微信）|20|微信支付|2020.1.5|买杯奶茶或者咖啡喝喝|
|foreverdreamer_sdy（微信）|20|微信支付|2020.4.29|请您喝个奶茶|
|hms1997716（微信）|20|微信支付|2020.4.30|感谢感谢|
|coolsalahei（微信）|20|微信支付|2020.5.12|感谢|
|【昵称未知】（微信）|20|微信支付|2020.5.31|程序谢谢！请你喝咖啡|

## 联系 
#### 微信 And QQ
<img src="https://github.com/jdassd/imgRepository/blob/master/%E6%B7%BB%E5%8A%A0%E5%BE%AE%E4%BF%A1%E5%A5%BD%E5%8F%8B%E7%A0%81.jpg" width="200" hegiht="200" alt="微信添加好友码" /><img src="https://github.com/jdassd/imgRepository/blob/master/%E6%B7%BB%E5%8A%A0QQ%E5%A5%BD%E5%8F%8B%E7%A0%81.jpg" width="200" hegiht="200" alt="QQ添加好友码" />



## 请作者喝咖啡
<img src="https://github.com/jdassd/imgRepository/blob/master/%E5%BE%AE%E4%BF%A1%E6%94%B6%E6%AC%BE%E7%A0%81.jpg" width="200" hegiht="200" align=center alt="微信收款码"/>
