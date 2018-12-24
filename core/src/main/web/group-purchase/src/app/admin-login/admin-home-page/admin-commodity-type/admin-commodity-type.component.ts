import { Component, OnInit, Input, Output, EventEmitter} from '@angular/core';
import * as g from './../../../type'
@Component({
  selector: 'app-admin-commodity-type',
  templateUrl: './admin-commodity-type.component.html',
  styleUrls: ['./admin-commodity-type.component.css']
})
export class AdminCommodityTypeComponent implements OnInit {
  @Output() changHeight=new EventEmitter();
  commodityType:any;
  bodyShow:boolean=false;
  addTypePage:boolean=false;
  updateTypePage:boolean=false
  dataTableDataObj:any;
  updateData:any={};
  constructor() {
    this.dataTableDataObj=g.dataTable;
  }

  ngOnInit() {
    $("#adminCommodityType").fadeIn(g.time);
    this.bodyShow=false;
    this.getAllCommodityType();
    this.setTable();
  }
  ngOnChanges(){

  }
  //设置表格属性分页
  setTable(){
    setTimeout(json=>{
      if ($("#table").dataTable()) {
        // $("#table").dataTable().fnClearTable();    //清空数据
        $("#table").dataTable().fnDestroy();         //销毁datatable
      }
      $("#table").dataTable( this.dataTableDataObj);
      this.changHeight.emit();
    },200)
  }
//获取所有商品类别
  getAllCommodityType(){
    this.commodityType={};
    let url=g.namespace+"/gpsys/commodity/getCommodityType"
    $.ajax(url,{
      data:{},
      dataType:"jsonp",
      jsonp:"callback",
      type:"GET",
      success:json=>{
        this.commodityType=json.data;
        this.bodyShow=true;
      }
    })
  }

  //增加页面显示方法
  addTypeShow(){
    this.addTypePage=true;
  }

  //关闭增加页面
  closeAdd(){
    this.addTypePage=false;
    this.bodyShow=false;
    this.getAllCommodityType();
    this.setTable()
  }
  //删除类型
  delete(id){
    let url=g.namespace+"/gpsys/commodity/delTypeById";
    let send={
      typeId:id
    }
    $.ajax(url,{
      data:send,
      dataType:"jsonp",
      jsonp:"callback",
      success:json=>{
        if(json.stage==1){
          alert("删除成功");
          this.bodyShow=false;
          this.getAllCommodityType();
          this.setTable()
        }
        else{
          alert(json.msg)
        }
      }
    })
  }
  //修改页面显示方法
  updateTypeShow(item){
    this.updateData=item;
    this.updateTypePage=true;
  }

  //关闭增加页面
  closeUpdate(){
    this.updateTypePage=false;
    this.bodyShow=false;
    this.getAllCommodityType();
    this.setTable()
  }
}
