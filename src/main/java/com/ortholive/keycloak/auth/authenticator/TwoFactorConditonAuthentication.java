package com.ortholive.keycloak.auth.authenticator;

import org.keycloak.authentication.AuthenticationFlowContext;
import org.keycloak.authentication.authenticators.conditional.ConditionalAuthenticator;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.RealmModel;
import org.keycloak.models.UserModel;

public class TwoFactorConditonAuthentication implements ConditionalAuthenticator {
    @Override
    public boolean matchCondition(AuthenticationFlowContext authenticationFlowContext) {
        String expectedChoice = null;
        if(authenticationFlowContext.getAuthenticatorConfig()!=null && authenticationFlowContext.getAuthenticatorConfig().getConfig()!=null){
            expectedChoice = authenticationFlowContext.getAuthenticatorConfig().getConfig().get("expectedChoice");
        }
        String selectedChoice = authenticationFlowContext.getAuthenticationSession().getAuthNote("2FactorChoice");
        return  expectedChoice !=null && expectedChoice.equalsIgnoreCase( selectedChoice);
    }
    @Override
    public void authenticate(AuthenticationFlowContext context) {
        // This conditional authenticator is only for checking the condition; mark success.
        context.success();
    }

    @Override
    public void action(AuthenticationFlowContext authenticationFlowContext) {
        authenticationFlowContext.success();

    }

    @Override
    public boolean requiresUser() {
        return false;
    }

    @Override
    public void setRequiredActions(KeycloakSession keycloakSession, RealmModel realmModel, UserModel userModel) {

    }

    @Override
    public void close() {

    }
}
