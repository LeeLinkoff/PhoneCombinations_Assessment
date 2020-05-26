import { Component, OnInit } from '@angular/core';

import { PhoneNumberCombinations } from './phoneNumberCombinations';
import { PhoneNumberCombinationsService } from './phoneNumberCombinations.service';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})


export class AppComponent implements OnInit
{

  title = 'Phone-Combinations';
  
  error = '';
  success = '';
  
  phoneNumberCombinations: PhoneNumberCombinations;
  
  constructor(private phoneNumberCombinationsService: PhoneNumberCombinationsService) { }
  
  ngOnInit() {
    this.getCombinations();
  }
  
   getCombinations(): void {
    this.phoneNumberCombinationsService.getCombinations().subscribe(
      (res: PhoneNumberCombinations) => {
		this.phoneNumberCombinations = res;
      },
      (err) => {
        this.error = err;
		console.log("error", this.error);
      }
    );
  }
 
 
  private resetErrors(){
    this.success = '';
    this.error   = '';
  }
}
