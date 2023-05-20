(ns service.candidates
  (:refer-clojure :exclude [seqable? get update])
  (:require [clojure.java.jdbc :as jdbc]
            [clojure.edn :as edn]))

(def db (edn/read-string (slurp "configuration/db.edn")))

(defn get-all-candidates []
  (jdbc/query db ["SELECT * FROM candidates"]))

(defn get-candidate-by-id [id]
  (jdbc/query db ["SELECT * FROM candidates WHERE id = ?" id]))

(defn create-candidate [name age first-aid theoretical-classes theoretical-exam driving-classes driving-exam]
  (jdbc/execute! db
                 ["INSERT INTO candidates (name, age, first_aid_certificate, theoretical_classes_listened, theoretical_exam_passed, driving_classes_listened, driving_exam_passed)
      VALUES (?, ?, ?, ?, ?, ?, ?)"
                  name age first-aid theoretical-classes theoretical-exam driving-classes driving-exam]))

(defn update-candidate [id name age first-aid theoretical-classes theoretical-exam driving-classes driving-exam]
  (jdbc/execute! db
                 ["UPDATE candidates
      SET name = ?, age = ?, first_aid_certificate = ?, theoretical_classes_listened = ?,
          theoretical_exam_passed = ?, driving_classes_listened = ?, driving_exam_passed = ?
      WHERE id = ?"
                  name age first-aid theoretical-classes theoretical-exam driving-classes driving-exam id]))

(defn delete-candidate [id]
  (jdbc/execute! db ["DELETE FROM candidates WHERE id = ?" id]))