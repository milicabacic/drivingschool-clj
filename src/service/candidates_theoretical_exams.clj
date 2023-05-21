(ns service.candidates_theoretical_exams
  (:refer-clojure :exclude [seqable? get update])
  (:require [clojure.java.jdbc :as jdbc]
            [clojure.edn :as edn]))

(def db (edn/read-string (slurp "configuration/db.edn")))

(defn get-all-candidate-theory-exams []
  (jdbc/query db ["SELECT * FROM candidate_theory_exam"]))

(defn get-candidate-theory-exam-by-id [candidate-id theory-exam-id theory-exam-date]
  (jdbc/query db ["SELECT * FROM candidate_theory_exam WHERE candidate_id = ? AND theory_exam_id = ? AND theory_exam_date = ?"
                  candidate-id theory-exam-id theory-exam-date]))

(defn create-candidate-theory-exam [candidate-id theory-exam-id theory-exam-date points]
  (jdbc/execute! db
                 ["INSERT INTO candidate_theory_exam (candidate_id, theory_exam_id, theory_exam_date, points)
                   VALUES (?, ?, ?, ?)"
                  candidate-id theory-exam-id theory-exam-date points]))

(defn update-candidate-theory-exam [candidate-id theory-exam-id theory-exam-date points]
  (jdbc/execute! db
                 ["UPDATE candidate_theory_exam
                   SET points = ?
                   WHERE candidate_id = ? AND theory_exam_id = ? AND theory_exam_date = ?"
                  points candidate-id theory-exam-id theory-exam-date]))

(defn delete-candidate-theory-exam [candidate-id theory-exam-id theory-exam-date]
  (jdbc/execute! db ["DELETE FROM candidate_theory_exam WHERE candidate_id = ? AND theory_exam_id = ? AND theory_exam_date = ?"
                     candidate-id theory-exam-id theory-exam-date]))