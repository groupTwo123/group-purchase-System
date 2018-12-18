import { Component, OnInit ,Input, Output} from '@angular/core';

@Component({
  selector: 'app-admin-home-page',
  templateUrl: './admin-home-page.component.html',
  styleUrls: ['./admin-home-page.component.css']
})
export class AdminHomePageComponent implements OnInit {
  @Input() adminId:any=''
  $:any=(window as any).$;
  navPick:any="0" //左侧导航栏选择 0为首页，1为账号信息，2为商品列表，3为订单状态修改
  username:any='user';  //用于存储用户名
  isChangeInfo:boolean=false;
  scorllHeight:any=(document.documentElement.scrollHeight).toString()+'px';
  isAddCommodity:boolean=false;
  constructor() { }

  ngOnInit() {
    this.isChangeInfo=false;
    // $('#leftBox').style.minHeight=(document.body.scrollHeight).toString()+'px';
    document.getElementById('leftBox').style.height=this.scorllHeight;

  }
  //导航栏点击事件
  leftPickFun(index){
    this.navPick=index;
    this.scrollHeightChange();
  }
  //左侧导航栏高度变化
  scrollHeightChange(){
    setTimeout(json=>{
      if($("#rightBox").outerHeight(true)<=this.scorllHeight){
        document.getElementById('leftBox').style.height=this.scorllHeight;
      }else{
        document.getElementById('leftBox').style.height=  $("#rightBox").outerHeight(true)+"px";

      }
    },100)
  }




}
