FROM ubuntu:22.04
RUN apt-get update && apt install tzdata -y
ENV TZ="Europe/Warsaw"
RUN apt install software-properties-common -y && add-apt-repository ppa:deadsnakes/ppa && apt-get install python3.8 -y
RUN apt-get install openjdk-8-jdk -y
RUN apt-get install kotlin -y

