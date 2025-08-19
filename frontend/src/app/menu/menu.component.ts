import { Component } from '@angular/core';
import { UserService } from '../user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent {
  constructor(public userService:UserService,private router:Router){

  }
  public logout(){
    this.userService.setUserInfo({islogin:false,token:undefined})
    this.router.navigate(['/login'])
  }
}
