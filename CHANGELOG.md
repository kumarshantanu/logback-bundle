# Change Log
All notable changes to this project will be documented in this file.


## [WIP] 0.2.4 / 2017-February-??

- Update dependencies
  - `janino` from `2.7.8` to `3.0.6`
  - `logback-classic`, `logback-core` from `1.1.7` to `1.2.1`
  - `jackson-core`, `jackson-databind` from `2.5.3` to `2.8.6`


## 0.2.3 / 2016-August-09

- Update dependencies
  - `logback-classic`, `logback-core` from `1.1.6` to `1.1.7`
  - `logback-json-classic`, `logback-jackson` from `0.1.2` to `0.1.5`
- In `logback_bundle.json.FlatJsonLayout` use throwable-proxy-converter from
  the base class, thereby preserving correct lifecycle behavior


## 0.2.2 / 2016-March-22

- Allow `logback_bundle.json.FlatJsonlayout` to set a global MDC transformer


## 0.2.1 / 2016-March-09

- Upgrade logback classic/core dependencies to version `1.1.6`
- Allow `logback_bundle.json.FlatJsonlayout` to set a global MDC value decoder


## 0.2.0 / 2016-February-12

- Strategy based TurboFilter extension mechanism
  - Override log level at runtime via MDC key/value
  - Override log level per logger at runtime using dynamic strategy


## 0.1.0 / 2015-November-26

- Core bundle
- JSON bundle
- Flat JSON layout

