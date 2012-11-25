(defproject org.bodil/redlobster "0.1.0"
  :description "Promises for Node"
  :url "https://github.com/bodil/redlobster"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.4.0"]]
  :plugins [[lein-cljsbuild "0.2.9"]
            [org.bodil/lein-noderepl "0.1.1"]]
  :cljsbuild {:builds
              [{:source-path "src"
                :compiler
                {:output-to "js/main.js"
                 :output-dir "js"
                 :optimizations :simple
                 :jar true}}]})
