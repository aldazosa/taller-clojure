(ns taller.funciones
  (:require [clojure.repl :refer [doc]]))

;; Para crear una función se utiliza la forma special `fn`

'(fn name? [params*] exprs*)

;; Para sumar 10

(fn [x]
  (+ 10 x))
;; => #function[taller.funciones/eval12085/fn--12086]

;; Para realizar una llamada, la función debe aparecer como el primer elemento de una lista:

((fn [x] (+ 10 x)) 33)
;; => 43

;; Para definir funciones y que tenga un nombre, se usa defn

'(defn name doc-string? attr-map? [params*] prepost-map? body)

'(defn name  ;; el nombre de la nueva función
   doc-string?  ;; documentación asociada (mostrada por doc)
   attr-map?    ;; metainformación
   [params*]    ;; lista de parámetros (acepta destructuring)
   prepost-map? ;; pre y post condiciones, para programar por contrato
   body)        ;; el cuerpo de la función


(defn saluda
  "Regresa un saludo de la forma 'Hola, `nombre`'"
  [nombre]
  (str "Hola, " nombre))

(saluda "mundo")
;; => "Hola, mundo"

(doc saluda)
;; -------------------------
;; taller.funciones/saluda
;; ([usuario])
;;   Regresa un saludo de la forma 'Hola, `usuario'`


;; ¿Como hacemos que esta función tenga un comportamiento por omisión?

(saluda)

;; Execution error (ArityException) at taller.funciones/eval13452 (REPL:53).
;; Wrong number of args (0) passed to: taller.funciones/saluda

;; Entonces necesitamos agregar una nueva 'aridad' (otra lista de
;; parametros de distinto tamaño).

(defn name doc-string? attr-map?
  ([params*] prepost-map? body)+) ;; !! <- Una o más definiciones


(defn saluda
  "Regresa un saludo de la forma 'Hola, `nombre`'. El `nombre` por
  omisión es 'mundo'."
  ([] (saluda "mundo"))
  ([nombre] (str "Hola, " nombre)))


(saluda)
;; => "Hola, mundo"

(saluda "Pedro")
;; => "Hola, Pedro"

;; Si se desea tener aridad variable, se puede hacer incluyendo un '&'
;; en la lista de parámetros. Dicho parámetro siempre debe ser el
;; último de la lista de parámetros.

;; Esto reúne todos los parámetro en la llamada dentro de una
;; secuencia y se conoce como funciones variadicas (varargs)

;; Una función con un elemento posicional y los demás en secuencia

(defn saluda [primero & resto]
  (str "Hola " primero " y " resto))

(saluda "Pedro" "Juan" "Matías", "Roberto")
;; => "Hola Pedro y (\"Juan\" \"Matías\" \"Roberto\")"

(defn saluda [primero & resto]
  (apply str "Hola " primero ", " (interpose ", " resto)))

(saluda "Pedro" "Juan" "Matías", "Roberto")
;; => "Hola Pedro, Juan, Matías, Roberto"


;;;;;;;;;;;;;;;;;;;;;;;;
;; Funciones anónimas ;;
;;;;;;;;;;;;;;;;;;;;;;;;

;; Dado que clojure es un lenguaje funcional, las funciones se
;; consideran valores de primera clase (se pueden pasar como
;; argumentos, regresar como un resultado). Existen varias razones
;; para tener una función anónima (creada con `fn`):

;; - La función es corta y evidente (darle un nombre no aporta valor)
;; - La función se usa solamente dentro de otra función y le basta un
;;   nombre local
;; - La función se crea para capturar valores


;; Para duplicar cada valor de una secuencia:

(map (fn [x] (* 2 x)) [1 2 3 4 5])
;; => (2 4 6 8 10)

;; En estas ocasiones se puede utilizar un macro con parámetros implícitos:
(map #(* 2 %) [1 2 3 4 5])
;; => (2 4 6 8 10)

;; La sintaxis es

#('cuerpo)

;; Y los parámetros son de la forma `%n`, con excepción del primero
;; que puede omitir el número

(#(str "Recibí " %1 ", " %2 " y " %&)
  :foo :bar :baz :qux)

;; => "Recibí :foo, :bar y (:baz :qux)"


;; Cuando se usa esta forma, las funciones anónimas no se pueden anidar.
;;
;; Correcto:

(fn [x]
  (fn [y]
    (+ x y)))
;; => #function[taller.funciones/eval13503/fn--13504]


;; Incorrecto

#(;; x
  #(;; y
    (+ % %)))
;; => ;; => ReaderException
