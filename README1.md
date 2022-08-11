# Hotelbeds Suppliers Integrations Developer TEST (SOLUTION1)

## Configuracion
* daemon.writer.request.time=10  (tiempo in ms para simular la escrituras de registros en el fichero)
* daemon.read.registers.time=1000 (frecuencia cada segundo con la que se leen registros del fichero de logs)
* request.failed.attemps.limit=5 (numero de intentos fallidos para marcar sospechoso)
* request.write.register.filename=registers.txt (log file registers signin requests)
* request.read.register.filename=registers.txt  (log file read signin requests)
* ips.number.game=5 (simulamos un numero de ips fijas, también funcionaria con todo el catalogo posible.)

## Implementación
Con dos scheduler, uno para simular el reqistro de request y otro que marca el instante de tiempo durante el cual se van a ir leyendo
las peticiones,

Iremos leyendo una a una buscando de entre todas las que tenemos, aquellas que esten compredidas entre ese instante de tiempo
y 5 minutos antes si existen para cada IP 5 intentos o mas de inicio de sesion fallidos.

En cada ejecucion saca por consola la petición a partir de la cual dicha ip se considera sospechosa

Hemos tenido en cuenta ademas que el usuario podria ser diferente para una misma ip,
auqnue se podria haber asociado una Ip a un usuario. (Esto no tiene relevancia)

Esta solucion plantela la situación inicial aunque no cumple todos los requisitos.
