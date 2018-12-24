import { Component, OnInit, Output,EventEmitter, Input} from '@angular/core';
import * as g from'./../../type';
@Component({
  selector: 'app-personal-center',
  templateUrl: './personal-center.component.html',
  styleUrls: ['./personal-center.component.css']
})
export class PersonalCenterComponent implements OnInit {
  @Input() userId:any='';
  @Output() close=new EventEmitter();

  userInfo:any={};
  gender:any={
    '1':"男",
    '0':"女"
  }
  userPic:any="../../../assets/headExample.gif"
  imgBase64:any=""
  constructor() { }

  ngOnInit() {

  }
  ngOnChanges(){
    if(this.userId==''){
      setTimeout(json=>{
        alert("请先登录");
        this.close.emit();
      },100)

    }
    else{
      this.getUserPic()
      this.getUserInfo();
    }
  }
  // 获取用户头像
  getUserPic(){
    let url=g.namespace+"/gpsys/commodity/getCommodityPicById";
    let send={
      picId:this.userId
    }
    $.ajax(url,{
      data:send,
      dataType:"jsonp",
      success:json=>{
        if(json.stage==1){
          if(json.data.length!=0){
            for(let item of json.data){
              if(item.picType=='1'){
                this.userPic=item.picBase64
              }
            }
          }
          else{
            this.userPic="../../../assets/headExample.gif"
          }
        }
      }
    })
  }
  //获取用户信息
  getUserInfo(){
    let url=g.namespace+"/gpsys/user/getUserInfoById";
    let send={
      userId:this.userId
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
  //点击上传图片
  uploadImgFun(){
    $("#uploadImg").click()
  }
  //上传图片
  addPic() {
    var MyTest = $("#uploadImg")[0].files[0];
    var reader = new FileReader();
    reader.readAsDataURL(MyTest);
    reader.onload = theFile => {
      var res = theFile.target['result'];
      this.imgBase64 = res;
      let url=g.namespace+"/gpsys/commodity/addCommodityPicture";
      let send={
        picId:this.userId,
        picBase64:this.imgBase64,
        picType:1,
      }

      $.ajax(url ,{
        data:send,
        dataType:'jsonp',
        success:json=>{
          if(json.stage==1){
            alert("上传成功");
            this.imgBase64="";
            this.getUserPic();
          }
          else{
            alert(json.msg)
          }
        }
      })
    }

  }
}
