(defproject drivingschool-clj "0.1.0-SNAPSHOT"
  :description "Application for tracking activities in driving school"
  :url "https://github.com/milicabacic/drivingschool-clj"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.10.1"]
                 [org.clojure/java.jdbc "0.7.12"]
                 [mysql/mysql-connector-java "8.0.26"]]
  :repl-options {:init-ns drivingschool-clj.core})
