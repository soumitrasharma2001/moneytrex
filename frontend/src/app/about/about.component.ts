import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';

@Component({
  selector: 'app-about',
  templateUrl: './about.component.html',
  styleUrls: ['./about.component.css']
})
export class AboutComponent {
  public posts:any[]=[]
  constructor(http:HttpClient){
    http.get("https://dummyjson.com/posts").subscribe((result:any)=>this.posts=result.posts)
  }
}
