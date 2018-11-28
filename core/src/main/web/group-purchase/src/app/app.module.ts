import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';

import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { HomePageComponent } from './login/home-page/home-page.component';
import{RouterModule,Routes} from "@angular/router";

const appRoutes:Routes=[
	{path:'',redirectTo:'login',pathMatch:'full'},
	{path:'login',component:LoginComponent},
	{path:'register',component:RegisterComponent},
	{path:'homePage',component:HomePageComponent},

]

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    HomePageComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    RouterModule.forRoot(appRoutes)
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
