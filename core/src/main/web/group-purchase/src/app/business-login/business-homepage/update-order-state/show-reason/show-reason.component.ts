import { Component, OnInit,Output,EventEmitter,Input } from '@angular/core';
import * as g from './../../../../type';
@Component({
  selector: 'app-show-reason',
  templateUrl: './show-reason.component.html',
  styleUrls: ['./show-reason.component.css']
})
export class ShowReasonComponent implements OnInit {

  @Output()
  close = new EventEmitter();
  @Input()
  data:any;
  back_commodity_data:any='';

  constructor() { }

  ngOnInit() {
  }
  ngOnChanges(){
    this.getBackReason()
  }

  closeFun(){
    this.close.emit();
  }
  getBackReason(){
    let url=g.namespace+"/gpsys/commodity/getBackReasonByOrderId";
    let send={
      orderiId:this.data.orderId
    }
    $.ajax(url,{
      data:send,
      dataType:'jsonp',
      success:json=>{
        if(json.stage==1){
          this.back_commodity_data=json.data;
          console.log(this.back_commodity_data)
        }
        else{
          alert(json.msg)
        }
      }
    })
  }

}
