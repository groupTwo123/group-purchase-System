import { Component, OnInit,Input } from '@angular/core';

@Component({
  selector: 'app-step',
  templateUrl: './step.component.html',
  styleUrls: ['./step.component.css']
})
export class StepComponent implements OnInit {
  @Input() step:any=0;
  constructor() { }

  ngOnInit() {
  }

}
