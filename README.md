# dotenv [![Build Status](https://travis-ci.org/Harium/dotenv.svg?branch=master)](https://travis-ci.org/Harium/dotenv)
An utility tool to handle .env files in Java

## Maven
```
<dependency>
  <groupId>com.harium</groupId>
  <artifactId>dotenv</artifactId>
  <version>1.0.1</version>
</dependency>
```

## How to use it
Add a .env file to your current path (dotenv tries to load a file in System.getProperty("user.dir"));

You can see an example file [here](https://raw.githubusercontent.com/Harium/dotenv/master/src/main/.env)

## Debug Information
If you need more information about the library in runtime, just call:
`Env.DEBUG = true;`

## License
Apache License 2.0
