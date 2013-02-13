(defproject org.bodil/redlobster "0.2.0"
  :description "Promises for Node"
  :url "https://github.com/bodil/redlobster"
  :license {:name "Apache License, version 2.0"
            :url "http://www.apache.org/licenses/LICENSE-2.0.html"}
  :dependencies [[org.clojure/clojure "1.4.0"]]
  :plugins [[lein-cljsbuild "0.3.0"]
            [org.bodil/lein-noderepl "0.1.5"]]
  :cljsbuild {:builds
              [{:source-paths ["src"]
                :compiler
                {:output-to "js/main.js"
                 :output-dir "js"
                 :optimizations :simple
                 :jar true}}]})
