(ns cljs.node
  (:use-macros [cljs.node-macros :only [require]]))

(defn log [& args] (apply (.-log js/console) (map str args)))

(defn on-node? []
  (try (string? process.versions.node)
       (catch js/Error e false)))
