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
import { BusinessRegisterComponent } from './business-login/business-register/business-register.component';
import { ForgetPasswordComponent } from './login/forget-password/forget-password.component';
import { StepComponent } from './step/step.component';
import { AdminHomePageComponent } from './admin-login/admin-home-page/admin-home-page.component';
import { BForgetPasswordComponent } from './business-login/b-forget-password/b-forget-password.component';
import { CommodityListComponent } from './login/home-page/commodity-list/commodity-list.component';
import { PageComponent } from './page/page.component';
import { FileUploadComponent } from './file-upload/file-upload.component';
import { SearchLoadingComponent } from './search-loading/search-loading.component';
import { BackCommodityComponent } from './back-commodity/back-commodity.component';
import { CommodityDetailComponent } from './login/home-page/commodity-list/commodity-detail/commodity-detail.component';
import { BusinessPerComponent } from './business-login/business-homepage/business-per/business-per.component';
import { BusinessCommodityListComponent } from './business-login/business-homepage/business-commodity-list/business-commodity-list.component';
import {DataTableModule} from "angular2-datatable";
import {UpdateCommodityComponent} from "./business-login/business-homepage/update-commodity/update-commodity.component"
import {UpdateOrderStateComponent} from "./business-login/business-homepage/update-order-state/update-order-state.component"
import {UserInfoComponent} from "./business-login/business-homepage/update-order-state/user-info/user-info.component"
import {SellerListComponent} from "./admin-login/admin-home-page/seller-list/seller-list.component"
import {AdminCommodityListComponent} from "./admin-login/admin-home-page/admin-commodity-list/admin-commodity-list.component"
import {CommodityPicComponent} from "./admin-login/admin-home-page/admin-commodity-list/commodity-pic/commodity-pic.component"
import {AdminCommodityTypeComponent} from "./admin-login/admin-home-page/admin-commodity-type/admin-commodity-type.component"
import {AddTypeComponent} from "./admin-login/admin-home-page/admin-commodity-type/add-type/add-type.component"
import {UpdatetypeComponent} from "./admin-login/admin-home-page/admin-commodity-type/updatetype/updatetype.component"
import {UserListComponent} from "./admin-login/admin-home-page/user-list/user-list.component"
import {AdminOrderListComponent} from "./admin-login/admin-home-page/user-list/admin-order-list/admin-order-list.component"
import {BusinessArticleComponent} from "./business-login/business-homepage/business-article/business-article.component"
import {AddArticleComponent} from "./business-login/business-homepage/business-article/add-article/add-article.component"
import {FormAddressSelectComponent} from "./form-address-select/form-address-select.component"
import {DateComponent} from "./date/date.component"
import {UpdateArticleComponent} from "./business-login/business-homepage/business-article/update-article/update-article.component"
import {AdminArticleComponent} from "./admin-login/admin-home-page/admin-article/admin-article.component"
import {PayReturnPageComponent} from "./pay-return-page/pay-return-page.component"
import {ShowReasonComponent} from "./business-login/business-homepage/update-order-state/show-reason/show-reason.component"
import {CommmentComponent} from "./login/my-orders/commment/commment.component"

const appRoutes:Routes=[
	{path:'',redirectTo:'login',pathMatch:'full'},
	{path:'login',component:LoginComponent},
	{path:'register',component:RegisterComponent},
	{path:'homePage',component:HomePageComponent},
	{path:'adminLogin',component:AdminLoginComponent},
	{path:'businessLogin',component:BusinessLoginComponent},
	{path:'businessRegister',component:BusinessRegisterComponent},
	{path:'forgetPassword',component:ForgetPasswordComponent},
	{path:'b_forgetPassword',component:BForgetPasswordComponent},
	{path:'fileUpload',component:FileUploadComponent},
	{path:'payReturn',component:PayReturnPageComponent},
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
    AdminLoginComponent,
    BusinessRegisterComponent,
    ForgetPasswordComponent,
    StepComponent,
    AdminHomePageComponent,
    BForgetPasswordComponent,
    CommodityListComponent,
    PageComponent,
    FileUploadComponent,
    SearchLoadingComponent,
    BackCommodityComponent,
    CommodityDetailComponent,
    BusinessPerComponent,
    BusinessCommodityListComponent,
    UpdateCommodityComponent,
    UpdateOrderStateComponent,
    UserInfoComponent,
    SellerListComponent,
    AdminCommodityListComponent,
    CommodityPicComponent,
    AdminCommodityTypeComponent,
    AddTypeComponent,
    UpdatetypeComponent,
    UserListComponent,
    AdminOrderListComponent,
    BusinessArticleComponent,
    AddArticleComponent,
    FormAddressSelectComponent,
    DateComponent,
    UpdateArticleComponent,
    AdminArticleComponent,
    PayReturnPageComponent,
    ShowReasonComponent,
    CommmentComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    RouterModule.forRoot(appRoutes),
    DataTableModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
