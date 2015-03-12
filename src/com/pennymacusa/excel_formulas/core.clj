(ns com.pennymacusa.excel-formulas.core
  (:require [clj-time.core :as t]
            [clj-time.format :as f]
            [clj-time.predicates :as p]))

(defn abs
  "Calculates the absolute value of a number."
  {:added "0.2.1"}
  [n]
  (if (pos? n)
    n
    (- n)))

(defn days360
  "Calculates the number of days between a start-date and end-date according to
  a 360-day year. Expects date-time objects."
  [start-date end-date]
  (let [[sd ed] (if (t/after? start-date end-date)
                  [end-date start-date]
                  [start-date end-date])
        [sdy sdm sdd] (let [day (if (p/last-day-of-month? sd)
                                  30
                                  (t/day sd))]
                        [(t/year sd) (t/month sd) day])
        [edy edm edd] (let [[month day] (if (and (p/last-day-of-month? ed)
                                                 (if (p/february? sd)
                                                   (< (t/day sd) 29)
                                                   (< (t/day sd) 30)))
                                          [(+ 1 (t/month ed)) 1]
                                          [(t/month ed) (min 30 (t/day ed))])]
                        [(t/year ed) month day])]
    (+ (* 360 (- edy sdy))
       (* 30 (- edm sdm))
       (- edd sdd))))
