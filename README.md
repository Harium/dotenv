# dotenv [![Build Status](https://travis-ci.org/Harium/dotenv.svg?branch=master)](https://travis-ci.org/Harium/dotenv)
An utility tool to handle .env files in Java

## Maven
```
<dependency>
  <groupId>com.harium</groupId>
  <artifactId>dotenv</artifactId>
  <version>1.0.5</version>
</dependency>
```

## How to use it
Add a .env file to your current path (dotenv tries to load a file in `System.getProperty("user.dir")`);

Get your values using:
  ```
  String myValue = Env.get(your_variable);
  ```

## How it works
Dotenv looks for a .env file and loads it's contents. When you call
`Env.get(your_variable);`, dotenv tries to load your custom values.

If your variable is not declared in .env, it loads from the system, using `System.getenv(key)` (real environment values)

An example file: [example](https://github.com/Harium/dotenv/blob/master/.env).

## Debug Information
If you need more information about the library in runtime, just call:
`Env.DEBUG = true;`

## License
Apache License 2.0
