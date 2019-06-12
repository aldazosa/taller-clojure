(ns taller.transformando-secuencias)

;; Veremos ahora con más detalle algunas funciones muy usadas para
;; tomar una secuencia, iterarla y construir otro valor a partir
;; de los elementos de la secuencia

;;;;;;;;;
;; map ;;
;;;;;;;;;

;; Map recibe una función `f` y una secuencia `seq`. Construye una
;; secuencia perezosa con los resultados de aplicar `f` a cada elemento
;; de `seq`.

(map even? [47 4 9 1 3 4 6 7])
;; => (false true false false false true true false)

(map #(* % %) (range 10))
;; => (0 1 4 9 16 25 36 49 64 81)

;; Obtén el valor `:foo` de una secuencia de mapas
(map :foo [{:foo 4 :bar 8}
           {:foo 5 :bar 14}
           {:foo 7 :bar 10}
           {:foo 10 :bar 77}])
;; => (4 5 7 10)

;; `map` también puede iterar varias secuencias a la vez.
;; Esto ocasiona que se iteren al paralelo todas las secuencias,
;; llamando a la función con los elementos correspondientes, hasta
;; que se agote la secuencia más corta

(map + [1 4 8 6] [7 14 3 47 9] [1 4 7 8 96])
;; => (9 22 18 61) Sumar los vectores entrada por entrada

;; `mapv` es igual a map, solo que regresa un vector completamente
;; realizado en vez de una secuencia perezosa

(mapv inc [7 4 5 7 9]) ;; => [8 5 6 8 10]

;; `mapcat` es parecida a map, pero concatena todas los elementos
;; de la sequencia (sequencia de sequencias) antes de regresar el valor

(mapcat #(remove even? %) [[1 2] [2 2] [2 3]])
;; => (1 3)

;; `map-indexed` es parecida a `map` pero, al iterar una secuencia,
;; la función va a recibir el elemento y la posición del elemento

(map-indexed (fn [id x] {:posición id :elemento x})
             [:foo :bar :baz])
;; => ({:posición 0, :elemento :foo}
;;     {:posición 1, :elemento :bar}
;;     {:posición 2, :elemento :baz})

;; Ejercicio 1.
;; Implementa una función que reciba una secuencia y duplique cada
;; elemento en ella
;; http://www.4clojure.com/problem/33

(defn duplica [sec]
  ;; escribe tu código aquí
  )

;; Todas las siguientes propiedades deben de ser verdaderas
(= (duplica [1 2 3]) '(1 1 2 2 3 3))
(= (duplica [:a :a :b :b]) '(:a :a :a :a :b :b :b :b))
(= (duplica [[1 2] [3 4]]) '([1 2] [1 2] [3 4] [3 4]))
(= (duplica [[1 2] [3 4]]) '([1 2] [1 2] [3 4] [3 4]))

;;;;;;;;;;;;
;; reduce ;;
;;;;;;;;;;;;

;; `reduce` recibe una función `f`, que recibe dos argumentos, y una secuencia
;; Se aplicará la función `f` a los dos primeros elementos de la secuencia,
;; después se aplicará al resultado anterior y al tercer elemento, y así
;; hasta que se acabe la secuencia

;; suma todos los elementos de la secuencia
(reduce + [4 5 6 4 8 4 2 1 3]) ;; => 37

;; Calcula el máximo de una secuencia
(reduce (fn [máximo next]
          (if (< máximo next) next máximo))
        [4 7 2 1 3 4 8 45 4 6]) ;; => 45

;; También es posible proporcionarle un argumento extra a `reduce` para
;; que empiece con un valor inicial antes de empezar a consumir la
;; colección

;; Agrega elementos de un vector a un conjunto
(reduce conj #{} [7 8 8 6 1 1 4 6 5])
;; => #{7 1 4 6 5 8}
;; Primero se ejecuta (conj #{} 7)
;; después (conj #{7} 8)
;; despues (conj #{7 8} 8), etc..

;; Ejercicio 2.
;; Implementa una función que reciba una colección y cree un mapa
;; cuyas llaves sean los elementos de la colección y los valores su
;; frencuencia dentro de la colección (cuántas veces aparecen)
;; Hint: Considera que llamar `inc` con `nil` causuará una excepción.

(defn frecuencias
  [coll]
  ;; escribe tu código aquí
  )

;; Todas las siguientes propiedades deben de ser verdaderas
(= (frecuencias [:foo :bar :foo :foo :baz])
   {:foo 3 :bar 1 :baz 1})
(= (frecuencias "ABRACADABRA")
   {\A 5, \B 2, \R 2, \C 1, \D 1})

;;;;;;;;;;;;;;;
;; partition ;;
;;;;;;;;;;;;;;;

;; `partition` parte una colección en pedazos de `n` longitud.

(partition 3 (range 12))
;; => ((0 1 2) (3 4 5) (6 7 8) (9 10 11))

;; Si no hay suficientes elementos se entregan solo las particiones
;; con `n` elementos

(partition 3 [:a :b :c :d :e :f :g])
;; => ((:a :b :c) (:d :e :f))

;; Se puede especificar qué tanta distancia hay entre las particiones
;; Si la distancia es mayor al tamaño de las particiones, se saltarán
;; elementos de la colección

(partition 3 5 (range 13))
;; => ((0 1 2) (5 6 7) (10 11 12))

;; Si la distancia es menor al tamaño de las particiones, las "particiones"
;; se traslaparán

(partition 3 1 (range 6))
;; => ((0 1 2) (1 2 3) (2 3 4) (3 4 5))

;; Como último parámetro opcional, se puede especificar otra colección para
;; rellenar la última partición por si no alcanzan los elementos de
;; la colección.
;; Para usar este parámetro hay que especificar la distancia entre
;; particiones

(partition 3 3 [:foo :bar] (range 13))
;; => ((0 1 2) (3 4 5) (6 7 8) (9 10 11) (12 :foo :bar))

(partition 3 3 [:foo] (range 13))
;; => ((0 1 2) (3 4 5) (6 7 8) (9 10 11) (12 :foo))
