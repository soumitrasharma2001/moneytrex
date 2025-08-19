import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component,OnInit } from '@angular/core';
import { UserService } from 'src/app/user.service';

interface Transaction {
  id: string;
  desc: string;
  amt: number;
  type: 'income' | 'expense';
  date: string;
}
@Component({
  selector: 'app-customer-home',
  templateUrl: './customer-home.component.html',
  styleUrls: ['./customer-home.component.css']
})
export class CustomerHomeComponent implements OnInit{
   public username:String="";
  constructor(private userService:UserService,private http:HttpClient){
    this.username=userService.getUserInfo().name;
  }
   summary = {
    currentBalance: 0.0,
    income: 0.0,
    expense: 0.0,
    expenseLimit:0
  };
  ngOnInit(): void {
    const token=this.userService.getUserInfo().token
    console.log(token)
    const headers=new HttpHeaders({'Authorization':'Bearer '+token})
    this.http.get("http://localhost:8080/auth/cust/getsummary",{headers})
    .subscribe((result:any)=>{
      if(result.status){
        this.summary={...result.data}
        console.log("Fetched from server",result.data)
        console.log(this.summary)
      }
    })
  }

  formatCurrency(value: number): string {
    return `₹${Math.abs(value).toFixed(2)}`;
  }

  
}
