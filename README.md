# MiarmaProyect
Proyecto: MIARMA, una red social para sevillanos

Realizada por Moisés Miranda Corrales

Antes de comenzar a corregir el proyecto tener en cuenta lo siguiente:

Se implenenta una collección postman para realizar la comprobación de los endpoints, tienen agregados en cada petición los ficheros que necesitan y en caso de necesitar auntenticación, tengo creada una variable que guarda el token de la persona logueanda, en caso de dar error una de las peticiones puede ser por dos motivos:

- No se esta guardando el token de la persona logueada correctamente (me pasa a veces cuando logueo con un usuario y depues con otro), bastaria con copiar el token de la petición loguin y pegarla en la petición que deseas probar.

- Da error en la ubicació de los ficheros. Cuando pruebo en postman en mi casa y cambio a clase y viceversa, postman manda un error porque la ubicación de los ficheros no es la misma que teneiamos de casa a clase. ¿Qué tenemos que hacer? dentro del repositorio tenemos una carpeta assets en las que estan todos los ficheros de prueba. Si en la petición que estamos probando necestamos usuario.json, sólo tenemos que irnos a la carpeta assets/jsons/usuario.json, si en postman aparece el fichero publicaciones2 hacemos el mismo proceso asstes/jsons/publicaciones.json. En cada endPoint tenemos dos ficheros, el json y la imagen o el video que vamos a subir, hacemos este paso y funcionará correctamente.


A continuación se muestra los resultados de los endPoints del proyecto:

REGISTRO/LOGIN

- POST /auth/register
Funciona correctamente
- POST /auth/login
Funciona correctamente
- GET /me
Funciona correctamente


PUBLICACIONES

- POST /post/
Funciona correctamente
- PUT /post/{id}
Funciona correctamente
- DELETE /post/{id}
Funciona correctamente
- GET /post/public
Funciona correctamente
- GET /post/{id}
No intentado (si no podia aceptar las solicitudes no podia hacer este)
- GET /post/{nick}
No intentado (si no podia aceptar las solicitudes no podia hacer este)
- GET /post/me
Funciona correctamente


USUARIO

- GET /profile/{id}
No intentado (si no podia aceptar las solicitudes no podia hacer este)
- PUT /profile/me
Funciona correctamente
- POST /follow/{nick}
Funciona correctamente
- GET /follow/list
Funciona correctamente
- POST /follow/accept/{id}
Funciona pero no puedo borrar la solicitud
- POST /follow/decline/{id}
Intentado pero tiene errores y no funciona (esta el código hecho)



