import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ErrorComponent } from './error/error.component';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { LogoutComponent } from './logout/logout.component';
import { PostQuestionComponent } from './post-question/post-question.component';
import { QaportalComponent } from './qaportal/qaportal.component';
import { RegisterUserComponent } from './register-user/register-user.component';
import { RouteGuardService } from './service/guard/route-guard.service';

const routes: Routes = [

  {path : '', component : LoginComponent},
  {path : 'home', component : HomeComponent, canActivate:[RouteGuardService]},
  {path : 'qaportal/questions', component : QaportalComponent, canActivate:[RouteGuardService]},
  {path : 'login', component : LoginComponent},
  {path : 'logout', component : LogoutComponent},
  {path : 'qaportal/post-question', component : PostQuestionComponent, canActivate:[RouteGuardService]},
  {path : 'register', component : RegisterUserComponent},

  {path : '**', component : ErrorComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
