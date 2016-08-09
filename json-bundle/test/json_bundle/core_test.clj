(ns json-bundle.core-test
  (:require [clojure.test :refer :all]
            [clojure.tools.logging :as log])
  (:import [org.slf4j MDC]))


(deftest a-test
  (testing "Simple test"
    (MDC/put "foo" "bar")
    (MDC/put "baz" "qux")
    (log/info "hello")))


(deftest e-test
  (testing "Exception"
    (MDC/put "x" "y")
    (log/error {} (RuntimeException. "Exception happened") "Testing exception")))
