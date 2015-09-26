(ns core-bundle.core-test
  (:require [clojure.test :refer :all]
            [clojure.tools.logging :as log]))

(deftest a-test
  (testing "Simple test"
    (println "System property 'enable.dummy' =" (System/getProperty "enable.dummy"))
    (log/info "This is a test log.")))

