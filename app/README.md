# Bakery orders web application

# Setup with 3x wildfly servers
- run keycloak-6.0.1 with ./standalone.sh  -Djboss.socket.binding.port-offset=100
- run service-wildfly with ./standalone.sh
- run web-wildfly ./standalone.sh  -Djboss.socket.binding.port-offset=1



