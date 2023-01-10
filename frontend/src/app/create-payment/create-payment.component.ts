import { Component, OnInit } from '@angular/core';
import { Client } from '../model/client'
import { PaymentService } from '../service/payment.service';

@Component({
  selector: 'app-create-payment',
  templateUrl: './create-payment.component.html',
  styleUrls: ['./create-payment.component.scss']
})
export class CreatePaymentComponent implements OnInit {

  clients: Client[] = [];
  fullName = "";
  amount = 0;

  constructor(private paymentService: PaymentService) { }

  ngOnInit(): void {
    this.getClients();
  }

  getClients(): void {
    this.paymentService.getClients()
      .subscribe(clients => this.clients = clients);
  }

  create(): void {
    let id = Math.max.apply(Math, this.clients.map(function (c) {return c.id!;} ));

    this.paymentService.createPayment({id: id + 1, fullName: this.fullName,
      amount: this.amount} as Client).subscribe(client => this.clients.push(client));

    this.fullName = "";
    this.amount = 0;
  }
}
