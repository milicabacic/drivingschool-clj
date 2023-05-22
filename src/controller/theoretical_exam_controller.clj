(ns controller.theoretical_exam_controller
  (:require [ring.util.response :as response]
            [compojure.core :refer [defroutes GET POST PUT DELETE]]
            [service.theoretical_exams :as theoretical-exams-service]))

(defn to-json-string [data]
  (clojure.core/pr-str data))

(defroutes theoretical-exam-routes
           (GET "/theory-exams" []
             (let [exams (theoretical-exams-service/get-all-theory-exams)
                   json-string (to-json-string exams)]
               (response/response json-string)))

           (GET "/theory-exams/:id/:date" [id date]
             (let [exam (theoretical-exams-service/get-theory-exam-by-id id date)
                   json-string (to-json-string exam)]
               (response/response json-string)))

           (POST "/theory-exams" request
             (let [json-parsed (:body request)]
               (let [{:keys [id date policeman]} json-parsed]
                 (theoretical-exams-service/create-theory-exam id date policeman)
                 (response/response "Theory exam created successfully"))))

           (PUT "/theory-exams/:id/:date" request
             (let [json-parsed (:body request)
                   exam-id (-> request :params :id)
                   exam-date (-> request :params :date)]
               (let [{:keys [policeman]} json-parsed]
                 (theoretical-exams-service/update-theory-exam exam-id exam-date policeman)
                 (response/response "Theory exam updated successfully"))))

           (DELETE "/theory-exams/:id/:date" [id date]
             (theoretical-exams-service/delete-theory-exam id date)
             (response/response "Theory exam deleted successfully"))))

