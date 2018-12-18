import { Component, OnInit ,Output, Input, EventEmitter} from '@angular/core';
import * as g from'./../../../type';
@Component({
  selector: 'app-add-commodity',
  templateUrl: './add-commodity.component.html',
  styleUrls: ['./add-commodity.component.css']
})
export class AddCommodityComponent implements OnInit {
  @Input() sellerInfo:any={};
  @Output() close=new EventEmitter();
  @Output() changeHeight=new EventEmitter();
  typeDataObj:any;
  commodityPicData:any=[];
  commodityDetailPicData:any=[];
  dataObj:any={
    commodityName:'',
    commodityNum:'',
    commodityPrice:'',
    commodityDescription:'',
    type:'1'
  }
  newCommodityId:any='';  //用于存储新建的商品id
  constructor() { }

  ngOnInit() {
    this.getAllCommodityType()
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
        this.typeDataObj=json.data

      }
    })
  }

  //关闭
  closeFun(){
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
  //关闭增加
  closeAddFun(){
    if(!(confirm("警告！数据将不会保存，是否继续"))){
      return;
    }
    this.close.emit();
  }
  //提交数据
  submitData(){
    for(let item in this.dataObj){
      if(this.dataObj[item]==''){
        alert("请填写必要数据")
        return;
      }
    }
    if(this.commodityDetailPicData.length==0||this.commodityPicData.length==0){
      alert("请填写必要数据")
      return;
    }
    let url=g.namespace+"/gpsys/commodity/addCommodityById";
    let send={
      volumeIds:this.sellerInfo.volumeId,
      commodityName:this.dataObj.commodityName,
      commodityNumber:this.dataObj.commodityNum,
      commodityDescription:this.dataObj.commodityDescription,
      commodityPrice:this.dataObj.commodityPrice,
      commodityType:this.dataObj.type
    };
    $.ajax(url,{
      data:send,
      dataType:"jsonp",
      success:json=>{
        if(json.stage==1){
          this.newCommodityId=json.data
          this.addPic();
        }
        else if(json.stage==2){
          alert(json.msg)
          this.close.emit();
        }
      }
    })
  }
  //提交图片上传
  addPic(){
    let url=g.namespace+"/gpsys/commodity/addCommodityPicture"
    for( var i=0;i<this.commodityPicData.length;i++){
      let send={
        picId:this.newCommodityId,
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
        picId:this.newCommodityId,
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
    alert("添加成功");
    this.close.emit()
  }
}
