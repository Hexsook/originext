# 🌍 originext
**originext** is a development library for Java developers. It provides a number of utilities and extended APIs
which help to reduce boilerplate code and offer alternative paradigms for creating content.

originext supports **Java 1.8** or above.

### Modules
##### [`originext-all`](https://github.com/Hexsook/originext/tree/master/all): The all-in-one module
##### [`originext-config`](https://github.com/Hexsook/originext/tree/master/config): Provides simplified ways to manage config
##### [`originext-object`](https://github.com/Hexsook/originext/tree/master/object): Provides object operations
##### [`originext-reflection`](https://github.com/Hexsook/originext/tree/master/reflection): Provides reflection operations
##### [`originext-common`](https://github.com/Hexsook/originext/tree/master/common): Provides common used classes and utilities
##### [`originext-annotations`](https://github.com/Hexsook/originext/tree/master/annotations): Provides common used annotations

### Adding originext to your project
**How to**: Add the dependency to pom.xml in your project:
```xml
<dependencies>
    <dependency>
        <groupId>me.hexsook</groupId>
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
**X** (Major version update) **. X** (Minor functional updates) **. X** (Bug fixes/Minor changes)