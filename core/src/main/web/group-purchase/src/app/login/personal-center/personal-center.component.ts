import { Component, OnInit, Output,EventEmitter} from '@angular/core';
import * as g from'./../../type';
@Component({
  selector: 'app-personal-center',
  templateUrl: './personal-center.component.html',
  styleUrls: ['./personal-center.component.css']
})
export class PersonalCenterComponent implements OnInit {
  @Output() close=new EventEmitter();
  userInfo:any={};
  gender:any={
    '1':"男",
    '0':"女"
  }
  constructor() { }

  ngOnInit() {
    this.getUserInfo();
  }
  //获取用户信息
  getUserInfo(){
    let url=g.namespace+"/gpsys/user/getUserInfoById";
    let send={
      userId:"1101"
    }
    $.ajax(url,{
      data:send,
      dataType:"jsonp",
      success:json=>{
        if(json.stage==1){
          this.userInfo=json.data;
          console.log(this.userInfo)
        }
        else{
          alert("服务器错误："+json.msg);
        }
      }
    })
  }
  //提交保存数据
  submitData(){
    if(!confirm("确定要提交吗？")){
      return;
    }
    for(let item in this.userInfo){
      if(this.userInfo[item]==''&&item!='rebateId'&&item!='type'&&item!='level'){
        alert("请填写相关信息")
        return;
      }
    }
    let url=g.namespace+"/gpsys/user/updateUserMessage"
    let send={
      id:this.userInfo.id,
      userName:this.userInfo.userName,
      gender:this.userInfo.gender,
      birth:this.userInfo.birth,
      phone:this.userInfo.phone,
      email:this.userInfo.email,
      area:this.userInfo.area,
    }
    $.ajax(url,{
      data:send,
      dataType:"jsonp",
      success:json=>{
        if(json.stage==1){
          alert("修改成功")
        }
        else{
          alert("修改失败："+json.msg)
        }
      }
    })
  }
  //返回首页
  back(){
    this.close.emit()
  }
}
