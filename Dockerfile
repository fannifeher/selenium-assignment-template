FROM gradle:8.8-jdk17

USER root
RUN apt-get update \
    && apt-get install -y --no-install-recommends curl ca-certificates \
    && rm -rf /var/lib/apt/lists/*

RUN mkdir -p /home/selenium/tests \
    && chown -R gradle:gradle /home/selenium

USER gradle
WORKDIR /home/selenium/tests

CMD ["/bin/bash"]
