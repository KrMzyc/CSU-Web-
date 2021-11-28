package com.krmzyc.Dao;

import com.krmzyc.User.User;

import java.sql.SQLException;

/*用户操作接口*/

public interface UserDao {
    public User getUserByACandPSW(String useraccout, String userpassword) throws ClassNotFoundException, SQLException;
    public void AddUser(String usraccount, String userpassword,String phonid);
    public void ChangePSW(String userpsw, String usernewpsw, String useraccount);
    public void SendMS(String phonenumber);
    public User checkphone(String phonenumber);
    public void Addcode(String phonenumber, String code);
    public String CheckCode(String phonenumber);
    public void Findpsw(String phonenumber, String newpsw);

}
