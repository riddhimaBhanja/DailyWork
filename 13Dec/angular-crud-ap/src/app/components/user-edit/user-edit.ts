import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { UserService } from '../../services/user.service';

@Component({
  selector: 'app-user-edit',
  standalone: true,
  imports: [ReactiveFormsModule],
  templateUrl: './user-edit.html',
  styleUrl: './user-edit.css',
})
export class UserEdit implements OnInit {

  form!: FormGroup;
  id!: number;

  constructor(
    private fb: FormBuilder,
    private route: ActivatedRoute,
    private userService: UserService
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

  
  updateUser() {
    this.userService.updateUser(this.id, this.form.value)
      .subscribe(() => alert('User updated'));
  }
}
