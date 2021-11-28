package com.krmzyc.Log;

import com.krmzyc.Dao.UserDao;
import com.krmzyc.Dao.UserDaoImpl;
import com.krmzyc.Random.RandomCode;
import com.krmzyc.User.User;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.sms.v20210111.SmsClient;
import com.tencentcloudapi.sms.v20210111.models.SendSmsRequest;
import com.tencentcloudapi.sms.v20210111.models.SendSmsResponse;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*通过验证码找回密码*/

public class SendFind extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("验证码请求过来了。。。");


        String phonenumber=req.getParameter("find_usraccount");
        System.out.println(phonenumber);
        try {
            UserDao userDao=new UserDaoImpl();
            User user=userDao.checkphone(phonenumber);
//        System.out.println(user.getUsrAccount());

            if(user==null)
            {
                resp.sendRedirect("failed.jsp");
            }
            else
            {
                RandomCode randomCode=new RandomCode();
                String code=randomCode.RandomCodeCreate();
                user.setUsercode(code);
                userDao.Addcode(phonenumber,code);
                /* 必要步骤：
                 * 实例化一个认证对象，入参需要传入腾讯云账户密钥对secretId，secretKey。
                 * 这里采用的是从环境变量读取的方式，需要在环境变量中先设置这两个值。
                 * 你也可以直接在代码中写死密钥对，但是小心不要将代码复制、上传或者分享给他人，
                 * 以免泄露密钥对危及你的财产安全。
                 * CAM密匙查询: https://console.cloud.tencent.com/cam/capi*/
                Credential cred = new Credential("AKID50f3B2cuwhlbASTCeplQp0uXtymKhrkM", "MRFm1zg6E5exzlGVTAaiaBVYKp3zf5ew");

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
                SendSmsRequest smsRequest = new SendSmsRequest();


                /* 填充请求参数,这里request对象的成员变量即对应接口的入参
                 * 你可以通过官网接口文档或跳转到request对象的定义处查看请求参数的定义
                 * 基本类型的设置:
                 * 帮助链接：
                 * 短信控制台: https://console.cloud.tencent.com/smsv2
                 * sms helper: https://cloud.tencent.com/document/product/382/3773 */

                /* 短信应用ID: 短信SdkAppId在 [短信控制台] 添加应用后生成的实际SdkAppId，示例如1400006666 */
                String sdkAppId = "1400602897";
                smsRequest.setSmsSdkAppId(sdkAppId);

                /* 短信签名内容: 使用 UTF-8 编码，必须填写已审核通过的签名，签名信息可登录 [短信控制台] 查看 */
                String signName = "风林轩";
                smsRequest.setSignName(signName);

                /* 国际/港澳台短信 SenderId: 国内短信填空，默认未开通，如需开通请联系 [sms helper] */
                String senderid = "";
                smsRequest.setSenderId(senderid);

                /* 用户的 session 内容: 可以携带用户侧 ID 等上下文信息，server 会原样返回 */
                String sessionContext = "xxx";
                smsRequest.setSessionContext(sessionContext);

                /* 短信号码扩展号: 默认未开通，如需开通请联系 [sms helper] */
                String extendCode = "";
                smsRequest.setExtendCode(extendCode);

                /* 模板 ID: 必须填写已审核通过的模板 ID。模板ID可登录 [短信控制台] 查看 */
                String templateId = "1216954";
                smsRequest.setTemplateId(templateId);

                /* 下发手机号码，采用 E.164 标准，+[国家或地区码][手机号]
                 * 示例如：+8613711112222， 其中前面有一个+号 ，86为国家码，13711112222为手机号，最多不要超过200个手机号 */
                String[] phoneNumberSet = {"+86"+phonenumber};
                smsRequest.setPhoneNumberSet(phoneNumberSet);

                /* 模板参数: 若无模板参数，则设置为空 */
                String[] templateParamSet = {code};
                smsRequest.setTemplateParamSet(templateParamSet);

                /* 通过 client 对象调用 SendSms 方法发起请求。注意请求方法名与请求对象是对应的
                 * 返回的 res 是一个 SendSmsResponse 类的实例，与请求对象对应 */
                SendSmsResponse res = client.SendSms(smsRequest);

                // 输出json格式的字符串回包
                System.out.println(SendSmsResponse.toJsonString(res));

                // 也可以取出单个值，你可以通过官网接口文档或跳转到response对象的定义处查看返回字段的定义
                System.out.println(res.getRequestId());

            }

        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();

        }

    }
}
