(defproject logback-bundle/json-bundle "0.2.1"
  :description "Core and JSON Logback dependencies bundle"
  :url "https://github.com/kumarshantanu/logback-bundle"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[logback-bundle/core-bundle "0.2.1"]
                 [com.fasterxml.jackson.core/jackson-core     "2.5.3"]  ; in use by cheshire 5.5.0
                 [com.fasterxml.jackson.core/jackson-databind "2.5.3"]  ; in use by cheshire 5.5.0
                 [ch.qos.logback.contrib/logback-json-classic "0.1.2"]
                 [ch.qos.logback.contrib/logback-jackson      "0.1.2"]]
  :java-source-paths ["java-src"]
  :javac-options ["-target" "1.7" "-source" "1.7" "-Xlint:-options"]
  :global-vars {*warn-on-reflection* true
                *unchecked-math* :warn-on-boxed}
  :profiles {:dev {:dependencies [[org.clojure/clojure "1.8.0"]
                                  [cambium             "0.3.0"]
                                  [org.slf4j/slf4j-api "1.7.12"]]}})
