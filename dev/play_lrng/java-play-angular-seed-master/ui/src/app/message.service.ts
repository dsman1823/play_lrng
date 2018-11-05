import { Injectable } from '@angular/core';

import { HttpClient } from '@angular/common/http';

import { map } from 'rxjs/operators';
import { Observable } from 'rxjs/index';

@Injectable({
  providedIn: 'root'
})
export class MessageService {
  private getAllMessagesUrl = '/api/message';

  constructor(private http: HttpClient) {
  }

  public getAllMessages(): Observable<any> {
    return this.http.get(this.getAllMessagesUrl);
  }
}
