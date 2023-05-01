import { Role } from './role';

export interface User {
  firstname: string;
  lastname: string;
  username: string;
  role: Role[];
}
