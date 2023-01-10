import { Status } from './status';

export interface Client {
  id: number;
  fullName: string;
  amount: number;
  paymentId?: string;
  status?: Status;
}
