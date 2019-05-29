(ns taller.colecciones)

;; Las colecciones son agrupaciones de valores de clojure: números,
;; cadenas, keywords, símbolos, otras colecciones, etc.

[1 2 3 4 5]

#{:a :b :c :d}

'("hola" "que" "hace")

;; Pueden almacenar distintos tipos de valores

[1 :a "hola" [:b :c]]

;; Hay varios tipos de colecciones, cada una con distintas
;; funcionalidades y comportamiento

;;;;;;;;;;;;;;;;
;; 5.1 Listas ;;
;;;;;;;;;;;;;;;;

;; Secuencias de valores. Mantiene el orden en el que los
;; valores son agregados a ésta.

;; Se pueden crear con la función `list`

(list 8 7 6 2) ;; => (8 7 6 2)

;; O con el macro '. (Esto hace que se interprete la forma escrita sin
;; evaluarla. Para más info consulte http://clojure.org/special_forms#quote

'(:a :b :c :d) ;; => (:a :b :c :d)

;; Se puede corroborar que algún valor es una lista con `list?`

(list? '(1 2 3)) ;; => true
(list? [1 2 3]) ;; => false

;; Es posible manipular listas como si fueran pilas con la función
;; `peek` para obtener el primer valor y `pop` para obtener el resto de
;; la lista

(peek '(1 2 3)) ;; => 1
(pop '(1 2 3)) ;; => (2 3)

;; Se pueden agregar valores al inicio de una lista con `conj`

(conj '(1 2 3) "otro") ;; => ("otro" 1 2 3)
(conj '(1 2 3) "muchos" "a" "la" "vez") ;; => ("vez" "la" "a" "muchos" 1 2 3)

;; También se puede usar `first`, `second` y `nth` para obtener valores
;; de la lista por su posición

(first '(:a :b :c :d :e :f)) ;; => :a
(second '(:a :b :c :d :e :f)) ;; => :b
(nth '(:a :b :c :d :e :f) 4) ;; => :e. Empieza desde 0

;; Ejercicios

;; 5.1.1 Sin usar `reverse` construye una lista con los elementos
;; de `l` en el orden inverso

(def l '(:a :b :c :d))

;;;;;;;;;;;;;;;;;;
;; 5.2 Vectores ;;
;;;;;;;;;;;;;;;;;;

;; Los vectores son secuencias como las listas: conservan el orden
;; de sus elementos al ser manipulados.
;; Adicionalmente soportan la escritura de sus elementos a través
;; de su posición

;; Se pueden crear con la función `vector`, `vec` o la notación `[]`

(vector "af" "qwr" "hola") ;; => ["af" "qwr" "hola"]
[1 2 43 5] ;; => [1 2 43 5]

;; `vec` convierte otra colección a vector
(vec '(1 2 43)) ;; => [1 2 43]

;; De misma manera existe `vector?` para verificar que un valor es un vector

(vector? []) ;; => true
(vector? '(1 2 3)) ;; => false

;; `peek` obtiene el último elemento del vector

(peek [1 2 3 4]) ;; => 4

;; `pop` regresa todos los elementos exceptuando el último

(pop [1 2 3 4])

;; `conj` agrega un elemento al final de un vector

(conj [:d :e :f] :9) ;; => [:d :e :f :9]
(conj [:d :e :f] :1 :2 :3) ;; => [:d :e :f :1 :2 :3]

;; `first`, `second` y `nth` también funcionan con vectores

(first ["hey" "esto" "es" "un" "vector"]) ;; => "hey"
(second ["hey" "esto" "es" "un" "vector"]) ;; => "esto"
(nth ["hey" "esto" "es" "un" "vector"] 3) ;; => "un"

;; Con `subvec` se puede obtener un subvector indicando el inicio y fin
;; del intervalo

(subvec [:a :b :c :d :e] 2) ;; => [:c :d :e] El inicio empieza desde 0 y es inclusivo
(subvec [:a :b :c :d :e] 2 4) ;; => [:c :d] El fin es exclusivo

;; Con `assoc` se puede reemplazar un valor por otro, usando su posición

(assoc [:a :v :e :w] 2 :nuevo) ;; => [:a :v :nuevo :w]

;; Ejercicios

;; 5.2.1 `A` partir del vector `mensaje` y usando `nth` y `assoc`,
;; construye otro vector con los animales ordenados del más
;; pequeño al más grande

(def mensaje ["elefante" "ratón" "caballo" "ballena" "gato"])

;; 5.2.2 ¿Qué tipo de colección usarías para implementar una colección FIFO?
;; (el primer elemento insertado es el primer elemento extraído)
;; ¿Qué funciones usas para insertar y extraer elementos de la colección?


;;;;;;;;;;;;;;;;;;;
;; 5.3 Conjuntos ;;
;;;;;;;;;;;;;;;;;;;

;; Los conjuntos son colecciones que no permiten duplicados.
;; Éstos no siempre conservan el orden de sus elementos al manipularlos o
;; iterarlos

;; Los conjuntos se pueden crear con la función `set` o la notación `#{}`

#{1 2 3 4} ;; => #{1 4 3 2}
(set [:a :b :c :d :b]) ;; => #{:c :b :d :a}

;; Si se declara un conjunto con elementos repetidos se lanzará una excepción
;; #{1 1} => Syntax error: Dupĺicate key 1

;; `contains?` pregunta si cierto elemento existe dentro del conjunto

(contains? #{1 2 3 4} 4) ;; => true
(contains? #{1 2 3 4} :a) ;; => false

;; también se puede usar el conjunto como función para hacer la misma pregunta

(#{:a :b :c} :a) ;; => true

;; `conj` agrega elementos a un conjunto. `disj` los remueve

(conj #{1 2 3 4} 2 5) ;; => #{1 4 3 2 5}

(disj #{4 7 8} 7) ;; => #{4 8}

;; Las funciones de conjuntos en el espacio de nombres `clojure.set`
;; funcionan como esperado

(clojure.set/union #{1 2 3} #{3 4 5})
;; => #{1 4 3 2 5}
(clojure.set/intersection #{1 2 3} #{3 4 5})
;; => #{3}
(clojure.set/difference #{1 2 3 4} #{3 4 5})
;; => #{1 2}

;; Ejercicios
;; 5.3.1 Construye un conjunto con los elementos de `v1`, `v2` y `v3`,
;; obviamente sin duplicados
(def v1 [1 5 61 8 8 3 1])
(def v2 [7 6 1 4 9 8])
(def v3 [1 3 4 7 8 5 1 3 21])

;; 5.3.2 Calcula la diferencia simétrica entre `A` y `B`:
;; Los elementos en la union de `A` y `B` que no forman parte de la
;; intersección de `A` y `B`
(def A #{"a" "b" "c" "d"})
(def B #{"c" "d" "e" "f"})

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; 5.4 Funciones de colecciones ;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; `empty?` pregunta si una colección es vacía

(empty? []) ;; => true
(empty? [1 23]) ;; => false

;; `count` regresa cuántos elementos hay en una colección

(count [:a :b :c]) ;; => 3

;; `concat` concatena dos colecciones. Con vectores y listas, conserva el orden.
;; `concat` regresa una secuencia (lista), no importando qué argumentos regresa

(concat [1 2 3] '(:a :b :c)) ;; => (1 2 3 :a :b :c)
(concat #{:a :b :c} #{:d :e :b}) ;; => (:c :b :a :e :b :d)

;; `reverse` regresa una secuencia con los elementos en el orden inverso

(reverse ["A" "B" "C" "D"]) ;; => ("D" "C" "B" "A")

;; `sort` ordena una secuencia de elementos comparables

(sort [3 6 1 8 3]) ;; => (1 3 3 6 8)

;; `apply` aplica una función que recibe una cantidad arbitraria de
;; argumentos (como `conj`) a una colección

(apply conj '(:a :b :c) [1 2 3])
;; => (3 2 1 :a :b :c)

;; Ejercicios
;; 5.4.1 Crea una colección con todos los elementos de `c1`, `c2` y `c3`
;; ordenados de mayor a menor
(def c1 '(78 4 2 98 6))
(def c2 [7 45 1 0 -8])
(def c3 [7 1 3 45 8 9])

;;;;;;;;;;;;;;;;;;
;; 5.5 Hashmaps ;;
;;;;;;;;;;;;;;;;;;

;; Los hashmaps o mapas son una colección de llaves y valores.

;; Para crear hashmaps se usa la función `hash-map` o la notación `{}`

{:a 1 :b 2 :c 3}
(hash-map :a 1 :b 2 :c 3) ;; => {:c 3, :b 2, :a 1}

;; Siempre hay que agregar elementos en pares de llave y valor.

;; Tanto como las llaves como los valores pueden ser cualquier tipo de dato

{:foo        :keyword
 1           :entero
 "asdf"      :cadena
 count       :función
 {:bar :baz} {:otro :mapa}}

;; Si se escribe un mapa con llaves repetidas, causará un error
;; {:a 1 :a 2} Duplicate key: 1

;; Con la función `get` es posible recuperar un valor dentro de un mapa
;; por su llave

(get {:foo "bar" :baz "algo"} :foo) ;; => "bar"

;; Si no existe, regresa `nil`

(get {} :a) ;; => nil

;; Se puede especificar un valor por defecto para regresar por si no se
;; encuentra la llave

(get {:foo 1 :bar 2} :baz "No hay") ;; => "No hay"

;; `contains?` pregunta si el mapa contiene cierta llave,
;; no importando el valor de la llave

(contains? {:foo nil :bar nil} :foo) ;; => true
(contains? {:foo 1 :bar 2} :baz) ;; => false

;; `select-keys` obtiene un "submapa" con las llaves especificadas

(select-keys {:foo 1 :bar 2 :baz 3} [:foo :bar])
;; => {:foo 1, :bar 2}

;; Para agregar o sobre escribir un valor en un mapa se usa `assoc`

(assoc {:foo 1} :bar 3) ;; => {:foo 1, :bar 3}
(assoc {:foo 1} :foo "nuevo") ;; => {:foo "nuevo"}

;; También se puede transformar un valor de un mapa con `update`
;; Esta función, en vez de recibir un valor, recibe una función
;; que recibe el valor original como parámetro y devuelve en nuevo valor

(update {:foo 3 :bar 2} :foo inc)
;; => {:foo 4, :bar 2}

(update {:altura 1.65} :altura (fn [en-metros]
                                 (* 100 en-metros)))
;; => {:altura 165.0}

;; Si la llave no existe, la función se llama con `nil`
;; y crea la llave en el mapa
(update {:foo 1} :bar str) ;; => {:foo 1, :bar ""}

;; `dissoc` remueve una pareja de llave,valor de un mapa

(dissoc {:foo 1 :bar 2} :foo) ;; => {:bar 2}
(dissoc {:foo 1 :bar 2} :baz) ;; => {:foo 1, :bar 2}

;; `merge` combina 2 o más mapas, sobrescribiendo los valores de las
;; llaves que se repiten

(merge {:foo 2 :bar 3} {:bar 6 :baz 10})
;; => {:foo 2, :bar 6, :baz 10}

(merge {:bar 3 :foo 2}
       {:bar 6 :baz 10}
       {:bar nil :otro 1})
;; => {:foo 2, :bar nil, :baz 10, :otro 1}

;; Cuando un mapa tiene valores que son mapas (mapas anidados) se pueden
;; usar las funciones `get-in` y `assoc-in` para obtener y escribir valores
;; en los niveles anidados y `update-in` para transformarlos con una función

;; Del valor de `:foo` obtén `:bar`
(get-in {:foo {:bar 3}
         :goo {:bar 2
               :baz {:a 1 :b 2 :c 2}}}
        [:foo :bar]) ;; => 3

;; Del valor de `:goo` obtén `:baz` y de ahí obtén `:a`
(get-in {:foo {:bar 3}
         :goo {:bar 2
               :baz {:a 1 :b 2 :c 2}}}
        [:goo :baz :a]) ;; => 1

;; Dentro de `:goo` métete a `:baz` y ahí asigna a `:a` el valor de 4
(assoc-in {:foo {:bar 3}
           :goo {:bar 2
                 :baz {:a 1 :b 2 :c 2}}}
          [:goo :baz :a] 4)
;; => {:foo {:bar 3}
;;     :goo {:bar 2
;;           :baz {:a 4, :b 2, :c 2}}}

;; multiplica por tres `:bar` dentro de `:goo`
(update-in {:foo {:bar 3}
            :goo {:bar 2
                  :baz {:a 1 :b 2 :c 2}}}
           [:goo :bar] (fn [v] (* 3 v)))
;; => {:foo {:bar 3}
; ;    :goo {:bar 6
;;           :baz {:a 1, :b 2, :c 2}}}

;; Puedes ir creando niveles dentro de un mapa con `assoc-in`
;; o `update-in` aunque no existan

(assoc-in {:foo 2} [:bar :baz :a] 1)
;; => {:foo 2 :bar {:baz {:a 1}}}

;; Ejercicios
;; Usando el mapa de `puntuaciones`:
(def puntuaciones
  {:juego-1 {:jugador-1 1
             :jugador-2 2
             :jugador-3 0}
   :juego-2 {:jugador-1 0
             :jugador-2 1
             :jugador-3 3}
   :juego-3 {:jugador-1 1
             :jugador-2 1
             :jugador-3 2}})

;; 5.5.1 Agregar un `:juego-4` con las puntuaciones de cada jugador en 0
;; 5.5.2 Agregar un `:jugador-4` en todos los juegos con cualquier puntuación
;; 5.5.3 Quitar a `:jugador-3` de todos los juegos
;; 5.5.4 Duplicar la puntuación de `:jugador-2` en el `:juego-1`
;; 5.5.5 Crear un mapa con las llaves siendo los jugadores y los valores
;; la suma de sus puntuaciones

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; 5.6 Lazy lists (colecciones perezosas) ;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; Las listas perezosas son sequencias de valores que se no se calculan
;; hasta que se necesitan. El calculo de los elementos de la lista se le
;; llama "realización".

;; Las listas perezosas tienen dos beneficios
;; - Pueden ser infinitas (por lo menos puedes pensarlas como infinitas)
;; - Se evita el calculo de sus elementos si es que no son necesarios todos

;; Existen varias funciones que regresan una lista perezosa como resultado:

;; `repeat` simplemente repite un valor infinitamente

(take 5 (repeat "ha"))
;; => ("ha" "ha" "ha" "ha" "ha")

;; `cycle` repite los valores de una colección

(take 10 (cycle [:fi :fa :fo :fu]))
;; => (:fi :fa :fo :fu :fi :fa :fo :fu :fi :fa)

;; `iterate` Recibe una función `f` y un valor `v`
;; Regresa una lista perezosa infinita con (v, f(v), f(f(v)), ...)

(take 8 (iterate (fn [v] (* v 3)) 1))
;; => (1 3 9 27 81 243 729 2187)

;; En todos los ejemplos usamos `take` para solo tomar un número finito
;; de los elementos de la lista. Si no se usara, se intentaría resolver
;; la lista infinita completa y se trabaría el repl

;; Se puede forzar la realización de una lista perezosa con `doall`

;; Ejercicios
;; 5.6.1 Generar una lista con las primeras 20 potencias de dos
;; empezando con 8

;; Es importante recordar que los valores de la lista no se calcularán
;; hasta que sea necesario. Si el calculo de los elementos de la lista
;; incluye efectos secundarios (algo fuera del cálculo del resultado de
;; la función, como escribir a una base de datos) no se efectuarán hasta
;; que se realice la lista

(defn imprime-y-aumenta [v]
  (println "imprimir: " v)
  (inc v))

;; Esto no realiza la lista, por lo tanto nada se imprime en consola
(def lista
  (iterate imprime-y-aumenta 10))

(take 5 lista)
;; => imprimir 10
;; => imprimir 11
;; => imprimir 12
;; => imprimir 13
;; => (10 11 12 13 14)

;; Los valores ya calculados se guardan, entonces si se piden elementos de
;; la lista de nuevo no se calcularán otra vez

(take 6 lista)
;; => imprimir 14
;; => (10 11 12 13 14 15)

;; Referencias
;; Colecciones https://clojure.org/reference/data_structures#Collections
;; Lazy lists http://clojure-doc.org/articles/language/laziness.html
