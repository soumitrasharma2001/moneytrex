import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';

@Component({
  selector: 'app-contact',
  templateUrl: './contact.component.html',
  styleUrls: ['./contact.component.css']
})
export class ContactComponent {
  public departments:any[]=[]
  constructor(private http:HttpClient){}
  public add(dno:string,dname:string,dloc:string){
    this.http.post("https://rest6pm.pythonanywhere.com/myapp/dept",{dno,dname,dloc})
    .subscribe((result:any)=>{
      
    })
  }
}
