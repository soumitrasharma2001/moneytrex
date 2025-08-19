import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FormsModule } from '@angular/forms';

import { HomeComponent } from './home/home.component';
import { AboutComponent } from './about/about.component';
import { ContactComponent } from './contact/contact.component';

import { HttpClientModule } from '@angular/common/http';
import { RegistComponent } from './regist/regist.component';
import { LoginComponent } from './login/login.component';
import { MenuComponent } from './menu/menu.component';
import { CustomerHomeComponent } from './customer/customer-home/customer-home.component';
import { CustomerDashboardComponent } from './customer/customer-dashboard/customer-dashboard.component';

import { CustomerExpenseLimiterComponent } from './customer/customer-expense-limiter/customer-expense-limiter.component';
import { CustomerTransactionComponent } from './customer/customer-transaction/customer-transaction.component';
import { ReportComponent } from './customer/report/report.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    AboutComponent,
    ContactComponent,
    RegistComponent,
    LoginComponent,
    MenuComponent,
    CustomerHomeComponent,
    CustomerDashboardComponent,
    CustomerTransactionComponent,
    CustomerExpenseLimiterComponent,
    ReportComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
