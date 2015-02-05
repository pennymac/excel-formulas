(ns com.pennymacusa.excel-formulas.core-test
  (:require [clojure.test :refer :all]
            [com.pennymacusa.excel-formulas.core :refer :all]))

(deftest calculating-days-between-dates-in-360-day-year
  (testing "days360"
    (testing "when the start-date and end-date are in the middle of a month"
      (is (= (days360 "2008-01-30" "2008-02-01") 1))
      (is (= (days360 "2008-01-01" "2008-02-01") 30)))
    (testing "when the start-date is at the end of the month"
      (is (= (days360 "2015-01-31" "2015-02-01") 1))
      (is (= (days360 "1996-02-29" "1996-03-01") 1))
      (is (= (days360 "1993-02-28" "1993-03-01") 1))
      (is (= (days360 "2008-02-29" "2008-03-30") 30)))
    (testing "when the start-date and end-date are at the end of the month"
      (is (= (days360 "2008-02-29" "2008-03-31") 30))
      (is (= (days360 "2008-02-29" "2008-08-31") 180)))
    (testing "when the end-date is on the 31st of the month"
      (is (= (days360 "2008-01-30" "2008-01-31") 0))
      (is (= (days360 "2008-02-28" "2008-03-31") 33))
      (is (= (days360 "2015-01-01" "2015-01-31") 30))
      (is (= (days360 "2008-01-01" "2008-12-31") 360)))))
