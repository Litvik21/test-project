import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CreateTicketComponent } from './create-ticket/create-ticket.component';
import { FlightsInfoComponent } from './flights-info/flights-info.component';
import { TicketBuyComponent } from './ticket-buy/ticket-buy.component';
import { TicketInfoComponent } from './ticket-info/ticket-info.component';
import { CreatePaymentComponent } from './create-payment/create-payment.component';
import { GettingStatusComponent } from './getting-status/getting-status.component';

const routes: Routes = [
  {path: 'tickets', component: CreateTicketComponent},
  {path: 'tickets/info', component: FlightsInfoComponent},
  {path: 'tickets/buy', component: TicketBuyComponent},
  {path: 'tickets/ticket-info', component: TicketInfoComponent},

  {path: 'payments/create', component: CreatePaymentComponent},
  {path: 'payments/status', component: GettingStatusComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
