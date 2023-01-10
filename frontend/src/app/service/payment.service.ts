import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { Client } from '../model/client';

@Injectable({providedIn: 'root'})
export class PaymentService {
  httpOptions = {
    headers: new HttpHeaders({'Content-Type': 'application/json'})
  };
  private paymentsUrl = 'http://localhost:8080/payments';

  constructor(
    private http: HttpClient) {
  }

  getClients(): Observable<Client[]> {
    return this.http.get<Client[]>(this.paymentsUrl)
      .pipe(
        catchError(this.handleError<Client[]>('getClients', []))
      );
  }

  getStatus(paymentId: string): Observable<any> {
    const url = `${this.paymentsUrl}/status`;
    return this.http.post<any>(url, paymentId, this.httpOptions).pipe(
      catchError(this.handleError<any>('getStatus'))
    );
  }

  createPayment(client: Client): Observable<Client> {
    const url = `${this.paymentsUrl}/create`;
    return this.http.post<Client>(url, client, this.httpOptions).pipe(
      catchError(this.handleError<Client>('createPayment'))
    );
  }

  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {

      console.error(error);

      return of(result as T);
    };
  }
}
