import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import  * as g from "./../../../../type"
@Component({
  selector: 'app-update-article',
  templateUrl: './update-article.component.html',
  styleUrls: ['./update-article.component.css']
})
export class UpdateArticleComponent implements OnInit {
  @Input() updateData:any;
  @Output() close=new EventEmitter()
  articleType:any=[];
  sellerVolumeId:any='';
  commoditySelect:any=[];
  constructor() { }

  ngOnInit() {
  }
  ngOnChanges(){
    $("#updateArticle").fadeIn(g.time);
    for(let item in g.articleType){
      this.articleType.push(g.articleType[item]);
    }
    this.getSellerVoluemId();
  }
  getSellerVoluemId(){
    let url=g.namespace+'/gpsys/seller/getSellerInfoById';
    let send={
      sellerId:this.updateData.id
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
  submit(){
    console.log(this.updateData)
    if(this.updateData.article==''){
      alert("请填入必要信息");
      return;
    }

    let url=g.namespace+"/gpsys/commodity/updateArticle";
    let send={
      id:this.updateData.id,
      commodityId:this.updateData.commodityId,
      article:this.updateData.article,
      type:this.updateData.type
    }
    console.log(send)
    $.ajax(url,{
      data:send,
      dataType:'jsonp',
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
