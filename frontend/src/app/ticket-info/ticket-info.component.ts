import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Ticket } from '../model/ticket';
import { TicketControlService } from '../service/ticket.control.service';

@Component({
  selector: 'app-ticket-info',
  templateUrl: './ticket-info.component.html',
  styleUrls: ['./ticket-info.component.scss']
})
export class TicketInfoComponent implements OnInit {

  ticketIdForm!: FormGroup;

  tickets: Ticket[] = [];
  ticket!: Ticket;

  constructor(private ticketService: TicketControlService,
              private fb: FormBuilder) { }

  ngOnInit(): void {
    this.getTickets();
    this.ticketIdForm = this.fb.group({
      ticket: [null]
    })
  }

  getTickets(): void {
    this.ticketService.getTickets()
      .subscribe(tickets => this.tickets = tickets);
  }

  submit() {
    this.ticket = this.tickets.find(t => t.id == this.ticketIdForm.value)!;
  }

}
