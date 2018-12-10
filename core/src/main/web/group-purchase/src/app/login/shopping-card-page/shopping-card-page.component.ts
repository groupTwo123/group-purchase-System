import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-shopping-card-page',
  templateUrl: './shopping-card-page.component.html',
  styleUrls: ['./shopping-card-page.component.css']
})
export class ShoppingCardPageComponent implements OnInit {
  $thr:any=$('table thead tr');
  $tbr:any=$('table tbody tr');
  dataList:any;
  tbShow:boolean=false;
  constructor() { }

  ngOnInit() {
    this.getShopCarData()
  }


  //全选点击操作
  checkAllFun(){
    if($("#checkAll").prop("checked")){
      this.$tbr=$('table tbody tr');
      this.$tbr.find('input').prop('checked',true);
      return;
    }
    this.$tbr=$('table tbody tr');
    this.$tbr.find('input').prop('checked',false);
  }
  //当选中其中之一
  checkItemFun(){
   $("#checkAll").prop('checked',this.$tbr.find('input:checked').length == this.$tbr.length ? true : false);
  }
  //获取购物车列表
  getShopCarData(){
    this.tbShow=false;
    let url="http://localhost:8080/gpsys/shopcar/getShopcarInfo"
    let send={
      userId:"1101"
    }
    $.ajax(url,{
      data:send,
      dataType:"jsonp",
      jsonp:"callback",
      success:json=>{
       if(json.stage==1){
         this.dataList=[];
         for(let item in json.data){
           if(parseInt(json.data[item].commodityData.commodityNumber)>parseInt(json.data[item].commodityNumber)){
             json.data[item]['hasNumber']='true';
           }else{
             json.data[item]['hasNumber']='false';
           }
           json.data[item]['totalPrice']=parseInt(json.data[item].commodityNumber)*parseInt(json.data[item].commodityData.commodityPrice)
           this.dataList.push(json.data[item]);
         }
         this.tbShow=true;
         console.log(this.dataList)
       }
       else{
         alert("服务器错误："+json.msg)
       }
      }
    })
  }
  //购物车点击减少
  reduceNumber(commodityId,commodityNumber,commodityAllNumber,index){
    if(commodityNumber!='1'){
      let url="http://localhost:8080/gpsys/shopcar/changeShoppingCarVolumeNumById";
      let send={
        commodityId:commodityId,
        changeNum:(parseInt(commodityNumber)-1).toString(),
        userId:"1101"
      }
      $.ajax(url,{
        data:send,
        dataType:"jsonp",
        json:"callback",
        success:json=>{
          if(json.stage==1){
            if(parseInt(commodityAllNumber)>parseInt(commodityNumber)-1){
              this.dataList[index]['hasNumber']='true';
            }else{
              this.dataList[index]['hasNumber']='false';
            }
            this.dataList[index]['totalPrice']=parseInt(this.dataList[index].commodityNumber)*parseInt(this.dataList[index].commodityData.commodityPrice)
            this.dataList[index]['commodityNumber']=(parseInt(commodityNumber)-1).toString();
          }
          else{
            alert("修改失败："+json.msg)
          }
        }
      })
    }
    else{
      alert("已为最小值");
      return;

    }
  }
  //购物车点击减少
  AddNumber(commodityId,commodityNumber,commodityAllNumber,index){
      let url="http://localhost:8080/gpsys/shopcar/changeShoppingCarVolumeNumById";
      let send={
        commodityId:commodityId,
        changeNum:(parseInt(commodityNumber)+1).toString(),
        userId:"1101"
      }
      $.ajax(url,{
        data:send,
        dataType:"jsonp",
        json:"callback",
        success:json=>{
          if(json.stage==1){
            if(parseInt(commodityAllNumber)>parseInt(commodityNumber)+1){
              this.dataList[index]['hasNumber']='true';
            }else{
              this.dataList[index]['hasNumber']='false';
            }
            this.dataList[index]['totalPrice']=parseInt(this.dataList[index].commodityNumber)*parseInt(this.dataList[index].commodityData.commodityPrice)
            this.dataList[index]['commodityNumber']=(parseInt(commodityNumber)+1).toString();
          }
          else{
            alert("修改失败："+json.msg)
          }
        }
      })
    }
  //删除购物车商品
  delete(commodityId){
    if(confirm("确定要删除吗？")){
      let url="http://localhost:8080/gpsys/shopcar/delShopcarInfo"
      let send={
        commodityIds:commodityId,
        userId:"1101"
      }
      $.ajax(url,{
        data:send,
        dataType:"jsonp",
        jsonp:"callback",
        success:json=>{
          if(json.stage==1){
            alert("删除成功");
            this.getShopCarData();
          }
          else{
            alert("删除失败:"+json.msg);
          }
        }
      })
    }
    else{
      return;
    }

  }
}
