(ns taller.threading-macros)

;; Los threading macros, o macros flecha, ayudan a reescribir expresiones
;; anidadas para que sean más claras y fáciles de leer

;;;;;;;;
;; -> ;;
;;;;;;;;

;; `->` o thread-first macro ayuda a agrupar varias llamadas de funciones
;; pasando el resultado de la anterior como el primer parámetro de la siguiente

;; Esta expresión:
(update (update (assoc {:curp "momo080808hdfnnn00"
                        :edad 18}
                       :valido? true)
                :curp clojure.string/upper-case)
        :edad inc)

;; es equivalente a
(-> {:curp "momo080808hdfnnn00"
     :edad 18}
    (assoc :valido? true)
    (update :curp clojure.string/upper-case)
    (update :edad inc))
;; => {:curp "MOMO080808HDFNNN00", :edad 19, :valido? true}

;; El orden de las operaciones ahora se puede leer de izquierda a derecha
;; (o arriba a abajo en este caso) en vez de leer varios niveles
;; de anidado de adentro hacia afuera

;; `->` es particularmente útil para cuando un solo valor sufre una
;; serie de transformaciones

(-> " FOO_2   "
    clojure.string/trim
    clojure.string/lower-case
    (clojure.string/replace #"[_]" "-"))
;; => "foo-2"

;;;;;;;;;
;; ->> ;;
;;;;;;;;;

;; `->>` o thread-last macro es parecido a `->` en que ayuda a reescribir
;; expresiones anidadas en un flujo más legible. La diferencia radica en
;; que el resultado de la expresión anterior es pasado como el último
;; parámetro de la siguiente.

(->> (range 50)                ;; construye una secuencia de 0 a 49
     (filter #(= (mod % 5) 0)) ;; obtén solo los múltplos de 5
     (map #(* % %))            ;; saca el cuadrado de cada uno
     (reduce +))               ;; suma todos
;; => 7125

;; Ideal para hilar funciones de la biblioteca de secuencias como
;; `filter`, `map`, `reduce`, `take`, `drop`, etc.

;; Existen otros macros de hilado como:
;; - `as->` asocia un nombre al valor que se pasa de una expresión a otra
;;   para poder cambiarla de posición a lo largo de las expresiones
;; - `some->` y `some->>` Iguales a `->` y `->>`, solo que detienen la
;;   evaluación si una expresión se evalúa a `nil`
;; - `cond->` y `cond->>` A cada expresión en el hilo se le asocia una
;;   condición. La expresión se evalúa solo si la expresión es verdad
;; Para ver más de estas expresiones visite https://clojure.org/guides/threading_macros

;;;;;;;;;;;;;;;;
;; Ejercicios ;;
;;;;;;;;;;;;;;;;

;; 1.
(def juego
  {:ronda-1 {:jugador-1 1
             :jugador-2 1}
   :ronda-2 {:jugador-1 2
             :jugador-2 1}})

;; Reescribe la siguiente expresión usando `->`
(update-in (assoc-in (assoc-in (assoc-in juego
                                         [:ronda-1 :jugador-3] 3)
                               [:ronda-2 :jugador-3] 0)
                     [:ronda-3 :jugador-1] 1)
           [:ronda-1 :jugador-2] inc)
;; => {:ronda-1 {:jugador-1 1, :jugador-2 2, :jugador-3 3},
;;     :ronda-2 {:jugador-1 2, :jugador-2 1, :jugador-3 0},
;;     :ronda-3 {:jugador-1 1}}

;; 2. Reescribe la siguiente expresión usando `->>`

(reduce + (map inc (take 3 (drop 2 [2 5 4 1 3 6]))))
;; => 11

;; Referencias
;; https://stuartsierra.com/2018/07/06/threading-with-style
;; https://stuartsierra.com/2018/07/15/clojure-donts-thread-as
;; https://medium.com/@hlship/confessions-of-a-threading-macro-addict-5a026dae4af7
