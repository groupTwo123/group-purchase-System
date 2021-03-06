package com.felix.grouppurchase.service.impl;

import com.felix.grouppurchase.mapper.UserMapper;
import com.felix.grouppurchase.model.User;
import com.felix.grouppurchase.service.IUserService;
import com.felix.grouppurchase.util.ErrorCodeDesc;
import com.felix.grouppurchase.util.GetSMS;
import com.felix.grouppurchase.util.JsonTransfer;
import jdk.internal.dynalink.linker.LinkerServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;

/**
 * @Date: 2018/11/22 16:03
 * @Author: fangyong
 */
@Service
public class UserServiceImpl implements IUserService{

    private final static transient Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    UserMapper userMapper;

    @Override
    public String registerUser(String id, String userName, String gender, String birth, String phone, String email, String password, String area, String type ,String callback) {
        String result="";
        JsonTransfer s=new JsonTransfer();
        try {
            User user=userMapper.checkIdHasRegist(id);
            if(user==null){
                userMapper.insertRegisterMessage(id, userName, gender, birth, phone, email, password, area, type);
                result = s.result(1, "注册成功", user, callback);
            }
            else{
                result = s.result(0, "登录名已被注册", "", callback);
            }

        } catch (Exception e) {
            e.printStackTrace();
            result = s.result(0, e.toString(), "", callback);
        }
        return result;

    }

    @Override
    public void registerNormal(String id, String userName, String gender, String birth, String phone, String email, String password, String area, String type) {
        userMapper.insertRegisterNormalMessage(id, userName, gender, birth, phone, email, password, area, type);
    }

    @Override
    public String login(HttpServletRequest request,String id, String password,  String isOut,String callback) {
        //isOut 是否注销
        String result = "";
        JsonTransfer s = new JsonTransfer();
        HttpSession session = request.getSession();
        if(isOut.equals("1")){
            session.setAttribute("sessionLoginId",null);
            session.setAttribute("sessionLoginPassword",null);
            result = s.result(1,"已注销","",callback);
        }
        else{
            if (id.equals("") && password.equals("")) {
                if (session.getAttribute("sessionLoginId")==null || session.getAttribute("sessionLoginPassword") == null){
                    result = s.result(0,"用户未登录","",callback);
                    return result;
                }else {
                    id = session.getAttribute("sessionLoginId").toString();
                    password = session.getAttribute("sessionLoginPassword").toString();
                }
            }
            session.setAttribute("sessionLoginId",id);
            session.setAttribute("sessionLoginPassword",password);
            User user =  userMapper.userLogin(id, password);
            if (user == null){
                result = s.result(0,"用户不存在或者账号密码输入错误","",callback);
                return result;
            }
            HashMap<String, Object> map = new HashMap<>();
            map.put("username",user.getUserName());
            map.put("id",id);
            map.put("type",user.getType());
            result = s.result(1,"",map,callback);
        }

        return result;
    }

    @Override
    public String checkPhoneLogin(String phone, String callback) {
        User user =  userMapper.checkPhone(phone);
        JsonTransfer s = new JsonTransfer();
        if (user == null){
            String result1 = s.result(0,ErrorCodeDesc.USER_NOEXIST,"",callback);
            return result1;
        }
        return GetSMS.getMssage(phone,callback);
    }

    @Override
    public String checkPhoneRegister(String phone, String callback) {
        User user =  userMapper.checkPhone(phone);
        JsonTransfer s = new JsonTransfer();
        if (user == null){
            return GetSMS.getMssage(phone,callback);
        }
        String result1 = s.result(0,ErrorCodeDesc.USER_EXIST,"",callback);
        return result1;
    }

    @Override
    public String updateUserMessage(String id, String userName, String gender, String birth, String phone, String email,  String area, String callback) {
        userMapper.updateUserMessage(id,userName,gender,birth,phone,email,area);
        JsonTransfer s = new JsonTransfer();
        String result = s.result(1,"修改成功","",callback);
        return result;
    }

    @Override
    public String checkIdWithPhone( String phone, String callback) {
        User user = userMapper.checkIdWithPhone(phone);
        JsonTransfer s = new JsonTransfer();
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("id",user.getId());
        String result2 = s.result(1,"匹配成功",map,callback);
        return result2;
    }

    @Override
    public String getUsernameByPhone(String phone, String callback) {
        User user = userMapper.getUsernameByPhone(phone);
        HashMap<Object, Object> map = new HashMap<>();
        map.put("id",user.getId());
        map.put("userName",user.getUserName());
        JsonTransfer s = new JsonTransfer();
        if (user.getPhone() == null){
            String result1 = s.result(0,ErrorCodeDesc.USER_NOEXIST,"",callback);
            return result1;
        }
        String result2 = s.result(1,"登录成功",map,callback);
        return result2;

    }

    @Override
    public String getUserInfoById(String userId, String callback) {
       User user=userMapper.getUserInfoById(userId);
       JsonTransfer s = new JsonTransfer();
        String result = s.result(1,"查询成功",user,callback);
        return result;
    }

    @Override
    public String getAllUserInfo(String callback) {
        JsonTransfer s = new JsonTransfer();
        String result="";
        try {
            List<User> userList= userMapper.getAllUserInfo();
            result=s.result(1,"",userList,callback);
        }catch (Exception e){
            result=s.result(0,e.toString(),"",callback);
        }
        return result;
    }

    @Override
    public String addVancy(String user,Float money, String callback) {
        JsonTransfer s=new JsonTransfer();
        String result="";
        try{
            userMapper.updateAccountData(user,money);
            result=s.result(1,"","",callback);
        }catch (Exception e){
            result=s.result(1,e.toString(),"",callback);

        }
        return result;
    }

    @Override
    public String resetPassword(String id, String password, String callback) {
        userMapper.resetPassword(id, password);
        JsonTransfer s = new JsonTransfer();
        HashMap<String, String> map = new HashMap<>();
        map.put("id",id);
        map.put("password",password);
        try {
            String result1 = s.result(1,"修改成功","map",callback);
            return result1;
        }catch (Exception e){
            e.printStackTrace();
            String result2 = s.result(0,"系统异常，修改失败",map,callback);
            return result2;
        }
    }

}