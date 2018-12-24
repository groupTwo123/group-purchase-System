import { Component, OnInit,Output ,EventEmitter} from '@angular/core';
import  * as g from "./../../../type"
@Component({
  selector: 'app-admin-pic',
  templateUrl: './admin-pic.component.html',
  styleUrls: ['./admin-pic.component.css']
})
export class AdminPicComponent implements OnInit {
  @Output() changeHeight=new EventEmitter();
  bodyShow:boolean=false;
  picList:any;
  picType:any={};
  constructor() {
    this.picType=g.picType;
  }

  ngOnInit() {
    $("#adminPic").fadeIn(g.time);
    this.bodyShow=false;
    this.getPic();
    // this.setTable();
  }
  ngOnChanges(){

  }
  //获取文章列表
  getPic(){
    this.picList={}
    let url=g.namespace+"/gpsys/commodity/getCommodityPicture";
    $.ajax(url,{
      data:{},
      dataType:'jsonp',
      success:json=>{
          this.picList=json.data;
          console.log(json)
          this.bodyShow=true;
      }
    })
  }
  // //设置表格属性分页
  // setTable() {
  //   setTimeout(json => {
  //     if ($("#table").dataTable()) {
  //       // $("#table").dataTable().fnClearTable();    //清空数据
  //       $("#table").dataTable().fnDestroy();         //销毁datatable
  //     }
  //     $("#table").dataTable(g.dataTable);
  //     this.changeHeight.emit();
  //   }, 100)
  // }

}
