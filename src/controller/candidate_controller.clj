(ns controller.candidate_controller
  (:require [ring.util.response :as response]
            [compojure.core :refer [defroutes GET POST PUT DELETE]]
            [service.candidates :as candidates-service]))

(defn to-json-string [data]
  (clojure.core/pr-str data))

(defroutes candidate-routes
           (GET "/candidates" []
             (let [candidates (candidates-service/get-all-candidates)
                   json-string (to-json-string candidates)]
               (response/response json-string)))

           (GET "/candidates/:id" [id]
             (let [candidate (candidates-service/get-candidate-by-id id)
                   json-string (to-json-string candidate)]
               (response/response json-string)))

           (POST "/candidates" request
             (let [json-parsed (:body request)]
               (let [{:keys [name age first-aid theoretical-classes theoretical-exam driving-classes driving-exam]} json-parsed]
                 (candidates-service/create-candidate name age first-aid theoretical-classes theoretical-exam driving-classes driving-exam)
                 (response/response "Candidate created successfully"))))

           (PUT "/candidates/:id" request
             (let [json-parsed (:body request)
                   candidate-id (-> request :params :id)]
               (let [{:keys [name age first-aid theoretical-classes theoretical-exam driving-classes driving-exam]} json-parsed]
                 (candidates-service/update-candidate candidate-id name age first-aid theoretical-classes theoretical-exam driving-classes driving-exam)
                 (response/response "Candidate updated successfully"))))

           (DELETE "/candidates/:id" [id]
             (candidates-service/delete-candidate id)
             (response/response "Candidate deleted successfully")))

