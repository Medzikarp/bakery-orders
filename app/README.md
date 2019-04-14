# Bakery orders web application

## Configure
- Configure arquillian.xml file inside resources test folder to your local installed Wildfly

## Run without locally installed Wildfly
- mvn clean install
- inside bakery-orders-web run mvn clean wildfly:run

## Run with locally installed WildFly
- mvn clean package
- copy created .war file inside bakery-orders-web/target to wildfly/standalone/deployments
- inside wildfly/bin run standalone.bat or standalone.sh
