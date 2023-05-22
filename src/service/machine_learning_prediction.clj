(ns service.machine_learning_prediction
  (:require [clojure.string :as str]
            [clojure.java.io :as io]))

(def dataset
  {:features [[18.0 70.0 1.0]
              [22.0 85.0 3.0]
              [20.0 75.0 2.0]
              [25.0 90.0 4.0]
              [19.0 68.0 1.0]]
   :labels   [60.0 80.0 70.0 85.0 65.0]})

(defn euclidean-distance [point1 point2]
  (Math/sqrt (reduce + (map #(* % %) (map - point1 point2)))))

(defn knn-predict [dataset k new-candidate]
  (let [features (:features dataset)
        labels (:labels dataset)
        distances (mapv #(euclidean-distance % new-candidate) features)
        sorted-neighbors (sort-by first (map vector distances labels))
        k-neighbors (take k (map second sorted-neighbors))
        predicted-label (/ (apply + k-neighbors) k)]
    predicted-label))


(def new-candidate [25 80 2])

(def k 5)

(def prediction (knn-predict dataset k new-candidate))

(println prediction)

