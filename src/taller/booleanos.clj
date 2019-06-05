(ns taller.booleanos)

;; Booleanos
true
false

;;;;;;;;;;;;;;;;;;
;; Truthfulness ;;
;;;;;;;;;;;;;;;;;;

(true? 1)
(true? true)
(true? (= 1 1))
(false? 0)
(false? 1)
(false? false)
(false? (< 9 4))

;;;;;;;;;;;;;;;;
;; Truthiness ;;
;;;;;;;;;;;;;;;;
;; Verdad lógica. Tiene una regla muy simple
;; Todo lo que no sea falso o nil es verdadero

(if 0 :truthy :falsey)
(if "" :truthy :falsey)
(if [] :truthy :falsey)
(if nil :truthy :falsey)
(if false :truthy :falsey)

;; En clojure and, or y when se construyen a partir del if.
;; and: evalúa todas las expresiones de izquierda a derecha.
;;      Si hay valores lógicamente falsos, regresa el primero y si todos son
;;      lógicamente verdaderos, regresa el último.
(and 1 2 [])
(and "a" :kw (= 1 2) nil)
(and nil 0 :uno "foo")

;; or: evalúa todas las expresiones de izquierda a derecha.
;;     Si hay valores lógicamente verdaderos, regresa el primero y si todos son
;;     lógicamente falsos, regresa el último.
(or [] "" 0 false)
(or "a" :kw (= 1 2) nil)
(or (< 1 0) (first []))

;; when: es como un if sin else. Si la condición es lógicamente falsa su valor es nil.
(when 1 :truthy)
(when (last '()) :truthy)

;; Negando un valor de verdad lógica.
(not "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum eget purus enim.")
(not [])
(not (or nil false))


;; if y when tienen sus contrapartes if-not y when-not
(if-not nil :falsey :truthy)
(when-not false :falsey)

;; Si las secuencias son tomadas como verdaderas,
;; ¿Cómo puedo saber si hay elementos para procesar en una secuencia?

(seq [1 2 3])
(seq [])

;; También está la función empty? para determinar si hay elementos
;; para procesar, pero comunmente se usa seq.
(empty? '())
