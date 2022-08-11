# Hotelbeds Suppliers Integrations Developer TEST (SOLUTION2)

## Configuracion
* request.write.register.filename=registers.txt (fichero donde se graban los registros de inicio de sesión)
request.read.register.filename=registers.txt (fichero donde se lee los registros de inicio de sesión)
daemon.writer.request.ms=100 (tiempo en milisegundos que simula el registro en log)
request.failed.attemps.limit=5 (limíte de intentos fallidos)
period.hacking.minutes=5 (periodo de tiempo en minutos durante el cual se detecta el intento de hackeo)
ips.number.simulated=5 (número de ips con las que jugamos para simular un entorno real, podria ser todo el campo de ips disponibles)
info.extended=false (opción que pinta los últimos 5 intentos de inicio de sesión para una ip, una vez la detectamo como sospechosa)

## Implementación

El mismo demonio que registra las peticiones, se encarga de llamar a la interfaz encargada de detectar intentos de hackeo.
Por cada linea que leemos del fichero, buscaremos en un instante de tiempo anterior de 5 minutos, todas las peticiones
fallidas para esa ip, si existen al menos intentos se marca dicha ip como sospechosa; de lo contrario no se muestra información
alguna en stdout.
Existe un único demonio que insertara a ritmo de (daemon.writer.request.time) una vez insertada el registro comprueba si dicha petición puede ser sospechosa de hackeo
Con dos schedule, uno para simular el reqgistro de request y otro que marca el instante de tiempo durante el cual se van a ir leyendo
las peticiones,

Iremos leyendo una a una buscando de entre todas las que tenemos aquellas que esten compredidas entre ese instante de tiempo
y 5 minutos antes si existen para cada IP 5 intentos o mas de inicio de sesion fallidos.

En cada ejecucion saca por consola la petición a partir de la cual dicha ip se considera sospechosa

Hemos tenido en cuenta ademas que el usuario podria ser diferente para una misma ip,
auqnue se podria haber asociado una Ip a un usuario. (Esto no tiene relevancia)


