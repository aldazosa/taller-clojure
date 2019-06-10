(ns taller.flujo)

;; https://clojure.org/guides/learn/flow
;; Las expresiones de flujo en clojure (if's, case's, etc) son,
;; como todo en clojure, expresiones. Eso significa que se evalúan
;; a un valor. Al contrario de flujo en java o C, que solo ejecuta código

;;;;;;;;
;; if ;;
;;;;;;;;

;; `if` se puede pensar como una función que recibe de tres argumentos;
;; `(if cond then else)`. Si `cond` se evalúa a verdadero, entonces la
;; expresión se evaluará a `then`. En otro caso, se devolverá `else`

(str "2 es " (if (even? 2) "par" "impar"))
;; => "2 es par"

(if (true? false) "imposible!") ;; => nil
 ;; else es opcional

;; Es importante notar que `if` solo puede recibir una simple expresión
;; en sus argumentos `then` y `else`. Si se necesita evaluar más de una
;; expresión (realizar efectos secundarios) se puede usar `do` para combinar
;; más de una expresión:

(if (even? 5)
  (do
    (println "Par")
    true)
  (do
    (println "Impar")
    false))

;;;;;;;;;;
;; when ;;
;;;;;;;;;;

;; `when` es como un `if` con solo la rama `then`.
;; Puede tener cualquier cantidad de expresiones en su cuerpo y todas
;; se realizarán sii la condición del when es verdadera.
;; La expresión completa se evaluará a la última expresión en el cuerpo.

(when (neg? -5)
  (println "Valor negativo inválido")
  0) ;; => 0

;; `when` expresa al lector del código que NO hay una rama en el flujo
;; para el caso falso de la condición

;;;;;;;;;;
;; cond ;;
;;;;;;;;;;

;; `cond` es una serie de condiciones y expresiones. Las condiciones se
;; evalúan en órden y se evalúa y regresa la expresión de la primera
;; condición verdadera

(let [x 5]
  (cond
    (< x 2)  "x es menor que 2"
    (< x 10) "x es menor que 10"
    (< x 15) "x es menor que 15"))
;; => "x is less than 10"

;; Si ninguna condición es verdadera, la expresión se evaluará a `nil`.
;; Es convención en clojure usar la keyword `:else` como última condición
;; al especificar la expresión para el caso en que no haya condición verdadera.

(let [x 11]
  (cond
    (< x 2)  "x es menor que 2"
    (< x 10) "x es menor que 10"
    :else    "x es mayor o igual a 10"))
;; => "x es mayor o igual a 10"

;; Ejercicio 1.
;; Para todo dato ordenable, es posible derivar todas las operaciones
;;  básicas (<, <=, ==, >=, >) con solo la operación para menor que.

;; La función `compara` recibe tres argumentos: `menor-que` una función
;; que define la operación `<` para cierto órden de algún tipo de datos
;; y dos valores a comparar: A y B

;; Acompleta la función para que:
;; - Si A < B, regrese la llave `:menor`
;; - Si A > B, regrese `:mayor`
;; - Si A = B, regrese `:igual`

(defn compara
  [menor-que A B]
  ;; escribe tu código aquí
  )

;; Todas estas expresiones deberían de evaluarse a verdad
(= :mayor (compara < 5 1))
(= :igual (compara (fn [x y] (< (count x) (count y)))
                   "pear"
                   "plum"))
(= :mayor (compara > 0 2))

;;;;;;;;;;
;; case ;;
;;;;;;;;;;

;; `case` compara un argumento a una serie de valores. Cada valor debe
;; de ser una literal (número, cadena, keyword, etc)

(case 10
  5  "x es 5"
  10 "x es 10"
  15 "x es 15")
;; => "x es 10"

;; A diferencia de `cond`, `case` lanzará una excepción si ningún caso
;; es igual al argumento

;; (case 1
;;   5 "x es 5"
;;   10 "x es 10"
;;   15 "x es 15")
;; => IllegalArgumentException No matching clause: 1

;; Se puede agregar una expresión al final para ser evaluada si no hay
;; algún caso igual al argumento

(case 1
  5  "x es 5"
  10 "x es 10"
  "x no es ni 5 ni 15")
;; => "x no es ni 5 ni 15"

;;;;;;;;;
;; for ;;
;;;;;;;;;

;; La expresión `for` nos permite iterar una secuencia y evaluar
;; varias expresiones por cada uno de los valores de la secuencia

;; Igual que `if`, `for` también se evalúa a un valor. Va a construir
;; una secuencia con los valores resultantes de evaluar el cuerpo en
;; cada uno de los elementos de la secuencia.

(for [n [7 4 6 1]]
  {:número n
   :doble (* n 2)})
;; => ({:número 7, :doble 14}
;;     {:número 4, :doble 8}
;;     {:número 6, :doble 12}
;;     {:número 1, :doble 2})

;; El primer argumento de `for` es un vector de ligados.
;; Se liga un nombre a la secuencia. Ese nombre representa a cada
;; elemento en la secuencia dentro del cuerpo del for.

;; Es posible ligar a más de una secuencia. Esto ocasionará que
;; se calcule el cuerpo del `for` para cada combinación de los elementos
;; de las secuencias ligadas:

(for [x [:a :b :c]
      y [1 2]]
  {:x x :y y})
;; => ({:x :a, :y 1}
;;     {:x :a, :y 2}
;;     {:x :b, :y 1}
;;     {:x :b, :y 2}
;;     {:x :c, :y 1}
;;     {:x :c, :y 2})

;; `doseq` es una función parecida a `for`, pero esta desecha la evaluación
;; de las expresiones (regresa `nil`) su intención es llevar a cabo efectos
;; secundarios por cada elemento en una secuencia.
(doseq [n (range 1 10)]
  (println "El doble de" n "es" (* n 2))) ;; => nil

;; Ejercicio 2.  http://www.4clojure.com/problem/59
;; Completa la función `aplica-fns` que reciba un número variable de
;; funciones `fns` y regrese otra función que reciba un número variable
;; de valores `xs` y aplique cada una de las `fns` a las `xs`, y regrese
;; los resultados en cada función en un vector

(defn aplica-fns
  [& fns]
  (fn [& xs]
    ;; escribe tu código aquí
    ))

;; Todas estas expresiones deben evaluarse a verdad
(= [21 6 1] ((aplica-fns + max min) 2 3 5 1 6 4))
(= ["HELLO" 5] ((aplica-fns #(.toUpperCase %) count) "hello"))
(= [2 6 4] ((aplica-fns :a :c :b) {:a 2, :b 4, :c 6, :d 8 :e 10}))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Recursión (loop y recur) ;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; `loop` y `recur` se usan en conjunto para definir una expresión cuya
;; evaluación requiere re-evaluar la misma expresión con distintos
;; argumentos.
;; `loop` define los ligados de los parámetros y valores iniciales
;; `recur` re ejecuta `loop` con nuevos parámetros

;; Calcula 2^8
(loop [base      2
       exponente 1]
  (if (<= 8 exponente)
    base
    (recur (* base 2) (inc exponente))))
;; => 256

;; Suma todos los elementos en `lista`
(loop [suma 0
       lista [7 4 5 7 98 1 3 74]]
  (if (empty? lista)
    suma
    (recur (+ (first lista) suma)
           (rest lista))))
;; => 199

;; Es importante notar que `recur` necesita ser llamado como la última
;; operación de la expresión (la llamada más externa).
;; De esta manera se garantiza la ejecución de recursión de cola y no
;; consume stack de memoria

;; También se puede usar `recur` dentro de la definición de una
;; función para volver a llamar esa función
(defn suma-todos
  [lista]
  (let [suma-todos* (fn [lista res]
                      (if (empty? lista)
                        res
                        (recur (rest lista)
                               (+ res (first lista)))))]
    (suma-todos* lista 0)))

(suma-todos [1 2 3 4 5 6 7 8 9 10]) ;; => 55

;; Se recomienda que se usen funciones de más alto nivel
;; (`map`, `reduce`, `apply`) en vez de usar `loop` y `recur` directamente
;; para facilitar la lectura y depuración del código.

;; `loop` y `recur` son funciones de muy bajo nivel abstracto y solo se
;; recomienda usarlas cuando las funciones de más alto nivel no puedan
;; ser usadas para el caso que se esté trabajando, por ejemplo cuando
;; se necesite optimizar la recursión con recursión de cola

;; Ejercicio 3.
;; Implementa la función factorial usando `recur`
