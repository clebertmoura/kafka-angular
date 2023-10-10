import { Injectable } from '@angular/core';
import { RxStomp } from '@stomp/rx-stomp';
import { myRxStompConfig } from './app.module';

export function rxStompServiceFactory() {
  const rxStomp = new RxStompService();
  rxStomp.configure(myRxStompConfig);
  rxStomp.activate();
  return rxStomp;
}

@Injectable({
  providedIn: 'root'
})
export class RxStompService extends RxStomp {

  constructor() {
    super();
  }
  
}
