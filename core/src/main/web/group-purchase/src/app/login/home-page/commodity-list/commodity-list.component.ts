import { Component, OnInit ,Input, Output, EventEmitter } from '@angular/core';
import * as g from'./../../../type';
@Component({
  selector: 'app-commodity-list',
  templateUrl: './commodity-list.component.html',
  styleUrls: ['./commodity-list.component.css']
})
export class CommodityListComponent implements OnInit {
  @Input() search:any='';
  @Input() id:any='';
  typeDataObj:any;
  sortWay:any='0';    //  排序顺序，0为销量，1为评论，2为价格，3为评论数
  commodityList:any;	//用于存储数据
  isHasData:boolean=false;
  commodityListDataObj:any={};
  page:number=0;
  pageChose:number=0;
  pageObj:any=[];
  isCommodityTypeShow:boolean=false;  //分类显示
  isLoading:boolean=false;
  commodityDetailPageShow:boolean=false;
  commodityDetail:any;
  constructor() { }

  ngOnInit() {
    this.changeFun();
  }
  ngOnChanges(){
    this.changeFun();
  }

  //触发搜索
  changeFun(){
    this.getAllCommodityType();
    if(this.search==''){
      this.getAllCommodity();
    }
    else{
      this.getCommodityBySearch();
    }
  }

  //分类显示
  showType(){
    this.isCommodityTypeShow=true;
  }
  //分类关闭
  closeType(){
    this.isCommodityTypeShow=false;
  }
  //获取所有商品类别
  getAllCommodityType(){
    let url=g.namespace+"/gpsys/commodity/getCommodityType"
    $.ajax(url,{
      data:{},
      dataType:"jsonp",
      jsonp:"callback",
      type:"GET",
      success:json=>{
        this.typeDataObj=json.data
      }
    })
  }
  //模糊获取商品
  getCommodityBySearch(){
    this.isHasData=false;
    this.isLoading=true;
    let url=g.namespace+"/gpsys/commodity/getCommodityByName";
    let send={
      commodityName:this.search
    }
    $.ajax(url,{
      data:send,
      dataType:"jsonp",
      jsonp:"callback",
      success:json=>{
        if(json.stage==1){
          this.commodityList=json.data;
          this.dataTrans();
        }
        else{
          alert("错误:"+json.msg);
          this.isHasData=false;
          setTimeout(json=>{
            this.isLoading=false
          },500)
        }

      }
    })

  }
  //重新排序
  resetSort(sortWay){
    this.sortWay=sortWay;
  }

  //获取所有商品
  getAllCommodity(){
    this.isHasData=false;
    this.isLoading=true;
	  let url=g.namespace+"/gpsys/commodity/getAllCommodity"
	  $.ajax(url,{
		  dataType:"jsonp",
		  jsonp:"callback",
		  success:json=>{
			  if(json.stage==1){
				  this.commodityList=json.data;
          this.dataTrans();
			  }
			  else{
				  alert("错误:"+json.msg);
				  this.isHasData=false;
			  }
		  }
	  })
  }
  //分页数据处理
  dataTrans(){

    this.page=0;
    this.pageChose=0;
    this.pageObj=[];
    this.commodityListDataObj={};

    var commodityLength=0;
    for(let item in this.commodityList){
      commodityLength++;
    }
    console.log(commodityLength)
    if(commodityLength==0){
      this.isHasData=false;
      setTimeout(json=>{
        this.isLoading=false
      },500)
      return;
    }
    if(commodityLength % 16!=0){
      this.page=parseInt((commodityLength / 16).toString()) +1;
    }
    else{
      this.page=commodityLength / 16;
    }
    var index=0;
    for(var i=0;i<this.page;i++){
      this.commodityListDataObj[i]=[];
      for(var j=0;j<16;j++){
        if(this.commodityList[index]){
          this.commodityListDataObj[i].push(this.commodityList[index])
          index++;
        }
      }
      this.pageObj.push( i+1);
    }
    this.isHasData=true;
    this.isLoading=false
  }

  // 回调数据
  choseCommodity(item){
    this.commodityDetailPageShow=true;
    this.commodityDetail=item;
  }
  //关闭详细页
  closeDetail(){
    this.commodityDetailPageShow=false;
  }
}
