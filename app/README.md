    # Bakery orders web application

# Setup
- create directory ./standalone/tmp/images
- in ./bin/ run jboss-cli
- connect
- allow public access for our images directory by following commands
- /subsystem=undertow/configuration=handler/file=img:add(path="${jboss.server.temp.dir}/images", directory-listing="true")
- /subsystem=undertow/server=default-server/host=default-host/location="/images":add(handler="img")
- check that you can access to http://localhost:8080/images/ from your browser

## Configure
- Configure arquillian.xml file inside resources test folder to your local installed Wildfly

## Run without locally installed Wildfly
- mvn clean install
- inside bakery-orders-web run mvn clean wildfly:run

## Run with locally installed WildFly
- mvn clean package
- copy created .war file inside bakery-orders-web/target to wildfly/standalone/deployments
- inside wildfly/bin run standalone.bat or standalone.sh
