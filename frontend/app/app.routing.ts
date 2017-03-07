import { Routes, RouterModule } from '@angular/router';

import { LoginComponent } from './login/index';
import { RegisterComponent } from './register/index';
import {ProfileComponent} from './profile/index';
import { AuthGuard } from './_guards/index';

const appRoutes: Routes = [
    { path: 'login', component: LoginComponent },
    {path:  'register', component:RegisterComponent},
    { path: 'profile', component: ProfileComponent, canActivate: [AuthGuard] },
    { path: '', component: ProfileComponent, canActivate: [AuthGuard] },

    { path: '**', redirectTo: '' }
];

export const routing = RouterModule.forRoot(appRoutes);