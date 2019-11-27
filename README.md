# scg-oauth-demo
Demo Application with Spring Cloud Gateway, UAA, Eureka and Token Relay

## UAA

### Build UAA Container Image with pack

```
cd uaa/
export UAA_VERSION="74.11.0"
pack build uaa:${UAA_VERSION} --builder cloudfoundry/cnb:bionic -e BP_BUILT_MODULE=uaa -e ORG_GRADLE_PROJECT_version=${UAA_VERSION} -p ./uaa
```

### Run UAA local with docker

```
docker run -d --name uaa -p 8080:8080 -e UAA_CONFIG_PATH="./uaa_config" -v "$(pwd)/uaa_config":/uaa_config uaa
```

Add uaa to your hosts file. This is necessary locally due to cookies being unaware of ports.
```
echo "127.0.0.1 uaa" | sudo tee -a /etc/hosts
```

## Services

### Maven

run in multiple shells:
```
mvn spring-boot:run -f ./registry/pom.xm
mvn spring-boot:run -f ./gateway/pom.xm
mvn spring-boot:run -f ./service/pom.xm
mvn spring-boot:run -f ./resource/pom.xm
```

### Docker Desktop

#### Build Docker Images

```
pack build vchrisb/registry --builder cloudfoundry/cnb:bionic -p ./registry
pack build vchrisb/gateway --builder cloudfoundry/cnb:bionic -p ./gateway
pack build vchrisb/service --builder cloudfoundry/cnb:bionic -p ./service
pack build vchrisb/resource --builder cloudfoundry/cnb:bionic -p ./resource
```

#### Run

```
docker run -d --name registry -p 8761:8080 -e PORT=8080 vchrisb/registry
docker run -d --name gateway -p 8081:8080 -e PORT=8080 -e EUREKA_URI="http://registry:8080/eureka" --link registry -e ISSUER_URI="http://uaa:8080/oauth/token" --link uaa vchrisb/gateway
docker run -d --name service -e PORT=8080 -e EUREKA_URI="http://registry:8080/eureka" --link registry -e ISSUER_URI="http://uaa:8080/oauth/token" --link uaa vchrisb/service
docker run -d --name resource -e PORT=8080 -e EUREKA_URI="http://registry:8080/eureka" --link registry -e ISSUER_URI="http://uaa:8080/oauth/token" --link uaa vchrisb/resource
```
