# CSU-Web-
中南大学Web课程实验，实现登录等操作。。。
在git上找了好长时间都没找到，那不如就自己写了
这里除了用户的增删改查都实现了 ~~（用户自己增删改查。。。感觉有点蠢。。。）~~
具体功能
- 账号登录
- 注册
- 手机验证码登录
- 修改密码
- 找回密码（通过手机验证码）

**基于Servlet，没用框架**


### 1.实验目的
通过设计并实现一个简单全栈系统的方式提升对Web相关技术的理解、掌握及应用。
### 2.实验任务
本次实验任务为实现一个登录页面。具体要求如下：
- 实现前端页面的基本布局。要求：
  - 布局类似于学校门户http://my.csu.edu.cn/login/index.jsp
  - 顶部需有LOGO栏目；
  - 左侧提供轮播图；
  - 提供账号密码登录方式；
  - 提供手机号码（邮箱）+验证码登录方式；
  - 登录成功后跳到显示“登录成功”四字的页面（简单设计）；
  - *提供忘记密码和修改密码功能；
  - *提供用户的增删改查。
- 完成前后端数据交互（用JSON格式）
- 数据操作要求：
  - 数据统一存储在后端数据库中；
  - 账号密码登录方式需进行验证，验证通过方能登录；
  - 手机（邮箱）验证码需调用第三方短信接口发送验证码并进行验证；
  - 后端实现技术不限、数据库系统不限。

- *为可选项目（加分项目）
