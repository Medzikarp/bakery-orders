SWARM_URL='http://repo2.maven.org/maven2/org/wildfly/swarm/servers/microprofile/2018.3.3/microprofile-2018.3.3-hollowswarm.jar'
SWARM_JAR='microprofile-hollowswarm.jar'

.PHONY: build
build:
	mvn package -DskipTests

.PHONY: run
run:
	test -f $(SWARM_JAR) || wget -O $(SWARM_JAR) $(SWARM_URL)
	java -jar $(SWARM_JAR) ./bakery-orders-web/target/*.war

.PHONY: open
open:
	-(sleep 8 && xdg-open http://localhost:8080 &> /dev/null)&
