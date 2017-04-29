(defproject logback-bundle/core-bundle "0.3.0-SNAPSHOT"
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
                *unchecked-math* :warn-on-boxed}
  :profiles {:dev {:dependencies [[org.clojure/clojure  "1.8.0"]
                                  [cambium/cambium.core "0.9.0"]  ; pulls in [org.slf4j/slf4j-api "1.7.25"]
                                  [cambium/cambium.codec-simple "0.9.0"]]
                   :jvm-opts ["-Denable.dummy=true"]}})
