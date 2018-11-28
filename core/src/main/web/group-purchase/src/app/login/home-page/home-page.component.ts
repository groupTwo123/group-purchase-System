import { Component, OnInit, Input, Output } from '@angular/core';

@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.css']
})
export class HomePageComponent implements OnInit {
  @Input() usernameFromParent:any;
  username:any="";
  isLogin:boolean=false;
  constructor() { }

  ngOnInit() {

  }
  ngOnChanges(){
    this.isLogin=false;
    this.username=this.usernameFromParent;
    if(this.username!=""){
      this.isLogin=true;
    }
  }

}
