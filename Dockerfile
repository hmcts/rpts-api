ARG APP_INSIGHTS_AGENT_VERSION=3.4.11
FROM hmctspublic.azurecr.io/base/java:17-distroless

COPY build/libs/spring-boot-template.jar /opt/app/

EXPOSE 4000
CMD [ "rpts-api.jar" ]
