import { Component, OnInit } from '@angular/core';
import { Ticket } from '../model/ticket';
import { TicketControlService } from '../service/ticket.control.service';
import { FormBuilder, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-ticket-buy',
  templateUrl: './ticket-buy.component.html',
  styleUrls: ['./ticket-buy.component.scss']
})
export class TicketBuyComponent implements OnInit {
  flightIdForm!: FormGroup;
  tickets: Ticket[] = [];
  fullName = "";
  flightId = 0;

  constructor(private ticketService: TicketControlService,
              private fb: FormBuilder) { }

  ngOnInit(): void {
    this.getTickets();
    this.flightIdForm = this.fb.group({
      flightId: [null]
    });
  }

  getTickets(): void {
    this.ticketService.getTickets()
      .subscribe(tickets => this.tickets = tickets);
  }

  submitTicket() {
    this.flightId = this.flightIdForm.value;
  }

  buy(): void {
    this.ticketService.buyTicket(this.fullName, this.flightId);

    this.fullName = "";
    this.flightId = 0;
  }
}
