import { Component, OnInit } from '@angular/core';
import * as g from'./../../type';
@Component({
  selector: 'app-business-register',
  templateUrl: './business-register.component.html',
  styleUrls: ['./business-register.component.css']
})
export class BusinessRegisterComponent implements OnInit {
  step:number=0;
  phone:any='';			//手机
  sellerId:any='';		//登录名
  sellerNickName:any='';//昵称
  sellerName:any='';	//真实姓名
  sellerPassword:any='';	//登录密码
  sellerIdentityId:any='';	//身份证
  sellerEmail:any='';		//邮箱
  storeName:any='';			//店铺名称
  storeArea:any='';			//所在地区
  emailCheck:any= /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;  //邮箱验证正则
  code:any="";
  checkCode:any="";
  constructor() { }

  ngOnInit() {
    this.step=0;
    document.getElementById("registerBox").style.height=(document.documentElement.scrollHeight).toString()+'px';
    document.getElementById("registerBox").style.width=(document.documentElement.scrollWidth).toString()+'px';
    document.getElementById("registerBox").style.backgroundImage="url('../../../assets/background.png')";


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
  getCode(){
    if(this.phone.length!=11){
      alert("请输入正确的手机号码");
      return;
    }
    let url=g.namespace+"/gpsys/sendSMS/sendMessage/sellerRegister";
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
				this.checkCode=json.data.code;

			}
			else{
				alert(json.msg)
			}
		}
	})
  }

  //提交填写信息
  submitRegisterData(){
	  if(this.sellerId==''||this.sellerNickName==""||this.sellerName==""||this.sellerPassword==""||this.sellerIdentityId==""||this.sellerEmail==""||this.storeName==""||this.storeArea==""){
		  alert("请填写完成信息");
		  return;
	  }
	  else{
		  if(!this.emailCheck.test(this.sellerEmail)){
        alert('提示\n\n请输入有效的E_mail！');
        return;
		  }
		  if(this.sellerIdentityId.length!=18){
		    alert("身份证格式错误");
		    return;
      }
	  }
	  let url=g.namespace+"/gpsys/seller/register";
	  let send={
		  sellerId:this.sellerId,
		  sellerNickName:this.sellerNickName,
		  sellerName:this.sellerName,
		  sellerPassword:this.sellerPassword,
		  sellerIdentityId:this.sellerIdentityId,
		  sellerPhone:this.phone,
		  sellerEmail:this.sellerEmail,
		  storeName:this.storeName,
		  storeArea:this.storeArea,
	  }
	  $.ajax(url,{
		data:send,
		dataType:"jsonp",
		jsonp:"callback",
		success:json=>{
			if(json.stage==1){
				alert("注册成功");
				this.nextStepFun();

			}
			else{
				alert("登录失败:"+json.msg);
			}
		}
	  })
  }

  //返回上一步操作
  beforeStep(){
    if(confirm("慎重，此操作会导致数据丢失")){
      this.step=0;
      this.phone='';
      this.sellerId='';
      this.sellerNickName='';
      this.sellerName='';
      this.sellerPassword='';
      this.sellerIdentityId='';
      this.sellerEmail='';
      this.storeName='';
      this.storeArea='';
    }
    else{
      return;
    }
  }
}
