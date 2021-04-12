FROM openjdk:11-jre

ENV PORT=10001
ENV PROJECT_NAME=ecomm-payment-service
ENV JAR_VSERSION=1.0-SNAPSHOT
ENV REMOTE_DIR=/apps/ecomm
ENV REMOTE_NAME=${PROJECT_NAME}.jar
ENV JAR_FILE=build/libs/${PROJECT_NAME}-${JAR_VSERSION}.jar
RUN mkdir -p ${REMOTE_DIR}
COPY ${JAR_FILE} ${REMOTE_DIR}/${REMOTE_NAME}

ENTRYPOINT ["java", "-jar", "/apps/ecomm/ecomm-payment-service.jar"]
