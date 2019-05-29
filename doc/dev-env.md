# Pasos para instalar y configurar _Atom_ para _Clojure_
Las instrucciones a que se dan a continuación son para Linux.

## Requisitos
* __Java__: La instalación puede hacerse desde un gestor de paquetes. Por ejemplo en debian o ubuntu:

        #  apt install openjdk-8-jre

* [__Leinigen__](https://leiningen.org/#install):

        $ wget https://raw.githubusercontent.com/technomancy/leiningen/stable/bin/lein
        # mv lein /usr/local/bin/lein
        # chmod a+x /usr/local/bin/lein
        $ lein

## Instalar Atom y paquetes
* Descarga el paquete que se acomode mejor a tu distribución [del sitio de atom](https://atom.io/)
* El paquete tiene las dependencias `git`, `libcurl3 | libcurl4`. Instalarlas si no están instaladas.

        # apt install git libcurl3
* Instalar el paquete descargado. Ejemplo para `.deb`:

        # dpkg -i atom-amd64.deb

* Instalar paquetes de atom:

        $ apm install rainbow-delimiters proto-repl ink linter-joker linter linter-ui-default intentions busy-signal

* Abrir atom:

        $ atom .

## Configuración de atom
¿Pretty Print?
* Agregar el path de lein. `Edit > Preferences (Ctrl+,) > Packages > proto-repl (Settings)` Si lo pusieron en su path, esto no debe ser necesario

## Test de configuración
* Crear un proyecto de prueba con la plantilla **app**

        $ lein new app proyecto

* Abrir el proyecto en Atom
* Agregar `proto-repl` como dependencia al proyecto. En `project.clj`:

        :dependencies [[org.clojure/clojure "1.10.0"]
                       [proto-repl "0.3.1"]]


* Abrir un repl en atom:  `Packages > proto-repl > Start REPL`
* Escribir:

        (+ 1 1)
en el REPL, luego `Shift + Enter`. Debe aparecer el número `2`.

## Shorcuts útiles
* Evaluar bloque _in-line_: `ctrl, + b`
* compilar archivo de nuevo `ctrl, + f`
* Documentación de función `ctrl, + d`
* Más `Edit > Preferences > Keybindings`
