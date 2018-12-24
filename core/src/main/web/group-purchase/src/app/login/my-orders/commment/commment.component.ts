import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import * as g from"./../../../type";
@Component({
  selector: 'app-commment',
  templateUrl: './commment.component.html',
  styleUrls: ['./commment.component.css']
})
export class CommmentComponent implements OnInit {
  @Input() data:any;
  @Output() close=new EventEmitter();
  @Output() updateOrder=new EventEmitter();
  @Input() userId:any
  article:any=''
  val:any='';
  commentType:any='2';
  constructor() { }
  ngOnChanges(){
    console.log(this.data)
  }
  ngOnInit() {
  }
  submit(){
    this.commentType=$('input:radio[name="commodityChose"]:checked').val();

      if(this.article==''){
        alert("请填入必要信息");
        return;
      }

    let url=g.namespace+"/gpsys/commodity/addArticle";
    let send={
      id:this.userId,
      commodityId:this.data.CommodityData.commodityId,
      article:this.article,
      type:"1",
      commentType:this.commentType
    }
    console.log(send)
    $.ajax(url,{
      data:send,
      dataType:'jsonp',
      success:json=>{
        if(json.stage==1){
          alert("评论成功");
          this.updateOrder.emit();
        }
        else{
          alert(json.msg)
        }
      }
    })
  }
}
