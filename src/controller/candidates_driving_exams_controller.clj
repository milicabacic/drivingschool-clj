(ns controller.candidates_driving_exams_controller
  (:require [ring.util.response :as response]
            [compojure.core :refer [defroutes GET POST PUT DELETE]]
            [service.candidates_driving_exams :as candidates-driving-exams-service]))

(defn to-json-string [data]
  (clojure.core/pr-str data))

(defroutes candidates-driving-exams-routes
           (GET "/candidate-driving-exams" []
             (let [exams (candidates-driving-exams-service/get-all-candidate-driving-exams)
                   json-string (to-json-string exams)]
               (response/response json-string)))

           (GET "/candidate-driving-exams/:candidate-id/:driving-exam-id/:driving-exam-date" [candidate-id driving-exam-id driving-exam-date]
             (let [exam (candidates-driving-exams-service/get-candidate-driving-exam candidate-id driving-exam-id driving-exam-date)
                   json-string (to-json-string exam)]
               (response/response json-string)))

           (POST "/candidate-driving-exams" request
             (let [json-parsed (:body request)]
               (let [{:keys [candidate-id driving-exam-id driving-exam-date points]} json-parsed]
                 (candidates-driving-exams-service/create-candidate-driving-exam candidate-id driving-exam-id driving-exam-date points)
                 (response/response "Candidate driving exam created successfully"))))

           (PUT "/candidate-driving-exams/:candidate-id/:driving-exam-id/:driving-exam-date" request
             (let [json-parsed (:body request)
                   candidate-id (-> request :params :candidate-id)
                   driving-exam-id (-> request :params :driving-exam-id)
                   driving-exam-date (-> request :params :driving-exam-date)]
               (let [{:keys [points]} json-parsed]
                 (candidates-driving-exams-service/update-candidate-driving-exam candidate-id driving-exam-id driving-exam-date points)
                 (response/response "Candidate driving exam updated successfully"))))

           (DELETE "/candidate-driving-exams/:candidate-id/:driving-exam-id/:driving-exam-date" [candidate-id driving-exam-id driving-exam-date]
             (candidates-driving-exams-service/delete-candidate-driving-exam candidate-id driving-exam-id driving-exam-date)
             (response/response "Candidate driving exam deleted successfully")))

