(ns taller.coder)

;; https://github.com/gigasquid/wonderland-clojure-katas/tree/master/alphabet-cipher

;; Implementar un cifrado de sustitución alfabética usando una contraseña

;; Primero construye una tabla de sustitución como esta, onde cada fila
;; del alfabeto está rotada una posición a la derecha más que la fila
;; anterior

 ;;   ABCDEFGHIJKLMNOPQRSTUVWXYZ
 ;; A abcdefghijklmnopqrstuvwxyz
 ;; B bcdefghijklmnopqrstuvwxyza
 ;; C cdefghijklmnopqrstuvwxyzab
 ;; D defghijklmnopqrstuvwxyzabc
 ;; E efghijklmnopqrstuvwxyzabcd
 ;; F fghijklmnopqrstuvwxyzabcde
 ;; G ghijklmnopqrstuvwxyzabcdef
 ;; H hijklmnopqrstuvwxyzabcdefg
 ;; I ijklmnopqrstuvwxyzabcdefgh
 ;; J jklmnopqrstuvwxyzabcdefghi
 ;; K klmnopqrstuvwxyzabcdefghij
 ;; L lmnopqrstuvwxyzabcdefghijk
 ;; M mnopqrstuvwxyzabcdefghijkl
 ;; N nopqrstuvwxyzabcdefghijklm
 ;; O opqrstuvwxyzabcdefghijklmn
 ;; P pqrstuvwxyzabcdefghijklmno
 ;; Q qrstuvwxyzabcdefghijklmnop
 ;; R rstuvwxyzabcdefghijklmnopq
 ;; S stuvwxyzabcdefghijklmnopqr
 ;; T tuvwxyzabcdefghijklmnopqrs
 ;; U uvwxyzabcdefghijklmnopqrst
 ;; V vwxyzabcdefghijklmnopqrstu
 ;; W wxyzabcdefghijklmnopqrstuv
 ;; X xyzabcdefghijklmnopqrstuvw
 ;; Y yzabcdefghijklmnopqrstuvwx
 ;; Z zabcdefghijklmnopqrstuvwxy

;; Los dos miembros de la comunicación deciden en una contraseña,
;; por ejemplo `scones`

;; Para cifrar el mensaje, primero lo escribes:
;; `meetmebythetree`

;; Después escribes la contraseña, repetida tantas veces sean necesarias
;; para cubrar cada letra del mensaje
;; `sconessconessco`
;; `meetmebythetree`

;; Para reemplazar cada letra, buscas la `columna` de la letra del mensaje original
;; y la `fila` de la letra en la contraseña. La letra del mensaje cifrado será el
;; valor en la tabla

;; `sconessconessco`
;; `meetmebythetree`
;; `egsgqwtahuiljgs`

;; Para descigrar, el recipiente usa la misma contraseña y hace el
;; proceso contrario

;; Instrucciones
;; 1. Completa las tres funciones de abajo para que realicen lo que su
;;    docstring dice
;; 2. Corre las pruebas (corriendo `lein test` en la consola) y que
;;    todas pasen

;; Las pruebas están definidas en `taller-clojure/test/taller/coder_test.clj`

(defn encode
  "Cifra `message` usando `keyword`."
  [keyword message]
  "encodeme")

(defn decode
  "Descifra `message` usando `keyword`."
  [keyword message]
  "decodeme")

(defn decipher
  "Considerando el mensaje cifrado `cipher` y el mensaje original
  `mesage` deduce la contraseña usada."
  [cipher message]
  "decypherme")
