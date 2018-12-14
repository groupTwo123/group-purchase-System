import { Component, OnInit ,Input,Output,EventEmitter} from '@angular/core';
/*
* pageObj为页数数组,page为总页数，pageChose为选择页数
* <app-page [(page)]="page" [(pageChose)]="pageChose" [(pageObj)]="pageObj"></app-page>
 * */
@Component({
  selector: 'app-page',
  templateUrl: './page.component.html',
  styleUrls: ['./page.component.css']
})
export class PageComponent implements OnInit {
  private _page = {};
  private _pageChose = {};
  private _pageObj = {};
  @Input()
  get page() {
    return this._page;
  }
  set page(value) {
    this._page = value;
    this.pageChange.emit(this._page);
  }
  @Output()
  pageChange = new EventEmitter();

  @Input()
  get pageChose() {
    return this._pageChose;
  }
  set pageChose(value) {
    this._pageChose = value;
    this.pageChoseChange.emit(this._pageChose);
  }
  @Output()
  pageChoseChange = new EventEmitter();

  @Input()
  get pageObj() {
    return this._pageObj;
  }
  set pageObj(value) {
    this._pageObj = value;
    this.pageObjChange.emit(this._pageObj);
  }
  @Output()
  pageObjChange = new EventEmitter();

  constructor() { }

  ngOnInit() {
  }
  ngOnChanges(){


  }
  //选择页数
  pageChoseFun(page){

    if(page==-1||page==this.page){
      alert("已经到达首页或尾页")
      return;
    }
    this.pageChose=page
  }

}
