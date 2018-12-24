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
  isSearchLoading:boolean=false;
  addPrice:number=0
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
      userId:this.userId
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
    this.goToPay()
  }
  onBodyMouseDown(event) {
    if ( !($(event.target).parents("#templateBox").length>0)) {
      $("#templateBox").hide();
      $("body").unbind("mousedown",this.onBodyMouseDown);
    }
  }

  //结算
  goToPay(){
    setTimeout(json=>{
      let url=g.namespace+"/gpsys/order/getPaySession";
      $.ajax(url,{
        data:{stage:"-1"},
        dataType:'jsonp',
        success:json=>{
        }
      })
    },900000)
    this.isSearchLoading=true;
    window.open(g.namespace+"/gpsys/order/alipayToOrder?money="+this.addPrice);
    var get=setInterval(json=>{
      let url=g.namespace+"/gpsys/order/getPaySession";
      $.ajax(url,{
        data:{stage:"0"},
        dataType:'jsonp',
        success:json=>{
          if(json.stage==1){
            console.log(json.data.paySessionId)
            if(json.data.paySessionId=='1'){
              clearInterval(get)
              this.addVancy("1")
            }else if (json.data.paySessionId=='-1'){
              clearInterval(get)
              this.addVancy("-1")
            }
          }
        }
      })
    },2000)
  }

  addVancy(stage){
    if(stage==1){
      let url=g.namespace+"/gpsys/user/addVancy";
      let send={
        user:this.userId,
        money:this.addPrice
      }
      $.ajax(url,{
        data:send,
        dataType:'jsonp',
        success:json=>{
          if(json.stage==1){
            alert("支付成功")
            this.isSearchLoading=false;
            this.getUserInfo()
          }
          else{
            alert(json.msg)
          }
        }
      })

    }else{
      alert("支付失败")
      this.isSearchLoading=false
    }

  }
}
