import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import * as g from'./../../type';
@Component({
  selector: 'app-my-rebate',
  templateUrl: './my-rebate.component.html',
  styleUrls: ['./my-rebate.component.css']
})
export class MyRebateComponent implements OnInit {
  @Input() userId:any="";
  @Input() myRebateNum:number=0;


  @Output()
  bindChange = new EventEmitter();
  userInfo:any={};
  constructor() { }

  ngOnInit() {
    $("#templateBox").show();

  }
  ngOnChanges(){
    this.getUserInfo();
    $("#templateBox").show();
  }
  //获取用户信息
  getUserInfo(){
    let url=g.namespace+"/gpsys/user/getUserInfoById";
    let send={
      userId:"1101"
    }
    $.ajax(url,{
      data:send,
      dataType:"jsonp",
      success:json=>{
        if(json.stage==1){
          this.userInfo=json.data;
          console.log(this.userInfo)
          $("body").bind("mousedown", this.onBodyMouseDown);
        }
        else{
          alert("服务器错误："+json.msg);
        }
      }
    })
  }
  //充钱
  addMoney(){
    this.bindChange.emit();
  }
  onBodyMouseDown(event) {
    if ( !($(event.target).parents("#templateBox").length>0)) {
      $("#templateBox").hide();
      $("body").unbind("mousedown",this.onBodyMouseDown);
    }
  }

}
