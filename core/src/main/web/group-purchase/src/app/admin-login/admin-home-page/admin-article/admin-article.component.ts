import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import  * as g from "./../../../type"

@Component({
  selector: 'app-admin-article',
  templateUrl: './admin-article.component.html',
  styleUrls: ['./admin-article.component.css']
})
export class AdminArticleComponent implements OnInit {
  @Output() changeHeight=new EventEmitter();
  bodyShow:boolean=false;
  articleList:any;
  articleType:any={};
  constructor() {
    this.articleType=g.articleType;
  }

  ngOnInit() {
    this.bodyShow=false;
    this.getArticle();
    this.setTable();
  }
  ngOnChanges(){

  }
  //获取文章列表
  getArticle(){
    this.articleList={}
    let url=g.namespace+"/gpsys/commodity/getArticleById";
    let send={
      id:"",
      idType:"0"
    }
    $.ajax(url,{
      data:send,
      dataType:'jsonp',
      success:json=>{
        if(json.stage==1){
          this.articleList=json.data;
          console.log(this.articleList)
          this.bodyShow=true;
        }
        else{
          alert(json.msg)
        }
      }
    })
  }
  //设置表格属性分页
  setTable() {
    setTimeout(json => {
      if ($("#table").dataTable()) {
        // $("#table").dataTable().fnClearTable();    //清空数据
        $("#table").dataTable().fnDestroy();         //销毁datatable
      }
      $("#table").dataTable(g.dataTable);
      this.changeHeight.emit();
    }, 100)
  }
  //改变状态
  changeArticleState(item,state){
    let url=g.namespace+"/gpsys/commodity/changeArticleState";
    let send={
      id:item.id,
      commodityId:item.commodityId,
      article:item.article,
      type:item.type,
      state:state
    }
    $.ajax(url,{
      data:send,
      dataType:'jsonp',
      success:json=>{
        if(json.stage==1){
          alert("修改成功");
          this.bodyShow=false;
          this.getArticle();
          this.setTable();
        }
        else{
          alert(json.msg)
        }
      }
    })
  }

}
