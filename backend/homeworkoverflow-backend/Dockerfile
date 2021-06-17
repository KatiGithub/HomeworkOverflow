FROM openjdk:11
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]

FROM nginx
COPY nginx.conf /etc/nginx/nginx.conf
EXPOSE 8080;
CMD ["nginx", "-g" "daemon off;"]