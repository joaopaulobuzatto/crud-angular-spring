import {Component, OnInit} from '@angular/core';
import {MatDialog} from '@angular/material/dialog';
import {ActivatedRoute, Router} from '@angular/router';
import {MatSnackBar} from '@angular/material/snack-bar';

import {catchError, Observable, of} from 'rxjs';
import {Course} from '../../model/course';
import {CoursesService} from '../../services/courses.service';
import {ErrorDialogComponent} from '../../../shared/components/error-dialog/error-dialog.component';
import {
  ConfirmationDialogComponent
} from "../../../shared/components/confirmation-dialog/confirmation-dialog.component";

@Component({
  selector: 'app-courses',
  templateUrl: './courses.component.html',
  styleUrls: ['./courses.component.scss']
})
export class CoursesComponent implements OnInit {

  courses$: Observable<Course[]> | null = null;

  constructor(private coursesService: CoursesService,
              public dialog: MatDialog,
              private router: Router,
              private route: ActivatedRoute,
              private snackBar: MatSnackBar,) {
    this.refresh();
  }

  private refresh() {
    this.courses$ = this.coursesService.list()
      .pipe(
        catchError(error => {
          this.onError('Erro ao carregar cursos.');
          return of([]);
        })
      );
  }

  ngOnInit(): void {
  }

  onError(errorMsg: string) {
    this.dialog.open(ErrorDialogComponent, {
      data: errorMsg,
    });
  }

  onAdd() {
    this.router.navigate(['new'], {relativeTo: this.route});
  }

  onEdit(course: Course) {
    this.router.navigate(['edit', course._id], {relativeTo: this.route});
  }

  onDelete(course: Course) {
    const dialogRef = this.dialog.open(ConfirmationDialogComponent, {
      data: 'Tem certeza que deseja remover este curso?',
    });

    dialogRef.afterClosed().subscribe((result: boolean) => {
      if (result) {
        this.coursesService.delete(course._id)
          .subscribe(
            () => this.onDeleteSuccess(),
            () => this.onError('Erro ao remover curso.')
          );
      }
    });
  }

  onDeleteSuccess() {
    this.snackBar.open('Curso removido com sucesso!', 'X', {
      duration: 5000,
      verticalPosition: 'top',
      horizontalPosition: 'center'
    });
    this.refresh();
  }

}
