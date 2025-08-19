import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CustomerExpenseLimiterComponent } from './customer-expense-limiter.component';

describe('CustomerExpenseLimiterComponent', () => {
  let component: CustomerExpenseLimiterComponent;
  let fixture: ComponentFixture<CustomerExpenseLimiterComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [CustomerExpenseLimiterComponent]
    });
    fixture = TestBed.createComponent(CustomerExpenseLimiterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
