import { Component, OnInit, Input, Output } from '@angular/core';
import {tokenReference} from "@angular/compiler";
import * as g from'./../../type';

@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.css']
})
export class HomePageComponent implements OnInit {
  @Input() usernameFromParent:any;
  @Input() idFormParent:any='';
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
  myRebateNum:number=0;
  articleList:any={};
  articleData:any={
    promotion:[], //促销
    notice:[], //公告
    userWelfare:[], //会员专享
    integral:[]//积分商城
  }
  picData:any={
    integral:[],
    userWelfare:[]
  }
  userWelfareShow:boolean=false
  integralShow:boolean=false
  constructor() { }

  ngOnInit() {
    $("#homepage").fadeIn(g.time);
    this.checkLoginSession();
    this.getAllCommodityType();
    this.getArticle();
    $('.carousel').carousel({  interval: 2000})
    if(this.usernameFromParent==''||this.idFormParent==''){

    }

  }

  //查询session是否有登录信息
  checkLoginSession(){
    let url=g.namespace+"/gpsys/user/login";
    let send={
      id:"",
      password:"",
      isOut:"0"
    }
    $.ajax(url,{
      data:send,
      dataType:'jsonp',
      success:json=>{
        console.log(json)
        if(json.stage==1){
         this.usernameFromParent=json.data.username;
         this.idFormParent=json.data.id;
          this.username=this.usernameFromParent;
          this.isLogin=true;
        }
      }
    })
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
  //搜索事件
  searchFun(){
    var str = JSON.stringify(this.searchObj);
    this.search = JSON.parse(str);
   this.showCommodityList();
  }
  //返回到登录页面
  returnLogin(){
    //查询session是否有登录信息
      let url=g.namespace+"/gpsys/user/login";
      let send={
        id:"",
        password:"",
        isOut:"1"
      }
      $.ajax(url,{
        data:send,
        dataType:'jsonp',
        success:json=>{
          console.log(json)
          if(json.stage==1){
            window.location.reload();
          }
        }
      })
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
//关闭我的订单界面
  closeMyOrder(){
    this.myOrdersPageShow=false;
    this.shoppingCarPageshow=false;
    this.CommodityListShow=false;
    this.personCenterPageshow=false;
    this.myRebatePageShow=false;

  }
  //关闭个人中心页面
  closePersonCenter(){
    $("#homepage").fadeIn(g.time);
    this.myOrdersPageShow=false;
    this.shoppingCarPageshow=false;
    this.CommodityListShow=false;
    this.personCenterPageshow=false;
    this.myRebatePageShow=false;
  }

  //显示我的会员页面
  showMyRebate(){
    if(this.idFormParent==''){
      setTimeout(json=>{
        alert("请先登录");
        this.closeMyRebate()
      },100)
    }
    else{
      this.myRebatePageShow=true;
      this.myRebateNum++;
    }

  }
  //关闭会员页面
  closeMyRebate(){

    this.myRebatePageShow=false;
  }
  //获取文章列表
  getArticle(){
    this.articleList={}
    let url=g.namespace+"/gpsys/commodity/getArticleById";
    let send={
      id:"",
      idType:"0"
    }
    $.ajax(url,{
      data:send,
      dataType:'jsonp',
      success:json=>{
        if(json.stage==1){
          this.articleList=json.data;
          console.log(this.articleList)
          for(let item in this.articleList){
            if(this.articleList[item].type=='2' &&this.articleList[item].state=='1'){
              this.articleData.promotion.push(this.articleList[item])
            }
            else  if(this.articleList[item].type=='3' &&this.articleList[item].state=='1'){
              this.articleData.notice.push(this.articleList[item])
            }
            else if(this.articleList[item].type=='4' &&this.articleList[item].state=='1'){
              var dataObj={
                articleObj:{},
                picData:""
              };
              var articleObj=this.articleList[item];
              let url=g.namespace+"/gpsys/commodity/getCommodityPicById";
              let send={
                picId:this.articleList[item].commodityId
              }
              $.ajax(url,{
                data:send,
                dataType:'jsonp',
                success:json=>{
                  if(json.stage==1){
                    for(let item1 of json.data){
                      if(item1.picType=='2'){
                        // var str1=JSON.stringify(articleObj)
                        // dataObj.articleObj=JSON.parse(str1);
                        // dataObj.articleObj=articleObj
                        // var str=JSON.stringify(item1.picBase64)
                        // dataObj.picData=JSON.parse(str);
                        this.articleList[item]['picData']=[]
                        this.articleList[item]['picData'].push(item1.picBase64)
                        this.articleData.userWelfare.push(this.articleList[item])
                        break;
                      }
                    }
                  }
                }
              })


            }
            else if(this.articleList[item].type=='5' &&this.articleList[item].state=='1'){
              var dataObj1={
                articleObj:{},
                picData:""
              }
              var articleObj1=this.articleList[item];
              let url=g.namespace+"/gpsys/commodity/getCommodityPicById";
              let send={
                picId:this.articleList[item].commodityId
              }
              $.ajax(url,{
                data:send,
                dataType:'jsonp',
                success:json=>{
                  if(json.stage==1){
                    for(let item1 of json.data){
                      if(item1.picType=='2'){
                        // var str1=JSON.stringify(articleObj1)
                        // dataObj1.articleObj=JSON.parse(str1);
                        // // dataObj1.articleObj= articleObj1
                        // var str=JSON.stringify(item1.picBase64)
                        // dataObj1.picData=JSON.parse(str);
                        this.articleList[item]['picData']=[]
                        this.articleList[item]['picData'].push(item1.picBase64)
                        this.articleData.integral.push(this.articleList[item])
                        // this.picData.integral.push(item1.picBase64)
                        break;
                      }
                    }
                  }
                }
              })
              // this.articleData.integral.push(dataObj1)

            }
          }
          console.log(this.articleData)
          setTimeout(json=>{
            this.userWelfareShow=true;
            this.integralShow=true;
          },100)
        }
        else{
          alert(json.msg)
        }
      }
    })
  }
}
