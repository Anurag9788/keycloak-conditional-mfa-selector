keycloak-conditional-mfa-selector


## Providers

`mvn package` will create a jar file.
copy `2-factor-selection-authenticator.jar` to `keycloak/providers/` directory.

## Build

Don't forget to start kc.sh with build parameter to make KeyCloak recognize the new povider:

> bin/kc.sh build


