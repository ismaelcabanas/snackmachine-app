# Domain Driven Design example

Este repositorio es un desarrollo de una aplicación aplicando Domain Driven Design con
arquitectura hexagonal.

Trataremos de modelar el funcionamiento de máquinas automáticas. Se modelará 
una máquina de snacks, un cajero automático (ATM). 
 
## Máquina de snacks

Debemos modelar la máquina de snacks para que venda snacks a cambio de dinero en 
efectivo. A continuación se enumeran las reglas de negocio necesarias para modelar el problema:

 - La máquina tiene únicamente tendrá 3 slots de snacks.
 - Cada slot de snacks tendrá un número de snacks determinado y un precio asignado. Todos los snacks de un slot son del mismo tipo.
 - La máquina acepta monedas de 1, 10 y 25 céntimos, y billetes de 1, 5 y 20 dólares.
 - Para comprar snacks se introducen monedas o billetes y se selecciona un slot para obtener el snack, 
 en ese momento la cantidad de snack del slot decrementará en una unidad.
 - Se podrá cargar dinero (monedas y billetes permitidos) en la máquina.
 - Si se introduce más dinero que el precio del snack a comprar, la máquina deberá devolver el cambio.
 - Si el dinero insertado en la máquina no es suficiente para comprar el snack seleccionado, la máquina deberá advertirlo (lanzar una excepción?)
 - Si se intenta comprar un snack pero no hay existencias, la máquina deberá advertirlo.
 - La máquina debería comprobar si existe suficiente cambio antes de realizar la compra de un snack
 - Habrá la opción de devolver el dinero introducido.
 
 
## Máquina ATM

Debemos modelar un cajero automático que retira efectivo con una tarjeta. Un usuario puede retirar efectivo de una cuenta bancaria con un pequeña comisión. Los requisitos del modelo son:

 - Se indicará la cantidad que se quiere retirar (monedas y/o billetes), que provoca que se decremente la cantidad de monedas y/o billetes 
 retirados del cajero.
 - A la cantidad retirada se le aplicará una comisión del 1%
 - Realizar un seguimiento de todo el dinero cargado por los clientes
 - En el caso que se retire del cajero un céntimo, la comisión por defecto será de 1 céntimo.
 - En el caso de haber decimales en la comisión, se redondeará al siguiente decimal.
 
 
## Gestor del balance

También necesitamos un sistema que gestione el saldo de los cargos hechos por los cajeros y las máquina de snakcs.

Los requisitos del nuevo subsistema serán:

 - Mantener un seguimiento de todos los cargos realizados desde las tarjetas de los usuarios del banco.
 


## Bounded Contexts

### Snack Machine Bounded Context

El Bounded Context de la máquina de Snacks se ha modelado con las siguientes **entities** y **value objects**

#### Entities

 - SnackMachine (Aggregate Root)
 - Slot
 - Snack
 
#### Value Objects

 - Money
 - SnackPile
 - SnanckMachineId
 - SlotId
 - SnackId
 
### ATM Bounded Context


 
