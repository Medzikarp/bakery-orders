# Bakery orders web application

# Setup
- build project
- run key cloak with offset: ./standalone.sh  -Djboss.socket.binding.port-offset=100
- copy bakery-orders-web.war and bakery-orders-service.war to wildfly/wildfly-14.0.1.Final/standalone/deployments
- go to wildfly/wildfly-14.0.1.Final/bin and run ./standalone.sh  -Djboss.socket.binding.port-offset=1

