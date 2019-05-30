import { KeycloakService } from 'keycloak-angular';

export function initializer(keycloak: KeycloakService): () => Promise<any> {
    return (): Promise<any> => keycloak.init({
        config: {
            realm: 'muni',
            url: 'http://localhost:8180/auth',
            clientId: 'bakery-orders-web'
        },
        initOptions: {
            onLoad: 'login-required',
            checkLoginIframe: false
        },
        enableBearerInterceptor: true,
        bearerExcludedUrls: []
    });
}

