import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-business-register',
  templateUrl: './business-register.component.html',
  styleUrls: ['./business-register.component.css']
})
export class BusinessRegisterComponent implements OnInit {
  step:number=0;
  constructor() { }

  ngOnInit() {
    this.step=0;
    document.getElementById("registerBox").style.height=(document.body.scrollHeight).toString()+'px';
    document.getElementById("registerBox").style.width=(document.body.scrollWidth).toString()+'px';
  }
  //下一步点击炒作
  nextStyleFun(){
    this.step++;
  }
}
