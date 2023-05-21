(ns service.theoretical_exams
  (:refer-clojure :exclude [seqable? get update])
  (:require [clojure.java.jdbc :as jdbc]
            [clojure.edn :as edn]))

(def db (edn/read-string (slurp "configuration/db.edn")))

(defn get-all-theory-exams []
  (jdbc/query db ["SELECT * FROM theory_exam"]))

(defn get-theory-exam-by-id [id date]
  (jdbc/query db ["SELECT * FROM theory_exam WHERE id = ? AND date = ?"
                  id date]))

(defn create-theory-exam [id date policeman]
  (jdbc/execute! db
                 ["INSERT INTO theory_exam (id, date, policeman)
                   VALUES (?, ?, ?)"
                  id date policeman]))

(defn update-theory-exam [id date policeman]
  (jdbc/execute! db
                 ["UPDATE theory_exam
                   SET policeman = ?
                   WHERE id = ? AND date = ?"
                  policeman id date]))

(defn delete-theory-exam [id date]
  (jdbc/execute! db ["DELETE FROM theory_exam WHERE id = ? AND date = ?"
                     id date]))