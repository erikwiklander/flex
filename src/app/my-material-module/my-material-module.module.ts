import {MatButtonModule, MatCheckboxModule} from '@angular/material';
import {MatIconModule} from '@angular/material/icon';
import { NgModule } from '@angular/core';

@NgModule({
  imports: [MatButtonModule, MatCheckboxModule, MatIconModule],
  exports: [MatButtonModule, MatCheckboxModule, MatIconModule],
})
export class MyMaterialModuleModule { }
