# logback-bundle

[Logback](http://logback.qos.ch/) dependencies gathered in one place for use with Clojure.

Requires Java 7, Clojure 1.5 or above.


## Usage

You should use Logback via a log facade, e.g. [SLF4j](http://www.slf4j.org/) or
a library that wraps SLF4j such as [Cambium](https://github.com/kumarshantanu/cambium),
[clj-io/logging](https://github.com/clj-io/logging) etc.


### Leiningen coordinates

Include the artifacts in your project:

| Leiningen artifact                     | Description                                  |
|----------------------------------------|----------------------------------------------|
| `[logback-bundle/core-bundle "0.3.0"]` | Core Logback dependencies                    |
| `[logback-bundle/json-bundle "0.3.0"]` | Logback Core+JSON dependencies using Jackson |


#### Clojars

* Core-bundle: https://clojars.org/logback-bundle/core-bundle
* JSON-bundle: https://clojars.org/logback-bundle/json-bundle


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


### Overriding log level at runtime

Logback-bundle provides support for overriding log levels at runtime using a strategy based
[TurboFilter](https://logback.qos.ch/manual/filters.html#TurboFilter).

#### One time setup

Add the following Logback configuration (or equivalent) to `logback.xml`:

```xml
    <turboFilter class="logback_bundle.core.StrategyTurboFilter">
      <name>mdcStrategy</name>
    </turboFilter>
    <turboFilter class="logback_bundle.core.StrategyTurboFilter">
      <name>multiStrategy</name>
    </turboFilter>
```


#### Configure log level overrides

Configure strategy:

```clojure
(require '[logback-bundle.core.strategy :as strategy])

;; set new log level to be determined by MDC attribute `forcelevel`
(strategy/set-mdc-strategy! "mdcStrategy" "forcelevel")

;; set levels for logger names for next 15 seconds, root logger being set to ERROR
(strategy/set-multi-strategy! "multiStrategy" "error" (strategy/multi-millis-validator 15000))
(strategy/set-log-level! "multiStrategy" ["com.foo" "com.bar"] "debug")
```


#### Generate log events

```clojure
(log/debug "If this code is in com.foo.* or com.bar.* namespace this event will be logged.")

(import '[org.slf4j MDC])

(MDC/put "forcelevel" "debug")  ; force new level to be considered DEBUG
(log/debug "This event will be logged overriding any existing INFO/WARN/ERROR level")
```


#### Remove log level overrides

You can remove log level overrides at any time:

```clojure
(strategy/remove-strategy! "mdcStrategy")
(strategy/remove-strategy! "multiStrategy")
```


### Configuring `logback_bundle.json.FlatJsonLayout`

The `logback_bundle.json.FlatJsonLayout` class is designed to accommodate customizations to preserve types/nesting
of MDC attributes and wholesale MDC transformation.

```clojure
(require '[logback-bundle.json.flat-layout :as flat])
```

#### Setting MDC value decoder

Setting a value decoder is useful when you encode MDC values (for preserving data types/nesting etc.) and want them to
be decoded before they are sent to the appender.

```clojure
;; assume EDN-encoded value, so parse using EDN reader
(flat/set-decoder! clojure.edn/read-string)
```


#### Setting MDC map transformer

In a large number of cases this may not be necessary. However, applying arbitrary transformation to the MDC map is
useful at times to backfill custom attributes, or to normalize certain attributes.

```clojure
(import 'java.util.Map)
;; insert an extra attribute to the MDC
(flat/set-transformer! (fn [^Map m] (.put m "extra-attr" "some value") m))
```


## License

Copyright © 2015-2017 Shantanu Kumar (kumar.shantanu@gmail.com, shantanu.kumar@concur.com)

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
