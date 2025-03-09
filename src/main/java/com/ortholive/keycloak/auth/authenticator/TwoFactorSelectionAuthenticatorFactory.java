package com.ortholive.keycloak.auth.authenticator;


import org.keycloak.Config;
import org.keycloak.authentication.Authenticator;
import org.keycloak.authentication.AuthenticatorFactory;
import org.keycloak.models.AuthenticationExecutionModel;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.KeycloakSessionFactory;
import org.keycloak.provider.ProviderConfigProperty;
import java.util.ArrayList;
import java.util.List;

public class TwoFactorSelectionAuthenticatorFactory implements AuthenticatorFactory {

    public static final String PROVIDER_ID = "2-factor-selection";

    @Override
    public Authenticator create(KeycloakSession session) {
        return new TwoFactorSelectionAuthenticator();
    }

    @Override
    public void init(Config.Scope config) {
        // No initialization needed for now
    }

    @Override
    public void postInit(KeycloakSessionFactory factory) {
        // No post-initialization actions required
    }

    @Override
    public void close() {
        // No cleanup required
    }

    @Override
    public String getId() {
        return PROVIDER_ID;
    }

    @Override
    public String getDisplayType() {
        return "2 Factor Selection Authenticator";
    }

    @Override
    public String getHelpText() {
        return "Displays a form for users to choose a 2 factor (email, SMS, or recovery code).";
    }

    @Override
    public List<ProviderConfigProperty> getConfigProperties() {
        // No configurable properties in this example.
        return new ArrayList<>();
    }

    @Override
    public boolean isConfigurable() {
        return false;
    }

    @Override
    public AuthenticationExecutionModel.Requirement[] getRequirementChoices() {
        return new AuthenticationExecutionModel.Requirement[] {
                AuthenticationExecutionModel.Requirement.REQUIRED,
                AuthenticationExecutionModel.Requirement.ALTERNATIVE
        };
    }

    @Override
    public boolean isUserSetupAllowed() {
        return false;
    }

    @Override
    public String getReferenceCategory() {
        return "2-factor-selection";
    }
}
