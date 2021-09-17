(ns giggin.fb.db
  (:require ["firebase/database" :refer (getDatabase ref set onValue)]
            [clojure.string :as str]
            [giggin.state :as state]))

(defn db-ref
  [path]
  (let [db (getDatabase)]
    (ref db (str/join "/" path))))

(defn db-save!
  [path value]
  (set (db-ref path) value))

(defn db-subscribe
  [path]
  (onValue (db-ref path)
           (fn [snapshot]
             (reset! state/gigs (js->clj (.val snapshot) :keywordize-keys true)))))

(comment
  ;; test
  (set (ref (getDatabase) "users/ada") (clj->js {:last "lovelace"}))

  ;; or with thread first
  (-> (getDatabase)
      (ref "users/ada")
      (set (clj->js {:last "lovelace"}))))
