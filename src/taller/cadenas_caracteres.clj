(ns taller.cadenas-caracteres)

;;;;;;;;;;;;;
;; Cadenas ;;
;;;;;;;;;;;;;

;; Delimitadas por comillas dobles "
"Esto es una cadena"

;; Si queremos comillas dobles dentro de la cadena, hay que escaparlas
"El nombre del libro es \"Effective Java\"."

(def título "Effective Java")

;; Son cadenas de java

(class "foo")
;; => java.lang.String

;; Por lo tanto, podemos usar cualquier método definido en java:
;; https://docs.oracle.com/javase/8/docs/api/java/lang/String.html

(.lastIndexOf título "e")  ;; (título.lastIndexOf "e")
;; => 8

(.toCharArray título)
;; => [\E, \f, \f, \e, \c, \t, \i, \v, \e, \space, \J, \a, \v, \a]


;; se pueden escribir directamente en UTF-8
\u0042
;; => \B

(= \u0042 \B)
;; => true


;; Y nuevamente son respaldados por la JVM
(class \B)
;; => java.lang.Character


;; clojure tiene un espacio de nombres con funciones que operan sobre cadenas: `clojure.string`

(keys (ns-publics 'clojure.string))
;; => (ends-with?
;;     capitalize
;;     reverse
;;     join
;;     replace-first
;;     starts-with?
;;     escape
;;     last-index-of
;;     re-quote-replacement
;;     includes?
;;     replace
;;     split-lines
;;     lower-case
;;     trim-newline
;;     upper-case
;;     split
;;     trimr
;;     index-of
;;     trim
;;     triml
;;     blank?)

(clojure.string/trim "  Una cadena escrita por un usuario ")
;; => "Una cadena escrita por un usuario"

(clojure.string/includes?
  "Nunc rutrum turpis sed pede.
   Donec vitae dolor.
   Phasellus purus."
  "it")
;; => true

;; Las cadenas son secuencias de caracteres
(first "cadena")
;; => \c

(rest "cadena")
;; => (\a \d \e \n \a)

(seq "cadena")
;; => (\c \a \d \e \n \a)

;; Por lo que podemos usar toda la biblioteca de secuencias para manipularlas:

(filter #{\a \e \i \o \u} "Onec vitae dolor. Phasellus purus.")
;; => (\e \i \a \e \o \o \a \e \u \u \u)


;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Expresiones Regulares ;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; ¡ADVERTENCIA! Esto solo corresponde a clojure, en clojurescript las
;; cosas son algo distintas.

;; A veces es necesario realizar empates en cadenas utilizando algo
;; más que subcadenas. En esas ocasiones es útil (pero puede ser
;; difícil) hacerlo mediante expresiones regulares.

;; Clojure tiene soporte para ellas y al igual que la mayoría de lo
;; que hemos visto se basa en lo ofrecido por el host (JVM)

;; Una expresión regular se escribe así:

#"un patrón"
;; => #"un patrón"

;; Lo cuál regresa un patrón de java
(class #"un patrón")
;; => java.util.regex.Pattern

;; A diferencia de java, en clojure NO es necesario escapar las
;; diagonales invertidas que son para el motor de expresiones
;; regulares. En java dos dígitos podría ser: "\\d\\d"

(java.util.regex.Pattern/compile "\\d\\d")
;; => #"\d\d" ;; No se escapa la diagonal

;; La mayor parte del trabajo con expresiones regulares usa `re-seq`.
;; Esta función regresa una secuencia perezosa de todos los empates
;; con una cadena.

(re-seq #"\w+" "un.espacio.de.nombres.una-variable")
;; => ("un" "espacio" "de" "nombres" "una" "variable")

;; Si la expresión regular tiene grupos, el resultado de la función
;; nos regresa una secuencia de vectores, donde cada vector es de la
;; forma [el-empate-completo el-primer-grupo el-segundo-grupo ,,,,]

(re-seq #"(\d\d)a" "123a88a1a")
;; => (["23a" "23"] ["88a" "88"])

(re-seq #"(\d)a(\d)" "123a88a1a")
;; => (["3a8" "3" "8"] ["8a1" "8" "1"])


;; Aceptamos banderas al inicio de una expresión regular para modificar su comportamiento

(re-seq #"dapibus" "Pellentesque dapibus suscipit ligula")
;; => ("dapibus")

(re-seq #"dapibus" "Pellentesque daPiBus suscipit ligula")
;; => nil

(re-seq #"(?i)dapibus" "Pellentesque daPiBus suscipit ligula")
;; => ("daPiBus")



;;;;;;;;;;;;;;;;
;; Ejercicios ;;
;;;;;;;;;;;;;;;;


;; Escribe una función que tome una cadena y regrese una nueva cadena
;; que contenga solamente las mayúsculas
;;
;; (mayúsculas-en "Una CadeNA con altAs y BAJAS") => "UCNAABAJAS"
;; http://www.4clojure.com/problem/29


;; Escribe una función que tome una cadena en minúsculas separada por
;; guiones y la convierta a camel-case como lo usa java para variables
;; miembro.
;;
;; (camel-case "en-kebab-case") => "enKebabCase")
;; http://www.4clojure.com/problem/102
