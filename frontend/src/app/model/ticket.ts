export interface Ticket {
  id: number;
  flightId: number;
  from: string;
  to: string;
  departureTime: Date;
  price: number;
  availableTickets: number;
  ticketId?: string;
  status?: string;
}
