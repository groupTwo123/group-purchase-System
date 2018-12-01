import { Component, OnInit } from '@angular/core';

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
  username:any="";
  userType:number=0;      //0是会员，1是商家
  homePageShow:boolean=false; //主页展示
  businessShow:boolean=false;
  constructor() { }

  ngOnInit() {
    this.username="";
    this.password='';
    this.id='';
    this.homePageShow=false;
    this.businessShow=false;
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
            password:this.password
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
                if(this.userType==0){
                  this.homePageShow=true;
                }
                else if(this.userType==1){
                  this.businessShow==true;
                }

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
}
  //获取验证码
  getCode(){
    let url="http://localhost:8080/gpsys/sendSMS/sendMessage";
    let send={
      to:"13420120369",
    }
    $.ajax(url,{
      data:send,
      dataType:"jsonp",
      jsonp:"callback",
      type:"POST",
      success:json=>{
        console.log(json)
      }
    })
  }
}
