import { Component, OnInit } from '@angular/core';
import * as g from "../type";
@Component({
  selector: 'app-admin-login',
  templateUrl: './admin-login.component.html',
  styleUrls: ['./admin-login.component.css']
})
export class AdminLoginComponent implements OnInit {
  adminId:any='';
  adminPassword:any=''
  homePageShow:boolean=false
  constructor() { }

  ngOnInit() {
    document.getElementById("loginBox").style.height=(document.documentElement.scrollHeight).toString()+'px';
    document.getElementById("loginBox").style.width=(document.documentElement.scrollWidth).toString()+'px';
  }
  //登录验证
  login(){
    if(this.adminId==''||this.adminPassword==""){
      alert("请填入必要信息")
      return;
    }
    let url=g.namespace+"/gpsys/admin/adminLogin";
    let send={
      adminId:this.adminId,
      adminPassword:this.adminPassword
    }
    $.ajax(url,{
      data:send,
      dataType:"jsonp",
      success:json=>{
        if(json.stage==1){
          alert("登录成功");
          this.homePageShow=true
        }
        else{
          alert(json.msg)
        }
      }
    })
  }
}
