import { Component, OnInit,Output,EventEmitter,Input } from '@angular/core';

@Component({
  selector: 'app-back-commodity',
  templateUrl: './back-commodity.component.html',
  styleUrls: ['./back-commodity.component.css']
})
export class BackCommodityComponent implements OnInit {
  @Output()
  closeFun = new EventEmitter();
  @Input()
  backCommodityData:any;
  back_reason:any='';
  ps_reason:any='';
  constructor() { }

  ngOnInit() {
  }

  close(){
    this.closeFun.emit();
  }
  submitData(){
    var reason='';
    if(this.back_reason==''){
      alert("请填写申请理由");
      return;
    }
    if(this.ps_reason==''){
      reason="退货理由："+this.back_reason+",补充理由：无";
    }
    else{
      reason="退货理由："+this.back_reason+",补充理由："+this.ps_reason;
    }
    let url="http://localhost:8080/gpsys/order/addBackCommodity";
    let send={
      back_order_id:this.backCommodityData.orderData.orderId,
      user_id:"1101",
      commodity_id:this.backCommodityData.CommodityData.commodityId,
      commodity_number:this.backCommodityData.orderData.commodityNumber,
      money:this.backCommodityData.orderData.money,
      back_reason:reason,
      state:this.backCommodityData.orderData.state
    }
    $.ajax(url,{
      data:send,
      dataType:"jsonp",
      success:json=>{
        if(json.stage==1){
          alert("申请成功");
          this.closeFun.emit();
        }
        else{
          alert("申请失败："+json.msg)
        }
      }
    })
  }
}
