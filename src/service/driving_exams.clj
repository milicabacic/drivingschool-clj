(ns service.driving_exams
  (:refer-clojure :exclude [seqable? get update])
  (:require [clojure.java.jdbc :as jdbc]
            [clojure.edn :as edn]))

(def db (edn/read-string (slurp "configuration/db.edn")))

(defn get-all-driving-exams []
  (jdbc/query db ["SELECT * FROM driving_exam"]))

(defn get-driving-exam [id date]
  (jdbc/query db ["SELECT * FROM driving_exam WHERE id = ? AND date = ?"
                  id date]))

(defn create-driving-exam [id date policeman]
  (jdbc/execute! db
                 ["INSERT INTO driving_exam (id, date, policeman)
                   VALUES (?, ?, ?)"
                  id date policeman]))

(defn update-driving-exam [id date policeman]
  (jdbc/execute! db
                 ["UPDATE driving_exam
                   SET policeman = ?
                   WHERE id = ? AND date = ?"
                  policeman id date]))

(defn delete-driving-exam [id date]
  (jdbc/execute! db ["DELETE FROM driving_exam WHERE id = ? AND date = ?"
                     id date]))