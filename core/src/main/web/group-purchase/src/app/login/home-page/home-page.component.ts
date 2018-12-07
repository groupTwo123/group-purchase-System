import { Component, OnInit, Input, Output } from '@angular/core';

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
  shopCarPageShow:boolean;   //判断是否展示购物车组件
  personCenterPageshow:boolean=false;
  shoppingCarPageshow:boolean=false;

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
    this.CommodityListShow=false;
  }

  //显示个人中心
  personCenterShow(){
    this.personCenterPageshow=true;
  }
  //显示购物车
  showShoppingCar(){
    this.shoppingCarPageshow=true;
  }
}
