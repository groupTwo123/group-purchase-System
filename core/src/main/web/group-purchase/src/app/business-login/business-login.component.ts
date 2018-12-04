import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-business-login',
  templateUrl: './business-login.component.html',
  styleUrls: ['./business-login.component.css']
})
export class BusinessLoginComponent implements OnInit {
  loginWay:any='0';// 0为账号密码登录 1为手机登录
  constructor() { }

  ngOnInit() {
    document.getElementById("loginBox").style.height=(document.body.scrollHeight).toString()+'px';
    document.getElementById("loginBox").style.width=(document.body.scrollWidth).toString()+'px';

  }
  //切换登录方式
  changeLoginWay(way){
    this.loginWay=way;
  }
}
