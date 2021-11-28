package com.krmzyc.Random;

/*
生成六位数验证码
*/

public class RandomCode {
    public String RandomCodeCreate()
    {
        String code=(int)((Math.random()*9+1)*100000)+"";
        return code;
    }
}
