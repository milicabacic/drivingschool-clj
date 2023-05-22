(ns controller.candidates_theoretical_exams_controller
  (:require [ring.util.response :as response]
            [compojure.core :refer [defroutes GET POST PUT DELETE]]
            [service.candidates_theoretical_exams :as candidates-theory-exams-service]))

(defn to-json-string [data]
  (clojure.core/pr-str data))

(defroutes candidates-theoretical-exams-routes
           (GET "/candidates-theoretical-exams" []
             (let [exams (candidates-theory-exams-service/get-all-candidate-theory-exams)
                   json-string (to-json-string exams)]
               (response/response json-string)))

           (GET "/candidates-theoretical-exams/:candidate-id/:theory-exam-id/:theory-exam-date" [candidate-id theory-exam-id theory-exam-date]
             (let [exam (candidates-theory-exams-service/get-candidate-theory-exam-by-id candidate-id theory-exam-id theory-exam-date)
                   json-string (to-json-string exam)]
               (response/response json-string)))

           (POST "/candidates-theoretical-exams" request
             (let [json-parsed (:body request)]
               (let [{:keys [candidate-id theory-exam-id theory-exam-date points]} json-parsed]
                 (candidates-theory-exams-service/create-candidate-theory-exam candidate-id theory-exam-id theory-exam-date points)
                 (response/response "Candidate theoretical exam created successfully"))))

           (PUT "/candidates-theoretical-exams/:candidate-id/:theory-exam-id/:theory-exam-date" request
             (let [json-parsed (:body request)
                   candidate-id (-> request :params :candidate-id)
                   theory-exam-id (-> request :params :theory-exam-id)
                   theory-exam-date (-> request :params :theory-exam-date)]
               (let [{:keys [points]} json-parsed]
                 (candidates-theory-exams-service/update-candidate-theory-exam candidate-id theory-exam-id theory-exam-date points)
                 (response/response "Candidate theoretical exam updated successfully"))))

           (DELETE "/candidates-theoretical-exams/:candidate-id/:theory-exam-id/:theory-exam-date" [candidate-id theory-exam-id theory-exam-date]
             (candidates-theory-exams-service/delete-candidate-theory-exam candidate-id theory-exam-id theory-exam-date)
             (response/response "Candidate theoretical exam deleted successfully")))

