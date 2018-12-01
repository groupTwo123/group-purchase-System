import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-my-orders',
  templateUrl: './my-orders.component.html',
  styleUrls: ['./my-orders.component.css']
})
export class MyOrdersComponent implements OnInit {
  topDataObj:any=[
    {"id":"0", "name":"所有订单"},
    {"id":"1", "name":"代付款"},
    {"id":"2", "name":"代发货"},
    {"id":"3", "name":"待收货"},
    {"id":"4", "name":"待评价"},
    ];
  ordersTypeChose:any="0";  //头部订单分类选择
  constructor() { }

  ngOnInit() {
  }
  //获取不同类型的订单
  getOrdersByType(typeId){
    this.ordersTypeChose=typeId;
  }
}
