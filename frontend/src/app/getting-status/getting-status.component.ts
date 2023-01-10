import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Client } from '../model/client';
import { PaymentService } from '../service/payment.service';

@Component({
  selector: 'app-getting-status',
  templateUrl: './getting-status.component.html',
  styleUrls: ['./getting-status.component.scss']
})
export class GettingStatusComponent implements OnInit {
  paymentIdForm!: FormGroup;

  clients: Client[] = [];
  paymentId = "";

  constructor(private paymentService: PaymentService,
              private fb: FormBuilder) { }

  ngOnInit(): void {
    this.getClients();
    this.paymentIdForm = this.fb.group({
      paymentId: [null]
    })
  }

  getClients(): void {
    this.paymentService.getClients()
      .subscribe(clients => this.clients = clients);
  }

  submit() {
    this.paymentId = this.paymentIdForm.value;
  }

  get(): void {
    this.paymentService.getStatus(this.paymentId);

    this.paymentId = "";
  }
}
