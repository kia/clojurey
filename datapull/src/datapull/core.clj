(ns datapull.core
  (:require [clojure.java.jdbc :as sql]))

(let [db-host "localhost"
      db-port 3306
      db-name "slam"]
  (def db {:classname "com.mysql.jdbc.Driver"
           :subprotocol "mysql"
           :subname (str "//" db-host ":" db-port "/" db-name)
           :user "root"
           :password "root"}))

(defn get-keys-html [record]
  (reduce str (map #(str "<td>" % "</td>\n") (keys record))))

(defn get-row-html [record]
  (reduce str (map #(str "<td name=\"" (name (key %)) "\">" (val %) "</td>\n") record)))
 


(defn get-table-content-html [records]
  (reduce str (map #(str "<tr>\n" (get-row-html %) "</tr>\n") records)))


(defn print-table [query]
  (sql/with-connection db
    (sql/with-query-results rows
      [query]
      (get-table-content-html rows))))
