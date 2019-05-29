(ns taller.símbolos)

;; # Vars
;; ## Definición
;; Nombre completo de vars incluye el espacio de nombre. Nombre calificado.
(def lenguaje "Clojure")

(def carácterísticas ["funcional" "hosted"])

(def pi 3.141592653589793)

(println lenguaje)

;; ## Redefinición de vars.
(def lenguaje "ClojureScript")

(println lenguaje)

;; # Ligado con let
;; Se crea un contexto léxico en el que se pueden definir símbolos/variables
;; inmutables cuyo alcance sólo es el del let.
(let [lenguaje "Java"]
  (println lenguaje))

(println lenguaje)

;; Sumando valores
(let [x 1
      y 2
      z "El resultado de x + y es "]
  (str z (+ x y)))

;; volumen cilindro
(let [r  5
      r2 (* r r)
      h  10.2]
  (* pi r2 h))

;; ## Ejercicio
;; Calcula alguna las raíces del polinomio 2x^2 + 3x + 1 usando let y la
;; fórmula general para resolver ecuaciones de segundo grado
;; Salida -> "X1 = _ , X2 = _"


;; # Destructuring
;; A través de la sintaxis se puede acceder a las partes de una estructura
;; Sin destructuring
(let [v       [2 5 8 15]
      primero (first v)
      tercero (nth v 2)]
    (+ primero tercero))

;; Con destructuring
(let [[primero _ tercero] [2 5 8 15]]
    (+ primero tercero))

;; Sin destructuring
(def persona
    {:edad       33
     :nombre     "Joe"
     :apellido-p "Smith"
     :dirección  "Av. Remedios #34 ..."})

(let [nombre     (:nombre persona)
      apellido-p (:apellido-p persona)
      apellido-m (:apellido-m persona)]
    (str nombre " " apellido-p " " apellido-m))

;; Con Destructuring
(let [{nombre     :nombre
       apellido-p :apellido-p
       apellido-m :apellido-m} persona
      nombre-completo          (str nombre " " apellido-p " " apellido-m)]
    nombre-completo)

;; Otro sabor
(let [{:keys [nombre
              apellido-p
              apellido-m]} persona
      nombre-completo      (str nombre " " apellido-p " " apellido-m)]
    nombre-completo)


;; Otros ejemplos con destructuring
(let [[a b c] (range 10)]
  (println "a b c are:" a b c))

(let [[a b c & more] (range 10)]
 (println "a b c are:" a b c)
 (println "more is:" more))

(let [[a b c & more :as all] (range 10)]
  (println "a b c are:" a b c)
  (println "more is:" more)
  (println "All" all))

;; ## estructuras anidadas.
;; ### Mapas
(def multiplayer-game-state
  {:joe {:class "Ranger"
         :weapon "Longbow"
         :score 100}
   :jane {:class "Knight"
          :weapon "Greatsword"
          :score 140}
   :ryan {:class "Wizard"
          :weapon "Mystic Staff"
          :score 150}})

(let [{{:keys [class weapon]} :joe} multiplayer-game-state]
  (println "Joe is a" class "wielding a" weapon))


;; ### Vectores
;; Niveles arbitrarios de anidamiento
(def my-line [[5 10] [10 20]])

(let [[[x1 y1][x2 y2]] my-line]
  (println "Line from (" x1 "," y1 ") to (" x2 ", " y2 ")")

  (let [[[a b :as group1] [c d :as group2]] my-line]
    (println a b group1)
    (println c d group2)))

;; usando un default para valores que no están
(let [{:keys [título
              nombre
              apellido-p
              apellido-m]
       :or   {título "Don"}} persona
      nombre-completo           (str título " " nombre " " apellido-p " " apellido-m)]
    nombre-completo)

;; Destructuring vectores con mapas
(let [v           [2 5 8 15]
      {primero 0
       tercero 2} v]
    (+ primero tercero))


;; Ejercicios
;; Escribe en vector los días de la semana.
;; imprime el tercero y el quinto.

;; Aplica el destructuring adecuado y las operaciones necesarias para obtener
;; una comparación entre las densidades de población de esos dos países representados por un mapa.
;; El resultado debe ser una cadena
;; "La densidad de población de X (km2) es mayor a la de Y (km2)."

(def tailandia
  {:país       "Tailandia"
   :idioma     "Tailandes"
   :habitantes 65493298
   :superficie 513115
   :capital    "Bangkok"})

(def vietnam
  {:país       "Vietnam"
   :idioma     "Vietnamita"
   :habitantes 91519289
   :superficie 331210
   :capital    "Hanoi"})

;; # Espacios de nombres
;; Juntan vars y funciones relacionadas.

;; ## Bibliotecas
;; La biblioteca estándar de clojure es `clojure.core`.
;; Tiene funcionalidades muy básicas, pero hay muchas Bibliotecas que pueden ser
;; agregadas a un proyecto .
(clojure.repl/dir clojure.core)

;; ## Creando un espacio de nombres
;;(ns mi.espacio)
;; Esta forma de crear un espacio de nombres se utliza más en archivos e incluye
;; `clojure.core` y funciones de java de `java.lang`

;; todo lo que se crea dentro de ese espacio de nombres queda asociado a él
;; para poderse usar dentro de otros espacios de nombres se tiene que importar
;; el espacio de nombres
(println *ns*)

;; ## Otra manera de crear espacios de nombres (REPL)
;; (in-ns 'espacio.de.nombres)
;; En este caso no se agregan las funciones del core.

;; ## Cargar otros espacios de nombres
;; ### require (https://clojuredocs.org/clojure.core/require)
;; Función más usada.
;; Carga los espacios de nombres que no están cargados
;; Modificadores:
;; - :as -> alias
;; - :refer -> toma una lista de símbolos o el keyword :all
;; - :reload, reload-all, :verbose
(require '[clojure.string])

(clojure.string/replace "vueno vroma vien" "v" "b")

(require '[clojure.string :as st :refer [blank?]])
(blank? " ")

(clojure.string/replace "vueno vroma vien" "v" "b")
(st/replace "vueno vroma vien" "v" "b")

;; ### use (https://clojuredocs.org/clojure.core/use)
;; Lo mismo que require pero :
;; - Tiene los modificadores :only y :exclude
;; - Sin modificadores, agrega todos los símbolos públicos del espacio de nombres.
;;  require sólo carga el espacio de nombres

(use '[clojure.string :only [replace-first]])

(replace-first "vueno vroma vien" "v" "b")

;; ## Omisión de nombres
(use '[clojure.set :exclude [intersection]])

;; Recomendaciones:
;; - No se usar `use` sin modificadores.
;; - Conservar el nombre calificado (con o sin alias) de las funciones externas
;; Esto para evitar colisiones de nombres.

;; Importartar funciones de java
;; Por omisión siempre se cargan las de java.lang
(import java.util.Date)
(def ahoritita (Date.))
(str ahoritita)

;; generalmente cada ns (a.b.c.....x.e) obedece a un archivo con el nombre e.clj ubicado en el path a.b.c...x

;; En mi_espacio.clj se encuentran ejemplos de estas funciones directamente usadas al crear
;; el espacio de nombres (forma tradicional).

;; Ejercicio
;; Crea un nuevo archivo, utiliza ns y sus directivas (como en mi_espacio.clj)
;; para crear un espacio de nombres en el que :
;; - Se excluya `defstruct` de `clojure.core`.
;; - Se pueda usar todo lo de `clojure.set` y `clojure.xml` sin calificación de nombre.
;; - Se puedan usar sólo las funciones `are` e `is` de `clojure.test` sin calificación de nombre.
;; - Se cargue el espacio de nombres `clojure.zip` con el alias `z`.
;; - Se importen las clase de Java `java.util.Date` y `java.io.File`
