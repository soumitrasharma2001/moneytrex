import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/user.service';

@Component({
  selector: 'app-customer-transaction',
  templateUrl: './customer-transaction.component.html',
  styleUrls: ['./customer-transaction.component.css']
})
export class CustomerTransactionComponent implements OnInit{
  public updateIndex:number=-1
  public newTitle:string=""
  public newType:string=""
  public newDate:string=""
  public newAmount:string=""
  public newDesc:string=""
  public msg:string=""
  public translist:any[]=[]
  public currDate:string=""
  constructor(private http:HttpClient,private userService:UserService){
    const today=new Date()
    this.currDate=today.toISOString().substring(0,10)
  }
  ngOnInit(): void {
      const token=this.userService.getUserInfo().token
      const headers=new HttpHeaders({'Authorization':"Bearer "+token})
      this.http.get("http://localhost:8080/auth/cust/translist",{headers}).subscribe((result:any)=>{
        this.translist=result.data
      })
  }
  public makeTrans(fr:any){
    const token=this.userService.getUserInfo().token
    const headers=new HttpHeaders({'Content-Type':"application/json",'Authorization':'Bearer '+token});
    console.log(fr.value)
    this.http.post("http://localhost:8080/auth/cust/save",fr.value,{headers}).subscribe((result:any)=>{
      if(result.status)
        this.translist.push(result.data)
      this.msg=result.msg
    })
  }
  public setUpdateIndex(idx:number){
    this.updateIndex=idx;
    let transaction=this.translist[idx]
    this.newTitle=transaction.title;
    this.newAmount=transaction.amount;
    this.newDate=transaction.entrydate
    this.newDesc=transaction.description;
    this.newType=transaction.type;
  }
  public delTran(transaction:any){
    const token=this.userService.getUserInfo().token
    const headers=new HttpHeaders({'Content-Type':"application/json",'Authorization':'Bearer '+token});
    this.http.delete("http://localhost:8080/auth/cust/del/"+transaction.transid,{headers}).subscribe((result:any)=>{
      if(result.status)
        this.translist.splice(this.translist.indexOf(transaction),1)
    })
  }
  public updateData(id:number){
    const token=this.userService.getUserInfo().token
    const headers=new HttpHeaders({'Content-Type':"application/json",'Authorization':'Bearer '+token});
    this.http.put("http://localhost:8080/auth/cust/upd/"+id,
      {
        title:this.newTitle,
        type:this.newType,
        entrydate:this.newDate,
        amount:this.newAmount,
        description:this.newDesc
      },{headers}).subscribe((result:any)=>{
        if(result.status){
          console.log(result.data)
          this.translist=this.translist.map((tr)=>tr.transid===id?result.data:tr)
          this.setUpdateIndex(-1)
        }
      })
  }
}
