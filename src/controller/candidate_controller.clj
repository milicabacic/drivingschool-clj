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

           (DELETE "/candidates/:id" [id]
             (candidates-service/delete-candidate id)
             (response/response "Candidate deleted successfully")))

