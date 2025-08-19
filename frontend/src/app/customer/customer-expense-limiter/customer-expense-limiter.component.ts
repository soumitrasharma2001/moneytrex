import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/user.service';

@Component({
  selector: 'app-customer-expense-limiter',
  templateUrl: './customer-expense-limiter.component.html',
  styleUrls: ['./customer-expense-limiter.component.css']
})
export class CustomerExpenseLimiterComponent implements OnInit {
  public limit:any=null
  showModal = false;
  newLimit = '';
  constructor(private http: HttpClient, private userService: UserService) { }
  ngOnInit(): void {
    const token = this.userService.getUserInfo().token
    const headers = new HttpHeaders({
      'Content-type': "application/json",
      'Authorization': "Bearer " + token
    })
    this.http.get("http://localhost:8080/auth/cust/getlimit",{headers}).subscribe((result:any)=>{
      if(result.status){
        this.limit=result.data
      }
    })
  }

  public addExpense(title: string, limits: string) {
    const token = this.userService.getUserInfo().token
    const headers = new HttpHeaders({
      'Content-type': "application/json",
      'Authorization': "Bearer " + token
    })
    this.http.post("http://localhost:8080/auth/cust/savelimit", { title, limits }, { headers })
      .subscribe((result: any) => {
        if (result.status) {
          this.limit=result.data
        }
      })
  }
  public removeLimit(){
    const token = this.userService.getUserInfo().token
    const headers = new HttpHeaders({
      'Content-type': "application/json",
      'Authorization': "Bearer " + token
    })
    this.http.delete("http://localhost:8080/auth/cust/dellimit",{headers}).subscribe((result:any)=>{
      if(result.status){
        this.limit=null
      }
    })
  }
  public errmsg:string=""
  public updateLimit() {
    const token = this.userService.getUserInfo().token
    const headers = new HttpHeaders({
      'Content-type': "application/json",
      'Authorization': "Bearer " + token
    })
    console.log(token)
    this.http.put("http://localhost:8080/auth/cust/updlimit/"+this.newLimit,{},{headers})
    .subscribe((result:any)=>{
      if(result.status){
        this.limit=result.data
        this.closeModal();
      }
      else{
        this.errmsg=result.msg
      }
    })
    
  }

  

  closeModal() {
    this.showModal = false;
  }
}
