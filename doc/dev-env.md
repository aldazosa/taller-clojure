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
* Joker:
  1. Instalar curl y build-essential:

            # apt install curl build-essential

  2. Instalar [Homebrew](https://docs.brew.sh/Homebrew-on-Linux) (gestor de paquetes)


            $ sh -c "$(curl -fsSL https://raw.githubusercontent.com/Linuxbrew/install/master/install.sh)"
            $ test -d ~/.linuxbrew && eval $(~/.linuxbrew/bin/brew shellenv)
            $ test -d /home/linuxbrew/.linuxbrew && eval $(/home/linuxbrew/.linuxbrew/bin/brew shellenv)
            $ test -r ~/.bash_profile && echo "eval \$($(brew --prefix)/bin/brew shellenv)" >>~/.bash_profile
            $ echo "eval \$($(brew --prefix)/bin/brew shellenv)" >>~/.profile

  3. Instalar [Joker](https://github.com/candid82/joker) (interprete y analizador de Clojure)

            $ brew install candid82/brew/joker


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
* Pretty Printing: `Edit > Preferences (ctrl+,) > Packages > proto-repl (Settings) > Auto pretty printing`
* Agregar path de joker: `Edit > Preferences > Packages > linter-joker > joker path`. Para saber dónde está instalado joker en una terminal `which joker`.

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

* Compilar archivo `ctrl+, f`
* Ir a la definición de una función `ctrl+, o`
* Refrescar espacios de nombres `ctrl+, r`
* Evaluar bloque `ctrl+, b`
* Cambiar de búffer `ctrl+k ctrl+left/right`
* Clear REPL `ctrl+, k`
* Documentación de la función sobre la que está el cursor `ctrl+, d`
* Comentar línea  `ctrl+/`


Si quieres conocer más, Atom tiene un buscador de shortcuts que se puede consultar con `ctrl+shift+p`.
