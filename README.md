# Eventbrite - Homework EDA 3.O

El juego se basa en un **pensador**, que piensa un numero y un **adivinador** que intenta adivinarlo. Al adivinador se le va respondiendo la cantidad de dígitos que se encuentran en la posición correcta y en la posición incorrecta, pero que coinciden con alguno.

> Los números tienen que ser distintos entre si, no pueden haber dígitos
> repetidos.

Hay dos formas de jugar:

 1. La *computadora piensa* un numero y una persona adivina.
 2. La *persona piensa* un numero y la computadora adivina.

# Ejemplo
### Pensador elige: 1234

**Adivinador** dice: 1893
**Pensador** response:
    
	Bien => 1
	Regular => 1
	
**Adivinador** dice: 2893
**Pensador** response:

	Bien => 0
	Regular => 2
**Adivinador** dice: 1293
**Pensador** dice:

	Bien => 2
	Regular => 1

**Adivinador** dice: 1284
**Pensador** responde:

	Bien => 2
	Regular => 1
**Adivinador** dice: 1234
**Pensador** responde:

   	Bien => 4
	Regular => 0

Fin del juego.
