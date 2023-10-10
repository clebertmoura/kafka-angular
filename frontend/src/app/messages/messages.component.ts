import { HttpClient, HttpErrorResponse, HttpParams, HttpResponse } from '@angular/common/http';
import { Component, OnDestroy, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Message } from '@stomp/stompjs';
import { Observable, Subject, catchError, of, takeUntil } from 'rxjs';
import { RxStompService } from '../rx-stomp.service';

@Component({
  selector: 'app-messages',
  templateUrl: './messages.component.html',
  styleUrls: ['./messages.component.scss']
})
export class MessagesComponent implements OnInit, OnDestroy {

  myForm: FormGroup;

  messages: any[] = [];

  private destroy$ = new Subject();

  constructor(private frmBuilder: FormBuilder,
    private http: HttpClient,
    private stompService: RxStompService) {

    this.myForm = frmBuilder.group(
      { text: '' }
    );
  }

  ngOnInit(): void {
    this.stompService.watch('/topic/messages')
      .pipe(
        takeUntil(this.destroy$)
      ).subscribe((message: Message) => {
        console.log('Received from websocket: ' + message.body);
        this.messages.push(JSON.parse(message.body));
      });
  }

  ngOnDestroy(): void {
    this.destroy$.next(null);
    this.destroy$.unsubscribe();
  }

  submit(): void {
    const text = this.myForm.controls['text'].value;

    this.http.post(`http://localhost:8081/api/kafka/send`, text, 
      { observe: 'response' })
      .pipe(
        catchError(this.handleError.bind(this)),
        takeUntil(this.destroy$)
      ).subscribe();
  }

  private handleError(error: HttpErrorResponse): Observable<any> {
    return of(null);
  }

}
