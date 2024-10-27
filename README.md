# 🌍 originext
**originext** is a development library for Java developers. It provides a number of utilities and extended APIs
which help to reduce boilerplate code and offer alternative paradigms for creating content.

originext supports **Java 1.8** or above.

### Modules
##### `originext-all`: The all-in-one module
##### `originext-config`: Provides simplified ways to manage config
##### `originext-object`: Provides object operations
##### `originext-reflection`: Provides reflection operations
##### `originext-common`: Provides common used classes and utilities

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