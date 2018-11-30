import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';

import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './login/register/register.component';
import { HomePageComponent } from './login/home-page/home-page.component';
import{RouterModule,Routes} from "@angular/router";
import { ShoppingCardPageComponent } from './login/shopping-card-page/shopping-card-page.component';
import { MyOrdersComponent } from './login/my-orders/my-orders.component';
import { MyRebateComponent } from './login/my-rebate/my-rebate.component';
import { PersonalCenterComponent } from './login/personal-center/personal-center.component';
import { BusinessHomepageComponent } from './business-login/business-homepage/business-homepage.component';
import { AddCommodityComponent } from './business-login/business-homepage/add-commodity/add-commodity.component';
import { BusinessLoginComponent } from './business-login/business-login.component';
import { AdminLoginComponent } from './admin-login/admin-login.component';

const appRoutes:Routes=[
	{path:'',redirectTo:'login',pathMatch:'full'},
	{path:'login',component:LoginComponent},
	{path:'register',component:RegisterComponent},
	{path:'homePage',component:HomePageComponent},
	{path:'ShopCarPage',component:ShoppingCardPageComponent},
	{path:'myOrders',component:MyOrdersComponent},
	{path:'myRebate',component:MyRebateComponent},
	{path:'perCenter',component:PersonalCenterComponent},
	{path:'businessHomePage',component:BusinessHomepageComponent},
	{path:'addComodity',component:AddCommodityComponent},


]

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    HomePageComponent,
    ShoppingCardPageComponent,
    MyOrdersComponent,
    MyRebateComponent,
    PersonalCenterComponent,
    BusinessHomepageComponent,
    AddCommodityComponent,
    BusinessLoginComponent,
    AdminLoginComponent
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
