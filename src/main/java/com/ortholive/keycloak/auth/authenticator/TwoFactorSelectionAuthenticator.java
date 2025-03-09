package com.ortholive.keycloak.auth.authenticator;

import jakarta.ws.rs.core.Response;
import org.keycloak.authentication.AuthenticationFlowContext;
import org.keycloak.authentication.Authenticator;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.RealmModel;
import org.keycloak.models.UserModel;
import org.keycloak.forms.login.LoginFormsProvider;

public class TwoFactorSelectionAuthenticator implements Authenticator {

    @Override
    public void authenticate(AuthenticationFlowContext context) {
        // Check if a 2 factor choice is already made
        String choice = context.getAuthenticationSession().getAuthNote("2FactorChoice");
        if (choice != null) {
            context.success();
            return;
        }
        // Render the MFA selection form
        LoginFormsProvider form = context.form();
        Response challenge = form.createForm("mfa-selection.ftl");
        context.challenge(challenge);
    }

    @Override
    public void action(AuthenticationFlowContext context) {
        // Process the form submission
        String choice = context.getHttpRequest().getDecodedFormParameters().getFirst("2FactorChoice");
        if (choice == null || choice.trim().isEmpty()) {
            Response challenge = context.form()
                    .setError("Please select a 2-factor option.")
                    .createForm("mfa-selection.ftl");
            context.failureChallenge(org.keycloak.authentication.AuthenticationFlowError.INVALID_USER, challenge);
            return;
        }
        // Store the choice for later conditional branching in the flow
        context.getAuthenticationSession().setAuthNote("2FactorChoice", choice);
        context.success();
    }

    @Override
    public boolean requiresUser() {
        // User has been authenticated via username/password already
        return true;
    }

    @Override
    public boolean configuredFor(KeycloakSession session, RealmModel realm, UserModel user) {
        // This authenticator always runs so we return true.
        return true;
    }

    @Override
    public void setRequiredActions(KeycloakSession session, RealmModel realm, UserModel user) {
        // No additional required actions needed.
    }

    @Override
    public void close() {
        // No resources to close.
    }
}
