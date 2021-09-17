(ns giggin.fb.init
  (:require ["firebase/app" :refer (initializeApp)]
            [giggin.fb.auth :refer [on-auth-state-changed]]))

(defn firebase-init
  []
  (initializeApp
   #js {:apiKey "AIzaSyAcNTDSY2VX_o4bLuBX5ieDxwe8DP-snvo"
        :authDomain "giggin-2aa35.firebaseapp.com"
        :projectId "giggin-2aa35"
        :storageBucket "giggin-2aa35.appspot.com"
        :messagingSenderId "173907515"
        :appId "1:173907515:web:0f4dc851cb580709ef861e"})
  (on-auth-state-changed))
