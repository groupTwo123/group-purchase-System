import { Component, OnInit, Input, Output, EventEmitter} from '@angular/core';
import * as g from './../../../type'
@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css']
})
export class UserListComponent implements OnInit {
  @Output() changHeight=new EventEmitter()
  userInfo:any;
  bodyShow:boolean=false;
  orderPage:boolean=false;
  orderPageData:any;
  constructor() { }

  ngOnInit() {
    this.bodyShow=false
    this.getAllUser()
    this.setTable();
  }
  //设置表格属性分页
  setTable() {
    setTimeout(json => {
      if ($("#table").dataTable()) {
        // $("#table").dataTable().fnClearTable();    //清空数据
        $("#table").dataTable().fnDestroy();         //销毁datatable
      }
      $("#table").dataTable(g.dataTable);
      this.changHeight.emit();
    }, 100)
  }

    //获取所有用户列表
  getAllUser(){
    this.bodyShow=false;
    let url=g.namespace+"/gpsys/user/getAllUserInfo";
    $.ajax(url,{
      data:{},
      dataType:"jsonp",
      success:json=>{
        if(json.stage==1){
          this.userInfo=json.data;
          console.log(this.userInfo)
          this.bodyShow=true;
        }
        else{
          alert(json.msg)
        }
      }
    })
  }
  //显示订单列表
  showOrderList(item){
    this.orderPage=true;
    this.orderPageData=item;
    this.changHeight.emit();
  }
  //关闭订单列表
  closeOrderList(){
    this.orderPage=false;
    this.bodyShow=false;
    this.getAllUser();
    this.setTable()
    this.changHeight.emit();
  }
}
