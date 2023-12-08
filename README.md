##  TECNOLOGIAS: Spring boot, Java 17, BDDD H2

##  DATOS DE CONFIGURACIÓN
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.username=sa
spring.datasource.password=password
server.port=8081

## PASOS PARA CREAR UN USUARIO

### 1. Obtener el JWT generico:

Ejemplo Request:
POST localhost:8081/auth/login

{
  "email": "octavio@gmail.com",
  "password": "octavio"
}

Ejemplo Response:
{
  "email": "octavio@gmail.com",
  "accessToken": "eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiJCQ0kiLCJzdWIiOiJvY3RhdmlvQGdtYWlsLmNvbSIsImF1dGhvcml0aWVzIjpbIkFETUlOIl0sImlhdCI6MTcwMjA0NTQ5NSwiZXhwIjoxNzAyMDQ2MDk1fQ.vc3RIp7kFyDzGQThyJGBTG1pyJi7p-lgF0w-8ORgO2ciYewZNdtyurv2k-im6AtLmeE5gOlQvB3sfJ2vNn2_eA"
}

### 2. Usar el accessToken generado el paso 1 y enviarlo en la cabecera Authorization: Bearer XXX...

Ejemplo Request:
POST localhost:8081/api/v1/user

{
  "name": "octavio4",
  "email": "octavio@mail.com",
  "password": "hunter2",
  "phones": [
    {
      "number": "1234567",
      "citycode": "1",
      "contrycode": "57"
    }
  ]
}

Ejemplo Response:
{
  "id": 3,
  "created": "08/12/2023 09:25:36",
  "modified": "08/12/2023 09:25:36",
  "last_login": "octavio@gmail.com",
  "token": "eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiJCQ0kiLCJzdWIiOiJvY3RhdmlvQG1haWwuY29tIiwiYXV0aG9yaXRpZXMiOlsiQURNSU4iXSwiaWF0IjoxNzAyMDQ1NTM2LCJleHAiOjE3MDIwNDYxMzZ9.jDg9aciFl_Bgmu0508PlVg0UoEsi11JnMA7BMHEvEIyQwvJJDQDzrHkUasCb4nJvLoxld28BFQudS90Dq7W1mw",
  "isactive": true
}
