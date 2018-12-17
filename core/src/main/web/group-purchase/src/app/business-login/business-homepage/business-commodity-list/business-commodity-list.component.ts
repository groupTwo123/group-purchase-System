import { Component, OnInit,Input,Output,EventEmitter } from '@angular/core';
import * as globle from "./../../../type"

@Component({
  selector: 'app-business-commodity-list',
  templateUrl: './business-commodity-list.component.html',
  styleUrls: ['./business-commodity-list.component.css']
})
export class BusinessCommodityListComponent implements OnInit {
  @Input() username:any=''
  isAddCommodity:boolean=false;
  @Output() changeHeight=new EventEmitter();
  commodityList:any={};
  userInfo:any='';
  typeDataObj:any={};
  bodyShow:boolean=false;
  page:any=0;
  pageChose:any=0;
  pageObj:any=[];
  commodityListDataObj:any={};
  editCommodityData:any={};
  editPage:boolean=false;
  constructor() { }

  ngOnChanges(){
    this.bodyShow=false;
    this.getSellerInfo();
    this.getAllCommodityType();
  }
  ngOnInit() {
  }

  //改变父类高度
  changeHeightFun(){
    this.changeHeight.emit()
  }

  //获取所有商品类别
  getAllCommodityType(){
    let url="http://localhost:8080/gpsys/commodity/getCommodityType"
    $.ajax(url,{
      data:{},
      dataType:"jsonp",
      jsonp:"callback",
      type:"GET",
      success:json=>{
        this.typeDataObj=json.data;
      }
    })
  }
  //获取商家信息
  getSellerInfo(){

    let url=globle.namespace+"/gpsys/seller/getSellerInfoById";
    let send={
      sellerId:this.username
    }
    $.ajax(url,{
      data:send,
      dataType:"jsonp",
      success:json=>{
        if(json.stage==1){
          this.userInfo=json.data;
         this.getCommodityList('0')
        }
        else{
          alert("服务器错误："+json.msg)
        }
      }
    })
  }
    //获取商品列表通过商家id
  getCommodityList(index){
    let url=globle.namespace+"/gpsys/commodity/getCommodityInfo"
    let send={
      volumeIds:this.userInfo.volumeId
    }
    $.ajax(url,{
      data:send,
      dataType:"jsonp",
      success:json=>{
        if(json.stage==1){
          this.commodityList=json.data;
          if(index=='1'){
            this.dataTrans('1')
          }else{
            this.dataTrans('0')
          }

        }
        else{
          alert("服务器错误："+json.msg)
        }
      }
    })
  }

  //显示增加商品
  addCommodityShow(){
    this.isAddCommodity=true;

  }
  //关闭增加
  closeAdd(){
    this.isAddCommodity=false;
    this.getCommodityList('0');
  }

  dataTrans(index) {
    if(index=='0'){
      setTimeout(json=>{
        $("#table").DataTable( globle.dataTable)

        this.changeHeight.emit();
      },50)
    }

    this.bodyShow=true;

  }
  //是否确定要删除商品
  deleteCommodity(index){
   if(!(confirm("确定要删除吗"))){
     return;
   }
   let url=globle.namespace+"/gpsys/commodity/delCommodityById";
   let send={
     commodityIds:this.commodityList[index].commodityId
   }
   $.ajax(url,{
     data:send,
     dataType:"jsonp",
     success:json=>{
       if(json.stage==1){
         alert("删除成功");
         this.getCommodityList('1')
       }
       else{
         alert("服务器错误:"+json.msg)
       }
     }
   })
  }

  //修改商品
  editShow(item){
    this.editCommodityData=item;
    this.editPage=true;
    this.changeHeight.emit()
  }
  //关闭修改页面
  closeUpdate(){
    this.editPage=false;
    this.getCommodityList('0')
  }
}
