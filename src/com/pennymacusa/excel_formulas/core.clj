(ns com.pennymacusa.excel-formulas.core
  (:require [clj-time.core :as t]
            [clj-time.format :as f]
            [clj-time.predicates :as p]))

(defn days360
  "Calculates the number of days between a start-date and end-date according to
  a 360-day year. Expects a string formatted as a date."
  [start-date end-date]
  (let [[sd ed] (let [d1 (f/parse start-date)
                      d2 (f/parse end-date)]
                  (if (t/after? d1 d2) [d2 d1] [d1 d2]))
        [sdy sdm sdd] [(t/year sd) (t/month sd) (t/day sd)]
        [edy edm edd] [(t/year ed) (t/month ed) (t/day ed)]
        sdd (if (p/last-day-of-month? sd)
              30
              sdd)
        [edm edd] (if (and (p/last-day-of-month? ed)
                           (if (p/february? sd)
                             (< (t/day sd) 29)
                             (< (t/day sd) 30)))
                    [(+ 1 (t/month ed)) 1]
                    [(t/month ed) (min 30 (t/day ed))])]
    (+ (* 360 (- edy sdy))
       (* 30 (- edm sdm))
       (- edd sdd))))
