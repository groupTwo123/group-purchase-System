import { Component, OnInit, Input, Output } from '@angular/core';
import {tokenReference} from "@angular/compiler";

@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.css']
})
export class HomePageComponent implements OnInit {
  @Input() usernameFromParent:any;
  @Input() idFormParent:any;
  username:any="";
  isLogin:boolean=false;
  typeDataObj:any;
  classificationLeftChose:any="Promotion"     //Promotion为促销，Notice为公告
  CommodityListShow:boolean=false;
  commodityList:any;  //商品列表
  isHasData:boolean=false;  //判断是否有数据
  search:any='';
  searchObj:any='';
  personCenterPageshow:boolean=false; //个人中心显示
  shoppingCarPageshow:boolean=false;  ////判断是否展示购物车组件
  myOrdersPageShow:boolean=false; //我的订单显示
  myRebatePageShow:boolean=false;  //我的会员页面显示
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
    this.myOrdersPageShow=false;
    this.shoppingCarPageshow=false;
    this.CommodityListShow=true;
    this.personCenterPageshow=false;
    this.myRebatePageShow=false;
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
  //搜索事件
  searchFun(){
    var str = JSON.stringify(this.searchObj);
    this.search = JSON.parse(str);
   this.showCommodityList();
  }
  //返回到登录页面
  returnLogin(){
    window.location.reload();
  }
  //返回首页
  backToHome(){
    this.myOrdersPageShow=false;
    this.shoppingCarPageshow=false;
    this.CommodityListShow=false;
    this.personCenterPageshow=false;
    this.myRebatePageShow=false;

  }

  //显示个人中心
  personCenterShow(){
    this.myOrdersPageShow=false;
    this.shoppingCarPageshow=false;
    this.CommodityListShow=false;
    this.personCenterPageshow=true;
    this.myRebatePageShow=false;

  }
  //显示购物车
  showShoppingCar(){
    this.myOrdersPageShow=false;
    this.shoppingCarPageshow=true;
    this.CommodityListShow=false;
    this.personCenterPageshow=false;
    this.myRebatePageShow=false;

  }
  //显示我的订单界面
  showMyOrder(){
    this.myOrdersPageShow=true;
    this.shoppingCarPageshow=false;
    this.CommodityListShow=false;
    this.personCenterPageshow=false;
    this.myRebatePageShow=false;

  }

  //关闭个人中心页面
  closePersonCenter(){
    this.myOrdersPageShow=false;
    this.shoppingCarPageshow=false;
    this.CommodityListShow=false;
    this.personCenterPageshow=false;
    this.myRebatePageShow=false;
  }

  //显示我的会员页面
  showMyRebate(){
    this.myOrdersPageShow=false;
    this.shoppingCarPageshow=false;
    this.CommodityListShow=false;
    this.personCenterPageshow=false;
    this.myRebatePageShow=true;
  }
}
