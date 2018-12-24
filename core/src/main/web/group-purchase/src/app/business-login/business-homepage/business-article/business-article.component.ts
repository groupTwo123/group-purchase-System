import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import  * as g from "./../../../type"
@Component({
  selector: 'app-business-article',
  templateUrl: './business-article.component.html',
  styleUrls: ['./business-article.component.css']
})
export class BusinessArticleComponent implements OnInit {
  @Input() id:any=''
  @Output() changeHeight=new EventEmitter();
  bodyShow:boolean=false;
  articleList:any;
  articleType:any={};
  addPageShow:boolean=false;
  updatePageShow:boolean=false;
  updateDataObj:any;
  constructor() {
    this.articleType=g.articleType;
  }


  ngOnInit() {

  }
  ngOnChanges(){
    $("#businArticle").fadeIn(g.time);
    this.bodyShow=false;
    this.getArticleById();
    this.setTable();
  }
  //获取文章列表
  getArticleById(){
    this.articleList={}
    let url=g.namespace+"/gpsys/commodity/getArticleById";
    let send={
      id:this.id,
      idType:'1'
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

  //增加文章页面显示
  addShow(){
    this.addPageShow=true;
    this.changeHeight.emit()
  }
  closeAdd(){
    this.addPageShow=false;
    this.bodyShow=false
    // this.changeHeight.emit();
    this.getArticleById();
    this.setTable()
  }
  //删除文章
  deleteArticle(item){
    if(!confirm("确定要删除吗?")){
      return;
    }
    let url=g.namespace+"/gpsys/commodity/deleteArticle";
    let send={
      id:item.id,
      commodityId:item.commodityId,
      article:item.article,
      type:item.type
    }
    $.ajax(url,{
      data:send,
      dataType:"jsonp",
      success:json=>{
        if(json.stage==1){
          this.bodyShow=false;
          this.getArticleById();
          this.setTable();
          this.changeHeight.emit()
        }
        else{
          alert(json.msg)
        }
      }
    })
  }
  updateShow(item){
    this.updatePageShow=true;
    this.updateDataObj=item;
    this.changeHeight.emit();
  }
  closeUpdate(){
    this.updatePageShow=false;
    this.bodyShow=false
    // this.changeHeight.emit();
    this.getArticleById();
    this.setTable()
  }
}
