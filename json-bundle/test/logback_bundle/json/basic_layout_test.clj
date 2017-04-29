;   Copyright (c) Shantanu Kumar. All rights reserved.
;   The use and distribution terms for this software are covered by the
;   Eclipse Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php)
;   which can be found in the file LICENSE at the root of this distribution.
;   By using this software in any fashion, you are agreeing to be bound by
;   the terms of this license.
;   You must not remove this notice, or any other, from this software.


(ns logback-bundle.json.basic-layout-test
  (:require
    [clojure.test :refer :all]
    [cambium.core :as log])
  (:import
    [org.slf4j MDC]))


(deftest a-test
  (testing "Simple test"
    (MDC/put "foo" "bar")
    (MDC/put "baz" "qux")
    (log/info "hello")))


(deftest e-test
  (testing "Exception"
    (MDC/put "x" "y")
    (log/error {} (RuntimeException. "Exception happened") "Testing exception")))
