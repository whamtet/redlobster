(defproject redlobster "0.2.2-SNAPSHOT"
  :description "Promises for ClojureScript"
  :url "https://github.com/bodil/redlobster"
  :license {:name "Apache License, version 2.0"
            :url "http://www.apache.org/licenses/LICENSE-2.0.html"}
  :plugins [[lein-cljsbuild "0.3.0"]
            [lein-npm "0.6.1"]]
  :npm {:dependencies [[mongodb "2.0.42"]]}
  :profiles
  {:dev
   {:dependencies [[org.bodil/cljs-noderepl "0.1.6"]
                   [com.cemerick/piggieback "0.0.2"]
                   [org.clojure/clojurescript "1.7.170"]
                   [org.clojure/clojure "1.7.0"]
                   ]
    :plugins [[org.bodil/lein-noderepl "0.1.6"]]
;    :repl-options {:nrepl-middleware [cemerick.piggieback/wrap-cljs-repl]}
    }}

  :cljsbuild {:test-commands
              {"phantom" ["phantomjs" "test/phantom/test.js"]
               "node" ["node" "js/test.js"]
               "nashorn" ["jjs" "js/test.js"]}
              :builds {:dev {:source-paths ["src"]
                             :compiler
                             {:output-to "js/main.js"
                              :optimizations :simple
                              :pretty-print true
                              :jar true}}
                       :test {:source-paths ["test"]
                              :compiler
                              {:output-to "js/test.js"
                               :optimizations :simple
                               :pretty-print true}}}}
  :aliases {"build" ["trampoline" "run" "-m" "redlobster.build" "redlobster.io"]}
  )
