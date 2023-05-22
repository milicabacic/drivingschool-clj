(ns service.machine_learning_prediction
  (:require [clojure.edn :as edn]
            [clojure.java.jdbc :as jdbc]))

(def db (edn/read-string (slurp "configuration/db.edn")))

(defn get-driving-data []
  (jdbc/with-db-connection [conn db]
                           (jdbc/query conn
                                       ["SELECT c.age, max(t.points) AS max_theory_points, COUNT(d.candidate_id) AS driving_count, max(d.points) AS max_driving_points
        FROM candidates c
        LEFT JOIN candidate_theory_exam t ON (t.candidate_id = c.id)
        LEFT JOIN candidate_driving_exam d ON (d.candidate_id = c.id)
        GROUP BY c.age"])))

(defn convert-data [result]
  (let [features (map (fn [row] [(row :age) (or (row :max_theory_points) 0) (row :driving_count)]) result)
        labels (map :max_driving_points result)]
    {:features features :labels labels}))

(defn euclidean-distance [point1 point2]
  (Math/sqrt (reduce + (map #(* % %) (map - point1 point2)))))

(defn knn-predict [dataset k new-candidate]
  (let [features (:features dataset)
        labels (:labels dataset)
        distances (mapv #(euclidean-distance % new-candidate) features)
        sorted-neighbors (sort-by first (map vector distances labels))
        k-neighbors (take k (map second sorted-neighbors))
        predicted-label (->> k-neighbors
                             (frequencies)
                             (sort-by val)
                             (reverse)
                             (first)
                             (key))]
    predicted-label))


(defn predict-with-values [age theory-points driving-exams]
  (let [new-candidate [age theory-points driving-exams]
        k 3
        dataset (convert-data (get-driving-data))
        prediction (knn-predict dataset k new-candidate)]
    prediction))



