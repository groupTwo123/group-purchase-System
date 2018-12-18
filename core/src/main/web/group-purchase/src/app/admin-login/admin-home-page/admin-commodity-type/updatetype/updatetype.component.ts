import { Component, OnInit, Input, Output, EventEmitter} from '@angular/core';
import * as g from './../../../../type'
@Component({
  selector: 'app-updatetype',
  templateUrl: './updatetype.component.html',
  styleUrls: ['./updatetype.component.css']
})
export class UpdatetypeComponent implements OnInit {
  @Input() data:any={};
  @Output() close=new EventEmitter();
  commodityType:any;
  constructor() { }

  ngOnInit() {
  }
  ngOnChanges(){
    this.getAllCommodityType();
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
        this.commodityType=json.data;
      }
    })
  }
  //提交表单数据
  submit(){
    for(let item in this.commodityType ){
      if(this.commodityType[item].name==this.data.name){
        alert("已有相同类型");
        return;
      }
    }
    if(this.data.name==''){
      alert("请输入必要字段");
      return;
    }
    let url=g.namespace+"/gpsys/commodity/updateTypeById";
    let send={
      id:this.data.id,
      name:this.data.name
    }
    $.ajax(url,{
      data:send,
      dataType:"jsonp",
      jsonp:"callback",
      success:json=>{
        if(json.stage==1){
          alert("修改成功");
          this.close.emit();
        }
        else{
          alert(json.msg)
        }
      }
    })
  }
}
