import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { AuthenticationService } from '../_services/index';

@Component({
    moduleId: module.id,
    templateUrl: 'register.component.html'
})

export class RegisterComponent {
    model: any = {};
    loading = false;
    error = '';

    constructor(
        private router: Router,
        private authenticationService: AuthenticationService) { }

    register2() {
        this.loading = true;

        this.authenticationService.register(this.model.username, this.model.email, this.model.password)
            .subscribe(result => {
                if (result.status === "ok") {
                    this.router.navigate(['/profile']);
                } else {
                    this.error = result.error != null && result.error != "" ? result.error : "Registration fail,please try again";
                }

                this.loading = false;
            });
    }
}
