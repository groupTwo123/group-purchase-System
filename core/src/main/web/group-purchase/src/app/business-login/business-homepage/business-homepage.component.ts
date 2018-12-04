import { Component, OnInit, Input} from '@angular/core';
@Component({
  selector: 'app-business-homepage',
  templateUrl: './business-homepage.component.html',
  styleUrls: ['./business-homepage.component.css']
})
export class BusinessHomepageComponent implements OnInit {
  $:any=(window as any).$;
  @Input() username:any="user";
  @Input() id:any="";
  navPick:any="0" //左侧导航栏选择 0为首页，1为账号信息，2为商品列表，3为订单状态修改
  isChangeInfo:boolean=false;
  scorllHeight:any=(document.documentElement.scrollHeight).toString()+'px';
  isAddCommodity:boolean=false;
  constructor() { }

  ngOnInit() {
    this.isChangeInfo=false;
    // $('#leftBox').style.minHeight=(document.body.scrollHeight).toString()+'px';
     document.getElementById('leftBox').style.height=this.scorllHeight;
     //这是测试21
  }
  //导航栏点击事件
  leftPickFun(index){
    this.navPick=index;
    this.scrollHeightChange();
  }
  //左侧导航栏高度变化
  scrollHeightChange(){
    if(document.getElementById('rightBox').style.height<this.scorllHeight){
      document.getElementById('leftBox').style.height=this.scorllHeight;
    }else{
      document.getElementById('leftBox').style.height= document.getElementById('rightBox').style.height;

    }

  }
  //修改账号信息显示
  isChangeShow(){
    this.isChangeInfo=true;
    this.scrollHeightChange();
  }

  //显示增加商品
  addCommodityShow(){
    this.isAddCommodity=true;
    this.scrollHeightChange();
  }
}
