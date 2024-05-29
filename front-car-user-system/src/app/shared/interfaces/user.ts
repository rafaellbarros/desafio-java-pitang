import { Car } from './car';

export interface User {
  id: number;
  firstName: string;
  lastName: string;
  email: string;
  birthday: Date;
  login: string;
  phone: string;
  cars: Car[];
}
