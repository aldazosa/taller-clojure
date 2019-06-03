(ns taller.colecciones)

;; Las colecciones son agrupaciones de valores de clojure (símbolos,
;; cadenas, funciones, números, keywords, otras colecciones, etc).

[1 2 3]

'("a" "asdf" "qwer")

#{:foo :bar :baz}

[5 "asdf" :97 #{nil 42 1 \d}]

{:llave-1 "valor 1"
 :llave-2 "valor 2"}

;; Como todos los valores de clojure, las colecciones son inmutables y
;; persistentes. Eso significa las funciones que "modifican" una colección
;; realmente regresan uno nuevo y no modifican el que reciben como parámetro:

(def v [:a :b :c :d])

(conj v :nuevo) ;; => [:a :b :c :d :nuevo]

v ;; => [:a :b :c :d]

;;;;;;;;;;;;;;;;
;; Secuencias ;;
;;;;;;;;;;;;;;;;

;; Una secuencia es una lista lógica de datos. Es una estrucutra de datos
;; que puede ser recorrida y sus elementos conservan su orden al ser manipulada

;; Todas las colecciones en clojure implementan la interfaz ISeq
;; (https://clojure.org/reference/sequences) y por lo tanto todas las
;; funciones de la interfaz y librería de secuencias se pueden usar en
;; colecciones.

;; Sin embargo, según el tipo de coleccion, algunas cambian la manera en la
;; que procesan los datos

(rest '(:a :b :c :d)) ;; => (:b :c :d)
(rest {:foo 3 :bar 1 :baz 3}) ;; => ([:bar 1] [:baz 3])

;; Es importante notar que todas las funciones de la librería de secuencias
;; trabajan sobre la interfaz de secuencias, y no sobre el tipo de secuencia
;; en concreto. Por lo tanto, es posible que el valor de retorno no sea
;; del mismo tipo en concreto que su(s) parámetro(s), ya que convierten
;; el parámetro en secuencia antes de procesarlo

;; La función `seq` construye secuencias a partir de una colección.
;; Depende del tipo de colección, es cómo va a acomodar los datos
(seq [1 23 3 5]) ;; => (1 23 3 5)
(seq #{:a :b :d :e}) ;; => (:e :b :d :a)
(seq {:foo 1 :bar 3}) ;; => ([:foo 1] [:bar 3])

;; La interfaz de secuencias constituye las funciones `first`, `rest` y `cons`

;; `first` obtiene el primer elemento en una secuencia
(first '(\a \b \c)) ;; => \a
(first [1 2 3 4]) ;; => 1

;; `rest` obtiene el resto de la secuencia, removiendo el primer elemento
(rest ["ola" "ke" "ase"]) ;; => ("ke" "ase")
(rest '(:foo :bar :goo :baz)) ;; => (:bar :goo :baz)

;; `next` ofrece una funcionalidad parecida a `rest`.
(next [:a :b :c]) ;; => (:b :c)
;; La diferencia es que `next` no entrega una secuencia perezosa
;; (se verán secuencias perezosas al final del capítulo)

;; `cons` construye una nueva secuencia con un nuevo primer elemento
(cons 0 [1 2 3 4]) ;; => (0 1 2 3 4)

;; `conj` ofrece una funcionalidad parecida a `cons`, aunque su
;; comportamiento varía según el tipo de colección.
(conj [:a :b :c] :nuevo) ;; => [:a :b :c :nuevo]
(conj '(:a :b :c) :nuevo) ;; => (:nuevo :a :b :c)
;; Puede agregar varios elementos a la vez
(conj [1 2 3] 4 5 6) ;; => [1 2 3 4 5 6]

;; Es importante notar que `cons` siempre devuelve una secuencia y
;; `conj` devuelve el mismo tipo de colección concreta que su parámetro

;; Clojure ofrece una extensa biblioteca de funciones para procesar y construir
;; secuencias. Veremos unos ejemplos notables:

;; `second`, `nth`, y `last` obtienen un elemento específico de la secuencia
;; según su posición
(second ["Cosme" "Fulanito" "Bologna"]) ;; => "Fulanito"
(nth '(:qwer :asdf :zxcv :asdf) 2) ;; => :zxcv El índice empieza en 0
(last [7 8 4 3 1 5 9]) ;; => 9

;; `empty?` y `count` pregunta sobre la cantidad de elementos
;; en la secuencia
(empty? []) ;; => true
(empty? #{nil}) ;; => false
(count [:a :f :d [:d :q]]) ;; => 4

;; `reverse`, `sort`, `sort-by` y `shuffle` cambian el orden de elementos
;; en la secuencia
(reverse ["a" "b" "c" "d"]) ;; => ("d" "c" "b" "a")
(shuffle '(:a :b :c :d :e :f))
;; => [:f :b :d :e :a :c]
;; El órden del resultado es aleatorio

;; Para usar `sort` los elementos deben de implementar `Comparable`
(sort ["carro" "volante" "llanta" "coche"])
;; => ("carro" "coche" "llanta" "volante")

;; `sort-by` recibe una función y ordena los elementos comparando
;; el resultado de (función elemento)
(sort-by count ["a" "aaaaaaaa" "muuuy laaaargaaa" "aaaa"])
;; => ("a" "aaaa" "aaaaaaaa" "muuuy laaaargaaa")

;; `take`, `drop` obtienen los primeros o últimos elementos de una secuencia
(take 3 '(\a \b \c \d \e \f)) ;; => (\a \b \c) Toma los primeros 3
(drop 4 '(\a \b \c \d \e \f)) ;; => (\e \f) Remueve los primeros 4

;; `filter` y `remove` obtienen una parte de los elementos de la secuencia
;; determinado por un predicado
(filter even? [4 8 7 6 1 3 4 5]) ;; => (4 8 6 4)
(remove #(< % 12) [78 4 2 15 4 6]) ;; => (78 15)

;; `into` toma una colección y agrega sus elementos a otra
;; colección. Sirve para cambiar de un tipo concreto de colección a otro
(into [] '(4 5 8 6)) ;; => [4 5 8 6]

;; Para explorar más funciones en la librería de secuencias, ir a
;; https://clojure.org/reference/sequences#_the_seq_library

;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Tipos de colecciones ;;
;;;;;;;;;;;;;;;;;;;;;;;;;;
;; https://clojure.org/reference/data_structures#Collections

;; En clojure las colecciones son abstracciones de un conjunto de estructuras
;; de datos concretas con comportamiento en común.

;; Hay varios tipos de implementaciones concretas de colecciones en clojure,
;; cada una con sus diferencias en eficiencia y comportamiento.

;; En este capítulo abordaremos: listas, vectores, conjuntos y hash maps

;;;;;;;;;;;;
;; Listas ;;
;;;;;;;;;;;;

;; Las listas son la implementación más directa de secuencias.
;; Para crear una lista se puede usar las funciones `list`, `list*`
;; o rodear los elementos con paréntesis
(list 7 5 4 2 6 4) ;; => (7 5 4 2 6 4)
(list* [4 56 7]) ;; => (4 56 7)
'(7 8 9 :asdf) ;; => (7 8 9 :asdf)
;; Es importante agregar ' al principio para que clojure tome el valor
;; sin intentar evualuarlo como una llama a función

;; `list?` nos permite saber si un valor es una lista
(list? [1 2 4]) ;; => false
(list? '()) ;; => true

;; La función `count` tarda tiempo constante en calcularse
;; `nth` y `last` son de orden lineal

;;  `conj` agrega los elementos al principio de la lista
(conj '(7 4 9 6) 4) ;; => (4 7 4 9 6)
(conj '(7 4 9 6) :a :b :c) ;; => (:c :b :a 7 4 9 6)

;;;;;;;;;;;;;;
;; Vectores ;;
;;;;;;;;;;;;;;

;; Los vectores son secuencias de valores indexados por enteros contiguos
;; (empezando desde cero).

;; Para crear un vector se pueden usar las funciones `vector`, `vec` o
;; rodear los elementos con corchetes `[]`
(vector 7 4 5 8 9 1 2 4) ;; => [7 4 5 8 9 1 2 4]
(vec '(14 4 9 7 6)) ;; => [14 4 9 7 6]
[:a :b :c :d]

;; Soportan acceso de complejidad logaritmica de sus elementos a través
;; del índice. Para esto se puede usar `nth`, `get`
(nth [\q \w \e \r \t \y \u \i \o \p] 4) ;; => \t
(get [\q \w \e \r \t \y \u \i \o \p] 4) ;; => \t

;; O se puede usar al vector como una función pasándole el índice del
;; elemento buscado como parámetro
([\q \w \e \r \t \y \u \i \o \p] 8) ;; => \o

;; Si se usa un índice que no existe en el vector se lanza una
;; IndexOutOfBoundsException
;; (nth [] 34)

;; `count` es constante y `conj` agrega elementos al final del vector:
(conj [7 8 9] 12 11) ;; => [7 8 9 12 11]

;; `vector?` pregunta si un valor es un vector
(vector? []) ;; => true
(vector? '()) ;; => false

;; `assoc` sobre escribe el elemento en cierta posición con uno nuevo
(assoc [:a :b :c :d] 3 :nuevo) ;; => [:a :b :c :nuevo]
;; Se puede dar el sucesor del último índice y el elemento se
;; agrega al final
(assoc ["foo" "bar"] 2 "baz") ;; => ["foo" "bar" "baz"]
;; Si se da un índice mayor al tamaño del vector, se lanza un
;; IndexOutOfBoundsException

;; `subvec` obtiene el subvector desde inicio (inclusivo)
;; hasta fin (exclusivo)
(subvec [:a :b :c :d :e] 2 4) ;; => [:c :d]
;; Si no se da fin, se regresa hasta el final
(subvec [:a :b :c :d :e] 2) ;; => [:c :d :e]

;;;;;;;;;;;;;;;
;; Conjuntos ;;
;;;;;;;;;;;;;;;

;; Los conjuntos son colecciones de valores únicos: no permiten
;; duplicados en sus elementos.

;; Para construir un conjunto se puede usar la función `set` o
;; la notación `#{}`
#{:a :b :c :d}
(set [7 8 9 4 5 7]) ;; => #{7 4 9 5 8} quita duplicados

;; Si se define un conjunto con `#{}` con duplicados se lanzará
;; un Syntax error
;; #{1 1}

;; Los conjuntos son optimizados para preguntar si un elemento
;; existe dentro de ellos. Se pude usar la función `contains?` o `get`
(contains? #{:a :b :c :d} :a) ;; => true
(contains? #{:a :b :c :d} 2) ;; => false
(get #{"foo" "bar" "baz"} "baz") ;; => "baz"
(get #{"foo" "bar" "baz"} :baz) ;; => nil

;; O se puede usar el conjunto como función en vez de usar `get`
(#{1 5 9 4 78} 5) ;; => 5

;; La igualdad de conjuntos solo radica en sus elementos, no en el orden
(= #{1 2} #{2 1}) ;; => true

;; Por lo tanto no necesariamente conservarán el orden de sus elementos
;; al usarse con funciones de secuencias:
(seq #{1 2 3 4 5 6}) ;; => (1 4 6 3 2 5)
(conj #{1 2 4 5 6} 2) ;; => #{1 4 6 2 5}

;; Para eliminar un elemento en particular de un conjunto se usa `disj`
(disj #{:a :b :c :d} :c) ;; => #{:b :d :a}

;; En el espacio de nombres `clojure.set` existen funciones relacionadas
;; a operaciones matemáticas de conjuntos
;; https://clojuredocs.org/clojure.set
(clojure.set/union #{:a :b :c :d} #{:b :d :e :f})
;; => #{:e :c :b :d :f :a}
(clojure.set/intersection #{:a :b :c :d} #{:b :d :e :f})
;; => #{:b :d}
(clojure.set/difference #{:a :b :c :d} #{:b :d :e :f})
;; => #{:c :a}
(clojure.set/subset? #{:b} #{:a :b :c}) ;; => true
(clojure.set/superset? #{:a :b :c} #{:b}) ;; => true

;;;;;;;;;;;
;; Mapas ;;
;;;;;;;;;;;

;; Los mapas son colecciones de parejas (llave,valor).
;; Son optimizados para conseguir y actualizar los valores a partir de
;; las llaves.

;; Existen dos tipos de mapas concretos: hashed y sorted maps.
;; Los hashed maps son más eficientes al momento de conseguir valores.
;; Los sorted maps mantienen un orden de sus elementos en comparando las llaves

;; Para crear un mapa se pueden usar las funciones `hash-map` o `sorted-map`
;; ingresando como argumentos parejas de llaves y valores (alternados)
(hash-map :foo 2 :bar 1 :baz 10) ;; => {:baz 10, :bar 1, :foo 2}
(sorted-map :foo 2 :bar 1 :baz 10) ;; => {:bar 1, :baz 10, :foo 2}

;; O rodear las llaves y valores con llaves `{}` creando un hashed map
{:ronda-1 9 :ronda-2 7 :ronda-3 10}

;; Si se escribe un mapa con llaves repetidas, causará un error
;; {:a 1 :a 2} Duplicate key: 1

;; Aunque es muy común usar keywords como llaves, las llaves y valores
;; pueden ser cualquier tipo de datos: números, cadenas, booleanos, nil,
;; vectores, otros mapas, etc:
{1           :entero
 "asdf"      :cadena
 count       :función
 [1 3]       :vector
 {:bar :baz} {:otro :mapa}}

;; `seq` regresa una secuencia de parejas (vectores) de llaves y valores
(seq {"ronda-1" 1 "ronda-2" 3})
;; => (["ronda-1" 1] ["ronda-2" 3])

;; Para usar `conj` en un mapa puedes darle vectores de parejas llave,valor
(conj {1 "primero" 2 "segundo"} [3 "tercero"])
;; => {1 "primero", 2 "segundo", 3 "tercero"}
;; O otro mapa, mezclando los dos
(conj {:foo :val-1} {:bar :val-2}) ;; => {:foo :val-1, :bar :val-2}
;; Si se repiten llaves, se sobrescribirán los valores
(conj {:bar 3 :foo 2} {:bar 6 :baz 10})
;; => {:bar 6, :foo 2, :baz 10}

;; `count` y `empty?` también funcionan con mapas.
;; `count` regresa la cantidad de parejas en el mapa en tiempo constante
(empty? {}) ;; => true
(empty? {3 1}) ;; => false
(count {:nombre   "Cristóstomo"
        :apellido "Modesto"}) ;; => 2

;; Con la función `get` es posible recuperar un valor dentro de un mapa
;; por su llave
(get {:foo "bar" :baz "algo"} :foo) ;; => "bar"
;; Si no existe, regresa `nil`
(get {} :a) ;; => nil
;; Se puede especificar un valor por defecto para regresar por si no se
;; encuentra la llave
(get {:foo 1 :bar 2} :baz "No hay") ;; => "No hay"
;; También se puede usar el mapa como función en vez de usar `get`
({1 "uno" 2 "dos"} 2) ;; => "dos"

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

;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Secuencias perezosas ;;
;;;;;;;;;;;;;;;;;;;;;;;;;;
;; http://clojure-doc.org/articles/language/laziness.html

;; Aunque clojure como lenguaje no es perezoso, soporta la creación
;; y manejo de secuencias peresozas. En estas secuencias, los elementos
;; no son calculados (o realizados) hasta que se necesiten.

;; Muchas de las funciones de secuencias en realidad devuelven
;; secuencias perezosas: `rest`, `filter`, `remove`, `map`, etc.
;; El uso de secuencias perezosas en estas funciones aumenta la eficiencia
;; del programa en general, pero involucra ciertas cuestiones impredecibles

;; Por cuestiones de eficiencia, clojure realiza los elementos de una secuencia
;; perezosa en "chunks" de 32 elementos. Esto se ejemplifica muy bien
;; con el siguiente ejemplos

(take 1 (map println (range 100)))
;; 0
;; 1
;; 2
;; 3
;; 4
;; 5
;; 6
;; 7
;; 8
;; 9
;; 10
;; 11
;; 12
;; 13
;; 14
;; 15
;; 16
;; 17
;; 18
;; 19
;; 20
;; 21
;; 22
;; 23
;; 24
;; 25
;; 26
;; 27
;; 28
;; 29
;; 30
;; 31
;; => (nil)

;; Aunque solo pedimos el primer elemento con `take`, se realizó
;; el cálculo (imprimir en consola el elemento) de los primeros 32
;; elementos en la secuencia

;; Por este comportamiento hay que evitar la ejecución de escritura o lectura
;; (escribir a base de datos, llamar a un servicio web, etc) dentro de
;; secuencias perezosas.

;; En el caso que se requiera, existen funciones para forzar la realización
;; de las secuencias:

;; `doall` forza la realización y regresa la secuencia realizada
(doall (map println (range 50)))
;; => (nil, nil, nil, nil, nil ... )

;; `dorun` forza la realización y tira el resultado, regresando `nil`.
;; Su intención es solo realizar efectos secundarios.
(dorun (map println (range 50))) ;; => nil

;; Una ventaja de las secuencias perezosas es la construcción de
;; aparentemente secuencias infinitas. Así podemos procesar tantos
;; elementos como sea necesario, sin necesidad de definir un límite

;; Para construir una secuencia "infinita" existen varias funciones:

;; `repeat` simplemente repite un solo valor una y otra vez
(take 5 (repeat "ho")) ;; => ("ho" "ho" "ho" "ho" "ho")

;; `cycle` cicla sobre los valores de una secuencia
(take 7 (cycle ["fi" "fa" "fo" "fu"]))
;; => ("fi" "fa" "fo" "fu" "fi" "fa" "fo")

;; `iterate` Recibe una función `f` y un valor `v`
;; Regresa una secuencia con (v, f(v), f(f(v)), ...)
(take 8 (iterate (fn [v] (* v 3)) 1))
;; => (1 3 9 27 81 243 729 2187)

;;;;;;;;;;;;;;;;
;; Ejercicios ;;
;;;;;;;;;;;;;;;;

;; 1. A partir del vector `mensaje` y usando `nth` y `assoc`,
;; construye otro vector con los animales ordenados del más
;; pequeño al más grande

(def mensaje ["elefante" "ratón" "caballo" "ballena" "gato"])

;; 2. ¿Qué tipo de colección usarías para implementar una colección FIFO?
;; (el primer elemento insertado es el primer elemento extraído)
;; ¿Qué funciones usas para insertar y extraer elementos de la colección?

;; 3. Crea una colección con todos los elementos de `c1`, `c2` y `c3`
;; ordenados de mayor a menor
(def c1 '(78 4 2 98 6))
(def c2 [7 45 1 0 -8])
(def c3 [7 1 3 45 8 9])

;; 4. Usando el mapa `puntuaciones` y las funciones
;; `assoc-in`, `update-in` y `get-in`:
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

;; 4.1 Agrega un `:jugador-4` en todos los juegos con cualquier puntuación
;; 4.2 Duplica la puntuación de `:jugador-2` en el `:juego-1`
;; 4.3 Crea un mapa con las llaves siendo los juegos y los valores
;; la suma de las puntuaciones de todos los jugadores
