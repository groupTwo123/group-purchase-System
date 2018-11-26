import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  loginWay:any='mobile';  //登陆方式
  id:any;   //用户名
  password:any;//密码
  constructor() { }

  ngOnInit() {
  }
  //改变登陆方式
  changeLoginWay(way){
    this.loginWay=way;
  }
}
