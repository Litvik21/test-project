import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { Ticket } from '../model/ticket';

@Injectable({providedIn: 'root'})
export class TicketControlService {
  httpOptions = {
    headers: new HttpHeaders({'Content-Type': 'application/json'})
  };
  private ticketsUrl = 'http://localhost:8081/tickets';

  constructor(
    private http: HttpClient) {
  }

  getTickets(): Observable<Ticket[]> {
    const url = `${this.ticketsUrl}/info`;
    return this.http.get<Ticket[]>(url)
      .pipe(
        catchError(this.handleError<Ticket[]>('getTickets', []))
      );
  }

  ticketInfo(ticketId: string): Observable<Ticket> {
    const url = `${this.ticketsUrl}/ticket-info`;
    return this.http.get<Ticket>(url)
      .pipe(
        catchError(this.handleError<Ticket>('ticketInfo'))
      );
  }

  buyTicket(fullName: string, flightId: number): Observable<any> {
    const url = `${this.ticketsUrl}/buy`;
    return this.http.post<any>(url, this.httpOptions).pipe(
      catchError(this.handleError<any>('buyTicket'))
    );
  }

  createTicket(ticket: Ticket): Observable<Ticket> {
    return this.http.post<Ticket>(this.ticketsUrl, ticket, this.httpOptions).pipe(
      catchError(this.handleError<Ticket>('createTicket'))
    );
  }

  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {

      console.error(error);

      return of(result as T);
    };
  }
}
