import { Component, OnInit ,Output, Input, EventEmitter } from '@angular/core';
import * as g from "./../../../../type";
@Component({
  selector: 'app-user-info',
  templateUrl: './user-info.component.html',
  styleUrls: ['./user-info.component.css']
})
export class UserInfoComponent implements OnInit {
  @Input() userId:any=''
  @Output() close=new EventEmitter();
  userInfo:any={};
  constructor() { }

  ngOnInit() {
  }
  ngOnChanges(){
    if(this.userId!=''){
      this.getUserInfo();
    }
  }
  //获取用户信息
  getUserInfo(){
    let url=g.namespace+"/gpsys/user/getUserInfoById"
    let send={
      userId:this.userId
    }
    $.ajax(url,{
      data:send,
      dataType:"jsonp",
      success:json=>{
        if(json.stage==1){
          this.userInfo=json.data;
          console.log(this.userInfo)
        }
        else{
          alert("服务器错误："+json.msg)
        }
      }
    })
  }
}
