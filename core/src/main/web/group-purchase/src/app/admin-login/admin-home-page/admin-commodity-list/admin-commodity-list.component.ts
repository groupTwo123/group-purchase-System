import { Component, OnInit,Input ,Output, EventEmitter } from '@angular/core';
import * as g from './../../../type'
@Component({
  selector: 'app-admin-commodity-list',
  templateUrl: './admin-commodity-list.component.html',
  styleUrls: ['./admin-commodity-list.component.css']
})
export class AdminCommodityListComponent implements OnInit {
  @Input() sellerDataObj:any='';
  @Output() changHeight=new EventEmitter();
  @Output() close=new EventEmitter();

  commodityList:any;
  bodyShow:boolean=false;
  commodityType:any={};
  PicData:any;
  PicPage:boolean=false;
  constructor() {
  }

  ngOnInit() {
  }
  ngOnChanges(){
    $("#adminCommodityList").fadeIn(g.time);
    this.bodyShow=false;
    this.getAllCommodityType();
    this.getCommodityByVolumeId()
    this.setTable()

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
        this.commodityType=json.data
      }
    })
  }
  //设置表格属性分页
  setTable(){
    setTimeout(json=>{
      $("#table1").DataTable(g.dataTable);
      this.changHeight.emit();
    },100)
  }
  //获取该商家的商品
  getCommodityByVolumeId(){
    this.commodityList=[];
    let url=g.namespace+"/gpsys/commodity/getCommodityAndPicByVolumeId";
    let send={
      volumeId:this.sellerDataObj.volumeId
    }
    $.ajax(url,{
      data:send,
      dataType:"jsonp",
      success:json=>{
        if(json.stage==1){
          for(let item in json.data){
            this.commodityList.push(json.data[item]);
          }
          this.bodyShow=true;
        }
        else{
          alert(json.msg)
        }
      }
    })
  }
  showCommodityPic(item){
    this.PicData=item;
    this.PicPage=true;
  }
}
