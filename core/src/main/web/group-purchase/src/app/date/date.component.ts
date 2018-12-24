import { Component, OnInit,Input,Output,EventEmitter } from '@angular/core';

@Component({
  selector: 'app-date',
  templateUrl: './date.component.html',
  styleUrls: ['./date.component.css']
})
export class DateComponent implements OnInit {
  timeChose:any=moment().format('YYYY-MM-DD ');
  private _bind = {};
  @Input()
  get bind() {
    return this._bind;
  }

  set bind(value) {
    this._bind = value;
    this.bindChange.emit(this._bind);
  }

  @Output()
  bindChange = new EventEmitter();
  constructor() { }

  ngOnInit() {

  }
  ngOnChanges(){
    this.bind=moment().format('YYYY-MM-DD ');
    this.dateRangePicker();
  }
  dateRangePicker(){

    let picker:any=$('#startEndTime');
    let dataRageOption:Object={
      singleDatePicker: true,
      showDropdowns: true,
      autoApply:true,
      autoUpdateInput: false,
      timePicker24Hour : false,
      timePicker : false,
      "locale": {
        format: 'YYYY-MM-DD',
        applyLabel: "应用",
        cancelLabel: "取消",
        resetLabel: "重置",
        daysOfWeek: ["日","一","二","三","四","五","六"],
        monthNames: ["一月","二月","三月","四月","五月","六月","七月","八月","九月","十月","十一月","十二月"]
      }
    };
    picker.daterangepicker(dataRageOption,function (start, end, label) {
      if(!this.startDate){
        this.element.val('');
      }else{
        this.element.val(this.startDate.format(this.locale.format));
      }
    });

  }

}
