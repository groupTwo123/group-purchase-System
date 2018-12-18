import { Component, OnInit, Input, Output, EventEmitter} from '@angular/core';
import * as g from './../../../../type'

@Component({
  selector: 'app-add-type',
  templateUrl: './add-type.component.html',
  styleUrls: ['./add-type.component.css']
})
export class AddTypeComponent implements OnInit {
  @Output() close=new EventEmitter();
  typeName:any='';
  commodityType:any;
  constructor() { }

  ngOnInit() {
    this.getAllCommodityType();
  }
  //提交表单数据
  submit(){
    for(let item in this.commodityType ){
      if(this.commodityType[item].name==this.typeName){
        alert("已有相同类型");
        return;
      }
    }
    if(this.typeName==''){
      alert("请输入必要字段");
      return;
    }
    let url=g.namespace+"/gpsys/commodity/addType";
    let send={
      typename:this.typeName
    }
    $.ajax(url,{
      data:send,
      dataType:"jsonp",
      jsonp:"callback",
      success:json=>{
        if(json.stage==1){
          alert("添加成功");
          this.close.emit();
        }
        else{
          alert(json.msg)
        }
      }
    })
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
}
