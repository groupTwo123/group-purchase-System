import { Component, OnInit, Input, Output } from '@angular/core';

@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.css']
})
export class HomePageComponent implements OnInit {
  @Input() usernameFromParent:any;
  username:any="";
  isLogin:boolean=false;
  typeDataObj:any;
  classificationLeftChose:any="Promotion"     //Promotion为促销，Notice为公告
  CommodityListShow:boolean=false;
  commodityList:any;  //商品列表
  isHasData:boolean=false;  //判断是否有数据
  search:any='';
  shopCarPageShow:boolean;   //判断是否展示购物车组件
  constructor() { }

  ngOnInit() {
    this.getAllCommodityType();
    $('.carousel').carousel({  interval: 2000})

  }
  ngOnChanges(){
    this.CommodityListShow=false;
    this.isLogin=false;
    this.username=this.usernameFromParent;
    if(this.username!=""){
      this.isLogin=true;
    }
  }

  //公告栏和促销转换
  adverRightPick(pickType){
    this.classificationLeftChose=pickType;
  }

  //显示商品类别
  showCommodityList(){
    this.CommodityListShow=true;
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
        console.log(this.typeDataObj);
      }
    })
  }
  //返回到登录页面
  returnLogin(){
    window.location.reload();
  }

}
