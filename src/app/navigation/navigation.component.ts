import { Component, OnInit } from '@angular/core';
import { ObservableMedia, MediaChange } from '@angular/flex-layout';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.css']
})
export class NavigationComponent implements OnInit {

  opened = false;
  mode = 'side';
  mediaWatcher: Subscription;

  constructor(private media: ObservableMedia) { }

  ngOnInit() {

    // if screen is small hide the navigation
    this.mediaWatcher = this.media.subscribe((change: MediaChange) => {
      if (change.mqAlias === 'xs') {
        this.opened = false;
        this.mode = 'over';
      } else {
        this.opened = true;
        this.mode = 'side';
      }
    });
  }

}
