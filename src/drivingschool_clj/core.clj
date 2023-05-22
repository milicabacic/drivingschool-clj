(ns drivingschool-clj.core
  (:require [service.candidates :as candidates_service]
            [clojure.java.jdbc :as jdbc]
            [clojure.edn :as edn]
            [controller.candidate_controller :as candidate-controller]
            [controller.candidates_driving_exams_controller :as candidates_driving_exams_controller]
            [controller.candidates_theoretical_exams_controller :as candidates_theoretical_exams_controller]
            [controller.driving_exam_controller :as driving_exam_controller]
            [controller.theoretical_exam_controller :as theory_exam_controller]
            [ring.adapter.jetty :as jetty]
            [ring.middleware.defaults :refer [wrap-defaults api-defaults]]
            [ring.middleware.json :as middleware]
            [compojure.route :as route]))

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
                   {:port 8085}))

(-main)



