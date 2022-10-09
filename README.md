# Neoris Labs JAVA 2022
# API de turnos rotativos para manejo de Jornadas Laborales
### Alumno: Jorge Iván Vitian
---
## Notas
* Por simplicidad, los Empleados y tipso de Jornadas solo tienen nombre y id.  
* Se incluye la base de datos en memoria H2 para pruebas.
* Las pruebas fueron realizadas en Postman y las colecciones fueron adjuntadas en la carpeta "Postman".
* Se integró Swagger para documentar la API. Suponiendo que el proyecto se ejecuta en el puerto 8080, la documentación de Swagger se encuentra en la siguiente URL: http://localhost:8080/swagger-ui.html
* Por simplicidad, supongo que existen los principales tipos de actividades laborales (Normal, Horas Extras, Vacaciones y Día libre). Por lo tanto, guardé estos valores en un enum para utilizarlos para validaciones en la lógica de negocios.