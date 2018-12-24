import { Component, OnInit,Input,Output,EventEmitter } from '@angular/core';
import * as g from"./../../type";
/*
* 订单状态
* 0：未付款
* 1：已付款
* 2：商家已发货
* 3：交易完成
* 4：待评价
* 5：取消订单申请中
* 6：交易关闭
* 7：退货申请中
* */

@Component({
  selector: 'app-my-orders',
  templateUrl: './my-orders.component.html',
  styleUrls: ['./my-orders.component.css']
})
export class MyOrdersComponent implements OnInit {
  @Input() userId:any=''
  @Output() close=new EventEmitter();
  topDataObj:any=[
    {"id":"-1", "name":"所有订单"},
    {"id":"0", "name":"待付款"},
    {"id":"1", "name":"已付款"},
    {"id":"2", "name":"已发货"},
    {"id":"3", "name":"交易完成"},
    {"id":"4", "name":"已评价"},
    ];
  ordersTypeChose:any="-1";  //头部订单分类选择
  OrderDataList:any;  //存储订单列表
  pageObj:any=[]; //为页数数组
  page:any=0;//为总页数
  pageChose:number=0//为选择页数
  orderListDataObj:any;
  isHasData:boolean=false;
  orderDataBuffer:any;  //用于缓存所有列表数据
  searchOrder:any='';   //存储搜索关键词
  backCommodityPageShow:boolean=false;
  backCommodityData:any;
  orderState:any={}
  picData:any=[];
  isCommentShow:boolean=false;
  commentData:any;
  constructor() {
    this.orderState=g.orderState;
  }

  ngOnInit() {

  }
  ngOnChanges(){
    $("#myOrder").fadeIn(g.time);
    if(this.userId==''){
      setTimeout(json=>{
        alert("请先登录");
        this.close.emit();
      },100)

    }
    else{
      this.getAllOrderById();
    }
  }
  //获取不同类型的订单
  getOrdersByType(typeId){
    this.isHasData=false;
    this.ordersTypeChose=typeId;
    this.OrderDataList={};
    var index=0;
    if(typeId=='-1'){
      this.getAllOrderById();
    }else{
      for(let item in this.orderDataBuffer){
        if(this.orderDataBuffer[item].orderData.state==typeId){
          this.OrderDataList[index]=this.orderDataBuffer[item];
          index++;
        }
      }
      this.dataTrans();
    }

  }
  //获取所有订单
  getAllOrderById(){
    this.picData=[]
    let url="http://localhost:8080/gpsys/order/getOrderByUserId"
    let send={
      userId:this.userId
    }
    $.ajax(url,{
      data:send,
      dataType:"jsonp",
      success:json=>{
        if(json.stage==1){
          this.OrderDataList=json.data;
          console.log(this.OrderDataList)
          for(let item in this.OrderDataList){
            let url=g.namespace+"/gpsys/commodity/getCommodityPicById";
            let send={
              picId:this.OrderDataList[item].CommodityData.commodityId
            }
            $.ajax(url,{
              data:send,
              dataType:'jsonp',
              success:json=>{
                if(json.stage==1){
                  for(let item of json.data){
                    if(item.picType=='2'){
                      this.picData.push(item.picBase64);
                      break;
                    }
                  }
                }
              }
            })
          }
          this.orderDataBuffer=json.data;
          var str = JSON.stringify(this.OrderDataList);
          this.orderDataBuffer = JSON.parse(str);
          this.dataTrans();
        }
        else{
          alert("服务器错误："+json.msg);
        }
      }
    })
  }
  //数据处理
  dataTrans(){
    this.pageObj=[];
    this.page=0;
    this.pageChose=0
    this.orderListDataObj={};
    var orderLength=0;
    for(let item in this.OrderDataList){
      orderLength++;
    }
    if(orderLength==0){
      this.isHasData=false;
      return;
    }
    if(orderLength % 8!=0){
      this.page=parseInt((orderLength / 8).toString()) +1;
    }
    else{
      this.page=orderLength / 8;
    }
    var index=0;
    for(var i=0;i<this.page;i++){
      this.orderListDataObj[i]=[];
      for(var j=0;j<8;j++){
        if(this.OrderDataList[index]){
          this.orderListDataObj[i].push(this.OrderDataList[index])
          index++;
        }
      }
      this.pageObj.push( i+1);
    }
    this.isHasData=true;
  }
  //搜索订单
  searchOrderFun(){
    this.isHasData=false;
    this.OrderDataList={};
    this.ordersTypeChose='-1';
    var index=0;
    setTimeout(json=> {
      if(this.searchOrder==''){
        this.getAllOrderById();
      }
      else{
        var regStr=this.searchOrder;
        var reg =  new RegExp(regStr);
        for(let item in this.orderDataBuffer){
          if(reg.test(this.orderDataBuffer[item].orderData.orderId)||reg.test(this.orderDataBuffer[item].CommodityData.commodityName)){
            console.log(reg.test(this.orderDataBuffer[item].orderData.orderId))
            this.OrderDataList[index]=this.orderDataBuffer[item];
            index++;
          }
        }
        if(index==0){
          this.isHasData=false;
          return;
        }
        this.dataTrans();
      }
    },200)

  }
  //展示退货页面
  backCommodityShow(item){
    if(confirm("是否要申请退货")){
      this.backCommodityPageShow=true;
      this.backCommodityData=item;
    }
  }
  //关闭退货页面
  closeBackCommodity(){
    this.backCommodityPageShow=false;
    this.getAllOrderById();
  }
  //根据仓库id获取卖家信息
  getSellerInfoByVolumeId(item){
    let url=g.namespace+"/gpsys/seller/getSellerInfoByVolumeId"
    let send={
      volumeId:item.CommodityData.volumeId
    }
    $.ajax(url,{
      data:send,
      dataType:"jsonp",
      success:json=>{
        if(json.stage==1){
          this.cancelBackCommodity(item,json.data);
        }
      }
    })
  }
  //取消退货
  cancelBackCommodity(item,sellerInfo){
    if(confirm("是否要取消退货申请")){
      let url=g.namespace+"/gpsys/order/updateStateByOrderId";
      let send={
        orderId:item.orderData.orderId,
        state:"8",
        beforeState:item.orderData.state,
        money:item.orderData.money,
        userId:item.orderData.userId,
        sellerId:sellerInfo.sellerId
      };
      $.ajax(url,{
        data:send,
        dataType:"jsonp",
        success:json=>{
          if(json.stage==1){
            alert("修改成功");
            this.getAllOrderById();
          }
          else{
            alert("修改失败："+json.msg)
          }
        }
      })
    }
  }

  //展示评论区
  showComment(item ,index){
    this.commentData={}
    console.log(item)
    this.commentData=item;
    this.commentData['pic']=this.picData[index];
    this.isCommentShow=true;
  }
  //关闭评论区
  closeComment(){
    this.isCommentShow=false;
    this.getAllOrderById();
  }
  //更新订单为已评论
  updateOrder(state){
    let url=g.namespace+"/gpsys/seller/getSellerInfoByVolumeId";
    let send={
      volumeId:this.commentData.CommodityData.volumeId
    }
    console.log(send)
    $.ajax(url,{
      data:send,
      dataType:'jsonp',
      success:json=>{
        if(json.stage==1){
          let url=g.namespace+"/gpsys/order/updateStateByOrderId";
          let send={
            orderId:this.commentData.orderData.orderId,
            state:state,
            beforeState:this.commentData.orderData.state,
            money:this.commentData.orderData.money,
            userId:this.userId,
            sellerId:json.data.sellerId,

          }
          $.ajax(url,{
            data:send,
            dataType:'jsonp',
            success:json=>{
              if(json.stage==1){
                this.closeComment();
              }
            }
          })
        }
      }
    })

  }
}
