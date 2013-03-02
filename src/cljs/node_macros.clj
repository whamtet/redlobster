(ns cljs.node-macros
  (:refer-clojure :exclude [require]))

(defmacro require [path sym]
  (if (vector? sym)
    `(do
       ~@(for [s sym]
           `(def ~s (aget (js/require ~path) ~(str s)))))
    `(def ~sym (js/require ~path))))
