########################################################
# Dockerfile per l'applicazione infoUniService
########################################################

FROM frolvlad/alpine-oraclejdk8 

ADD build/libs/infoUniService-0.0.1-SNAPSHOT.jar infoUniService.jar

EXPOSE 8080

ENTRYPOINT ["/usr/bin/java", "-Xmx128m", "-Xms128m"]

CMD ["-jar", "infoUniService.jar"]


