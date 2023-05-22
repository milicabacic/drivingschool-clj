(ns drivingschool-clj.core
  (:require [service.candidates :as candidates_service]
            [clojure.java.jdbc :as jdbc]
            [clojure.edn :as edn]
            [controller.candidates_theoretical_exams_controller :as controller]
            [ring.adapter.jetty :as jetty]
            [ring.middleware.defaults :refer [wrap-defaults api-defaults]]
            [ring.middleware.json :as middleware]
            [cheshire.core :as cheshire]))

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
  (jetty/run-jetty (-> controller/candidates-theoretical-exams-routes
                       (middleware/wrap-json-body {:keywords? true :bigdecimals? true})
                       (middleware/wrap-json-response)
                       (wrap-defaults api-defaults))
                   {:port 8085}))

(-main)

(let [all-candidates (candidates_service/get-all-candidates)]
  (doseq [candidate all-candidates]
    (println candidate)))

(candidates_service/get-all-candidates)

(defn finished-theory? [candidates]
  (filter #(= (:theoretical_classes_listened %1) 40) candidates))


(finished-theory? (get-all-candidates))

(defn passed_theoretical_exam [candidates]
  (filter #(= (:theoretical_exam_passed %1) true) candidates))

(passed_theoretical_exam? (get-all-candidates))

(defn make-list-for-theoretical-exam [candidates]
  (filter-candidates-not-passed-theoretical-exam (filter-candidates-finished-theory candidates)))

(make-list-for-theoretical-exam candidates)

(defn finished_practice? [candidates]
  (filter #(= (:driving_classes_listened %1) 40) candidates))

(finished_practice? (get-all-candidates))

(defn not_passed_driving_exam [candidates]
  (filter #(= (:practical-exam-passed %1) false) candidates))

(filter-candidates-not-passed-practical-exam candidates)

(defn make-list-for-practical-exam [candidates]
  (filter-candidates-not-passed-practical-exam (filter-candidates-finished-practice candidates)))

(make-list-for-practical-exam candidates)

(def theoretical-exam-results [{:candidate-id 2 :points 70}])

(:theorethical-exam-passed (first candidates))

(defn evaluate-theoretical-exam-results [candidates results]
  ())

(def candidates-theory-exam (make-list-for-theoretical-exam candidates))

(print candidates-theory-exam)

(make-list-for-theoretical-exam candidates)

(remove :practical-exam-passed candidates)

(fn qualified? [student] (

                           ))

(filter qualified? candidates)


