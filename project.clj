(defproject drivingschool-clj "0.1.0-SNAPSHOT"
  :description "Application for tracking activities in driving school"
  :url "https://github.com/milicabacic/drivingschool-clj"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.10.1"]
                 [org.clojure/java.jdbc "0.7.12"]
                 [mysql/mysql-connector-java "8.0.26"]
                 [ring "1.9.5"]
                 [ring-server "0.5.0"]
                 [compojure "1.6.3"]
                 [ring/ring-defaults "0.3.3"]
                 [ring/ring-jetty-adapter "1.10.0"]
                 [cheshire "5.11.0"]
                 [ring/ring-json "0.5.1"]
                 ]
  :plugins [[lein-ring "0.12.6"]]
  :ring {:handler drivingschool_clj.core/-main
         :init drivingschool_clj.core/init
         :port 8080}
  :repl-options {:init-ns drivingschool-clj.core})
