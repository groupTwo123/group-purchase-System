import { Component, OnInit, Input, Output,EventEmitter } from '@angular/core';
import  * as g from'./../../../../type'
@Component({
  selector: 'app-commodity-detail',
  templateUrl: './commodity-detail.component.html',
  styleUrls: ['./commodity-detail.component.css']
})
export class CommodityDetailComponent implements OnInit {
  @Input() data:any;
  @Input() id:any='';
  @Output() close= new EventEmitter();
  commodityNumber:number=1;
  commidtyTypeName:any;
  sellerInfo:any={};
  pageShow:number=0;
  commodityPic:any=[];
  commodityPicDetail:any=[]
  bodyShow:boolean=false;
  articleData:any={
    article:[],
    picData:""
  }
  articleObj:any=[]
  articleIsHasData:boolean=false
  constructor() { }

  ngOnInit() {
  }
  ngOnChanges(){
    this.bodyShow=false;
    this.commodityNumber=1;
    this.getCommodityPic();
    this.getCommodityType();
    this.getSellerInfo();
    this.getArticleByCommodityId();

  }
  //获取评论通过商品id
  getArticleByCommodityId(){
    this.articleIsHasData=false
    let url=g.namespace+"/gpsys/commodity/getArticleByCommodityId";
    let send={
      commodityId:this.data.volumeData.commodityId,
      type:"1"
    }
    $.ajax(url,{
      data:send,
      dataType:'jsonp',
      success:json=>{
        if(json.stage==1){
          if(json.data.length!=0){
            for(let item in json.data){
              this.articleData={
                article:[],
                picData:""
              }
              this.articleData.article.push(json.data[item])
              let url=g.namespace+"/gpsys/commodity/getCommodityPicById";
              let send={
                picId:json.data[item].id
              }
              console.log(send)
              $.ajax(url ,{
                data:send,
                dataType:'jsonp',
                success:json=>{
                  if(json.stage==1){
                    if(json.data.length!=0){
                      for(let item of json.data){
                        if(item.picType=='1'){
                          this.articleData.picData=item.picBase64
                          this.articleObj.push(this.articleData);
                        }
                      }
                    }
                    else{
                      this.articleData.picData='./../../../../../assets/headExample.gif'
                      this.articleObj.push(this.articleData);
                    }

                  }
                }
              })
            }
            this.articleIsHasData=true;
          }
        }
      }
    })
  }
  //获取商品图片
  getCommodityPic(){
    let url=g.namespace+'/gpsys/commodity/getCommodityPicById';
    let send={
      picId:this.data.volumeData.commodityId
    }
    $.ajax(url,{
      data:send,
      dataType:"jsonp",
      success:json=>{
        if(json.stage==1){
          for(let item of json.data){
            if(parseInt(item.picType)==3){
              this.commodityPicDetail.push(item.picBase64)
            }
            else{
              this.commodityPic.push(item.picBase64)
            }
          }
          this.bodyShow=true
        }
        else{
          alert(json.msg)
        }
      }
    })
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
    if(this.id==''){
      alert("请先登录");
      return;
    }
    let url=g.namespace+"/gpsys/shopcar/addShoppingCar";
    let send={
      commodityId:this.data.volumeData.commodityId,
      commodityNumber:this.commodityNumber,
      volume_id:this.data.volumeData.volumeId,
      user_id:this.id
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
  //页面选择
  pageChose(item){
    this.pageShow=item;
  }
}
