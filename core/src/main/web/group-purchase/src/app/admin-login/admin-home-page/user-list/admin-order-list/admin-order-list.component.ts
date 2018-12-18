import { Component, OnInit, Input, Output, EventEmitter} from '@angular/core';
import * as g from './../../../../type'
@Component({
  selector: 'app-admin-order-list',
  templateUrl: './admin-order-list.component.html',
  styleUrls: ['./admin-order-list.component.css']
})
export class AdminOrderListComponent implements OnInit {
  @Input() data:any={}
  @Output() close=new EventEmitter();
  bodyShow:boolean=false;
  orderListData:any;
  orderState:any;
  constructor() {
    this.orderState=g.orderState;
  }

  ngOnInit() {

  }
  ngOnChanges(){
    this.bodyShow=false;
    this.getOrderByUserId();
    this.setTable();
  }
  //设置表格属性分页
  setTable() {
    setTimeout(json => {
      if ($("#table1").dataTable()) {
        // $("#table").dataTable().fnClearTable();    //清空数据
        $("#table1").dataTable().fnDestroy();         //销毁datatable
      }
      $("#table1").dataTable(g.dataTable);
    }, 100)
  }
  //根据用户id获取订单列表
  getOrderByUserId(){
    this.orderListData=[];
    let url=g.namespace+'/gpsys/order/getOrderByUserId';
    let send={
      userId:this.data.id
    }
    $.ajax(url,{
      data:send,
      dataType:"jsonp",
      success:json=>{
        if(json.stage==1){
          for(let item in json.data){
            this.orderListData.push(json.data[item])
          }
          this.bodyShow=true;
        }
        else{
          alert(json.msg)
        }
      }
    })
  }

}
