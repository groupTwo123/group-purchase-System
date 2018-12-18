import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'app-commodity-pic',
  templateUrl: './commodity-pic.component.html',
  styleUrls: ['./commodity-pic.component.css']
})
export class CommodityPicComponent implements OnInit {
  @Input() picData:any=[];
  @Output() close=new EventEmitter();
  pic:any=[];
  picDetail:any=[]
  constructor() { }

  ngOnInit() {
  }
  ngOnChanges(){
    console.log(this.picData)
    this.picDataTrans();
  }
  //数据处理
  picDataTrans(){
    this.pic=[];
    this.picDetail=[]
    for(let item of this.picData.picData){
      if(item.picType=='2'){
        this.pic.push(item)
      }
      else{
        this.picDetail.push(item)
      }
    }
  }

}
