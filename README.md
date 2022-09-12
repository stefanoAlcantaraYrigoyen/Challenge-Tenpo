# Challenge Tenpo - Stefano Alcantara

## instrucciones


   - Clonar el repositorio
   
   - Tener instalado docker, maven y java 11 para que el proyecto funcione correctamente
  
   - Instalar dependencias de Maven y correr el proyecto en un contenedor de docker utilizando:
   
```
   mvn clean install && docker-compose up
```

## Tecnologias utilizadas
   
   - Tests with JUnit y Mockito
   
   - Cache for Users with google common
   
   - Swagger2 en http://localhost:8080/swagger-ui.html#/
   
   - Authorization through JWT via the Authorization header: "Token: ${token}" that provides when logging in the previously created user
   
   - Spring security, JPA
   
   - Exception handling

##  Requerimientos

Debes desarrollar una API REST en Spring Boot utilizando java 11 o superior, con las siguientes funcionalidades:
a. Sign up usuarios.
b. Login usuarios.
c. Debe contener un servicio llamado por api-rest que reciba 2 números, los sume, y le aplique una suba de un porcentaje que debe ser adquirido de un servicio externo (por ejemplo, si el servicio recibe 5 y 5 como valores, y el porcentaje devuelto por el servicio externo es 10, entonces (5 + 5) + 10% = 11). Se deben tener en cuenta las siguientes consideraciones:
El servicio externo puede ser un mock, tiene que devolver el % sumado.
Dado que ese % varía poco, debe ser consumido cada media hora.
Si el servicio externo falla, se debe devolver el último valor retornado. Si no hay valor, debe retornar un error la api.
Si el servicio externo falla, se puede reintentar hasta 3 veces.
d. Historial de todos los llamados a todos los endpoint junto con la respuesta en caso de haber sido exitoso. Responder en Json, con data paginada. El guardado del historial de llamadas no debe sumar tiempo al servicio invocado.
e. El historial y la información de los usuarios se debe almacenar en una database PostgreSQL.
f. Incluir errores http. Mensajes y descripciones para la serie 4XX.
