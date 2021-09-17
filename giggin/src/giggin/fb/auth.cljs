(ns giggin.fb.auth
  (:require ["firebase/auth" :refer (getAuth signInWithPopup signOut onAuthStateChanged GoogleAuthProvider)]
            [giggin.fb.db :refer [db-save!]]
            [giggin.state :as state]))

(defn sign-in-with-google
  []
  (let [auth (getAuth)
        provider (new GoogleAuthProvider)]
    (signInWithPopup auth provider)))

(defn sign-out
  []
  (signOut (getAuth)))

(defn on-auth-state-changed
  []
  (onAuthStateChanged
   (getAuth)
   (fn
     [user]
     (if user
       (let [uid (.-uid user)
             display-name (.-displayName user)
             photo-url (.-photoURL user)
             email (.-email user)]
         (do
           (reset! state/user {:photo-url photo-url
                               :display-name display-name
                               :email email})
           (db-save!
            ["users" uid "profile"]
            #js {:photo-url photo-url
                 :display-name display-name
                 :email email})))
       (reset! state/user nil)))))
