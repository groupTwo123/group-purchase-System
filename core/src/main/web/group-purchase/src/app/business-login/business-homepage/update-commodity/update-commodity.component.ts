import { Component, OnInit,Input,Output, EventEmitter } from '@angular/core';
import * as g from "./../../../type";
@Component({
  selector: 'app-update-commodity',
  templateUrl: './update-commodity.component.html',
  styleUrls: ['./update-commodity.component.css']
})
export class UpdateCommodityComponent implements OnInit {
  @Input() updateData:any={};
  @Output() changeHeight=new EventEmitter();
  @Output() close=new EventEmitter();
  commodityPicData:any=[]
  commodityDetailPicData:any=[];
  typeDataObj:any;
  constructor() { }

  ngOnInit() {
    // this.getAllCommodityType()
    // this.getPic();
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
        this.typeDataObj=json.data;
      }
    })
  }
  ngOnChanges(){
    this.getPic();
    this.getAllCommodityType()
  }
  //根据id获取图片列表
  getPic(){
    this.commodityPicData=[];
    this.commodityDetailPicData=[];
    let url=g.namespace+"/gpsys/commodity/getCommodityPicById";
    let send={
      picId:this.updateData.commodityId
    }
    $.ajax(url,{
      data:send,
      dataType:"jsonp",
      success:json=>{
        if(json.stage==1){
          console.log(json.data)
          for(let item in json.data){
            if(json.data[item].picType=='2'){
              this.commodityPicData.push(json.data[item].picBase64)
            }else{
              this.commodityDetailPicData.push(json.data[item].picBase64)
            }
          }
          this.changeHeight.emit()
        }
        else{
          alert(json.msg)
        }
      }
    })
  }
  //关闭修改页面
  closeUpdateFun(){
    if(!confirm("警告！数据将不会保存，是否继续")){
      return;
    }
    this.close.emit();
  }
  //上传商品图片
  commodityPicAdd(index){
    if(index=='0'){
      var MyTest = $("#file")[0].files[0];
    }else{
      var MyTest = $("#file1")[0].files[0];
    }
    var reader = new FileReader();
    reader.readAsDataURL(MyTest);
    reader.onload = theFile=> {
      var res=theFile.target['result'];
      if(index=='0'){
        this.commodityPicData.push(res)
      }else{
        this.commodityDetailPicData.push(res)
      }
    }
    this.changeHeight.emit();
  }

  //删除商品图片数组中的图片
  deleteCommodityPic(index,judge){
    if(!(confirm("确定要删除吗？"))){
      return;
    }
    if(judge=='0'){
      this.commodityPicData.splice(index,1)
    }
    else{
      this.commodityDetailPicData.splice(index,1)
    }
  }
  //提交修改数据
  submitData(){
    let url=g.namespace+"/gpsys/commodity/updateCommodity";
    let send={
      commodityId:this.updateData.commodityId,
      commodityName:this.updateData.commodityName,
      commodityNumber:this.updateData.commodityNumber,
      commodityDescription:this.updateData.commodityDescription,
      commodityPrice:this.updateData.commodityPrice,
      commodityTypeId:this.updateData.commodityTypeId
    }
    $.ajax(url,{
      data:send,
      dataType:"jsonp",
      success:json=>{
        if(json.stage==1){
          this.delPic()
        }
        else{
          alert("修改失败："+json.msg)
        }
      }
    })
  }
  //删除图片
  delPic(){
    let url=g.namespace+"/gpsys/commodity/delPicByPicId";
    let send={
      picId:this.updateData.commodityId
    }
    $.ajax(url,{
      data:send,
      dataType:"jsonp",
      success:json=>{
        if(json.stage==1){
          this.updatePic()
        }
      }
    })

  }
  updatePic(){
    let url=g.namespace+"/gpsys/commodity/addCommodityPicture"
    for( var i=0;i<this.commodityPicData.length;i++){
      let send={
        picId:this.updateData.commodityId,
        picBase64:this.commodityPicData[i],
        picType:"2",
      }
      $.ajax(url,{
        data:send,
        dataType:"jsonp",
        success:json=>{
          if(json.stage==1){

          }
          else{
            alert(json.msg)
          }
        }
      })
      ;    }
    for( var i=0;i<this.commodityDetailPicData.length;i++){
      let send={
        picId:this.updateData.commodityId,
        picBase64:this.commodityDetailPicData[i],
        picType:"3",
      }
      $.ajax(url,{
        data:send,
        dataType:"jsonp",
        success:json=>{
          if(json.stage==1){

          }
          else{
            alert(json.msg)
          }
        }
      })
      ;    }
    alert("修改成功");
    this.close.emit()
  }
}
