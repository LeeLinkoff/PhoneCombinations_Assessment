import { Component, OnInit } from '@angular/core';

import { PhoneNumberCombinations } from './phoneNumberCombinations';
import { PhoneNumberCombinationsService } from './phoneNumberCombinations.service';

import { FormControl } from '@angular/forms';
import { FormGroup, Validators, FormBuilder } from '@angular/forms';

//in place where you wanted to use `HttpClient`
import { HttpClient } from '@angular/common/http';

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
  
  phoneNumberCombinations = new PhoneNumberCombinations('0',[]);
  
  constructor(
			  private phoneNumberCombinationsService: PhoneNumberCombinationsService,
              private http: HttpClient
			 ) { }
  

  ngOnInit() {
    //this.getCombinations();
  }

  
  phoneNum = new FormControl('');  
  
  updatephoneNum() {
	  //var formData: any = new FormData();
      //formData.append("name", this.form.get('name').value);
	  //formData.append("name","1");
	  
	  const headers = { 'content-type': 'application/json'}  
      const body=JSON.stringify(this.phoneNum.value);
      console.log('body: sent ' + body)
	
      this.http.post('http://localhost:8000', body, {'headers':headers}).subscribe(
      (response: PhoneNumberCombinations) => {
		                                       console.log('Combinations returned: ' + response['combinationsArray']);
											   this.phoneNumberCombinations.combinations = response['combinationsArray'];
											   this.phoneNumberCombinations.numberOfCombinations = response['numberOfCombinations'];
											 },
      (error) => console.log(error)
	  );
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
