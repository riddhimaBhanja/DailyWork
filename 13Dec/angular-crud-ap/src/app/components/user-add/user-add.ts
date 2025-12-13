import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { UserService } from '../../services/user.service';
import { User } from '../../models/user';
import { Router } from '@angular/router';

@Component({
  selector: 'app-user-add',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './user-add.html'
})
export class UserAddComponent {

  user: User = { name: '', email: '' };

  constructor(
    private userService: UserService,
    private router: Router
  ) {}

  addUser(): void {
    this.userService.addUser(this.user).subscribe(() => {
      alert('User added');
      this.router.navigate(['/']);
    });
  }
}
