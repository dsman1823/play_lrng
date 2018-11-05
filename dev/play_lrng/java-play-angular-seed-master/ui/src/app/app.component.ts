import { Component } from '@angular/core';

import { AppService } from './app.service';
import {MessageService} from "./message.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title: string;
  message: any;
  postRequestResponse: string;

  constructor(private appService: AppService, private messageService: MessageService) {
    this.messageService.getAllMessages().subscribe((data: any) => {
      console.log("*******************************");
      console.log(data.response[0]);
      console.log("*******************************");
      this.message = data.response[0];
    });

    this.postRequestResponse = "ASD";
  }

  /**
   * This method is used to test the post request
   */
  public postData(): void {
    this.appService.sendData().subscribe((data: any) => {
      // console.log("!!!!!!!!!!!!");
      // console.log(data);
      // console.log("!!!!!!!!!!!!");
      // this.postRequestResponse = data.content;
    });
  }
}
