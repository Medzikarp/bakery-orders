import {Component, OnInit} from '@angular/core';
import {KeycloakService} from "keycloak-angular";
import {Router} from "@angular/router";

@Component({
    selector: 'app-root',
    templateUrl: './app.component.html',
    styleUrls: ['./app.component.css']
})

export class AppComponent implements OnInit {
    userName;

    constructor(protected router: Router,
                protected keycloakService: KeycloakService) {
    }


    ngOnInit() {
        try {
            this.userName = this.keycloakService.getUsername()
        } catch (e) {
            console.log('Failed to load user details', e);
        }
    }

    onClickLogout() {
        this.keycloakService.logout();
    }

    onClickEditProfile() {
        this.keycloakService.getKeycloakInstance().accountManagement()
    }

    isAdmin(): boolean {
        return this.keycloakService.isUserInRole('ADMIN');
    }
}
