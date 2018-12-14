import { Component, OnInit,Input,Output,EventEmitter } from '@angular/core';

@Component({
  selector: 'app-business-commodity-list',
  templateUrl: './business-commodity-list.component.html',
  styleUrls: ['./business-commodity-list.component.css']
})
export class BusinessCommodityListComponent implements OnInit {
  @Input() username:any=''
  isAddCommodity:boolean=false;
  @Output() changeHeight=new EventEmitter();
  commodityList:any={};
  userInfo:any='';
  typeDataObj:any={};
  bodyShow:boolean=false;
  page:any=0;
  pageChose:any=0;
  pageObj:any=[];
  commodityListDataObj:any={}
  constructor() { }

  ngOnChanges(){
    this.bodyShow=false;
    this.getSellerInfo();
    this.getAllCommodityType();
  }
  ngOnInit() {
  }

  //改变父类高度
  changeHeightFun(){
    this.changeHeight.emit()
  }

  //获取所有商品类别
  getAllCommodityType(){
    let url="http://localhost:8080/gpsys/commodity/getCommodityType"
    $.ajax(url,{
      data:{},
      dataType:"jsonp",
      jsonp:"callback",
      type:"GET",
      success:json=>{
        this.typeDataObj=json.data
      }
    })
  }
  //获取商家信息
  getSellerInfo(){
    let url="http://localhost:8080/gpsys/seller/getSellerInfoById";
    let send={
      sellerId:this.username
    }
    $.ajax(url,{
      data:send,
      dataType:"jsonp",
      success:json=>{
        if(json.stage==1){
          this.userInfo=json.data;
         this.getCommodityList()
        }
        else{
          alert("服务器错误："+json.msg)
        }
      }
    })
  }
    //获取商品列表通过商家id
  getCommodityList(){
    let url="http://localhost:8080/gpsys/commodity/getCommodityInfo"
    let send={
      volumeIds:this.userInfo.volumeId
    }
    $.ajax(url,{
      data:send,
      dataType:"jsonp",
      success:json=>{
        if(json.stage==1){
          this.commodityList=json.data;
          this.dataTrans()
          console.log(this.commodityList)
        }
        else{
          alert("服务器错误："+json.msg)
        }
      }
    })
  }

  //显示增加商品
  addCommodityShow(){
    this.isAddCommodity=true;

  }
  //关闭增加
  closeAdd(){
    this.isAddCommodity=false;
    this.getCommodityList();
  }

  dataTrans() {
    setTimeout(json=>{


      $("#table").DataTable( {
        'columns': [{},{},{},{},{},{},{}],
        "pageLength":20,
        'searching' : true,
        'language': {
          'emptyTable': "<div class='cmn_warning tip'>无数据</div>",
          'loadingRecords': '处理中',
          'processing': '处理中',
          'lengthMenu': '_MENU_ 行/页',
          'zeroRecords': "<div class='cmn_warning tip'>无数据</div>",
          'info': '共_TOTAL_条记录',
          'infoEmpty': '共0条',
          'infoFiltered': '(从_MAX_条中筛选)',
          'infoPostFix': '',
          'infoPages': '_CUR_/_MENU_ 页',
          'jumpToPage': '转到',
          'search': '查询',
          'paginate': {
            'first': '首页',
            'previous': '上一页',
            'next': '下一页',
            'last': '末页'
          }
        },
      })

      this.changeHeight.emit();
    },50)
    this.bodyShow=true;

  }
}
