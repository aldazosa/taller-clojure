(ns taller.interop)

;; Clojure esta construido encima de la JVM (aunque no exclusivamente; CLR y JS)
;; por lo que se pueden aprovechar las clases de Java, como ya se ha podido observar.

;;;;;;;;;;;;;;;
;; Estáticos ;;
;;;;;;;;;;;;;;;

;;;;;;;;;;;;;;;;
;; Propiedades

java.lang.Math/E
;; => 2.718281828459045

;; Todas las clases de java.lang (preferido)
Math/E
;; => 2.718281828459045

Integer/MAX_VALUE
;; => 2147483647

Character/UPPERCASE_LETTER
;; => 1

;; Si no son de java.lang es necesario el nombre completo
java.util.Locale/JAPAN
;; => #object[java.util.Locale 0x4449c8a "ja_JP"]

;; o haberlas importado (preferido)
(import java.util.Locale)
Locale/JAPAN

;;;;;;;;;;;;
;; Métodos

(Math/sqrt 9)
;; => 3.0

(Locale/getDefault)
;; => #object[java.util.Locale 0x18e412aa "en_US"]

;;;;;;;;;;;;;;;;;;;
;; Constructores ;;
;;;;;;;;;;;;;;;;;;;

;; Creando instancias de clases de java

;; (new clase params*)
(new java.util.Date 1559246400000)
;; => #inst "2019-05-30T20:00:00.000-00:00"

;; Utilzando el operador punto (preferido)
;; (clase. params*)
(java.util.Date. 1559246400000)
;; => #inst "2019-05-30T20:00:00.000-00:00"

(Integer. "33")
;; => 33

;;;;;;;;;;;;;
;; Métodos ;;
;;;;;;;;;;;;;

;; Usando los métodos propios de las instancias

;; Con el operador punto .
;; (.método instancia params*)

(let [fecha-1 (java.util.Date. 1559246400000) ;; #inst "2019-05-30T20:00:00.000-00:00"
      fecha-2 (java.util.Date. 1559863230000) ;; #inst "2019-06-06T23:20:30.000-00:00"
      ]
  (.after fecha-1 fecha-2)) ;; fecha-1.after(fecha-2)
;; => false

(.endsWith (.toString (java.util.Date.)) "2010")
;=> true

;; Cuando la cadena de llamadas a métodos es muy grande, se dificulta la lectura.
;; Por lo que Clojure provee de un macro (..) que permite ordenar las llamadas de forma
;; secuencial

(.. (java.util.Date.) toString (endsWith "2010"))

;; Podemos observar que los métodos ya no necesitan el operador punto.


;; Ejercicio.
;; Convierte el siguiente código de java a Clojure.
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; java.util.HashMap props = new java.util.HashMap(); ;;
;; props.put("HOME", "/home/me");                     ;;
;; props.put("SRC", "src");                           ;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
