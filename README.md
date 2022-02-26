<h1>PruebaDeIngreso</h1>

Prueba de ingreso de la prueba enviado por correo electronico

* Lenguaje de Programacion Kotlin
* Base de datos en Room
* Consumo Rest en Retrofit y OKhttp
* Implementacion de ViewModel y StateFlow
* Se manejo Dagger Hilt para inyeccion de dependencias

<h2>Arquitectura Clean</h2>
* Modulos Manejados en la app <br />
  1 - <strong>DOMAIN</strong> -> Las entidades, pojos o logica de negocio <br />
  2 - <strong>DATA</strong> -> Manejo de la informacion (sincronizacion)<br />
  3 - <strong>USES CASES</strong> -> Todos los eventos relacionados con la aplicacion <br />
  4 - <strong>FRAMEWORK</strong> -> Todo el manejo de consumos rest - manejo de base datos - uso del hardware del movil etc...<br />
  5 - <strong>UI (app)</strong> -> Es la interfaz de usuario de la aplicacion <br />
  
<h2>Testing</h2>
se encuentran a nivel del modulo app <br />
* test -> Todos los test con mockito <br />
* androidTest -> Todos los test con expresso <br />
  
  
