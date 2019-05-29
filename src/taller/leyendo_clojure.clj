(ns taller.leyendo-clojure
  (:require [clojure.repl :refer :all]))


;;;;;;;;;;;;;
;; El REPL ;;
;;;;;;;;;;;;;

;; Read Eval Print Loop
;; Es algo que distingue a los lenguajes de la familia LISP de los demás
;; Desde una terminal: lein repl
;; Desde atom: Packages > proto-repl > Start REPL

;; Hello world
(println "Hello, world!")


;; De alguna forma, el REPL buscó `println` para saber que es (en este
;; caso, una función definida en el core de clojure)

;; una suma
(+ 100 2)
;; => 102


;; numero de elementos
(count [1 2 3])
;; => 3


;; Tenemos variables especiales para obtener los últimos resultados:
;; *1 *2 *3

(str *1 " y " *2)
;; => "3 y 102"


;; A veces lo que escribimos rompe las cosas
;; Lo cuál nos regresa una excepción (de la JVM):

(/ 1 0)

;; *e tiene la última excepción


;; El REPL es el lugar dónde la mayoría de los programadores de clojure viven (o en un editor asociado a un repl)
;; en un flujo como:
;; Escribir una expresión -> evaluarla -> probarla -> ajustarla

;; De esa forma, el REPL se convierte también en una herramienta para
;; explorar el código. El espacio de nombres `clojure.repl` (https://clojure.github.io/clojure/clojure.repl-api.html)
;; incluye funciones de utilidad para esto.

;; ¿Cómo sé que hace una función? Con doc

(doc str)

;; Convenciones de nombres para parámetros:
;;
;; a    = un arreglo
;; coll = una colección
;; expr = una expresión
;; f    = una función
;; idx  = un índice
;; v    = un vector
;; val  = un valor



;; ¿Cómo busco una función? con find-doc podemos buscar los docstrings:

(find-doc "reset")

;; O con apropos buscamos sobre los nombres de cosas públicas

(apropos "reset")

;; También podemos ver el código de la función:

(source map)

;; Cuando se tiene una excepción (pst) print-stack-trace nos muestra
;; que pasó (vive en clojure.repl)

(/ 1 0)
(pst)
