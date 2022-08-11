# Hotelbeds Suppliers Integrations Developer TEST

## Configuracion
* daemon.writer.request.time=10  (tiempo in ms para simular la escrituras de registros en el fichero)
* daemon.read.registers.time=10000 (intervalo de tiempo en ms durante el cual se quiere detectar hackers)
* request.failed.attemps.limit=10 (numero de intentos fallidos para marcar sospechoso)
* request.write.register.filename=registers.txt (log file registers signin requests)
* request.read.register.filename=registers.txt  (log file read signin requests)
* ips.number.game=20 (simulamos un numero de ips fijas, también funcionaria con todo el catalogo posible.)

## Implementación
Con dos schedule, uno para simular el reqgistro de request y otro que marca el instante de tiempo durante el cual se van a ir leyendo
las peticiones,

Iremos leyendo una a una buscando de entre todas las que tenemos aquellas que esten compredidas entre ese instante de tiempo
y 5 minutos antes si existen para cada IP 5 intentos o mas de inicio de sesion fallidos.

En cada ejecucion saca por consola la petición a partir de la cual dicha ip se considera sospechosa

Hemos tenido en cuenta ademas que el usuario podria ser diferente para una misma ip,
auqnue se podria haber asociado una Ip a un usuario. (Esto no tiene relevancia)

```
package com.hotelbeds.supplierintegrations.hackertest.detector
```
```
public interface HackerDetector {
    String parseLine(String line);
}
```
The parseline method will be called each time a new log line is produced.
The log lines will be in the following format:

ip,date,action,username

IP look like  **80.238.9.179**
Date is in the epoch format like  **1336129471**
Action is one of the following:  **SIGNIN_SUCCESS** or  **SIGNIN_FAILURE**
Username is a String like Will.Smith

A log line will therefore look like this:
**80.238.9.179,133612947,SIGNIN_SUCCESS,Will.Smith**

The first detection method will be to identify a single IP address that has attempted a failed
login 5 or more times within a 5 minute period. On detection you should return the suspicious
IP.

Our signin page can generate around 100,000 failed signing a day so memory consumption
should considered and managed.

Please bear in mind all the best practices you would normally employ when producing "done"
production code:

* A well factored object oriented domain model
* Testing
* Clean code


## Time Calculation
Write a function that returns the number of minutes (rounded down) between two
timestamps  _time1_ and  _time2_ in RFC 2822 format (ie: Thu, 21 Dec 2000 16:01:07 +0200).
Don’t forget about the time zones.
