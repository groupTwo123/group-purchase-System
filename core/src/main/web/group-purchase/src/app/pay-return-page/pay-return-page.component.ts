import { Component, OnInit } from '@angular/core';
import  * as g from '../type'
@Component({
  selector: 'app-pay-return-page',
  templateUrl: './pay-return-page.component.html',
  styleUrls: ['./pay-return-page.component.css']
})
export class PayReturnPageComponent implements OnInit {
  constructor() { }

  ngOnInit() {
    this.setSessionToOne()
  }
  setSessionToOne(){
    // var get=setInterval(json=>{
    //   let url=g.namespace+"/gpsys/order/getPaySession";
    //   $.ajax(url,{
    //     data:{stage:"0"},
    //     dataType:'jsonp',
    //     success:json=>{
    //       if(json.stage==1){
    //         console.log(json.data.paySessionId)
    //         if(json.data.paySessionId=='1'){
    //           alert("123")
    //           clearInterval(get)
    //         }
    //         else{
    //           console.log("**")
    //         }
    //       }
    //     }
    //   })
    // },2000)

      let url=g.namespace+"/gpsys/order/getPaySession";
      $.ajax(url,{
        data:{stage:"1"},
        dataType:'jsonp',
        success:json=>{
          if(json.stage==1){
           window.location.href="http://localhost:4200/homePage"
          }
        }
      })
  }

}
