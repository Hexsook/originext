# 🌍 originext
**originext** is a development library for Java developers. It provides a number of utilities and extended APIs
which help to reduce boilerplate code and offer alternative paradigms for creating content.

originext supports **Java 1.8** or above.

### Modules
##### [`originext-config`](https://github.com/Hexsook/originext/tree/master/config): Provides simplified ways to manage config. ![Maven Central Version](https://img.shields.io/maven-central/v/io.github.hexsook/originext-config)
##### [`originext-object`](https://github.com/Hexsook/originext/tree/master/object): Provides object operations. ![Maven Central Version](https://img.shields.io/maven-central/v/io.github.hexsook/originext-object)
##### [`originext-reflection`](https://github.com/Hexsook/originext/tree/master/reflection): Provides reflection operations. ![Maven Central Version](https://img.shields.io/maven-central/v/io.github.hexsook/originext-reflection)
##### [`originext-common`](https://github.com/Hexsook/originext/tree/master/common): Provides common used classes and utilities. ![Maven Central Version](https://img.shields.io/maven-central/v/io.github.hexsook/originext-common)

### Adding originext to your project
**How to**: Add the dependency to pom.xml in your project:
```xml
<dependencies>
    <dependency>
        <groupId>io.github.hexsook</groupId>
        <artifactId>[module]</artifactId>
        <version>[version]</version>
        <scope>compile</scope>
    </dependency>
</dependencies>
```
##### Placeholders:
`[module]` - the module you need to import. **See [Modules](#modules).** <br>
`[version]` - the module version.

### About Project
##### Version suffixes:
##### `no suffixes` - **Release version.**

`-pr` - Pre-release version. <br>
`-beta` - Beta test version. <br>
`-unstable` - Internal testing. (Very unstable!) <br>

##### Version naming rule (e.g. 1.3.2):
**X** (Major code changes) **. X** (Minor functional updates) **. X** (Bug fixes/Minor changes)