import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse, HttpParams } from '@angular/common/http';

import { Observable, throwError } from 'rxjs';
import { map, catchError } from 'rxjs/operators';

import { PhoneNumberCombinations } from './phoneNumberCombinations';

@Injectable({
  providedIn: 'root'
})
export class PhoneNumberCombinationsService
{
  baseUrl = 'http://localhost:8000';
	
  phoneCombinations = new PhoneNumberCombinations('', []);

  constructor(private http: HttpClient) { }

   getCombinations(): Observable<PhoneNumberCombinations>{
    return this.http.get(`${this.baseUrl}`).pipe(
      map((res) => {
        this.phoneCombinations.combinations = res['combinationsArray'];
		this.phoneCombinations.numberOfCombinations = res['numberOfCombinations'];
        return this.phoneCombinations;
    }),
    catchError(this.handleError));
  }
  
  
  private handleError(error: HttpErrorResponse) {
    console.log(error);

    // return an observable with a user friendly message
    return throwError('Error! something went wrong.');
  }
}
