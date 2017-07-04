# dotenv
A DotEnv to Java Applications

## Maven
```
<dependency>
  <groupId>com.harium</groupId>
  <artifactId>dotenv</artifactId>
  <version>1.0.0</version>
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
