# excel-formulas
[![Build Status](https://travis-ci.org/dyba/fellowship-one.svg)](https://travis-ci.org/dyba/fellowship-one)

Excel formuals ported to Clojure

## Usage

``` clojure
(ns test-drive.core
  (:require [com.pennymacusa.excel-formulas :refer [days360]]
            [clj-time.parse :as f]))
  
(days360 (f/parse "2015-02-14") (f/parse "2015-08-30"))
```

## License

Copyright © 2015

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
