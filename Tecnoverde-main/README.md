# Tecnoverde

Tecnoverde es una aplicación de Marketplace que permite a los usuarios publicar y comprar productos (contenidos y servicios). La aplicación sigue una arquitectura basada en microservicios, lo que significa que está diseñada para ser escalable y modular.

## Tecnologías utilizadas
- Spring MVC para el desarrollo del backend.
- Base de datos H2 para almacenamiento de datos.
- Autenticación y autorización utilizando JWT (JSON Web Tokens).
- Podman y Buildah (OCI-Compliant) para la creación de los contenedores de microservicios, el hecho de ser OCI-Compliant permite que funcione con Docker.
- Docker Compose para la orquestación de los microservicios.
- Jenkins para la implementación de un Pipeline DevOps.


## Lista de métodos que soporta la aplicación

### REST

#### Cuentas (No requieren registro)

##### POST /api/cuentas/login
--> Iniciar sesión en el sistema
###### Especificación del cuerpo de la solicitud
--> Un JSON que contenga:
- username: Variable que almacena el usuario de la persona que quiere iniciar sesión
- password: Variable que almacena la contraseña de la persona que quiere iniciar sesión

##### POST /api/cuentas/proveedor
--> Crear proveedor en el sistema
###### Especificación del cuerpo de la solicitud
--> Un JSON que contenga:
- username: Variable que almacena el usuario del proveedor que se quiere crear
- password: Variable que almacena la contraseña del proveedor que se quiere crear
- nombre: Variable que almacena el nombre del proveedor que se quiere crear
- edad: Variable que almacena la edad del proveedor que se quiere crear
- descripcion: Variable que almacena la descripcion del proveedor que se quiere crear
- telefono: Variable que almacena el telefono del proveedor que se quiere crear
- pagina_web: Variable que almacena la pagina web del proveedor que se quiere crear
- foto: Variable que almacena la foto del proveedor que se quiere crear
  - url: Variable que almacena la url de la foto del proveedor que se quiere crear
- redesSociales: Variable que almacena las redes sociales del proveedor que se quiere crear
  - facebook: Variable que almacena la informacion de la red social facebook del proveedor que se quiere crear
  - instagram: Variable que almacena la informacion de la red social instagram del proveedor que se quiere crear
  - twitter: Variable que almacena la informacion de la red social twitter del proveedor que se quiere crear

##### POST /api/cuentas/cliente
--> Crear cliente en el sistema
###### Especificación del cuerpo de la solicitud
--> Un JSON que contenga:
- username: Variable que almacena el usuario del cliente que se quiere crear
- password: Variable que almacena la contraseña del cliente que se quiere crear
- nombre: Variable que almacena el nombre del cliente que se quiere crear
- edad: Variable que almacena la edad del cliente que se quiere crear
- descripcion: Variable que almacena la descripcion del cliente que se quiere crear
- foto: Variable que almacena la foto del cliente que se quiere crear
- url: Variable que almacena la url de la foto del cliente que se quiere crear

##### GET /api/cuentas/cliente
--> Ver todos los clientes
###### Posibles parametros:
- tipo: Variable que almacena el id del cliente que quiere ver

##### GET /api/cuentas/proveedor
--> Ver todos los clientes
###### Posibles parametros:
- tipo: Variable que almacena el id del proveedor que quiere ver


#### Productos (Cliente)

##### GET /api/productos
--> Ver todos los productos disponibles (servicios y contenidos)
###### Posibles parametros:
- tipo_servicio: Variable que almacena el tipo de servicio que quiere ver
- tipo_contenido: Variable que almacena el tipo de contenido que quiere ver
- nombre: Variable que almacena el nombre de los productos que quiere ver
- descripcion: Variable que almacena la descripcion de los productos que quiere ver


#### Servicios (Cliente)

##### GET /api/servicios/clientes
--> Ver todos los servicios
###### Posibles parametros:
- tipo: Variable que almacena el tipo de servicio que quiere ver

##### GET /api/servicios/clientes/servicio?id=[NUM]
--> Ver el servicio con el ID especificado


#### Servicios (Proveedores)

##### POST /api/servicios/proveedores
--> Crear servicio en el sistema
###### Especificación del cuerpo de la solicitud
--> Un JSON que contenga:
- nombre: Variable que almacena el nombre del servicio a crear
- descripcion: Variable que almacena la descripcion del servicio a crear
- proveedor_ID: Variable que almacena el id del proveedor del servicio a crear
- precio: Variable que almacena el precio del servicio a crear
- tipo_servicio: Variable que almacena el tipo del servicio a crear
- informacionAdicional: Variable que almacena la informacion adicional del servicio a crear


#### Contenidos (Cliente)

##### GET /api/contenidos/clientes
--> Ver todos los contenidos
###### Posibles parametros:
- tipo: Variable que almacena el tipo de contenido que quiere ver

##### GET /api/contenidos/clientes/contenido?id=[NUM]
--> Ver el contenido con el ID especificado


#### Compras (Cliente)

##### POST /api/compras/clientes/compra
--> Crear una nueva compra en el sistema (Únicamente para servicios)
###### Especificación del cuerpo de la solicitud
--> Un JSON que contenga:
- cliente: Variable que almacena el ID del cliente que realiza la compra
- servicio: Variable que almacena el ID del servicio que se busca adquirir
- fecha_inicio: Variable que almacena la fecha de inicio en la cual se solicita el servicio
- fecha_fin: Variable que almacena la fecha de fin en la cual se solicita el servicio
###### Posibles parámetros
- card: Variable que almacena el numero de la tarjeta para efectuar el pago

##### PUT /api/compras/pagar?id=[NUM]&card=[CARD_NUM]
--> Pagar la compra especificada con la tarjeta especificada

##### GET /api/compras
--> Obtener todas las compras en el sistema


#### Calificaciones (Cliente)

##### GET /api/calificaciones
--> Ver todas las compras

##### POST /api/calificaciones
--> Crear una nueva calificación (Únicamente para servicios)
###### Especificación del cuerpo de la solicitud
--> Un JSON que contenga:
- cliente: Variable que almacena el ID del cliente que realiza la calificación
- servicio: Variable que almacena el ID del servicio que se busca calificar
- calificacion: Variable que almacena la puntuacion de la calificacion
- comentario: Variable que almacena un comentario adicional a la calificacion

### SOAP

#### Buscar ofertas turisticas (Cliente)

##### POST /api/busqueda-soap
--> Buscar productos en el sistema (Únicamente para servicios)
###### Especificación del cuerpo de la solicitud
--> Un XML que siga el siguiente esquema:
![Esquema SOAP](https://github.com/nclsbayona/Tecnoverde/blob/main/Otros/soap-request-example.png?raw=true)



## Vistas arquitectónicas

### Escenarios
#### Casos de uso
##### Version 1
![Diagrama de casos de uso 1](https://github.com/nclsbayona/Tecnoverde/blob/main/Diagramas/V1/Casos%20de%20uso.jpg?raw=true)

##### Version 2
![Diagrama de casos de uso 2](https://github.com/nclsbayona/Tecnoverde/blob/main/Diagramas/V2/Casos%20de%20uso.jpg?raw=true)


### Vista física
#### Diagrama de despliegue
##### Version 1
![Diagrama de despliegue 1](https://github.com/nclsbayona/Tecnoverde/blob/main/Diagramas/V1/Diagrama%20de%20despliegue.jpg?raw=true)

##### Version 2

###### Version 2.1
![Diagrama de despliegue 2](https://github.com/nclsbayona/Tecnoverde/blob/main/Diagramas/V2/Diagrama%20de%20despliegue-1.jpg?raw=true)

###### Version 2.2
![Diagrama de despliegue 2](https://github.com/nclsbayona/Tecnoverde/blob/main/Diagramas/V2/Diagrama%20de%20despliegue-2.jpg?raw=true)


### Vista lógica
#### Diagrama de clases
##### Version 1
![Diagrama de clases 1](https://github.com/nclsbayona/Tecnoverde/blob/main/Diagramas/V1/Diagrama%20de%20clases.jpg?raw=true)

##### Version 2
![Diagrama de clases 2](https://github.com/nclsbayona/Tecnoverde/blob/main/Diagramas/V2/Diagrama%20de%20clases.jpg?raw=true)


### Vista de desarrollo
#### Diagrama de persistencia
##### Version 1
![Diagrama de persistencia 1](https://github.com/nclsbayona/Tecnoverde/blob/main/Diagramas/V1/database-diagram.jpg?raw=true)

##### Version 2
![Diagrama de persistencia 2](https://github.com/nclsbayona/Tecnoverde/blob/main/Diagramas/V2/database-diagram.jpg?raw=true)



### Vista de proceso
#### Diagrama de componentes
##### Version 1
![Diagrama de componentes 1](https://github.com/nclsbayona/Tecnoverde/blob/main/Diagramas/V1/Diagrama%20de%20componentes.jpg?raw=true)

##### Version 2
![Diagrama de componentes 2](https://github.com/nclsbayona/Tecnoverde/blob/main/Diagramas/V2/Diagrama%20de%20componentes.jpg?raw=true)




## Pipeline DevOps

![Pipeline DevOps](https://github.com/nclsbayona/Tecnoverde/blob/main/Diagramas/V2/Pipeline.png?raw=true)
