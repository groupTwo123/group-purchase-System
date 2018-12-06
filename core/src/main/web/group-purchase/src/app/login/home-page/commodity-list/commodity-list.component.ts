import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-commodity-list',
  templateUrl: './commodity-list.component.html',
  styleUrls: ['./commodity-list.component.css']
})
export class CommodityListComponent implements OnInit {
  typeDataObj:any;
  sortWay:any='0';    //  排序顺序，0为销量，1为评论，2为价格，3为评论数
  commodityList:any;	//用于存储数据
  isHasData:boolean=false;
  commodityListDataObj:any={};
  page:number=0;
  pageChose:number=0;
  pageObj:any=[];
  constructor() { }

  ngOnInit() {
    this.getAllCommodityType();
	this.getAllCommodity();
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
        this.typeDataObj=json.data
      }
    })
  }
  //重新排序
  resetSort(sortWay){
    this.sortWay=sortWay;
  }

  //获取所有商品
  getAllCommodity(){
	  let url="http://localhost:8080/gpsys/commodity/getAllCommodity"
	  $.ajax(url,{
		  dataType:"jsonp",
		  jsonp:"callback",
		  success:json=>{
			  if(json.stage==1){
				  this.commodityList=json.data;
          if(this.commodityList.length % 16!=0){
            this.page=parseInt((this.commodityList.length / 16).toString()) +1;
          }
          else{
            this.page=this.commodityList.length / 16;
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
          console.log(this.commodityListDataObj);
				  this.isHasData=true;
			  }
			  else{
				  alert("错误:"+json.msg);
				  this.isHasData=false;
			  }
		  }
	  })
  }

}
