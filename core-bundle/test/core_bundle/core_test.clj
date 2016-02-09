(ns core-bundle.core-test
  (:require
    [clojure.test :refer :all]
    [cambium.core :as log])
  (:import
    [ch.qos.logback.classic Level]
    [logback_bundle.core StrategyTurboFilter]
    [logback_bundle.core.strategy MDCLevelOverrideStrategy MultiLevelOverrideStrategy MultiLevelOverrideValidator]))


(defmacro whereami
  "Return a map of attributes pointing to the call-site. May be useful for debugging. Sample return value is below:
  {:clj-varname \"foo.core/bar\",
   :method-name \"invoke\",
   :file-name   \"core.clj\",
   :class-name  \"foo.core$bar\",
   :line-number 21}"
  []
  `(let [e# (Exception.)
         ^StackTraceElement ste# (aget (.getStackTrace e#) 0)]
     ;; (.printStackTrace e#) ; uncomment this line to inspect stack trace
     {:class-name  (.getClassName  ste#)
      :file-name   (.getFileName   ste#)
      :line-number (.getLineNumber ste#)
      :method-name (.getMethodName ste#)
      :clj-varname (.replace (.getClassName  ste#) \$ \/)}))


(defmacro line
  [x]
  `(let [m# (whereami)]
     (format "<<%s:%3d>> %s" (:file-name m#) (:line-number m#) ~x)))


(deftest test-simple-log
  (testing "Simple test"
    (println "System property 'enable.dummy' =" (System/getProperty "enable.dummy"))
    (log/info (line "This is a test log."))))


(def mdc-strategy (MDCLevelOverrideStrategy. "mforce"))


(deftest test-mdc-level-override
  (log/info (line "Info message - no override - this log message should appear"))
  (log/debug (line "Debug message - no override - this log message should NOT appear"))
  (log/with-logging-context {:mforce "debug"}
    (log/debug (line "Debug message - override before MDC config - this log message should NOT appear")))
  (StrategyTurboFilter/setStrategy "mdcStrategy" mdc-strategy)
  (log/debug (line "Debug message - no override after MDC config - this log message should NOT appear"))
  (log/with-logging-context {:mforce "debug"}
    (log/debug (line "Debug message - override after MDC config - this log message should appear"))))


(def ^MultiLevelOverrideStrategy multi-strategy (MultiLevelOverrideStrategy. Level/DEBUG))


(log/deflogger foo "foo" :info :error)


(deftest test-multi-level-override
  (testing "Namespace logger"
    (log/debug (line "Debug message - override before MULTI config - this log message should NOT appear"))
    (StrategyTurboFilter/setStrategy "multiStrategy" multi-strategy)
    (.setValidator multi-strategy (constantly true))
    (log/info (line "Info message - override after MULTI config - this log message should appear"))
    (log/debug (line "Debug message - override after MULTI config - this log message should appear"))
    (log/trace (line "Trace message - lower override after MULTI config - this log message should NOT appear")))
  (testing "Custom logger"
    (.setLogLevel multi-strategy "foo" Level/ERROR)
    (foo (line "Info message - override after MULTI config - this log message should NOT appear"))
    (foo {} (Exception. "Ignore this exception")
      (line "Error message - override after MULTI config - this log message should appear"))))


(defn test-ns-hook
  []
  (test-simple-log)
  (test-mdc-level-override)
  (test-multi-level-override))
