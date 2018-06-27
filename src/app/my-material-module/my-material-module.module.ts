import {MatButtonModule, MatCheckboxModule} from '@angular/material';
import { MatIconModule } from '@angular/material/icon';
import { NgModule } from '@angular/core';
import { MatToolbarModule } from '@angular/material/toolbar';


@NgModule({
  imports: [MatButtonModule, MatCheckboxModule, MatIconModule, MatToolbarModule],
  exports: [MatButtonModule, MatCheckboxModule, MatIconModule, MatToolbarModule],
})
export class MyMaterialModuleModule { }
