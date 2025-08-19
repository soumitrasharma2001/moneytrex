import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {

  constructor(private router: Router,private userService:UserService) {}

  canActivate(): boolean {
    const token = this.userService.getUserInfo().token; // or sessionStorage.getItem('token')

    if (token) {
      // Token exists → allow navigation
      return true;
    } else {
      // No token → redirect to login
      this.router.navigate(['/login']);
      return false;
    }
  }
}
import { CanActivateFn } from '@angular/router';
import { UserService } from './user.service';

export const authGuard: CanActivateFn = (route, state) => {
  return true;
};
