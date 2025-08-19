import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { AboutComponent } from './about/about.component';
import { ContactComponent } from './contact/contact.component';
import { LoginComponent } from './login/login.component';
import { RegistComponent } from './regist/regist.component';
import { CustomerDashboardComponent } from './customer/customer-dashboard/customer-dashboard.component';
import { CustomerHomeComponent } from './customer/customer-home/customer-home.component';
import { CustomerTransactionComponent } from './customer/customer-transaction/customer-transaction.component';
import { CustomerExpenseLimiterComponent } from './customer/customer-expense-limiter/customer-expense-limiter.component';
import { ReportComponent } from './customer/report/report.component';


const routes: Routes = [
  {path:"",component:HomeComponent},
  {path:"about",component:AboutComponent},
  {path:"contact",component:ContactComponent},
  {path:"login",component:LoginComponent},
  {path:"regist",component:RegistComponent},
  {path:"customer",component:CustomerDashboardComponent,
    children:[
      {path:"",component:CustomerHomeComponent},
      {path:"trans",component:CustomerTransactionComponent},
      {path:"exp",component:CustomerExpenseLimiterComponent},
      {path:"report",component:ReportComponent},
    ]
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
