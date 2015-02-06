(ns com.pennymacusa.excel-formulas.core-test
  (:require [clojure.test :refer :all]
            [clj-time.format :as f]
            [com.pennymacusa.excel-formulas.core :refer :all]))

(deftest calculating-days-between-dates-in-360-day-year
  (testing "days360"
    (testing "when the start-date and end-date are in the middle of a month"
      (is (= (days360 (f/parse "2008-01-30") (f/parse "2008-02-01")) 1))
      (is (= (days360 (f/parse "2008-01-01") (f/parse "2008-02-01")) 30)))
    (testing "when the start-date is at the end of the month"
      (is (= (days360 (f/parse "2015-01-31") (f/parse "2015-02-01")) 1))
      (is (= (days360 (f/parse "1996-02-29") (f/parse "1996-03-01")) 1))
      (is (= (days360 (f/parse "1993-02-28") (f/parse "1993-03-01")) 1))
      (is (= (days360 (f/parse "2008-02-29") (f/parse "2008-03-30")) 30)))
    (testing "when the start-date and end-date are at the end of the month"
      (is (= (days360 (f/parse "2008-02-29") (f/parse "2008-03-31")) 30))
      (is (= (days360 (f/parse "2008-02-29") (f/parse "2008-08-31")) 180)))
    (testing "when the end-date is on the 31st of the month"
      (is (= (days360 (f/parse "2008-01-30") (f/parse "2008-01-31")) 0))
      (is (= (days360 (f/parse "2008-02-28") (f/parse "2008-03-31")) 33))
      (is (= (days360 (f/parse "2015-01-01") (f/parse "2015-01-31")) 30))
      (is (= (days360 (f/parse "2008-01-01") (f/parse "2008-12-31")) 360)))))
