
export const namespace: string="http://localhost:8080";
export const orderState:any={
  0:'未付款',
  1:'已付款',
  2:'商家已发货',
  3:'交易完成',
  4:'已评价',
  5:'取消订单申请中',
  6:'交易关闭',
  7:'退货申请中',
  8:'取消退货申请中',
  9:'已退货'
}
export const dataTable:any={
  "pageLength":20,
  'searching' : true,
  'language': {
    'emptyTable': "<div class='cmn_warning tip'>无数据</div>",
    'loadingRecords': '处理中',
    'processing': '处理中',
    'lengthMenu': '_MENU_ 行/页',
    'zeroRecords': "<div class='cmn_warning tip'>无数据</div>",
    'info': '',
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
}
export const articleType:any={
  1:'评论',
  2:'促销',
  3:'公告',
  4:'会员专享',
  5:'积分商城'
}
export const time:any=1000;

export  const  passwordLevel:any= {
  'weak' :  /^[0-9]{0,1000}$|^[a-zA-Z]{0,1000}$/,
  'middle': /^[A-Za-z0-9]{0,1000}$/,
  'strong' : /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[^]{8,16}$/
}
export const  emailCheck=/^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/

export const picType:any={
  1:'用户头像',
  2:'商品图片',
  3:'商品描述图片',
  4:'商家头像',
}
