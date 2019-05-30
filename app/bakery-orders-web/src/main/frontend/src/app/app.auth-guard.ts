import { Injectable } from '@angular/core';
import { CanActivate, Router, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { KeycloakService, KeycloakAuthGuard } from 'keycloak-angular';

@Injectable()
export class AppAuthGuard extends KeycloakAuthGuard {
    constructor(protected router: Router, protected keycloakAngular: KeycloakService) {
        super(router, keycloakAngular);
    }

    isAccessAllowed(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Promise<boolean> {
        return new Promise(async (resolve, reject) => {
            if (!this.authenticated) {
                this.keycloakAngular.login();
                return;
            }
            console.log('role restriction given at app-routing.module for this route', route.data.roles);
            console.log('User roles coming after login from keycloak :', this.roles);
            const requiredRoles = route.data.roles;
            let granted: boolean = false;
            if (!requiredRoles || requiredRoles.length === 0) {
                granted = true;
            } else {
                for (const requiredRole of requiredRoles) {
                    if (this.roles.indexOf(requiredRole) > -1) {
                        granted = true;
                        break;
                    }
                }
            }

            if(granted === false) {
                this.router.navigate(['/']);
            }
            resolve(granted);

        });
    }
}
