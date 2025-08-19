import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'my-app';
  public age:number=24
  public increaseAge():void{
    this.age+=10;
  }
  public names:string[]=["Ritik","Vishal","Vivek","Parth","Ranjeet"]
  public ages:number[]=[22,11,33,18,22,9,13]
  public students:any[]=[
    {roll:111, name:"Shubham", course:"BTECH", sem:2},
    {roll:112, name:"Amit", course:"BCA", sem:3},
    {roll:113, name:"Priya", course:"MBA", sem:1},
    {roll:114, name:"Rahul", course:"BTECH", sem:4},
    {roll:115, name:"Sneha", course:"MCA", sem:2},
    {roll:116, name:"Vikas", course:"BBA", sem:3},
    {roll:117, name:"Neha", course:"BTECH", sem:1},
    {roll:118, name:"Rohit", course:"MBA", sem:2},
    {roll:119, name:"Anjali", course:"BCA", sem:4},
    {roll:120, name:"Karan", course:"MCA", sem:1}
  ]
}
