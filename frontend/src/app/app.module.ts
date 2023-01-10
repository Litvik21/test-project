import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {HttpClientModule} from "@angular/common/http";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { DateAdapter, MAT_DATE_FORMATS } from '@angular/material/core';
import { APP_DATE_FORMATS, AppDateAdapter } from './adapter/date.adapter';
import { CreatePaymentComponent } from './create-payment/create-payment.component';
import { GettingStatusComponent } from './getting-status/getting-status.component';
import { CreateTicketComponent } from './create-ticket/create-ticket.component';
import { FlightsInfoComponent } from './flights-info/flights-info.component';
import { TicketBuyComponent } from './ticket-buy/ticket-buy.component';
import { TicketInfoComponent } from './ticket-info/ticket-info.component';

@NgModule({
  declarations: [
    AppComponent,
    CreatePaymentComponent,
    GettingStatusComponent,
    CreateTicketComponent,
    FlightsInfoComponent,
    TicketBuyComponent,
    TicketInfoComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule,
    FormsModule,
    BrowserAnimationsModule,
    MatFormFieldModule,
    MatInputModule,
    MatDatepickerModule
  ],
  providers: [
    {provide: DateAdapter, useClass: AppDateAdapter},
    {provide: MAT_DATE_FORMATS, useValue: APP_DATE_FORMATS}
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
