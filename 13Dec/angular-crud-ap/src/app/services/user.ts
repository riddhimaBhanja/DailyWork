import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class User {
  
}
export interface User {
  id?: number;
  name: string;
  email: string;
}
