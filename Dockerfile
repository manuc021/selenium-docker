 FROM bellsoft/liberica-openjdk-alpine:17.0.6

RUN apk add curl jq

#workspace
WORKDIR /usr/share/udmey

#ADD all .jar if we have any additional file need to add 
ADD target/selenium-docker.jar         	selenium-docker.jar
ADD target/selenium-docker-tests.jar   	selenium-docker-tests.jar
ADD target/libs                    		libs


ADD book-flight-module.xml				book-flight-module.xml
ADD search-module.xml					search-module.xml

# ADD health check script
ADD healthcheck.sh healthcheck.sh
RUN dos2unix healthcheck.sh

ENTRYPOINT sh healthcheck.sh