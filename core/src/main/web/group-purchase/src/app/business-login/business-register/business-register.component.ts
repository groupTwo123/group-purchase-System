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
    document.getElementById("registerBox").style.height=(document.documentElement.scrollHeight).toString()+'px';
    document.getElementById("registerBox").style.width=(document.documentElement.scrollWidth).toString()+'px';
    document.getElementById("registerBox").style.backgroundImage="url('../../../assets/background.png')";
  }
  //下一步点击炒作
  nextStyleFun(){
    this.step++;
  }
}
