package com.ortholive.keycloak.auth.authenticator;

import org.keycloak.Config;
import org.keycloak.authentication.Authenticator;
import org.keycloak.authentication.AuthenticatorFactory;
import org.keycloak.authentication.authenticators.conditional.ConditionalAuthenticator;
import org.keycloak.authentication.authenticators.conditional.ConditionalAuthenticatorFactory;
import org.keycloak.models.AuthenticationExecutionModel;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.KeycloakSessionFactory;
import org.keycloak.provider.ProviderConfigProperty;

import java.util.ArrayList;
import java.util.List;

public class TwoFactorConditionAuthenticatorFactory implements ConditionalAuthenticatorFactory {


    public static final String PROVIDER_ID = "2-factor-condition";
    public static final String CONFIG_EXPECTED_CHOICE = "expectedChoice";
    public  static final TwoFactorConditonAuthentication SINGLETON =  new TwoFactorConditonAuthentication();
    @Override
    public Authenticator create(KeycloakSession keycloakSession) {
        return SINGLETON;
    }




    @Override
    public void init(Config.Scope scope) {

    }

    @Override
    public void postInit(KeycloakSessionFactory keycloakSessionFactory) {

    }

    @Override
    public void close() {

    }

    @Override
    public String getId() {
        return PROVIDER_ID;
    }


    @Override
    public String getDisplayType() {
        return "2 Factor Condition Authenticator";
    }

    @Override
    public String getHelpText() {
        return "Checks the stored 2 factor choice against an expected value. Configure the expected value in the execution configuration.";
    }

    @Override
    public List<ProviderConfigProperty> getConfigProperties() {
        List<ProviderConfigProperty> configProperties = new ArrayList<>();
        ProviderConfigProperty expectedChoiceProp = new ProviderConfigProperty();
        expectedChoiceProp.setName(CONFIG_EXPECTED_CHOICE);
        expectedChoiceProp.setLabel("Expected 2 Factor");
        expectedChoiceProp.setType(ProviderConfigProperty.STRING_TYPE);
        expectedChoiceProp.setHelpText("Enter the expected 2 factor value (e.g., email, sms, authenticator, recovery)");
        configProperties.add(expectedChoiceProp);
        return configProperties;
    }

    @Override
    public boolean isConfigurable() {
        return true;
    }

    @Override
    public AuthenticationExecutionModel.Requirement[] getRequirementChoices() {
        return new AuthenticationExecutionModel.Requirement[]{
                AuthenticationExecutionModel.Requirement.REQUIRED,
                AuthenticationExecutionModel.Requirement.ALTERNATIVE,
                AuthenticationExecutionModel.Requirement.CONDITIONAL
        };
    }

    @Override
    public boolean isUserSetupAllowed() {
        return true;
    }

    @Override
    public String getReferenceCategory() {
        return "2-factor-condition";
    }

    @Override
    public ConditionalAuthenticator getSingleton() {
        return SINGLETON;
    }
}
