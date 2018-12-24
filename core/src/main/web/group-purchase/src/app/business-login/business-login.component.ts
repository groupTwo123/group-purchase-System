import { Component, OnInit } from '@angular/core';
import * as g from'./../type';
@Component({
  selector: 'app-business-login',
  templateUrl: './business-login.component.html',
  styleUrls: ['./business-login.component.css']
})
export class BusinessLoginComponent implements OnInit {
  loginWay:any='0';// 0为账号密码登录 1为手机登录
  id:any='';
  password:any='';
  username:any='';
  nickName:any='';
  phone:any='';
  code:any='';
  checkCode:any='';
  business_homePage:boolean=false;
  codeGetting:boolean=false;//获取验证码中
  time:number=120;
  constructor() { }

  ngOnInit() {
    setTimeout(function () {
      document.getElementById("loginBox").style.height=(document.documentElement.scrollHeight).toString()+'px';
      document.getElementById("loginBox").style.width=(document.documentElement.scrollWidth).toString()+'px';

    },100)

  }
  //切换登录方式
  changeLoginWay(way){
    this.loginWay=way;
  }

  //检测手机是否易被注册
  IsHasPhone(){
	  if(this.phone.length!=11||this.phone==''){
		  alert("请输入正确的手机");
		  return;
	  }
	  let url=g.namespace+"/gpsys/sendSMS/sendMessage/sellerLogin";
	  let send={
		  sellerPhone:this.phone
	  }
	  $.ajax(url,{
		  data:send,
		  dataType:"jsonp",
		  jsonp:"callback",
		  success:json=>{
			  if(json.stage==1){
				console.log(json);
				this.id=json.data.id;
				this.nickName=json.data.username;
				this.getCode();
			  }
			  else{
				alert(json.msg+",请先注册");
			  }
		  }
	  })

  }
  //获取验证码
  getCode(){
	  let url=g.namespace+"/gpsys/sendSMS/sendMessage/sendCode";
	  let send={
		  phone:this.phone
	  }
	  $.ajax(url,{
		  data:send,
		  dataType:"jsonp",
		  jsonp:"callback",
		  success:json=>{
			  if(json.stage==1){
				  this.checkCode=json.data.code;
			  }
			  else{
				  alert(json.msg);
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
  //提交登录
  submitData(){
	  if(this.loginWay==0){
		  if(this.password==''||this.id==""){
			  alert("请输入正确账号或者密码");
			  return;
		  }
		  let url=g.namespace+"/gpsys/seller/login";
		  let send={
			  sellerId:this.id,
			  sellerPassword:this.password
		  };
		  $.ajax(url,{
			  data:send,
			  dataType:"jsonp",
			  jsonp:"callback",
			  success:json=>{
				  if(json.stage==1){
					alert("登录成功");
					console.log(json);
					this.nickName=json.data.sellerNickname;
					this.business_homePage=true;
				  }
				  else{
					alert("登录失败："+json.msg);
				  }
			  }
		  })
	  }
	  else{
		  if(this.checkCode!=this.code||this.code==''){
			  alert("请输入正确验证码");
			  return;
		  }
		  alert("登录成功");
		  this.business_homePage=true;

	  }
  }
}
