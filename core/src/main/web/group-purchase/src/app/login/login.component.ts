import { Component, OnInit } from '@angular/core';
import * as g from './../type'
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  loginWay:any='mobile';  //登陆方式
  id:any='';   //用户名
  password:any='';//密码
  code:any=''; //验证码
  phone:any=''; //手机号码
  username:any="";
  userType:number=0;      //0是会员，1是商家
  homePageShow:boolean=false; //主页展示
  businessShow:boolean=false;
  checkCode:any=''; //获取后台验证码
  codeGetting:boolean=false;//获取验证码中
  time:number=120;
  constructor() { }

  ngOnInit() {
    this.checkLoginSession()
    this.homePageShow=false;
    this.username="";
    this.password='';
    this.id='';
    this.businessShow=false;
    // alert(document.getElementById("bgBox").style.height);
    // document.getElementById("login").style.width=(document.body.scrollWidth).toString()+'px';


  }
  //查询session是否有登录信息
  checkLoginSession(){
    let url=g.namespace+"/gpsys/user/login";
    let send={
      id:"",
      password:"",
      isOut:"0"
    }
    $.ajax(url,{
      data:send,
      dataType:'jsonp',
      success:json=>{
        console.log(json)
        if(json.stage==1){
          this.username=json.data.username;
          this.userType=json.data.type;
          this.homePageShow=true;
        }
      }
    })
  }

  //改变登陆方式
  changeLoginWay(way){
    this.loginWay=way;
  }

  //提交登录
  submitData(){
    if(this.loginWay=='Id'){
      if(this.id==''){
        alert("请输入账号");
        return;
      }
      else{
        if(this.password==''){
          alert("请输入密码");
          return;
        }
        else{
          let url="http://localhost:8080/gpsys/user/login";
          let send={
            id:this.id,
            password:this.password,
            isOut:"0"
          };
          $.ajax(url,{
            data:send,
            dataType:"jsonp",
            jsonp:"callback",
            type:"GET",
            success:json=>{
              if(json.stage=='1'){
                alert('登录成功');
                this.username=json.data.username;
                this.userType=json.data.type;
                this.homePageShow=true;

              }
              else{
                alert("登录失败："+json.msg);
              }
            }
          }
      )

    }

  }

}
    else{
      if(this.checkCode==this.code&&this.code!=''){
        let url="http://localhost:8080/gpsys/user/getUsernameByPhone";
        let send={
          phone:this.phone
        }
        $.ajax(url,{
          data:send,
          dataType:"jsonp",
          jsonp:"callback",
          type:"POST",
          success:json=>{
            if(json.stage==1){
              // alert("登录成功")
              this.id=json.data.id;
              this.username=json.data.userName;
              this.password=json.data.password;
              let url="http://localhost:8080/gpsys/user/login";
              let send={
                id:this.id,
                password:this.password,
                isOut:"0"
              };
              $.ajax(url,{
                  data:send,
                  dataType:"jsonp",
                  jsonp:"callback",
                  type:"GET",
                  success:json=>{
                    if(json.stage=='1'){
                      alert('登录成功');
                      this.homePageShow=true;

                    }
                    else{
                      alert("登录失败："+json.msg);
                    }
                  }
                }
              )

            }
            else{
              alert("登录失败:"+json.msg);
            }
          }
        })
      }
      else {
        alert("验证码不正确");
        return;
      }

    }
}
  //获取验证码
  getCode(){
    if(this.phone.length!=11){
      alert("请输入合法手机号码");
      return;
    }
    this.codeGetting=true;
    this.time=120;
    this.settimeDown()
    let url="http://localhost:8080/gpsys/sendSMS/sendMessage/login";
    let send={
      phone:this.phone,
    }
    $.ajax(url,{
      data:send,
      dataType:"jsonp",
      jsonp:"callback",
      type:"POST",
      success:json=>{
        if(json.stage==1){
          console.log(json);
          this.checkCode=json.data.code;
        }
        else{
          alert(json.msg);
          this.codeGetting=false;
        }

      }
    })
  }
  //倒计时
  settimeDown(){
    var time1=setInterval(json=>{
      if(this.time!=0){
        this.time--;
      }
      else{
        clearInterval(time1);
        this.codeGetting=false;
      }
    },1000)
  }
}
