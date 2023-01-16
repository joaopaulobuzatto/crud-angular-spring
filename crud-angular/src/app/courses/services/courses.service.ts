import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';

import {first, tap} from 'rxjs';
import {Course} from '../model/course';

@Injectable({
  providedIn: 'root'
})
export class CoursesService {

  private readonly API = '/api/courses';

  constructor(private httpClient: HttpClient) {
  }

  list() {
    return this.httpClient.get<Course[]>(this.API)
      .pipe(
        first()
        // delay(1000),
        // tap(courses => console.log(courses))
      );
  }

  loadById(id: string) {
    return this.httpClient.get<Course>(`${this.API}/${id}`)
      .pipe(
        first()
        // tap(courses => console.log(courses))
      );
  }

  save(record: Partial<Course>) {
    // console.log(record);
    if (record._id) {
      // console.log('update');
      return this.update(record);
    }
    return this.create(record)
  }

  private create(record: Partial<Course>) {
    return this.httpClient.post<Course>(this.API, record).pipe(first());
  }

  private update(record: Partial<Course>) {
    return this.httpClient.put<Course>(`${this.API}/${record._id}`, record).pipe(first());
  }

  delete(id: string) {
    return this.httpClient.delete(`${this.API}/${id}`).pipe(first());
  }
}
