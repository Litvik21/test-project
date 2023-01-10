import { Component, OnInit } from '@angular/core';
import { Ticket } from '../model/ticket';
import { TicketControlService } from '../service/ticket.control.service';

@Component({
  selector: 'app-create-ticket',
  templateUrl: './create-ticket.component.html',
  styleUrls: ['./create-ticket.component.scss']
})
export class CreateTicketComponent implements OnInit {
  tickets: Ticket[] = [];

  flightId = 0;
  from = "";
  to = "";
  availableTickets = 0;
  price = 0;
  picker = new Date;

  constructor(private ticketService: TicketControlService) { }

  ngOnInit(): void {
    this.getTickets();
  }

  getTickets(): void {
    this.ticketService.getTickets()
      .subscribe(tickets => this.tickets = tickets);
  }

  create(): void {
    let id = Math.max.apply(Math, this.tickets.map(function (t) {return t.id!;} ));

    this.ticketService.createTicket({id: id + 1, flightId: this.flightId, from: this.from,
    to: this.to, departureTime: this.picker, price: this.price, availableTickets: this.availableTickets} as Ticket)
      .subscribe(ticket => {this.tickets.push(ticket)});

    this.flightId = 0;
    this.from = "";
    this.to = "";
    this.availableTickets = 0;
    this.price = 0;
  }

}
