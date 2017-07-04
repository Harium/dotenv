# Publishing

- Change the version of the release in pom.xml (`mvn versions:set -DnewVersion=1.0.0`)
- mvn clean deploy

More details at [http://central.sonatype.org/pages/apache-maven.html](http://central.sonatype.org/pages/apache-maven.html).


# Testing publish

```
mvn clean install -Psonatype
```
