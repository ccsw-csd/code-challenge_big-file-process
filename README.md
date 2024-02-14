# Code Challenge - Big File Process

Se trata de un 'code challenge' para obtener el algoritmo Java que sea capaz de leer y procesar un fichero de texto enorme en el menor tiempo posible. El fichero tiene más de mil millones de líneas de información y ocupa más de 40 Gb.


## Ejecuciones

| #  | Time (min) |    User    |    Date   | Description                |
|----|------------|------------|-----------|----------------------------|
| 1  | 03:02:450  | amirzoya   |   12/feb  | Uso de Files.getLines, parallel, collectors y evitar estructuras de datos |
| 2  | 03:02:535  | cvelafor   |   13/feb  | Uso de Files.getLines, parallel y collectors. Ampliando el parallel. |
| 3  | 03:17:651  | pajimene   |   12/feb  | Uso de Files.getLines, parallel y synchronized. Cambio a datos primitivos |
| 4  | 05:12:231  | *          |   09/feb  | Línea base |


## Requisitos

### Requisitos Funcionales

El algoritmo deberá leer la información de un fichero de texto plano y procesarla para obtener unos resultados que más adelante comentaremos.

Cada línea (separada por salto de línea) representa una fila de información y dentro de cada fila cada uno de los datos se separa con un ;

Un ejemplo de la estructura de la fila es la siguiente:

```
Granada;Hombre;Jose;Alzate;Álvarez;9
Huesca;Hombre;Rocco;Lafuente;Paez;6
Barcelona;Mujer;Candela;Saez;Luna;6
Zaragoza;Hombre;Jose;Echeverria;Sánchez;5
Melilla;Mujer;Gianna;Valentín;Torre;4
Huelva;Hombre;Rocco;Parra;Natales;7
València;Mujer;Eluney;Quesada;Molleda;5
```

- El primer dato es el nombre de la provincia. En total hay 52 diferentes valores.
- El segundo dato es el sexo de la persona. Posibles valores -> Mujer / Hombre.
- El tercer dato es el nombre de la persona. En total hay 229 diferentes nombres.
- El cuarto dato es el primer apellido de la persona.
- El quinto dato es el segundo apellido de la persona.
- El sexto dato es el número de personas con ese nombre y apellidos dentro de la provincia. Será un número de una cifra, de 1-9 ambos incluídos.

El algoritmo deberá leer todos esos datos y extraer la siguiente información:

- El valor más alto obtenido comparando todos los nombre de la mujer más repetida en cada provincia.
- El valor más alto obtenido comparando todos los nombres del hombre más repetido en cada provincia.
- El valor más bajo obtenido comparando todos los nombres de la mujer menos repetida en cada provincia.
- El valor más bajo obtenido comparando todos los nombres del nombre del hombre menos repetido en cada provincia.

En el ejemplo de arriba, los resultados deberían ser:

- La mujer mas repetida esta en 'Barcelona', se llama 'Candela' y aparece '5' veces
- La mujer menos repetida esta en 'Melilla', se llama 'Gianna' y aparece '4' veces
- El hombre mas repetido esta en 'Granada', se llama 'Jose' y aparece '9' veces
- El hombre menos repetido esta en 'Zaragoza', se llama 'Jose' y aparece '5' veces



### Requisitos Técnicos

Todo el código del algoritmo debe estar incluido en un único fichero llamado "BigFileProcessCodeChallenge.java" y se debe respetar el método main del archivo original.

No se podrá utilizar librerías externas que NO formen parte del package de java. Es recomendable utilizar las últimas versiones de Java, pero cada cual puede utilizar la que quiera.

Como ayuda al ejercicio se proveerá un algoritmo "línea base" que funciona correctamente y también estarán accesibles todas aquellas soluciones presentadas por los participantes.


## Ejecución

Para obtener un juego de datos válido tienes tres archivos .jar que acompañan a este repositorio git. El comando para ejecutar es

```
java -jar generateData.jar
java -jar generateMediumData.jar
java -jar generateBigData.jar
```

**Mucho OJO** con los tamaños del fichero resultante: 

- generateData.jar --> Genera un fichero "data.txt" de unas 250 mil líneas y 100 Mb de tamaño
- generateMediumData.jar --> Genera un fichero "data.txt" de unas 40 millones de líneas y 1,5 Gb de tamaño
- generateBigData.jar --> Tardará unos 5 minutos en generar un fichero "data.txt" de 1.000 millones de líneas y 42 Gb Mb de tamaño. **Mucho OJO**

Escoge el fichero que más te guste para probar tu solución.


Para ejecutar el algoritmo, se acompaña en este repositorio de un proyecto maven que permite la ejecución de la clase principal. Simplemente modifica la clase java del ejemplo de "línea base" o coge como base alguna de las soluciones propuestas y mejora el algoritmo.

A partir de ahí, eres libre de hacer todas las modificaciones que quieras, siempre que cumpla los requisitos funcionales. **Compara siempre tu resultado con la línea base**, así te harás una idea de si es mejor o peor el rendimiento.


## Evaluación

Una vez tengas el algoritmo resuelto, envía tu código fuente al correo &#112;&#97;&#98;&#108;&#111;&#46;&#106;&#105;&#109;&#101;&#110;&#101;&#122;&#45;&#109;&#97;&#114;&#116;&#105;&#110;&#101;&#122;&#64;&#99;&#97;&#112;&#103;&#101;&#109;&#105;&#110;&#105;&#46;&#99;&#111;&#109; (la clase implementada) para que pueda ejecutarla en local.

La ejecución se hará siempre en el mismo entorno controlado, un intel i7 de 6 cores y 32 Gb de RAM, con jdk 21.0.1 oficial de Oracle. Se ejecutará varias veces y se calculará la media de tiempos.

El fichero de prueba será el mismo para todas las ejecuciones y cuenta con un total de 1.066.854.351 líneas y un tamaño de 42 Gb.


