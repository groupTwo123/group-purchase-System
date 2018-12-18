import { Component, OnInit, Input, Output,EventEmitter } from '@angular/core';
import  * as g from'./../../../../type'
@Component({
  selector: 'app-commodity-detail',
  templateUrl: './commodity-detail.component.html',
  styleUrls: ['./commodity-detail.component.css']
})
export class CommodityDetailComponent implements OnInit {
  @Input() data:any;
  @Output() close= new EventEmitter();
  commodityNumber:number=1;
  commidtyTypeName:any;
  sellerInfo:any={};

  constructor() { }

  ngOnInit() {
  }
  ngOnChanges(){
    this.commodityNumber=1;
    this.getCommodityType();
    this.getSellerInfo();

  }
  //改变数量
  numberChange(change){
    if(change=="+"){
      this.commodityNumber++
    }
    else{
      this.commodityNumber--
    }
  }

  //获取商品Type
  getCommodityType(){
    let url=g.namespace+"/gpsys/commodity/getCommodityTypeById";
    let send={
      commodityTypeId:this.data.volumeData.commodityTypeId
    }
    $.ajax(url,{
      data:send,
      dataType:"jsonp",
      success:json=>{
        if(json.stage==1){
          this.commidtyTypeName=json.data[0].name;
        }
        else{
          alert("服务器错误："+json.msg)
        }
      }
    })
  }
  //获取商家信息
  getSellerInfo(){
    let url=g.namespace+"/gpsys/seller/getSellerInfoById";
    let send={
      sellerId:this.data.seller_id
    }
    $.ajax(url,{
      data:send,
      dataType:"jsonp",
      success:json=>{
        if(json.stage==1){
          this.sellerInfo=json.data;
          console.log(this.sellerInfo)
        }
        else{
          alert("服务器错误："+json.msg);
        }
      }
    })
  }
  //返回到商品首页
  back(){
    this.close.emit();
  }
  //提交加入购物车
  addShoppingCar(){
    let url=g.namespace+"/gpsys/shopcar/addShoppingCar";
    let send={
      commodityId:this.data.volumeData.commodityId,
      commodityNumber:this.commodityNumber,
      volume_id:this.data.volumeData.volumeId,
      user_id:"1101"
    }
    $.ajax(url,{
      data:send,
      dataType:"jsonp",
      success:json=>{
        if(json.stage==1){
          alert("加入成功");

        }
        else{
          alert("服务器错误："+json.msg)
        }
      }
    })
  }
}
