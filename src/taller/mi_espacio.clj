(ns taller.mi-espacio
  (:require [clojure.set]
            [clojure.string :as st]
            [clojure.repl :refer [dir doc] :as r :rename {doc documentación}])
  (:use [clojure.walk])
  (:import (java.util.logging Logger
                              Level)))


(documentación postwalk)
