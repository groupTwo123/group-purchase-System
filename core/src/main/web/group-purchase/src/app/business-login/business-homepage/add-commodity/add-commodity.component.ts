import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-add-commodity',
  templateUrl: './add-commodity.component.html',
  styleUrls: ['./add-commodity.component.css']
})
export class AddCommodityComponent implements OnInit {
  typeDataObj:any;
  typechose:any=[];
  type:any='';
  constructor() { }

  ngOnInit() {
    this.getAllCommodityType()
  }
  //获取所有商品类别
  getAllCommodityType(){
    let url="http://localhost:8080/gpsys/commodity/getCommodityType"
    $.ajax(url,{
      data:{},
      dataType:"jsonp",
      jsonp:"callback",
      type:"GET",
      success:json=>{
        this.typeDataObj=json.data

      }
    })
  }
  //选择类型
  typeChose(type){
    if(type!=''){
      var array=type.split(',');
      var arrayObj={
        id:'',
        name:''
      };
      arrayObj.id=array[0];
      arrayObj.name=array[1];
      this.typechose.push(arrayObj);
      console.log(this.typechose)
    }
  }
}
