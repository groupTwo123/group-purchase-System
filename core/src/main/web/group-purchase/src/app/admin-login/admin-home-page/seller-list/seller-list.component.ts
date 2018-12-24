import { Component, OnInit ,Output, EventEmitter } from '@angular/core';
import * as g from "./../../../type";
@Component({
  selector: 'app-seller-list',
  templateUrl: './seller-list.component.html',
  styleUrls: ['./seller-list.component.css']
})
export class SellerListComponent implements OnInit {
  @Output() changHeight=new EventEmitter()
  bodyShow:boolean=false;
  sellerInfo:any;
  commodityListPageShow:boolean=false;
  sellerDataObj:any=''
  constructor() { }

  ngOnInit() {
    this.bodyShow=false;
    this.getAllSeller();
    this.setTable();
    this.changHeight.emit()
  }
  ngOnChanges(){
    // this.getAllSeller();
  }
  //设置表格属性分页
  setTable(){
    setTimeout(json=>{
      $("#table").DataTable(g.dataTable);
      this.changHeight.emit();
    },200)


  }
  //关闭商品列表
  closeCommodityList(){
    this.commodityListPageShow=false;
    this.changHeight.emit();
  }
  //自适应高度
  scrollHeightChange(){
    this.changHeight.emit()
  }
  //显示商品列表
  showCommodityList(item){
    this.commodityListPageShow=true;
    this.sellerDataObj=item;
  }
  //获取所有商家
  getAllSeller(){
    let url=g.namespace+"/gpsys/seller/getAllSeller";
    $.ajax(url,{
      data:{},
      dataType:"jsonp",
      success:json=>{
        if(json.stage==1){
          this.sellerInfo=json.data;
          console.log(this.sellerInfo)
          this.changHeight.emit()
          this.bodyShow=true
        }
        else{
          alert(json.msg)
        }
      }
    })
  }
  //修改商家账号状态
  updateSellerState(state,sellerId){
    let url=g.namespace+"/gpsys/seller/updateSellerState";
    let send={
      state:state,
      sellerId:sellerId
    }
    $.ajax(url,{
      data:send,
      dataType:"jsonp",
      success:json=>{
        if(json.stage==1){
          this.bodyShow=false;
          this.getAllSeller();
          this.bodyShow=true;
        }
        else{
          alert(json.msg)
        }
      }
    })
  }

}
