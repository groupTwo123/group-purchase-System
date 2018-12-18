import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import * as testing from "selenium-webdriver/testing";
import * as g from'./../../../type'
@Component({
  selector: 'app-business-per',
  templateUrl: './business-per.component.html',
  styleUrls: ['./business-per.component.css']
})
export class BusinessPerComponent implements OnInit {
  @Input() username:any=''
  @Output() changeHeight=new EventEmitter();
  userInfo:any={};
  heartNumber:any=[];
  heartNumberShow:boolean=false;
  isChangeInfo:boolean=true;
  userInfoBuffer:any={};
  constructor() { }
  ngOnChanges(){
    this.getSellerInfo();
  }
  ngOnInit() {
  }
  //获取商家信息
  getSellerInfo(){
    this.heartNumber=[];
    this.userInfoBuffer={};
    let url=g.namespace+"/gpsys/seller/getSellerInfoById";
    let send={
      sellerId:this.username
    }
    $.ajax(url,{
      data:send,
      dataType:"jsonp",
      success:json=>{
        if(json.stage==1){
          this.userInfo=json.data;
          for(var i=0;i<parseInt(json.data.sellerPink)/20;i++){
            console.log(i)
            this.heartNumber.push(i)
          }
          this.heartNumberShow=true;
          var a= JSON.stringify(this.userInfo);
          this.userInfoBuffer=JSON.parse(a)
          console.log(this.userInfo)
        }
        else{
          alert("服务器错误："+json.msg)
        }
      }
    })
  }
  //刷新数据
  reset(){
    if(!confirm("警告！！数据将不会保存")){
      return;
    }
    var a= JSON.stringify(this.userInfoBuffer);
    this.userInfo=JSON.parse(a)
  }

  //提交保存
  saveData(){
    for(let item in this.userInfo){
      if(this.userInfo[item]==''){
        alert("请填写必要数据");
        return;
      }
    }
    let url=g.namespace+"/gpsys/seller/updateSellerInfo"
    let send={
      sellerId:this.userInfo.sellerId,
      storeName:this.userInfo.storeName,
      sellerNickname:this.userInfo.sellerNickname,
      sellerName:this.userInfo.sellerName,
      sellerIdentityId:this.userInfo.sellerIdentityId,
      storeArea:this.userInfo.storeArea,
      sellerEmail:this.userInfo.sellerEmail
    }
    $.ajax(url,{
      data:send,
      dataType:"jsonp",
      success:json=>{
        if(json.stage==1){
          alert("保存成功")
          this.getSellerInfo();
        }
        else{
          alert("服务器错误："+json.msg)
        }
      }
    })
  }

}
