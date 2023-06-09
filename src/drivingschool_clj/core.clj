(ns drivingschool-clj.core
  (:require [clojure.java.jdbc :as jdbc]
            [clojure.edn :as edn]
            [controller.candidate_controller :as candidate-controller]
            [ring.adapter.jetty :as jetty]
            [ring.middleware.defaults :refer [wrap-defaults api-defaults]]
            [ring.middleware.json :as middleware]))

(def db (edn/read-string (slurp "configuration/db.edn")))

(defn create-db-connection []
  (jdbc/db-do-commands
    {:connection-uri (format "jdbc:%s://%s/%s?user=%s&password=%s"
                             (db :dbtype) (db :host)
                             (db :dbname) (db :user)
                             (db :password))}
    (read-string (slurp (format "src/scripts/%s"
                                ((edn/read-string (slurp "configuration/init-db.edn")) :init-file-name))))))

(defn init []
  (jdbc/db-do-commands
    {:connection-uri (format "jdbc:%s://%s?user=%s&password=%s"
                             (db :dbtype) (db :host)
                             (db :user) (db :password))}
    false
    (format "CREATE DATABASE IF NOT EXISTS %s" (db :dbname)))
  (create-db-connection))

(init)

(defn -main []
  (init)
  (jetty/run-jetty (-> controller.candidate_controller/candidate-routes
                       (middleware/wrap-json-body {:keywords? true :bigdecimals? true})
                       (middleware/wrap-json-response)
                       (wrap-defaults api-defaults))
                   {:port 8092}))

(-main)





