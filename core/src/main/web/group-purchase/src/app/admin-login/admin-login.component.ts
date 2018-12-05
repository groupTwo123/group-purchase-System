import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-admin-login',
  templateUrl: './admin-login.component.html',
  styleUrls: ['./admin-login.component.css']
})
export class AdminLoginComponent implements OnInit {

  constructor() { }

  ngOnInit() {
    document.getElementById("loginBox").style.height=(document.documentElement.scrollHeight).toString()+'px';
    document.getElementById("loginBox").style.width=(document.documentElement.scrollWidth).toString()+'px';
  }

}
