(ns cljs.node
  (:use-macros [cljs.node-macros :only [require]]))

(defn log [& args] (apply (.-log js/console) (map str args)))
