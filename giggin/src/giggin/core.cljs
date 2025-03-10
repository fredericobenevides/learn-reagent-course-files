(ns giggin.core
  (:require [reagent.core :as r]
            [giggin.components.header :refer [header]]
            [giggin.components.gigs :refer [gigs]]
            [giggin.components.orders :refer [orders]]
            [giggin.components.footer :refer [footer]]
            [giggin.api :as api]
            [giggin.fb.init :refer [firebase-init]]
            [giggin.fb.db :refer [db-subscribe]]))

(defn app
  []
  [:div.container
   [header]
   [gigs]
   [orders]
   [footer]])

(defn ^:dev/after-load start
  []
  (r/render
   [app]
   (.getElementById js/document "app")))

(defn ^:export main
  []
  (start)
  (firebase-init)
  (db-subscribe ["gigs"]))
