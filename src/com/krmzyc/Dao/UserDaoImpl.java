package com.krmzyc.Dao;

import com.krmzyc.User.User;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.sms.v20210111.SmsClient;
import com.tencentcloudapi.sms.v20210111.models.SendSmsRequest;
import com.tencentcloudapi.sms.v20210111.models.SendSmsResponse;

import java.sql.*;

/*用户操作接口的具体实现*/

/*
这里采用的数据库是阿里云的RDS服务
故涉及自己数据库的安全问题，则这里的数据库连接操作直接改掉
*/

public class UserDaoImpl implements UserDao{
    @Override
	
	//通过用户名和密码在数据库中查找用户
    public User getUserByACandPSW(String useraccout, String userpassword) {

        User user=null;
        //JDBC: 获取连接 编写SQL 预编译SQL 设置参数 执行SQL 封装结果 关闭连接
        //获取连接
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url ="jdbc:mysql://yourdatabase";
            String name="yorname";
            String psw="yourpsw";
            Connection conn =DriverManager.getConnection(url,name,psw);
            String sql = "select id,name,psw from user where name=? and psw=?";
            PreparedStatement ps =conn.prepareStatement(sql);
            ps.setString(1,useraccout);
            ps.setString(2,userpassword);

            ResultSet res= ps.executeQuery();

            if(res.next())
            {
                user=new User();
                user.setUserId(res.getString("id"));
                user.setUsrAccount(res.getString("name"));
                user.setUsrPassword(res.getString("psw"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;


    }

    @Override
	//注册时在数据库中添加用户
    public void AddUser(String useraccount, String userpassword, String phonid) {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url ="jdbc:mysql://yourdatabase";
            String name="yourname";
            String psw="yourpsw";
            Connection conn =DriverManager.getConnection(url,name,psw);
            String sql="insert into user (name,psw,id) values(?,?,?)";
            PreparedStatement ps =conn.prepareStatement(sql);
            ps.setString(1,useraccount);
            ps.setString(2,userpassword);
            ps.setString(3,phonid);
            ps.executeUpdate();


        } catch (Exception e) {
            e.printStackTrace();
        }

    }
	
	//在数据库中更改用户密码

    public void ChangePSW(String usrpsw, String usraccount, String usrnewpsw)
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url ="jdbc:mysql://yourdatabase";
            String name="yourname";
            String psw="yourpsw";
            Connection conn =DriverManager.getConnection(url,name,psw);
            String sql="update user set psw=? where psw=? and name=?";
            PreparedStatement ps =conn.prepareStatement(sql);
            ps.setString(1,usrnewpsw);
            ps.setString(2,usrpsw);
            ps.setString(3,usraccount);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
	
	//验证手机号是否存在于数据库
	//如果手机号在数据库中不存在则不会发送验证码
    public User checkphone(String phonenumber) {
        User user=null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://yourdatabase";
            String name = "yourname";
            String psw = "yourpsw";
            Connection conn = DriverManager.getConnection(url, name, psw);
            String sql = "select id,name,psw from user where id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, phonenumber);
            ResultSet resultSet = ps.executeQuery();
            if(resultSet.next())
            {
                user=new User();
                user.setUserId(resultSet.getString("id"));
                user.setUsrPassword(resultSet.getString("psw"));
                user.setUsrAccount(resultSet.getString("name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;

    }

    @Override
	//发送验证码
    public void SendMS(String phonenumber) {       //该函数可以去掉，后面并没有用到
        try {
            System.out.println("kkkk");
            /* 必要步骤：
             * 实例化一个认证对象，入参需要传入腾讯云账户密钥对secretId，secretKey。
             * 这里采用的是从环境变量读取的方式，需要在环境变量中先设置这两个值。
             * 你也可以直接在代码中写死密钥对，但是小心不要将代码复制、上传或者分享给他人，
             * 以免泄露密钥对危及你的财产安全。
             * CAM密匙查询: https://console.cloud.tencent.com/cam/capi*/
            Credential cred = new Credential("secretID", "secretkey");

            // 实例化一个http选项，可选，没有特殊需求可以跳过
            HttpProfile httpProfile = new HttpProfile();
            // 设置代理
            // httpProfile.setProxyHost("真实代理ip");
            // httpProfile.setProxyPort(真实代理端口);
            /* SDK默认使用POST方法。
             * 如果你一定要使用GET方法，可以在这里设置。GET方法无法处理一些较大的请求 */
            httpProfile.setReqMethod("POST");

            /* SDK有默认的超时时间，非必要请不要进行调整
             * 如有需要请在代码中查阅以获取最新的默认值 */
            httpProfile.setConnTimeout(60);
            /* SDK会自动指定域名。通常是不需要特地指定域名的，但是如果你访问的是金融区的服务
             * 则必须手动指定域名，例如sms的上海金融区域名： sms.ap-shanghai-fsi.tencentcloudapi.com */
            httpProfile.setEndpoint("sms.tencentcloudapi.com");


            /* 非必要步骤:
             * 实例化一个客户端配置对象，可以指定超时时间等配置 */
            ClientProfile clientProfile = new ClientProfile();
            /* SDK默认用TC3-HMAC-SHA256进行签名
             * 非必要请不要修改这个字段 */
            clientProfile.setSignMethod("HmacSHA256");
            clientProfile.setHttpProfile(httpProfile);
            /* 实例化要请求产品(以sms为例)的client对象
             * 第二个参数是地域信息，可以直接填写字符串ap-guangzhou，或者引用预设的常量 */
            SmsClient client = new SmsClient(cred, "ap-guangzhou",clientProfile);

            /* 实例化一个请求对象，根据调用的接口和实际情况，可以进一步设置请求参数
             * 你可以直接查询SDK源码确定接口有哪些属性可以设置
             * 属性可能是基本类型，也可能引用了另一个数据结构
             * 推荐使用IDE进行开发，可以方便的跳转查阅各个接口和数据结构的文档说明 */
            SendSmsRequest req = new SendSmsRequest();


            /* 填充请求参数,这里request对象的成员变量即对应接口的入参
             * 你可以通过官网接口文档或跳转到request对象的定义处查看请求参数的定义
             * 基本类型的设置:
             * 帮助链接：
             * 短信控制台: https://console.cloud.tencent.com/smsv2
             * sms helper: https://cloud.tencent.com/document/product/382/3773 */

            /* 短信应用ID: 短信SdkAppId在 [短信控制台] 添加应用后生成的实际SdkAppId，示例如1400006666 */
            String sdkAppId = "1400006666";
            req.setSmsSdkAppId(sdkAppId);

            /* 短信签名内容: 使用 UTF-8 编码，必须填写已审核通过的签名，签名信息可登录 [短信控制台] 查看 */
            String signName = "XXX";
            req.setSignName(signName);

            /* 国际/港澳台短信 SenderId: 国内短信填空，默认未开通，如需开通请联系 [sms helper] */
            String senderid = "";
            req.setSenderId(senderid);

            /* 用户的 session 内容: 可以携带用户侧 ID 等上下文信息，server 会原样返回 */
            String sessionContext = "xxx";
            req.setSessionContext(sessionContext);

            /* 短信号码扩展号: 默认未开通，如需开通请联系 [sms helper] */
            String extendCode = "";
            req.setExtendCode(extendCode);

            /* 模板 ID: 必须填写已审核通过的模板 ID。模板ID可登录 [短信控制台] 查看 */
            String templateId = "400000";
            req.setTemplateId(templateId);

            /* 下发手机号码，采用 E.164 标准，+[国家或地区码][手机号]
             * 示例如：+8613711112222， 其中前面有一个+号 ，86为国家码，13711112222为手机号，最多不要超过200个手机号 */
            String[] phoneNumberSet = {"+86"+phonenumber};
            req.setPhoneNumberSet(phoneNumberSet);

            /* 模板参数: 若无模板参数，则设置为空 */
            String[] templateParamSet = {"5678"};
            req.setTemplateParamSet(templateParamSet);

            /* 通过 client 对象调用 SendSms 方法发起请求。注意请求方法名与请求对象是对应的
             * 返回的 res 是一个 SendSmsResponse 类的实例，与请求对象对应 */
            SendSmsResponse res = client.SendSms(req);

            // 输出json格式的字符串回包
            System.out.println(SendSmsResponse.toJsonString(res));

            // 也可以取出单个值，你可以通过官网接口文档或跳转到response对象的定义处查看返回字段的定义
            System.out.println(res.getRequestId());

        } catch (TencentCloudSDKException e) {
            e.printStackTrace();
        }

    }

    @Override
	
	//将验证码添加到数据库，方便进行后续比对
    public void Addcode(String phonenumber, String code) {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://yourdatabase";
            String name = "yourname";
            String psw = "yourpsw";
            Connection conn = DriverManager.getConnection(url, name, psw);
            String sql = "update user set code=? where id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, code);
            ps.setString(2,phonenumber);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
	
	//通过手机号获取数据库中的验证码
    public String CheckCode(String phonenumber) {
        User user=null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://yourdatabase";
            String name = "yourname";
            String psw = "yourpsw";
            Connection conn = DriverManager.getConnection(url, name, psw);
            String sql = "select * from user where id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,phonenumber);
            ResultSet resultSet = ps.executeQuery();
            if(resultSet.next())
            {
                user=new User();
                user.setUserId(resultSet.getString("id"));
                user.setUsrPassword(resultSet.getString("psw"));
                user.setUsrAccount(resultSet.getString("name"));
                user.setUsercode(resultSet.getString("code"));
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user.getUsercode();
    }

    @Override
	//找回密码
    public void Findpsw(String phonenumber, String newpsw) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://yourdatabase";
            String name = "yourname";
            String psw = "yourpsw";
            Connection conn = DriverManager.getConnection(url, name, psw);
            String sql = "update user set psw=? where id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, newpsw);
            ps.setString(2,phonenumber);
            ps.executeUpdate();
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
