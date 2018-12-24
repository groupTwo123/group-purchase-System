import { Component, OnInit } from '@angular/core';
import * as g from'./../../type';
@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  $:any=(window as any).$;
  step:number=0;
  id:any=''  ;     //登录账号
  code:any ;         //验证码
  username:any='' ;     //用户名
  gender:any='男';       //性别
  phone:any='' ;        //手机
  email:any='' ;        //邮箱
  password:any='' ;         //密码
  type:number=0;         //账号类型
  birth:any=moment().format('YYYY-MM-DD ');        //出生年月日yyyy-mm-dd
  area:any={} ;         //所在地区
  isEamilCorrect:boolean=false; //判断email格式
  isPhoneCorrect:boolean=false; //判断手机正确
  repassword:any='' ;
  checkCode:any="";
  codeGetting:boolean=false;//获取验证码中
  time:number=120;
  passwordLevelObj=g.passwordLevel;
  passwordLevel="";
  emailCheck:any= g.emailCheck;  //邮箱验证正则

  constructor() { }

  ngOnInit() {
    this.step=0;
  }

  //下一步
  nextStep(){
    if(this.checkCode!=this.code||this.code==""){
      alert('验证码不正确');
      return;
    }
    this.step=this.step+1;
  }


  //验证手机
  getCode(){
    if(this.phone.length!=11){
      alert("请输入正确的手机号码");
      return;
    }
    this.codeGetting=true;
    this.time=120;
    this.settimeDown()
    let url=g.namespace+"/gpsys/sendSMS/sendMessage/register"
    let send={
      phone:this.phone
    }
    $.ajax(url,{
      data:send,
      dataType:"jsonp",
      json:"callback",
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


  //提交注册信息
  submitData(){
    if(!this.emailCheck.test(this.email))
    {
      alert('提示\n\n请输入有效的E_mail！');
      return;
    }

    if(this.id==''|| this.username ==''|| this.gender==''|| this.email==''|| this.password==''){

      alert('请输入必要信息');
      return;
    }
    else{
      if(this.repassword==this.password){
        let url=g.namespace+"/gpsys/user/register";
        let send={
          id:this.id,
          userName:this.username,
          gender:this.gender,
          phone:this.phone,
          email:this.email,
          password:this.password,
          type:this.type,
          birth:this.birth,
          area:this.area.country+','+this.area.province+','+this.area.city
        };
        console.log(send);
        this.$.ajax(url,{
          data:send,
          dataType:"jsonp",
          jsonp:"callback",
          type:"GET",
          success:json=>{
            if(json.stage=='1'){
              this.nextStep();
            }
            else{
              alert("注册失败");
            }
          }
        })
      }
      else{
        alert('两次填写密码不相同')
        return;
      }
    }
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
  showArea(){
    // console.log(this.area)
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
  showDataChose(){
    // alert(this.birth)
  }
}
