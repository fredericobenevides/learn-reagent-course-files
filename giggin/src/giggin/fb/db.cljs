(ns giggin.fb.db
  (:require ["firebase/database" :refer (getDatabase ref set)]
            [clojure.string :as str]))

(defn db-ref
  [path]
  (let [db (getDatabase)]
    (ref db (str/join "/" path))))

(defn db-save!
  [path value]
  (set (db-ref path) value))

(comment
  ;; test
  (set (ref (getDatabase) "users/ada") (clj->js {:last "lovelace"}))

  ;; or with thread first
  (-> (getDatabase)
      (ref "users/ada")
      (set (clj->js {:last "lovelace"}))))
