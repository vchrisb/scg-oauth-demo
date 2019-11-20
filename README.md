# scg-oauth-demo
Demo Application with Spring Cloud Gateway, UAA, Eureka and Token Relay

## UAA

### Build UAA Container Image with pack

```
cd uaa/
export UAA_VERSION="74.8.0"
pack build uaa:${UAA_VERSION} --builder cloudfoundry/cnb:bionic -e BP_BUILT_MODULE=uaa -e ORG_GRADLE_PROJECT_version=${UAA_VERSION}
```

### Run UAA local with docker

```
docker run -d --name uaa -p 8080:8080 -e UAA_CONFIG_PATH="./uaa_config" -v ${pdw}/uaa_config:/uaa_config uaa
```
