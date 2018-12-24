import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import  * as g from "./../../../../type"
@Component({
  selector: 'app-add-article',
  templateUrl: './add-article.component.html',
  styleUrls: ['./add-article.component.css']
})
export class AddArticleComponent implements OnInit {
  @Input()  id:any='';
  @Output() close=new EventEmitter();
  articleType:any=[];
  sellerVolumeId:any='';
  commoditySelect:any=[];
  dataObj:any={
    commodityId:'',
    article:"",
    type:""
  }
  constructor() {

  }

  ngOnInit() {

  }
  ngOnChanges(){
    this.dataObj={
      commodityId:'',
      article:"",
      type:""
    }
    for(let item in g.articleType){
      this.articleType.push(g.articleType[item]);
    }
    this.getSellerVoluemId();
  }
  getSellerVoluemId(){
    let url=g.namespace+'/gpsys/seller/getSellerInfoById';
    let send={
      sellerId:this.id
    }
    $.ajax(url,{
      data:send,
      dataType:'jsonp',
      success:json=>{
        if(json.stage==1){
          this.sellerVolumeId=json.data.volumeId;
          this.getCommodityListByVolumeId()
        }
        else{
          alert(json.msg)
        }
      }
    })

  }
  getCommodityListByVolumeId(){
    this.commoditySelect=[]
    let url=g.namespace+"/gpsys/commodity/getCommodityInfo";
    let send={
      volumeIds:this.sellerVolumeId
    }
    $.ajax(url,{
      data:send,
      dataType:"jsonp",
      success:json=>{
        if(json.stage==1){
          for(let item of json.data){
            this.commoditySelect.push(item)
          }
        }
        else{
          alert(json.msg)
        }
      }
    })
  }
  //提交数据
  submit(){
    for(let item in this.dataObj){
      if(this.dataObj[item]==''){
        alert("请填入必要信息");
        return;
      }
    }
    let url=g.namespace+"/gpsys/commodity/addArticle";
    let send={
      id:this.id,
      commodityId:this.dataObj.commodityId,
      article:this.dataObj.article,
      type:this.dataObj.type,
      commentType:'0'
    }
    console.log(send)
    $.ajax(url,{
      data:send,
      dataType:'jsonp',
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
}
