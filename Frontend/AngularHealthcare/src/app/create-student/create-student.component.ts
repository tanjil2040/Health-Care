import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Student } from '../student';
import { StudentService } from '../student.service';

@Component({
  selector: 'app-create-student',
  templateUrl: './create-student.component.html',
  styleUrls: ['./create-student.component.css']
})
export class CreateStudentComponent implements OnInit {
  student = new Student();
  constructor(private studentService: StudentService,
    private router: Router) { }

  ngOnInit(): void {
  }
  saveStudent() {
    this.studentService.createStudent(this.student).subscribe(data => {
      console.log(data);
      this.router.navigate(['/students']);
    },
      error => console.log(error));
  }

}
