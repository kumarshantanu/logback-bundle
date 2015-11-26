# logback-bundle

[Logback](http://logback.qos.ch/) dependencies gathered in one place. Projects
written in any JVM language can use this library.


## Usage

You should use Logback via a log facade, e.g. [SLF4j](http://www.slf4j.org/) or
a library that wraps SLF4j such as [Cambium](https://github.com/kumarshantanu/cambium),
[clj-io/logging](https://github.com/clj-io/logging) etc.


### Maven coordinates

Include the artifacts in your Leiningen/Gradle/SBT/Maven project.

| Leiningen artifact                     | Description                                  |
|----------------------------------------|----------------------------------------------|
| `[logback-bundle/core-bundle "0.1.0"]` | Core Logback dependencies                    |
| `[logback-bundle/json-bundle "0.1.0"]` | Logback Core+JSON dependencies using Jackson |

#### Clojars

Core-bundle: https://clojars.org/logback-bundle/core-bundle
JSON-bundle: https://clojars.org/logback-bundle/json-bundle


### Quickstart (plain text logging)

Create a `logback.xml` file in the _resources_ directory of your project with
the following content:

```xml
<configuration>

  <appender name="STDOUT" class="ch.qos.logback.core.ConsoleAppender">
    <!-- encoders are assigned the type
         ch.qos.logback.classic.encoder.PatternLayoutEncoder by default -->
    <encoder>
      <pattern>%d{HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n</pattern>
    </encoder>
  </appender>

  <root level="debug">
    <appender-ref ref="STDOUT" />
  </root>

</configuration>
```


### Quickstart (JSON logging)

Create a `logback.xml` file in the _resources_ directory of your project with
with the following content:

```xml
<configuration>

    <appender name="STDOUT" class="ch.qos.logback.core.ConsoleAppender">
        <encoder class="ch.qos.logback.core.encoder.LayoutWrappingEncoder">
          <layout class="ch.qos.logback.contrib.json.classic.JsonLayout">
            <jsonFormatter class="ch.qos.logback.contrib.jackson.JacksonJsonFormatter">
              <!-- prettyPrint is probably ok in dev, but usually not ideal in production: -->
              <prettyPrint>true</prettyPrint>
            </jsonFormatter>
            <!-- <context>api</context> -->
            <timestampFormat>yyyy-MM-dd'T'HH:mm:ss.SSS'Z'</timestampFormat>
            <timestampFormatTimezoneId>UTC</timestampFormatTimezoneId>
            <appendLineSeparator>true</appendLineSeparator>
          </layout>
        </encoder>
    </appender>

    <root level="debug">
      <appender-ref ref="STDOUT" />
    </root>

</configuration>
```

The above snippet uses the default _nested_ JSON layout where the
[Mapped Diagnostic Context](http://logback.qos.ch/manual/mdc.html) (_MDC_) map
appears as a child `mdc` attribute in the JSON output. To instead use a _flat_
layout where the _MDC_ pairs appear at the same level as log attributes,
replace `ch.qos.logback.contrib.json.classic.JsonLayout` with
`logback_bundle.json.FlatJsonLayout`.


## Development

For development you need JDK 1.7 (or higher) and [Leiningen](http://leiningen.org/).


## License

Copyright © 2015 Shantanu Kumar (kumar.shantanu@gmail.com, shantanu.kumar@concur.com)

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
