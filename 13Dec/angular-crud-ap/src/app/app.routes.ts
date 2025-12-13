import { Routes } from '@angular/router';
import { UserListComponent } from './components/user-list/user-list';
import { UserAddComponent } from './components/user-add/user-add';
import { UserEditComponent } from './components/user-edit/user-edit';

export const routes: Routes = [
  { path: '', component: UserListComponent },
  { path: 'add', component: UserAddComponent },
  { path: 'edit/:id', component: UserEditComponent }
];
