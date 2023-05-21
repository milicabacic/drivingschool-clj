(ns service.candidates_driving_exams
  (:refer-clojure :exclude [seqable? get update])
  (:require [clojure.java.jdbc :as jdbc]
            [clojure.edn :as edn]))

(def db (edn/read-string (slurp "configuration/db.edn")))

(defn get-all-candidate-driving-exams []
  (jdbc/query db ["SELECT * FROM candidate_driving_exam"]))

(defn get-candidate-driving-exam [candidate-id driving-exam-id driving-exam-date]
  (jdbc/query db ["SELECT * FROM candidate_driving_exam WHERE candidate_id = ? AND driving_exam_id = ? AND driving_exam_date = ?"
                  candidate-id driving-exam-id driving-exam-date]))

(defn create-candidate-driving-exam [candidate-id driving-exam-id driving-exam-date points]
  (jdbc/execute! db
                 ["INSERT INTO candidate_driving_exam (candidate_id, driving_exam_id, driving_exam_date, points)
                   VALUES (?, ?, ?, ?)"
                  candidate-id driving-exam-id driving-exam-date points]))

(defn update-candidate-driving-exam [candidate-id driving-exam-id driving-exam-date points]
  (jdbc/execute! db
                 ["UPDATE candidate_driving_exam
                   SET points = ?
                   WHERE candidate_id = ? AND driving_exam_id = ? AND driving_exam_date = ?"
                  points candidate-id driving-exam-id driving-exam-date]))

(defn delete-candidate-driving-exam [candidate-id driving-exam-id driving-exam-date]
  (jdbc/execute! db ["DELETE FROM candidate_driving_exam WHERE candidate_id = ? AND driving_exam_id = ? AND driving_exam_date = ?"
                     candidate-id driving-exam-id driving-exam-date]))