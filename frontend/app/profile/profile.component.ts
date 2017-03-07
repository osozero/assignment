import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { AuthenticationService } from '../_services/index';

@Component({
    moduleId: module.id,
    templateUrl: 'profile.component.html'
})

export class ProfileComponent implements OnInit {
    email: '';
    username: '';
    loading = false;
    error = '';

    data: any = {};

    constructor(
        private router: Router,
        private authenticationService: AuthenticationService) { }

    ngOnInit() {
        this.profile();
    }

    profile() {
        this.loading = true;
        this.authenticationService.profile()
            .subscribe(result => {
                if (result != null) {
                    this.email = result.email;
                    this.username = result.username;
                } else {
                    console.log("not authorized!");
                    this.router.navigate(['/login']);
                }
                this.loading = false;
            });
    }
}
