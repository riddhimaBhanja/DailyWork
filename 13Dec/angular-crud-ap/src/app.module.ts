import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { RouterModule } from '@angular/router';
import { routes } from './app/app.routes';

@NgModule({
  imports: [
    BrowserModule,
    HttpClientModule,     
    RouterModule.forRoot(routes)
  ]
})
export class AppModule {}
