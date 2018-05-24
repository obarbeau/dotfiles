{
 :sources-level-down {:source-paths ^:replace ["src/clj" "src/cljs"]}
 :auth {:repository-auth {#"." {:username "deployment"  :password "XXX"}}}
 
 :user {:clean-targets ^{:protect false} [:target-path]
        :javac-options ["-target" "1.8" "source" "1.8" "-Xlint:-options"]
        :jvm-opts ["-XX:+AggressiveOpts" "-XX:+UseCompressedOops"
                   "-XX:+OptimizeStringConcat"
                   "-XX:+UseFastAccessorMethods" "-server" "-Duser.timezone=Europe/Paris"]
                   :repositories [["releases" {:url "https://nexus.acs-ami.com/content/repositories/clj-releases"}]]
        :pom-location "target/"}
  :cljs
   [:sources-level-down
    {:cljsbuild {:builds {:dev
                {:source-paths ^:replace ["src/cljs"]
                      :jar true :compiler {:parallel-build true :asset-path "js/dev"
                                           :output-dir "resources/public/js/dev"
                                           :optimizations :none :pretty-print true
                                           :source-map  true :verbose :true}}
                      :prod {:source-paths ^:replace ["src/cljs"]
                            :jar false
                            :figwheel false
                             :compiler {:parallel-build true
                                         :asset-path "js/prod"
                                         :output-dir "resources/public/js/prod"
                                         :output-to "resources/public/js/prod/main.js"
                                         :optimizations :simple :pretty-print false
                                         :source-map "resources/public/js/prod/source.map"
                                         :verbose :true}}
                          :rec
                           {:source-paths ^:replace ["src/cljs"]
                            :jar false :figwheel false :compiler {:parallel-build true
                                                                  :asset-path "js/rec"
                                                                  :output-dir "resources/public/js/rec"
                                                                  :output-to "resources/public/js/rec/main.js"
                                                                  :optimizations :simple :pretty-print false
                                                                  :source-map "resources/public/js/rec/source.map"
                                                                  :verbose :true}}}}
     :plugins [[lein-cljsbuild        "1.1.4"]]}]
 :uberjar-common {:aot :all
  :repositories [["releases" {:url "https://nexus.acs-ami.com/content/repositories/clj-releases"}]]}
 :uberjar-additional {}
 :uberjar [:uberjar-common :uberjar-additional]}