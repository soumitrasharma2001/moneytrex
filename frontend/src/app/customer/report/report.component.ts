import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component } from '@angular/core';
import { UserService } from 'src/app/user.service';

@Component({
  selector: 'app-report',
  templateUrl: './report.component.html',
  styleUrls: ['./report.component.css']
})
export class ReportComponent {
  public msg:string=""
  public translist:any=[];
  public months: string[]=[]
  public details: any=null
  constructor(private http:HttpClient,private userService:UserService){
    this.months=["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"];
  }
  public showTrans(myform:any){
    this.details={...myform.value,month:this.months[myform.value.month-1]}
    const token=this.userService.getUserInfo().token;
    const headers=new HttpHeaders({'Content-Type':"application/json",'Authorization':"Bearer "+token});
    console.log(myform.value)
    this.http.post("http://localhost:8080/auth/cust/searchtrans",myform.value,{headers}).subscribe((result:any)=>{
      if(result.status){
        this.translist=result.data
      }
      this.msg=result.msg;
    })
  }
  public generatePdf(){}
}
