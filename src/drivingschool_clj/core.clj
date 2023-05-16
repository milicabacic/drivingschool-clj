(ns drivingschool-clj.core)

(require '[clojure.java.jdbc :as jdbc])

(def db-spec {:dbtype "mysql"
              :dbname "clojure"
              :host "localhost"
              :port 3306
              :user "root"
              :password "Marija1806976!"})

(defn get-connection []
  (jdbc/get-connection db-spec))

(get-connection)


(def candidates [{:id 1
                  :name                          "Milica Bacic"
                  :age                           23
                  :first-aid-certificate         true
                  :theorethical-classes-listened 30
                  :theorethical-exam-passed      false
                  :practical-classes-listened    0
                  :practical-exam-passed         false}
                 {:id 2
                  :name                          "Pera Peric"
                  :age                           27
                  :first-aid-certificate         false
                  :theorethical-classes-listened 40
                  :theorethical-exam-passed      false
                  :practical-classes-listened    0
                  :practical-exam-passed         false}
                 {:id 3
                  :name                          "Laza Lazic"
                  :age                           25
                  :first-aid-certificate         true
                  :theorethical-classes-listened 40
                  :theorethical-exam-passed      true
                  :practical-classes-listened    16
                  :practical-exam-passed         false}
                 {:id 4
                  :name                          "Mika Mikic"
                  :age                           26
                  :first-aid-certificate         true
                  :theorethical-classes-listened 40
                  :theorethical-exam-passed      true
                  :practical-classes-listened    40
                  :practical-exam-passed         false}
                 {:id                             5
                  :name                          "Sofija Sofic"
                  :age                           20
                  :first-aid-certificate         true
                  :theorethical-classes-listened 40
                  :theorethical-exam-passed      true
                  :practical-classes-listened    40
                  :practical-exam-passed         true}]
  )

(defn filter-candidates-finished-theory [candidates]
  (filter #(= (:theorethical-classes-listened %1) 40) candidates))

(filter-candidates-finished-theory candidates)

(defn filter-candidates-not-passed-theoretical-exam [candidates]
  (filter #(= (:theorethical-exam-passed %1) false) candidates))

(filter-candidates-not-passed-theoretical-exam candidates)

(defn make-list-for-theoretical-exam [candidates]
  (filter-candidates-not-passed-theoretical-exam (filter-candidates-finished-theory candidates)))

(make-list-for-theoretical-exam candidates)

(defn filter-candidates-finished-practice [candidates]
  (filter #(= (:practical-classes-listened %1) 40) candidates))

(filter-candidates-finished-practice candidates)

(defn filter-candidates-not-passed-practical-exam [candidates]
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