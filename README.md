# Detector de Mutantes
[![Build Status](https://travis-ci.org/grisoliarodrigo/meli-challenge-mutants.svg?branch=master)](https://travis-ci.org/grisoliarodrigo/meli-challenge-mutants) [![Coverage Status](https://coveralls.io/repos/github/grisoliarodrigo/meli-challenge-mutants/badge.svg)](https://coveralls.io/github/grisoliarodrigo/meli-challenge-mutants)

API Rest cuya principal funcionalidad consiste en detectar si un humano es mutante en función de su ADN. 

_________________________________________________

## Instrucciones de uso

La API consta de dos servicios:

### Servicio Mutant: 

Recibe como parámetro una secuencia de ADN, representada por un array de strings, y responde si la misma pertenece, o no, a un mutante. Se almacena la secuencia consultada en una base de datos, con el propósito de obtener estadísticas. 

- **URL:** http://mutants.us-west-2.elasticbeanstalk.com/mutant

- **Method:** POST

- **Body Params:** JSON representando una secuencia de ADN en el siguiente formato:

  ```javascript
  {"dna": [[string], [string], [string], ...]}
  ```
  Ejemplo:

  ```javascript
  {"dna":["ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"]}
  ```
  Cada string debe tener la misma longitud que el array al que pertenece. La secuencia solo puede contener los siguentes caracteres; A, T, C, G.
 
 - **Posibles respuestas:** 
  
    - 200: El humano es mutante. 
    - 403: El humano no es mutante.
    - 400: Input incorrecto. 
    

### Servicio Stats:

Devuelve estadísticas en base a las secuencias de ADN que han sido consultadas. 

- **URL:** http://mutants.us-west-2.elasticbeanstalk.com/stats

- **Method:** GET

- **Respuesta:** 

  ```javascript
  {
    "count_mutant_dna": [number],
    "count_human_dna": [number],
    "ratio": [number]
  }
  ```
_________________________________________________
