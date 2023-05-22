(ns controller.driving_exam_controller
  (:require [ring.util.response :as response]
            [compojure.core :refer [defroutes GET POST PUT DELETE]]
            [service.driving_exams :as driving-exams-service]))

(defn to-json-string [data]
  (clojure.core/pr-str data))

(defroutes driving-exam-routes
           (GET "/driving-exams" []
             (let [driving-exams (driving-exams-service/get-all-driving-exams)
                   json-string (to-json-string driving-exams)]
               (response/response json-string)))

           (GET "/driving-exams/:id/:date" [id date]
             (let [driving-exam (driving-exams-service/get-driving-exam id date)
                   json-string (to-json-string driving-exam)]
               (response/response json-string)))

           (POST "/driving-exams" request
             (let [json-parsed (:body request)]
               (let [{:keys [id date policeman]} json-parsed]
                 (driving-exams-service/create-driving-exam id date policeman)
                 (response/response "Driving exam created successfully"))))

           (PUT "/driving-exams/:id/:date" request
             (let [json-parsed (:body request)
                   driving-exam-id (-> request :params :id)
                   driving-exam-date (-> request :params :date)]
               (let [{:keys [policeman]} json-parsed]
                 (driving-exams-service/update-driving-exam driving-exam-id driving-exam-date policeman)
                 (response/response "Driving exam updated successfully"))))

           (DELETE "/driving-exams/:id/:date" [id date]
             (driving-exams-service/delete-driving-exam id date)
             (response/response "Driving exam deleted successfully"))))

