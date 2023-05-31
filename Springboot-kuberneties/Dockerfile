FROM openjdk
EXPOSE 9091
ADD target/k8s-app.jar k8s-app.jar
ENTRYPOINT ["java", "-jar", "k8s-app.jar"]