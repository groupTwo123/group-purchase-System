import { Component, OnInit } from '@angular/core';

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
  birth:any='' ;        //出生年月日yyyy-mm-dd
  area:any='' ;         //所在地区
  isEamilCorrect:boolean=false; //判断email格式
  isPhoneCorrect:boolean=false; //判断手机正确
  year:any='' ;
  month:any='' ;
  day:any='' ;
  repassword:any='' ;
  checkCode:any="";
  yearObj:any=[
    {
      'key':'1990','value':'1990'
    },
    {
      'key':'1991','value':'1991'
    },
    {
      'key':'1992','value':'1992'
    },
    {
      'key':'1993','value':'1993'
    },
    {
      'key':'1994','value':'1994'
    }
    ];
  monthObj:any=[
  { 'key':'01','value':'一月'},
  { 'key':'02','value':'二月'},
  { 'key':'03','value':'三月'},
  { 'key':'04','value':'四月'},
  { 'key':'05','value':'五月'},
  { 'key':'06','value':'六月'},
  { 'key':'07','value':'七月'},
  { 'key':'08','value':'八月'},
  { 'key':'09','value':'九月'},
  { 'key':'10','value':'十月'},
  { 'key':'11','value':'十一月'},
  { 'key':'12','value':'十二月'},
];
  dayObj:any=[
    { 'key':'01'},
    { 'key':'02'},
    { 'key':'03'},
    { 'key':'04'},
    { 'key':'05'},
    { 'key':'06'},
    { 'key':'07'},
    { 'key':'08'},
    { 'key':'09'},
    { 'key':'10'},
    { 'key':'11'},
    { 'key':'12'},
    { 'key':'13'},
    { 'key':'14'},
    { 'key':'15'},
    { 'key':'16'},
    { 'key':'17'},
    { 'key':'18'},
    { 'key':'19'},
    { 'key':'20'},
    { 'key':'21'},
    { 'key':'22'},
    { 'key':'23'},
    { 'key':'24'},
    { 'key':'25'},
    { 'key':'26'},
    { 'key':'27'},
    { 'key':'28'},
    { 'key':'29'},
    { 'key':'30'},
    { 'key':'31'},
  ]
  areaObj:any=[
    {'province':'广东省','city':[{'cityname':'佛山市','district':[{'districtName':'禅城区'},{'districtName':'南海区'}]},{'cityname':'广州市','district':[{'districtName':'天河区'},{'districtName':'荔湾区'}]}]},
  ]
  province:any;
  city:any;
  district:any;
  privinceObj:any;
  cityObj:any;
  districtObj:any;
  emailCheck:any= /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;  //邮箱验证正则
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

  //选中省
  choseProvince(province){
    this.cityObj=[];
    for(let item of this.areaObj){
      if(item.province==province){
        for(let item1 of item.city){
          this.cityObj.push(item1);
        }
      }
    }
  }

  //验证手机
  getCode(){
    if(this.phone.length!=11){
      alert("请输入正确的手机号码");
      return;
    }
    let url="http://localhost:8080/gpsys/sendSMS/register"
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
      }
    })
  }
  //选中市
  choseCity(city){
    this.districtObj=[];
    for(let item of this.areaObj){
      if(item.province==this.province){
        for(let item1 of item.city){
          if(item1.cityname==city){
            for(let item2 of item1.district){
              // console.log(item2)
              this.districtObj.push(item2)
            }
          }
        }
      }
    }
  }

  //提交注册信息
  submitData(){
    if(!this.emailCheck.test(this.email))
    {
      alert('提示\n\n请输入有效的E_mail！');
      return;
    }

    if(this.id==''|| this.username ==''|| this.gender==''|| this.email==''|| this.password==''|| this.year==''||this.day==''||this.month==''|| this.province==''){

      alert('请输入必要信息');
      return;
    }
    else{
      if(this.repassword==this.password){
        let url="http://localhost:8080/gpsys/user/register";
        let send={
          id:this.id,
          username:this.username,
          gender:this.gender,
          phone:this.phone,
          email:this.email,
          password:this.password,
          type:this.type,
          birth:this.year+'-'+this.month+'-'+this.day,
          area:this.province+'-'+this.city+'-'+this.district,
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

}
