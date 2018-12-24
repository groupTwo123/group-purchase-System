import { Component, OnInit } from '@angular/core';
import * as g from'./../../type';
@Component({
  selector: 'app-forget-password',
  templateUrl: './forget-password.component.html',
  styleUrls: ['./forget-password.component.css']
})
export class ForgetPasswordComponent implements OnInit {
  router:any=window as any;
  step:number=0;
  checkCode:any='';
  code:any='';
  phone:any='';
  id:any='';
  password:any='';
  repassword:any='';
  codeGetting:boolean=false;//获取验证码中
  time:number=120;
  passwordLevelObj=g.passwordLevel;
  passwordLevel="";
  constructor() { }

  ngOnInit() {
    this.step=0;
  }
  //下一步操作
  nextStep(){
    if(this.checkCode!=this.code){
      alert("验证码不正确");
      return;
    }
    this.step++;
  }

  //获取验证码
  checkIsHasId(){
    if(this.phone.length!=11||this.phone==''){
      alert("请输入正确手机号码");
      return;
    }
    this.codeGetting=true;
    this.time=120;
    this.settimeDown()
    let url=g.namespace+"/gpsys/user/confirmMessage";
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
          console.log(json.data);
          this.id=json.data.id;
          this.getPhoneCode();
        }
        else {
          alert("错误"+json.msg)
        }
      }
    })

  }
  //获取验证码
  getPhoneCode(){
    let url=g.namespace+"/gpsys/sendSMS/sendMessage/forgetPassword";
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
          console.log(json.data);
          this.checkCode=json.data.code;

        }
        else {
          alert("错误"+json.msg)
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
    let url=g.namespace+"/gpsys/user/resetPassword";
    let send={
      id:this.id,
      password:this.password
    }
    $.ajax(url,{
      data:send,
      dataType:"jsonp",
      success:json=>{
        if(json.stage==1){
          alert("修改成功");
          window.location.href='htttp://localhost:4200/login';
        }
        else{
          alert("修改失败");
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
  pswChange(){

    if(this.password.match(this.passwordLevelObj['weak'])){
      this.passwordLevel='weak'
    }
    else if(this.password.match(this.passwordLevelObj['middle'])){
      this.passwordLevel='middle'
    }
    else if(this.password.match(this.passwordLevelObj['strong'])){
      this.passwordLevel='strong'
    }
  }
}
