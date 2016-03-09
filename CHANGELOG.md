# Change Log
All notable changes to this project will be documented in this file.


## [0.2.1] - 2016-March-09

- Upgrade logback classic/core dependencies to version `1.1.6`
- Allow `logback_bundle.json.FlatJsonlayout` to set a global MDC value decoder


## [0.2.0] - 2016-February-12
- Strategy based TurboFilter extension mechanism
  - Override log level at runtime via MDC key/value
  - Override log level per logger at runtime using dynamic strategy


## [0.1.0] - 2015-November-26
- Core bundle
- JSON bundle
- Flat JSON layout

