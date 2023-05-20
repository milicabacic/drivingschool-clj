(ns drivingschool-clj.core
  (:require [clojure.java.jdbc :as jdbc]
            [clojure.edn :as edn]))

(def db-spec {:dbtype "mysql"
              :dbname "clojure"
              :host "localhost"
              :port 3306
              :user "root"
              :password "Marija1806976!"})

(defn create-db-connection []
  (jdbc/db-do-commands
    {:connection-uri (format "jdbc:%s://%s/%s?user=%s&password=%s"
                             (db-spec :dbtype) (db-spec :host)
                             (db-spec :dbname) (db-spec :user)
                             (db-spec :password))}
    (read-string (slurp (format "src/scripts/%s"
                                ((edn/read-string (slurp "configuration/init-db.edn")) :init-file-name))))))

(defn init []
  (jdbc/db-do-commands
    {:connection-uri (format "jdbc:%s://%s?user=%s&password=%s"
                             (db-spec :dbtype) (db-spec :host)
                             (db-spec :user) (db-spec :password))}
    false
    (format "CREATE DATABASE IF NOT EXISTS %s" (db-spec :dbname)))
  (create-db-connection))

(init)

(defn get-all-candidates []
  (jdbc/with-db-connection [conn db-spec]
                           (jdbc/query conn ["SELECT * FROM candidates"])))

(get-all-candidates)

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