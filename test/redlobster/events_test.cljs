(ns redlobster.events-test
  (:require [redlobster.events :as e]))

(def test-me (atom 0))

(def ee (e/event-emitter))
(e/on ee :ohai (fn [e] (swap! test-me #(+ % e))))
(e/emit ee :ohai 2)
(e/emit ee :ohai 3)

(assert (= @test-me 5))

(reset! test-me 0)

(e/once ee :single (fn [e] (swap! test-me #(+ % e))))
(e/emit ee :single 2)
(e/emit ee :single 3)

(assert (= @test-me 2))
