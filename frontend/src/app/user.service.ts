import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private userinfo:any={
    islogin:false,
    token:undefined,
    name:undefined,
    role:undefined
  }
  public setUserInfo(info:any){
    this.userinfo=info;
    localStorage.setItem("customer",JSON.stringify(info))
  }
  public getUserInfo(){
    return this.userinfo
  }
  constructor() {
    const data=localStorage.getItem("customer")
    if(data==null || data==undefined)
      this.userinfo={
        islogin:false,
        token:undefined,
        name:undefined,
        role:undefined
      }
    else
      this.userinfo=JSON.parse(data);
  }

}
