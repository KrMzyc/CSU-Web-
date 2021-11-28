// 登录窗口tab切换js

var id=document.getElementById("id");
var phone=document.getElementById("phone");
var acc=document.getElementById("acc");
var pho=document.getElementById("pho");

id.onclick=function()        //点击账号登录时
{
    this.className='active';
    phone.className='login2'
    acc.style.display="block";
    pho.style.display="none";
}

phone.onclick=function()          //点击验证码登录时
{
    this.className='active';
    id.className='login1';
    acc.style.display="none";
    pho.style.display="block";
}


//获取验证码按钮
var getcode = document.getElementById("getcodebt");
var num=60;        //定时器

getcode.onclick=function()
{

    var phonenumber=document.getElementById("phonenumber").value;
    console.log(phonenumber);

    var xmlhttp;
    if(window.XMLHttpRequest)
    {
        xmlhttp=new XMLHttpRequest();
    }else
    {
        xmlhttp=new ActiveXObject("Microsoft XMLHTTP");
    }

    xmlhttp.open('POST',"http://localhost:7070/Web_war_exploded/sendms?phonenumber="+phonenumber,true);
    xmlhttp.send();

    xmlhttp.onreadystatechange=function ()
    {
        if(xmlhttp.readyState==4)
        {
            if(xmlhttp.status>=200 && xmlhttp.status<300 || xmlhttp.status==304)
            {
                console.log("接收数据")
            }
            else
            {
                console.log("没有接收数据")
            }
        }
    }

    getcode.disabled=true;
    var time=setInterval(autoplay,1000);
    function autoplay()
    {
        num--;
        if(num>=0){
            getcode.value=num+"s后重新发送"
        }
        else {
            getcode.value="重新发送"
            getcode.disabled=false;
            clearInterval(time);           //关闭定时器
            num=60;
        }
    }
}

var findcodebt =document.getElementById("findcodebt");
var num2=60;

findcodebt.onclick=function ()
{
    var phonenumber=document.getElementById("find_usraccount").value;
    console.log(phonenumber);

    var xmlhttp;
    if(window.XMLHttpRequest)
    {
        xmlhttp=new XMLHttpRequest();
    }else
    {
        xmlhttp=new ActiveXObject("Microsoft XMLHTTP");
    }

    xmlhttp.open('POST',"http://localhost:7070/Web_war_exploded/findpsw?phonenumber="+phonenumber,true);
    xmlhttp.send();

    xmlhttp.onreadystatechange=function ()
    {
        if(xmlhttp.readyState==4)
        {
            if(xmlhttp.status>=200 && xmlhttp.status<300 || xmlhttp.status==304)
            {
                console.log("接收数据")
            }
            else
            {
                console.log("没有接收数据")
            }
        }
    }

    findcodebt.disabled=true;
    var time=setInterval(autoplay,1000);
    function autoplay()
    {
        num2--;
        if(num2>=0){
            findcodebt.value=num2+"s后重新发送"
        }
        else {
            findcodebt.value="重新发送"
            findcodebt.disabled=false;
            clearInterval(time);           //关闭定时器
            num2=60;
        }
    }
}
