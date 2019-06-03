package org.bakery.orders.rest;

import org.keycloak.KeycloakPrincipal;
import org.keycloak.representations.AccessToken;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

/**
 * @author Marek Perichta <mperichta@cesnet.cz>
 */
@RequestScoped
public class AccessTokenProducer {

    @Inject
    private HttpServletRequest request;

    @Produces
    public AccessToken getAccessToken() {
        return ((KeycloakPrincipal) request.getUserPrincipal())
                .getKeycloakSecurityContext().getToken();
    }
}