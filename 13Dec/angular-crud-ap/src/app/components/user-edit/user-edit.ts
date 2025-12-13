import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { UserService } from '../../services/user.service';

@Component({
  selector: 'app-user-edit',
  standalone: true,
  imports: [ReactiveFormsModule],
  templateUrl: './user-edit.html'
})
export class UserEditComponent implements OnInit {

  id!: number;
  form!: FormGroup;

  constructor(
    private fb: FormBuilder,
    private route: ActivatedRoute,
    private userService: UserService,
    private router: Router
  ) {}

  ngOnInit(): void {

   
    this.form = this.fb.group({
      name: [''],
      email: ['']
    });

    this.id = Number(this.route.snapshot.paramMap.get('id'));

    this.userService.getUserById(this.id).subscribe(data => {
      this.form.patchValue(data);
    });
  }

  updateUser(): void {
    this.userService.updateUser(this.id, this.form.value)
      .subscribe(() => {
        alert('User updated');
        this.router.navigate(['/']);
      });
  }
}
