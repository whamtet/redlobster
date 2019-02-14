(ns redlobster.https
  (:require-macros [cljs.node-macros :as n])
  (:require [redlobster.events :as e]
            [redlobster.promise :as p]
            [redlobster.stream :as s])
  (:use [cljs.node :only [log]])
  (:use-macros [redlobster.macros :only [promise let-realised]]))

(n/require "https" https)

(defn request
  ([options body]
     (promise
      (let [req (.request https (clj->js options) #(realise %))]
        (e/on req "error" #(realise-error %))
        (if body (s/write-stream req body)
            (.end req)))))
  ([options]
     (request options nil)))
