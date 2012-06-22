(ns datapull.core
  (:require [clojure.java.jdbc :as sql])
  (:gen-class
   :name datapull.core
   :methods [#^{:static true} [printtable [java.lang.String] java.lang.String]]))

(let [db-host "192.168.60.147"
      db-port 3306
      db-name "api"]
  (def db {:classname "com.mysql.jdbc.Driver"
           :subprotocol "mysql"
           :subname (str "//" db-host ":" db-port "/" db-name)
           :user "root"
           :password "p0stb@nk"}))

(defn get-keys-html [record]
  (reduce str (map #(str "<td>" (name %) "</td>\n") (keys (first record)))))

(defn get-row-html [record]
  (reduce str (map #(str "<td name=\"" (name (key %)) "\">" (val %) "</td>\n") record)))
 


(defn get-table-content-html [records]
  (str "<tr>\n" (get-keys-html records) "</tr>\n"
       (reduce str (map #(str "<tr>\n" (get-row-html %) "</tr>\n") records))))


(defn printtable [query]
  (sql/with-connection db
    (sql/with-query-results rows
      [query]
      (get-table-content-html rows))))

(defn -printtable [q] (printtable q))

(defmacro select [& rest-of-query]
  (let [q (reduce #(str %1 " " %2) rest-of-query)]
    (printtable (str "select " q))))

