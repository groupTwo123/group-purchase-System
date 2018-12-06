import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-b-forget-password',
  templateUrl: './b-forget-password.component.html',
  styleUrls: ['./b-forget-password.component.css']
})
export class BForgetPasswordComponent implements OnInit {
  step:number=0;
  phone:any='';			//手机
  code:any="";
  checkCode:any="";
  id:any;
  password:any='';
  repassword:any='';
  constructor() { }

  ngOnInit() {
    this.step=0;
    document.getElementById("forgetBox").style.height=(document.documentElement.scrollHeight).toString()+'px';
    document.getElementById("forgetBox").style.width=(document.documentElement.scrollWidth).toString()+'px';

  }
  //下一步点击操作
  nextStepFun(){
    if(this.checkCode!=this.code||this.code==''){
      alert("验证码不正确");
      return;
    }
    this.step++;
  }

  //手机检测是否易被注册
  isPhoneRegister(){
    if(this.phone.length!=11){
      alert("请输入正确的手机号码");
      return;
    }
    let url="http://localhost:8080/gpsys/seller/checkPhoneExist";
    let send={
      sellerPhone:this.phone
    }
    $.ajax(url,{
      data:send,
      dataType:"jsonp",
      jsonp:"callback",
      success:json=>{
        if(json.stage==1){
          console.log(json)
          this.id=json.data.id;
          this.getCode();
        }
        else{
          alert(json.msg)
        }
      }
    })
  }
  //获取验证码
  getCode(){
    let url="http://localhost:8080/gpsys/sendSMS/sendMessage/forgetPassword";
    let send={
      phone:this.phone
    }
    $.ajax(url,{
      data:send,
      dataType:"jsonp",
      jsonp:"callback",
      success:json=>{
        if(json.stage==1){
          console.log(json)
          this.checkCode=json.data.code
        }
        else{
          alert(json.msg)
        }
      }
    })
  }
  //提交密码
  submitPassword(){
    if(this.password!=this.repassword){
      alert("两次输入不一致");
      return;
    }
    let url="http://localhost:8080/gpsys/seller/resetPassword";
    let send={
      sellerId:this.id,
      sellerPassword:this.password
    }
    $.ajax(url,{
      data:send,
      dataType:"jsonp",
      success:json=>{
        if(json.stage==1){
          alert("修改成功");
          this.nextStepFun()
        }
        else{
          alert("修改失败");
        }
      }
    })
  }
}
