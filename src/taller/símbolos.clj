(ns taller.símbolos)

;; ¡CUIDADO! Este archivo incluye expresiones que lanzan excepciones,
;; por lo que no se puede cargar por completo de forma inmediata.

;;;;;;;;;;
;; Vars ;;
;;;;;;;;;;

;; # Definición
;; Se utiliza `def`.
;; (def símbolo doc-string? init?)
(def v-simple)
;; => #'taller.símbolos/v-simple

(clojure.repl/doc v-simple)

(def v-completa "Un bonito doc-string" 0)
;; => #'taller.símbolos/v-completa

(clojure.repl/doc v-completa)

;; Nombre completo de Vars incluye el espacio de nombre. Nombre calificado.
;; El valor asociado de un Vars puede ser de cualquier tipo, incluso funciones:
;; * Cadena
(def lenguaje "Clojure")
;; => #'taller.símbolos/lenguaje

;; * Colección
(def carácterísticas ["funcional" "hosted"])
;; => #'taller.símbolos/carácterísticas

;; * Numérico
(def pi 3.141592653589793)
;; => #'taller.símbolos/pi

;; * Función. def + función anónima =  defn
(def área (fn [radio] (* pi radio radio)))
;; => #'taller.símbolos/área

área
;; => #object[taller.símbolos$área 0x7062a3a4 "taller.símbolos$área@7062a3a4"]

(área 3)
;; => 28.274333882308138

;; En clojure todas las expresiones regresan un valor. En el caso de `def`
;; el valor que se está regresando es el objeto Var como tal.
(type (def q 1))
;; => clojure.lang.Var

;; # Redefinición de vars.
(def lenguaje "ClojureScript")

(println lenguaje)

;; Los nombres de los Vars también puede ser símbolos propios de clojure.
(def + "Sumando")
;; => #'taller.símbolos/+

(+ 2 3) ;; Error
(println +)

;; Lo regresamos a su definición original
(def + clojure.core/+)
;; => #'taller.símbolos/+

;; ¿Qué alcance tendría una definición dentro de un contexto local
;; como una función o un let?
(defn mi-fn []
  (def a 111))
;; => #'taller.símbolos/mi-fn

(mi-fn)
;; => #'taller.símbolos/a

;;;;;;;;;
;; Let ;;
;;;;;;;;;

;; Ahora, ¿Cómo puedo definir una variable local?
;; Uso let:
;;(let [bindings*] exprs*), dónde:
;; binding -> nombre valor
;; El valor del let es el valor de la última expresión.


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
;; => 801.1061266653973

;; ## Ejercicios
;; * Local bindings http://www.4clojure.com/problem/35
;; * Let it Be http://www.4clojure.com/problem/36
;; * Calcula las raíces del polinomio 2x^2 + 3x + 1 usando let y la
;;   fórmula general para resolver ecuaciones de segundo grado
;;   Salida -> "X1 = _ , X2 = _"

;;;;;;;;;;;;;;;;;;;
;; Destructuring ;;
;;;;;;;;;;;;;;;;;;;

;; La forma de las estructuras puede llegar a ser muy compleja y por lo general
;; una función sólo interactúa con una sección de ella.

;; Destructuring provee una manera de manipular sólo ciertas partes de una estructura.
;; Estos son los tipos de destructuring que hay

;; 1. Destructuring posicional (vectores y secuencias)
(let [[primero _ tercero] [2 5 8 15]]
  (+ primero tercero))

;; Nota: el `_` es un símbolo, pero usado en el contexto de destructuring tiene la
;; convención de un elemento que ocupa un lugar, pero no interesa.


;; ¿Que pasaría si el primer elemento fuera una secuencia y necesitara el segundo de esa secuencia?
;; El destructuring permite anidamiento sin límite de niveles.
(def mi-línea [[5 10] [10 20]])

(let [[[x1 y1][x2 y2]] mi-línea]
  (println "Línea de (" x1 "," y1 ") a (" x2 ", " y2 ")"))

;; Además de acceder a posiciones particulares se puede acceder al resto y al todo
(let [[a b c & resto] (range 10)]
 (println "a b c son:" a b c)
 (println "el resto es:" resto))

(let [[a b c & resto :as todo] (range 10)]
  (println "a b c son:" a b c)
  (println "el resto es:" resto)
  (println "Todo: " todo))

;; 2. Destructuring asociativo (mapas)
(def persona
    {:edad       33
     :nombre     "Joe"
     :apellido-p "Smith"
     :dirección  "Av. Remedios #34 ..."})

;; Ejemplo: Obtén una cadena con el nombre completo de la persona:

;; Sin destructuring
(let [nombre     (:nombre persona)
      apellido-p (:apellido-p persona)
      apellido-m (:apellido-m persona)]
    (str nombre " " apellido-p " " apellido-m))
;; => "Joe Smith "

;; Con Destructuring
(let [{nombre     :nombre
       apellido-p :apellido-p
       apellido-m :apellido-m} persona
      nombre-completo          (str nombre " " apellido-p " " apellido-m)]
    nombre-completo)
;; => "Joe Smith "

;; `:keys` provee otra manera de hacer el destructuring
(let [{:keys [nombre
              apellido-p
              apellido-m]} persona
      nombre-completo      (str nombre " " apellido-p " " apellido-m)]
    nombre-completo)
;; => "Joe Smith "

;; También en este tipo de destructuring se permite el anidamiento
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
;; => #'taller.símbolos/multiplayer-game-state

(let [{{:keys [class weapon]} :joe} multiplayer-game-state]
  (str "Joe es un " class " empuñando un " weapon))
;; => "Joe es un Ranger empuñando un Longbow"


;; usando un default para valores que no están
(let [{:keys [título
              nombre
              apellido-p
              apellido-m]
       :or   {título "Don"}} persona
      nombre-completo           (str título " " nombre " " apellido-p " " apellido-m)]
    nombre-completo)
;; => "Don Joe Smith "

;; 3. Destructuring posicional con mapas
(let [v           [2 5 8 15]
      {primero 0
       tercero 2} v]
    (+ primero tercero))
;; => 10

;; ## Destructuring en funciones.
;; Si la estructura con la que se tiene que trabajar es pasada como parámetro a una
;; función, como lo hemos visto hasta ahora, tendríamos que crear un let dentro de la función
;; para segmentarla y trabajar sólo con lo que necesitamos.
;; Pero Clojure nos evita esa fatiga, se puede hacer destructuring desde la declaración de los
;; parámetros de la función.

(defn nombre-completo [{:keys [nombre
                               apellido-p
                               apellido-m]}]
  (str nombre " " apellido-p " " apellido-m))
;; => #'taller.símbolos/nombre-completo

(nombre-completo persona)
;; => "Joe Smith "

;; ## Ejercicio
;; Aplica el destructuring  y las operaciones necesarias para obtener
;; una comparación entre las densidades de población de esos dos países
;; representados por un mapa.
;; El resultado debe ser una cadena:
;; "La densidad de población de X (km2) es mayor a la de Y (km2)."
;; X y Y son los nombres correspondientes de los paises
;; km2 es la densidad de población correspondiente a cada país.
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

;;;;;;;;;;;;;;;;;;;;;;;;;
;; Espacios de nombres ;;
;;;;;;;;;;;;;;;;;;;;;;;;;

;; Un espacio de nombres junta símbolos y funciones relacionados.

;; El primer espacio de nombres con el que se tiene contacto es `user`.
;; Ya que por omisión es dónde arranca el REPL.

;; La variable `*ns*` guarda el espacio de nombres actual.

;; # El Core
;; La biblioteca estándar de clojure es `clojure.core`.
;; Lista de los símboles de ese espacio de nombres.
(clojure.repl/dir clojure.core)

;; # Creación
;; Para crear un espacio de nombres se usa `ns`
;; (ns mi.espacio)
;; Generalmente la creación de espacios de nombres se hace en cada archivo
;; en la parte superior.
;; El espacio de nombres recién creado ya incluye `clojure.core` y funciones de java de `java.lang`.

;; Por requisitos de Java el nombre de los archivos y los espacios de nombres
;; están relacionados, ejemplo:
;; foo.bar/este-es-mi-nuevo-espacio debe declararse en el archivo foo/bar/este_es_mi_nuevo_espacio.clj

;; También se pueden crear espacios de nombres con la función create-ns
;; y esta se usa más comunmente en el REPL.

;; # Navegación
;; Para moverse de espacio de nombres se puede hacer con:
;; (in-ns 'espacio.de.nombres)
;; Si el espacio de nombres no existe, se crea pero no se agregan las funciones del core.

(in-ns 'taller.otro-ns)
(def lenguaje "PHP")
(println lenguaje) ;;error ya que no están las funciones del core.
lenguaje

(in-ns 'taller.símbolos)
lenguaje


;; Ahora, ¿cómo cargo otros espacios de nombres?

;; # Cargar otros espacios de nombres (require, use, import)
;; ## require (https://clojuredocs.org/clojure.core/require)
;; Carga los espacios de nombres que no están cargados
;; Modificadores:
;; - :as -> alias
;; - :refer -> toma una lista de símbolos o el keyword :all
;; - :reload, :reload-all, :verbose

;; carga clojure.string
(clojure.string/replace "vueno vroma vien" "v" "b") ;; error, no está cargado.

(require '[clojure.string])
(clojure.string/replace "vueno vroma vien" "v" "b")

;; carga clojure.string con el alias `st` e intengra la función blank? al espacio de nombres.
;; Si se define una función con nombre `blank?` se producirá un error, ya que
;; la función ya existe
(require '[clojure.string :as st :refer [blank?]])

(blank? " ")
;; => true

(defn blank? [s]
  false) ;; error. blank? ya existe.

;; Se puede usar el alias o el nombre completo.
(st/replace "vueno vroma vien" "v" "b")
;; => "bueno broma bien"

(clojure.string/replace "vueno vroma vien" "v" "b")
;; => "bueno broma bien"

;; En los espacios de nombres `taller.símbolos` y `taller.otro-ns` se define lenguaje
;; pero no hay colisión por que cada uno está en su espacio de nombres.
;; Si se necesitara en un mismo espacio de nombres poder acceder a ambas variables
;; se recomienda cargar el espacio de nombres con un alias.



;; ## use (https://clojuredocs.org/clojure.core/use)
;; Lo mismo que require pero :
;; - Tiene los modificadores :only y :exclude
;; - Sin modificadores, agrega todos los símbolos públicos del espacio de nombres.
;; Este comportamiento sobrecarga el espacio de nombres por lo que su uso es poco recomendado y require surgió
;; como alternativa.
;; https://grokbase.com/t/gg/clojure/137qrc7xmr/can-we-please-deprecate-the-use-directive#20130724kh5nybfrqhf2httfoefklqrghi

(in-ns 'foo.bar)
(clojure.core/use '[clojure.core])

(use '[clojure.string :only [replace-first]])

(replace-first "vueno vroma vien" "v" "b")
;; => "bueno vroma vien"

(use '[clojure.set :exclude [intersection]])

;; regresamos.
(in-ns 'taller.símbolos)

;; ## import
;; Importar funciones de java
;; Por omisión siempre se cargan las de java.lang
(import java.util.Date)

(def ahora (Date.))

(str ahora)


;; Normalmente cuando se crea el espacio de nombres con ns también se agregan
;; otros espacios de nombres. https://clojuredocs.org/clojure.core/ns

;; En mi_espacio.clj se encuentran ejemplos de estas funciones (use, require, import)
;; directamente usadas al crear el espacio de nombres (forma convencional).

;; Ejercicio
;; Crea un nuevo archivo, utiliza ns y sus directivas (como en mi_espacio.clj)
;; para crear un espacio de nombres en el que :
;; - Se pueda usar todo lo de `clojure.set` y `clojure.xml` sin calificación de nombre.
;; - Se puedan usar sólo las funciones `are` e `is` de `clojure.test` sin calificación de nombre.
;; - Se cargue el espacio de nombres `clojure.zip` con el alias `z`.
;; - Se importen las clase de Java `java.util.Date` y `java.io.File`

;; Los keywords también pueden tener un espacio de nombres (keyword calificado).
:espacio.nombre/nombre

;; Si comienzan con doble dos puntos (::) y no está calificado, se resuelve el keyword sobre el espacio actual
::nombre
;; => :taller.símbolos/nombre

;; Si comienzan con doble dos puntos (::) y esta calificado, se resuelve el keyword sobre el espacio cuyo
;; alias sea la cualificación
(require '[taller.mi-espacio :as x])

::x/doo
;; => :taller.mi-espacio/doo
