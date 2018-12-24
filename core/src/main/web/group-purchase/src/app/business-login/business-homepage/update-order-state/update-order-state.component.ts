import { Component, OnInit ,Input,Output,EventEmitter } from '@angular/core';
import * as g from "./../../../type";
@Component({
  selector: 'app-update-order-state',
  templateUrl: './update-order-state.component.html',
  styleUrls: ['./update-order-state.component.css']
})
export class UpdateOrderStateComponent implements OnInit {
  @Input() id:any='';
  @Output() changeHeight=new EventEmitter();
  userInfo:any={};
  orderList:any;
  bodyShow:boolean=false;
  orderState:any={}
  userInfoPageShow:boolean=false;
  userId:any='';
  isShowReason:boolean=false;
  reasonData:any;
  constructor() {
    this.orderState=g.orderState;
  }

  ngOnInit() {
  }
  ngOnChanges(){
    this.getSellerInfo();
  }
//获取商家信息
  getSellerInfo(){
    let url=g.namespace+"/gpsys/seller/getSellerInfoById";
    let send={
      sellerId:this.id
    }

    $.ajax(url,{
      data:send,
      dataType:"jsonp",
      success:json=>{
        if(json.stage==1){
          this.userInfo=json.data;
          this.getOrderByCommodityId('0');
        }
        else{
          alert("服务器错误："+json.msg)
        }
      }
    })
  }

  //获取订单列表
  getOrderByCommodityId(index){
    let url=g.namespace+"/gpsys/order/getOrderByVolumeId";
    let send={
      volumeId:this.userInfo.volumeId
    }
    $.ajax(url,{
      data:send,
      dataType:"jsonp",
      success:json=>{
        if(json.stage==1){
          this.orderList=json.data;
          if(index=='0'){
            this.dataTrans()
          }
        }
        else{
          alert("服务器错误："+json.msg)
        }
      }
    })
  }
  //表格索引
  dataTrans() {
    setTimeout(json=>{
      $("#table").DataTable(g.dataTable)
      this.changeHeight.emit();
    },50)
    this.bodyShow=true;

  }
  //展示用户信息
  editShow(item){
    this.userId=item.userId;
    this.userInfoPageShow=true;
  }
  closeUserInfoPage(){
    this.userId='';
    this.userInfoPageShow=false;
  }

  //修改订单状态
  updateOrderState(item,state){
    if(!confirm("是否继续该操作")){
      return;
    }
    let url=g.namespace+"/gpsys/order/updateStateByOrderId";
    let send={
      orderId:item.orderId,
      state:state,
      beforeState:item.state,
      money:item.money,
      userId:item.userId,
      sellerId:this.id
    };
    $.ajax(url,{
      data:send,
      dataType:"jsonp",
      success:json=>{
        if(json.stage==1){
          alert("修改成功");
          this.getOrderByCommodityId('1');
        }
        else{
          alert("修改失败："+json.msg)
        }
      }
    })
  }
  //同意取消退货申请，订单返回原来状态
  cancelBackCommodity(item){
    if(!confirm("是否继续该操作")){
      return;
    }
    let url=g.namespace+"/gpsys/order/cancelBackCommodity";
    let send={
      order_id:item.orderId
    }
    $.ajax(url,{
      data:send,
      dataType:"jsonp",
      success:json=>{
        if(json.stage==1){
          alert("修改成功");
          this.getOrderByCommodityId('1');
        }
        else{
          alert("修改失败："+json.msg)
        }
      }
    })
  }
  showReason(item){
    this.isShowReason=true;
    this.reasonData=item;
  }
  closeReason(){
    this.isShowReason=false;
    // this.getOrderByCommodityId()
  }
}
