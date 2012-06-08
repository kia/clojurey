(ns datapull.test.core
  (:use [datapull.core])
  (:use [clojure.test]))

(deftest get-header 
  (is (= "<td>:id</td>\n<td>:name</td>\n" (get-keys-html {:id 1 :name "Peter"}))))

(deftest get-row
  (is (= "<td name=\"id\">1</td>\n<td name=\"name\">Peter</td>\n" (get-row-html {:id 1 :name "Peter"}))))

(deftest get-table-content
  (is (= "<tr>\n<td name=\"id\">1</td>\n<td name=\"name\">Peter</td>\n</tr>\n<tr>\n<td name=\"id\">2</td>\n<td name=\"name\">Hans</td>\n</tr>\n"
         (get-table-content-html '({:id 1 :name "Peter"} {:id 2 :name "Hans"})))))
