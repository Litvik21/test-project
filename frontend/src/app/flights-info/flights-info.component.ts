import { Component, OnInit } from '@angular/core';
import { Ticket } from '../model/ticket';
import { TicketControlService } from '../service/ticket.control.service';

@Component({
  selector: 'app-flights-info',
  templateUrl: './flights-info.component.html',
  styleUrls: ['./flights-info.component.scss']
})
export class FlightsInfoComponent implements OnInit {
  tickets: Ticket[] = [];

  constructor(private ticketService: TicketControlService) { }

  ngOnInit(): void {
    this.getTickets();
  }

  getTickets(): void {
    this.ticketService.getTickets()
      .subscribe(tickets => this.tickets = tickets);
  }
}
