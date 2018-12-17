export const namespace: string="http://localhost:8080";
export const orderState:any={
  0:'未付款',
  1:'已付款',
  2:'商家已发货',
  3:'交易完成',
  4:'待评价',
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
