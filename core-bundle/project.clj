(defproject logback-bundle/core-bundle "0.3.0"
  :description "Core Logback dependencies bundle"
  :url "https://github.com/kumarshantanu/logback-bundle"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.codehaus.janino/janino     "3.0.7"]  ; for conditional config processing
                 [ch.qos.logback/logback-classic "1.2.3"]
                 [ch.qos.logback/logback-core    "1.2.3"]]
  :java-source-paths ["java-src"]
  :javac-options ["-target" "1.7" "-source" "1.7" "-Xlint:-options"]
  :global-vars {*warn-on-reflection* true
                *assert* true
                *unchecked-math* :warn-on-boxed}
  :profiles {:provided {:dependencies [[org.clojure/clojure  "1.5.1"]]}
             :dev {:dependencies [[cambium/cambium.core "0.9.0"]  ; pulls in [org.slf4j/slf4j-api "1.7.25"]
                                  [cambium/cambium.codec-simple "0.9.0"]]
                   :jvm-opts ["-Denable.dummy=true"]}
             :c15 {:dependencies [[org.clojure/clojure  "1.5.1"]]}
             :c16 {:dependencies [[org.clojure/clojure  "1.6.0"]]}
             :c17 {:dependencies [[org.clojure/clojure  "1.7.0"]]}
             :c18 {:dependencies [[org.clojure/clojure  "1.8.0"]]}
             :c19 {:dependencies [[org.clojure/clojure  "1.9.0-alpha16"]]}
             :dln {:jvm-opts ["-Dclojure.compiler.direct-linking=true"]}})
