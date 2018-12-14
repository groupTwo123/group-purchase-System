import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-file-upload',
  templateUrl: './file-upload.component.html',
  styleUrls: ['./file-upload.component.css']
})
export class FileUploadComponent implements OnInit {
  fileData:any;
  path:any="C:/Users/Administrator/Desktop/img/";
  filePath:any;
  constructor() { }

  ngOnInit() {
  }

  changFun(){
    var MyTest = $("#file")[0].files[0];
    var reader = new FileReader();
    reader.readAsDataURL(MyTest);
    reader.onload = function(theFile) {
      var res=theFile.target['result'];
      console.log(res)
      let url="http://localhost:8080/gpsys/commodity/saveCommodityPicture";
      let send={
        url:res

      }
      $.ajax(url,{
        data:send,
        dataType:"jsonp",
        success:json=>{
          console.log(json);
        }
      })
    }
    }


}
