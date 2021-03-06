(defproject logback-bundle/json-bundle "0.3.0"
  :description "Core and JSON Logback dependencies bundle"
  :url "https://github.com/kumarshantanu/logback-bundle"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[logback-bundle/core-bundle "0.3.0"]
                 [com.fasterxml.jackson.core/jackson-core     "2.8.6"]  ; in use by cheshire 5.7.0
                 [com.fasterxml.jackson.core/jackson-databind "2.8.6"]  ; in use by cheshire 5.7.0
                 [ch.qos.logback.contrib/logback-json-classic "0.1.5"]
                 [ch.qos.logback.contrib/logback-jackson      "0.1.5"]]
  :java-source-paths ["java-src"]
  :javac-options ["-target" "1.7" "-source" "1.7" "-Xlint:-options"]
  :global-vars {*warn-on-reflection* true
                *unchecked-math* :warn-on-boxed}
  :profiles {:provided {:dependencies [[org.clojure/clojure  "1.5.1"]]}
             :dev {:dependencies [[cambium/cambium.core "0.9.0"]  ; pulls in [org.slf4j/slf4j-api "1.7.25"]
                                  [cambium/cambium.codec-cheshire "0.9.0"]]}
             :c15 {:dependencies [[org.clojure/clojure  "1.5.1"]]}
             :c16 {:dependencies [[org.clojure/clojure  "1.6.0"]]}
             :c17 {:dependencies [[org.clojure/clojure  "1.7.0"]]}
             :c18 {:dependencies [[org.clojure/clojure  "1.8.0"]]}
             :c19 {:dependencies [[org.clojure/clojure  "1.9.0-alpha16"]]}
             :dln {:jvm-opts ["-Dclojure.compiler.direct-linking=true"]}})
