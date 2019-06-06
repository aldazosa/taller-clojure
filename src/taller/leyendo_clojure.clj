(ns taller.leyendo-clojure
  (:require [clojure.repl :refer :all]))


;; ¡CUIDADO! Este archivo incluye expresiones que lanzan excepciones,
;; por lo que no se puede cargar por completo de forma sencilla.


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


;;;;;;;;;;;;;;;;;
;; Expresiones ;;
;;;;;;;;;;;;;;;;;

;; Todo el código en clojure consiste de expresiones, donde cada una
;; de ellas se evalúa a ún único valor (no hay "return void" o
;; enunciados que no regresen algo "if, for, continue"). Ejemplos:

1                ;; un número
"clojure"        ;; una cadena
\a               ;; un caracter
1/3              ;; una fracción
true             ;; un booleano
nil              ;; Ausencia de valor, muy importante
(+ 1 2)          ;; una suma
(+ 1 (+ 10 2))   ;; expresiones anidadas
'(1 2 3)         ;; una lista de números
[\a \b \c]       ;; un vector (arreglo) de caracteres
(count
  [10 20 30 40]) ;; una llamada a función





;;;;;;;;;;;;;
;; Números ;;
;;;;;;;;;;;;;

(+ 8 9)
;; => 17

;;;;;;;;;;;;;;;;;;;
;; Notación prefija

;; Todas nuestras expresiones son listas, donde lo primero que aparece
;; es especial (lo más usual es el nombre de una función) y lo demás
;; es un número arbitrario de parámetros:

(+ 2 3 4 5 6 7 8)
;; => 35

(+)
;; => 0

(- 10 3)
;; => 7

(* 3 5 2)
;; => 30

(> 10 3)
;; => true

(>= 3 3)
;; => true

(= 4 5)
;; => false

(/ 10 3)
;; => 10/3
;; Esto último muestra que clojure tiene un tipo para fracciones


(/ 10. 3) ;; Si un parámetro es un flotante, el resultado también
;; => 3.3333333333333335

;; Para precisión arbitraria
;;   - de flotantes:  se usa el prefijo M (crea un BigDecimal de java)
;;   - de enteros: se usa el prefijo N (crea un BigInt)

(+ 1 (/ 0.00001 1000000000000000000))
;; => 1.0

(+ 1 (/ 0.00001M 1000000000000000000))
;; => 1.00000000000000000000001M




;;;;;;;;;;;;;;;;;;;;;;;;
;; Símbolos (nombres) ;;
;;;;;;;;;;;;;;;;;;;;;;;;


;; Formas como `+`, `count` y `doc` se conocen como `símbolos` y se
;; usan para nombrar cosas.
;; Se usan para nombrar:
;;   funciones
;;   clases de java
;;   espacios de nombres

;; Empiezan con un caractér no numérico y puede contener caracteres
;; alfanuméricos y *, +, !, -, _, ', ?, <, > and =
;;
;; https://clojure.org/reference/reader#_symbols

(def foo)
(def 1-2-3)
(def a1)
(def foo*)
(def _!-???)

;; `/` y `.` son especiales se usan en espacios de nombres


;;;;;;;;;;;;;;;;;
;; Colecciones ;;
;;;;;;;;;;;;;;;;;

;; Tenemos cuatro tipos básico de colecciones

;; vector
[1 2 3 4]

;; lista
(1 2 3 4) ;; Oops :-(


(quote (1 2 3 4))
'(1 2 3 4)

;; Conjunto

#{1 2 3 4}

;; Mapas
{"uno" 1 "dos" 2}

;; Las comas son opcionales

(= [1 2 3 4] [1, 2, 3, 4])
(= {"uno" 1 "dos" 2} {"uno" 1, "dos" 2})


;; keywords
;; Es como un símbolo, empieza con `:` y siempre apunta a si mismo

:keyword
:foo

;; Se usan frecuentemente como las llaves de mapas
{:uno 1 :dos 2}



;;;;;;;;;;;;;
;; Cadenas ;;
;;;;;;;;;;;;;

;; Siguen la misma sintaxis que en java (comillas dobles y escapes)
"Esta es una cadena"


"Esta es una cadena \ncon escapes"

;; Si la imprimimos, esto se nota:

(println "Esta es una cadena \ncon escapes")


;; Para crear una cadena a partir de otras cosas, usamos `str`

(str 1)

(str)

(str [:a :b :c])

;; Convierte cada parámetro a cadeana y los concatena

(str 1 2 3)

(str [:a :b] 2 [:c :d])

;; Ignora cualquier `nil`

(str 1 nil 2)


;; Los chars de clojure son chars de java
;; La sintáxis literal es `\{letra}`

\a
\b
\c

(str \a \b \c)


;;;;;;;;;;;;;;;
;; Funciones ;;
;;;;;;;;;;;;;;;

;; Una llamada a función es una lista cuyo primer elemento se resuelve a una función

(str "concatenando" \space "cadenas")
;; => "concatenando cadenas"

;; Convenciones
;;
;; - En clojure separamos los nombres mediante guiones (kebab-case)
;; - Las funciones que terminan en `?` regresan booleanos

(keyword? :foo)
;; => true

(keyword? "foo")
;; => false

;; Para crear una nueva función usamos `defn`)

(defn                     ;; esto es una nueva función
  hola-mundo              ;; que se llama "hola-mundo"
  []                      ;; que no recibe argumentos
(println "¡Hola mundo!")  ;; y hace esto
)

;; En los lisp, no dejamos paréntesis solos en una línea:
(defn hola-mundo []
  (println "¡Hola mundo!"))


(defn saluda [nombre]
  (str "¡Hola " nombre "!"))


;;;;;;;;;;;;;;;;
;; Evaluación ;;
;;;;;;;;;;;;;;;;

;; Tiene un mecanismo de evaluación muy simple:

;; 1. Listas (entre paréntesis) son llamadas, el primer parámetro en la
;;    lista es la operación y los demás los parámetros. El resultado de
;;    la expresión es el valor entregado por la función.
;; 2. Un símbolo (como average o +) se evalúa a "algo" que tenga ese
;;    nombre asociado en el alcance actual (espacios de nombres). Esto
;;    puede ser una función, una clase de java, etc.
;; 3. Cualquier otra expresión se evalúa al valor literal que describe


(saluda (str "bot-" (rand-int (+ 3 5))))

(saluda (str "bot-" (rand-int 8)))

(saluda (str "bot-" 3))

(saluda "bot-3")

;; En un lenguaje "tradicional" el proceso de compilación es a grandes rasgos:
;; Cadena -> Parser & Lexer -> AST (no disponible para el programa) -> Evaluator -> Bytecode o algo así

;; En un lenguaje con un REPL es:
;; Cadena -> Reader -> lista (que el programa puede usar) -> Eval -> valor -> Print -> Loop
;; (Hay algo más por ahí, con respecto a los macros pero no es un tema básico)

;; Esto no es buena idea (en general) pero:
(+ 1 2)
;; => 3

(eval (list + 1 2))
;; => 3

(eval (list 'def 'tres (list + 1 2)))
;; => #'taller.leyendo-clojure/tres

tres
;; => 3

;; En otras palabras evaluando en el REPL tenemos un programa que
;; mediante su evaluador se modifica a sí mismo


;;;;;;;;;;;;;;;
;; Pipelines ;;
;;;;;;;;;;;;;;;

;; Muchos programas se expresan de forma natural como una secuencia de
;; pasos. Dada la notación prefija en los lisp y la forma en que se
;; anidan los parámetros en ocasiones puede resultar difícil leer el
;; código dado que se debe ir de dentro hacia afuera:

'(defn foo [persona]
   (-> persona
       (selecciona-campos-relevantes :estudiante)
       (actualiza-fecha-registro :hoy)
       (transforma-nombre)))


'(defn foo [persona]
   (transforma-nombre
     (actualiza-fecha-registro
       (selecciona-campos-relevantes persona :estudiante) :hoy)))


;; `->` se conoce como "thread-first macro" y transforma una secuencia
;; de listas (o símbolos) insertando el resultado de una expresión
;; como el primer parámetro de la siguiente.

(macroexpand-1
  '(-> persona
       (selecciona-campos-relevantes :estudiante)
       (actualiza-fecha-registro :hoy)
       (transforma-nombre)))

;; => (transforma-nombre
;;     (actualiza-fecha-registro
;;      (selecciona-campos-relevantes persona :estudiante)
;;      :hoy))

;; Si alguno de los pasos no recibe más que un parámetro, los
;; paréntesis son opcionales:


'(defn foo [persona]
   (-> persona
       (selecciona-campos-relevantes :estudiante)
       (actualiza-fecha-registro :hoy)
       transforma-nombre))
