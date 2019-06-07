# Clojure

El objetivo del taller es que al finalizar los participantes conozcan las partes fundamentales del lenguaje, lo cuál les permitirá leer y escribir soluciones a problemas sencillos en clojure. De ser posible, se continuará con el taller revisando problemas del sitio 4clojure.

# Previos #

## 01. Introducción ##
[Notas](doc/introducción.md)

### ¿Por qué clojure? ###

### ¿En qué se enfoca? ###

https://clojure.org/about/rationale

### Características distintivas ###

- Excelente manipulación de datos
- Forma de desarrollo ágil
- Simplicidad
- Inmutabilidad

## 02. Ambiente de desarrollo básico ##
[Notas](doc/dev-env.md)

Instalar (en linux) los paquetes necesarios para desarrollar en clojure:

- OpenJDK
- Atom (editor)

# Fundamentales #

Explicación y ejemplos de cada uno de los elementos básicos de clojure.

## 03. Leyendo clojure ##
[Notas](src/taller/leyendo_clojure.clj)

- Sintaxis
- Evaluación
- Comentarios
  - end of line
  - comment macro
  - ignore (reader macro)

## 04. Símbolos ##
[Notas](src/taller/símbolos.clj)

  - Vars
  - let
  - destructuring
  - namespaces

## 05. Colecciones ##
[Notas](src/taller/colecciones.clj)

  - Tipos
    - Listas
    - Vectores
    - Mapas
    - Conjuntos
  - Secuencias
    - Pereza

## 06. Cadenas y caractéres ##
[Notas](src/taller/cadenas_caracteres.clj)

## 07. Booleanos ##
[Notas](src/taller/booleanos.clj)

  - Verdadero y falso
  - nil
  - nil punning

## 08. Funciones ##
[Notas](src/taller/funciones.clj)

  - Sintaxis
  - Docstring
  - Anónimas

## 09. Flujo ##
[Notas](src/taller/flujo.clj)

  - if
  - do
  - when
  - loop/recur
  - cond
  - Otros:
    - condp
    - case

## 10.Transformaciones ###
[Notas](src/taller/transformando_secuencias.clj)

- map
- reduce
- Otras

## 11. Threading macros ##
[Notas](src/taller/threading_macros.clj)

- thread-first
- thread-last

## 12. Java interop ##
[Notas](src/taller/interop.clj)

- Constructores
- Métodos
- Forma preferida
- Importando

## 13. Herramientas ##
[Notas](doc/herramientas.md)
