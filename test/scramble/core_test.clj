(ns scramble.core-test
  (:require [clojure.test :refer :all]
            [scramble.core :refer :all]))

(deftest scramble-test
  (is (true? (scramble? "rekqodlw" "world")))
  (is (true? (scramble? "cedewaraaossoqqyt" "codewars")))
  (is (false? (scramble? "katas"  "steak"))))
