# dotenv
[![CircleCI](https://circleci.com/gh/Harium/dotenv.svg?style=svg)](https://circleci.com/gh/Harium/dotenv)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.harium/dotenv/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.harium/dotenv/)

An utility tool to handle .env files in Java.

## Maven
```
<dependency>
  <groupId>com.harium</groupId>
  <artifactId>dotenv</artifactId>
  <version>1.0.6</version>
</dependency>
```

## Example
Create a .env file and place it at the root folder of your project.

Get your variables using:
```java
String myValue = Env.get("MY_SECRET");
```

Alternatively, you can use a custom path using the path parameter:
```java
String myValue = Env.path("/home/user/myFolder").get("MY_SECRET");
```

## Fallback
If your variable is not declared in .env, dotenv will try to load it from the environment variables.

## How it works
Dotenv looks for a .env file and loads it's contents. When you call
`Env.get("MY_SECRET");` for the first time, dotenv will try to load all the variables and cache them.

At this repo you can find an example (.env) file: [example](https://github.com/Harium/dotenv/blob/master/.env).

## License
Apache License 2.0
