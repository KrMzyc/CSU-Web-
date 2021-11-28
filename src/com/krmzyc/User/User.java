package com.krmzyc.User;
/*
创建用户类，用于存储用户的信息，便于与数据库中的用户信息进行比对
*/


public class User {
    private String UserId;
    private String UsrAccount;
    private String UsrPassword;
    private String Usercode;
    public String getUsercode() {
        return Usercode;
    }

    public void setUsercode(String usercode) {
        Usercode = usercode;
    }
    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getUsrAccount() {
        return UsrAccount;
    }

    public void setUsrAccount(String usrAccount) {
        UsrAccount = usrAccount;
    }

    public String getUsrPassword() {
        return UsrPassword;
    }

    public void setUsrPassword(String usrPassword) {
        UsrPassword = usrPassword;
    }

    @Override
    public String toString() {
        return "User{" +
                "UserId=" + UserId +
                ", UsrAccount='" + UsrAccount + '\'' +
                ", UsrPassword='" + UsrPassword + '\'' +
                '}';
    }
}
