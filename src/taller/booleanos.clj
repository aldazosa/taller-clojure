(ns taller.booleanos
  (:require [clojure.walk :refer [macroexpand-all]]))



;; Booleanos
true
false

;;;;;;;;;;;;;;;;;;
;; Truthfulness ;;
;;;;;;;;;;;;;;;;;;

(true? 1)
;; => false

(true? true)
;; => true

(true? (= 1 1))
;; => true

(false? 0)
;; => false

(false? 1)
;; => false

(false? false)
;; => true

(false? (< 9 4))
;; => true

;;;;;;;;;;;;;;;;
;; Truthiness ;;
;;;;;;;;;;;;;;;;
;; Verdad lógica. Tiene una regla muy simple
;; Todo lo que no sea falso o nil es verdadero

(if 0 :truthy :falsey)
;; => :truthy

(if "" :truthy :falsey)
;; => :truthy

(if [] :truthy :falsey)
;; => :truthy

(if nil :truthy :falsey)
;; => :falsey

(if false :truthy :falsey)
;; => :falsey

;; En clojure los macros and, or y when se construyen a partir del if.
;; Es decir, en su expansión se producen `ìfs`
;; Ejemplo:
(macroexpand-all '(and 1 nil 3))
;; (let*
;;  [and__5514__auto__ 1]
;;  (if
;;   and__5514__auto__
;;   (let*
;;    [and__5514__auto__ nil]
;;    (if and__5514__auto__
;;     3
;;     and__5514__auto__
;;   and__5514__auto__))



;; and: evalúa todas las expresiones de izquierda a derecha.
;;      Si hay valores lógicamente falsos, regresa el primero y si todos son
;;      lógicamente verdaderos, regresa el último.
(and 1 2 [])
;; => []

(and "a" :kw (= 1 2) nil)

(and nil 0 :uno "foo")


;; or: evalúa todas las expresiones de izquierda a derecha.
;;     Si hay valores lógicamente verdaderos, regresa el primero y si todos son
;;     lógicamente falsos, regresa el último.
(or [] "" 0 false)
;; => []

(or "a" :kw (= 1 2) nil)

(or (< 1 0) (first []))

;; when: es como un if sin else. Si la condición es lógicamente falsa su valor es nil.
(when 1 :truthy)
;; => :truthy

(when (last '()) :truthy)
;; => nil

;; Negando un valor de verdad lógica.
(not "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum eget purus enim.")
;; => false

(not [])
;; => false

(not (or nil false))
;; => true


;; if y when tienen sus contrapartes if-not y when-not
(if-not nil :falsey :truthy)
;; => :falsey

(when-not false :falsey)
;; => :falsey

;; Si las secuencias son tomadas como verdaderas,
;; ¿Cómo puedo saber si hay elementos para procesar en una secuencia?

(seq [1 2 3])
;; => (1 2 3)

(seq [])
;; => nil

;; También está la función empty? para determinar si hay elementos
;; para procesar, pero comunmente se usa seq.
(empty? '())
;; => true
