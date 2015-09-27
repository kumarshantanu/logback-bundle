(defproject logback-bundle/core-bundle "0.1.0-SNAPSHOT"
  :description "Core Logback dependencies bundle"
  :url "https://github.com/kumarshantanu/logback-bundle"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.codehaus.janino/janino     "2.7.8"]  ; for conditional config processing
                 [ch.qos.logback/logback-classic "1.1.3"]
                 [ch.qos.logback/logback-core    "1.1.3"]]
  :profiles {:dev {:dependencies [[org.clojure/clojure "1.7.0"]
                                  [org.clojure/tools.logging "0.3.1" :exclusion [org.clojure/clojure]]
                                  ;;[org.slf4j/slf4j-api "1.7.12"]
                                  ]
                   :jvm-opts ["-Denable.dummy=true"]}})

