import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { UserService } from '../user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  public msg:String="";
    constructor(private http:HttpClient,private userService:UserService,private router:Router){}
    public login(email:String,pass:String){
      this.http.post("http://localhost:8080/money/login",{
        email:email,
        password:pass
      }).subscribe((result:any)=>{
        if(result.status){
          this.userService.setUserInfo({islogin:true,...result.data})
          this.router.navigate(['/customer'])
        }else{
          this.msg=result.msg
        }
      })
      
    }
}
