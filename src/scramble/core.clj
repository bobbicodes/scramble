(ns scramble.core
  (:require [ring.middleware.params :refer :all]
            [ring.util.response :refer :all]
            [ring.adapter.jetty :as jetty])
  (:gen-class))

(defn scramble?
  "Returns true if letters of string b are contained in string a."
  [a b]
 (every? #(>= (if (contains? (frequencies a) %)
                  (get (frequencies a) %)
                  0)
              (get (frequencies b) %)) (keys (frequencies b))))

(defn page [a b]
  (str "<html><body>"
       (if b
         (scramble? a b)
         (str "<form>"
              "String A: <input name='a' type='text'>"
	      "String B: <input name='b' type='text'>"
              "<input type='submit'>"
              "</form>"))
       "</body></html>"))

(defn handler [{{a "a" b "b"} :params}]
  (-> (response (page a b))
      (content-type "text/html")))

(def app
  (-> handler wrap-params))

(jetty/run-jetty app {:port 8080})

;(scramble? “rekqodlw” ”world') ==> true
;(scramble? “cedewaraaossoqqyt” ”codewars”) ==> true
;(scramble? “katas”  “steak”) ==> false

