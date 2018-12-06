import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-file-upload',
  templateUrl: './file-upload.component.html',
  styleUrls: ['./file-upload.component.css']
})
export class FileUploadComponent implements OnInit {
  fileData:any;
  path:any="C:/Users/Administrator/Desktop/img/";
  constructor() { }

  ngOnInit() {
  }

  changFun(){
    // this.fileData=new FormData()
    // this.fileData.append("file",$("#file")[0].files[0]);
    // console.log(this.fileData)
    let url="http://localhost:8080/gpsys/commodity/addCommodityPicture/"
    $.ajax(url,{
      data:$('#form').serialize(),
      dataType:"jsonp",
      jsonp:"callback",
      processData:false,
      contentType:"multipart/form-data",
      success:json=>{
        console.log(json);
      }
    })
  }

}
