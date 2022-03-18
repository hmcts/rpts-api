ARG APP_INSIGHTS_AGENT_VERSION=3.2.4
FROM hmctspublic.azurecr.io/base/java:17-distroless

COPY lib/AI-Agent.xml /opt/app/
COPY build/libs/rpts-api.jar /opt/app/

EXPOSE 4000
CMD [ "rpts-api.jar" ]
