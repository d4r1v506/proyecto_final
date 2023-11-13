[__Inicio__](README.md) | 
[Diagrama](/Diagrama/Diagrama%20de%20clases.jpeg)

**Grupo 8 Citas Médicas**
-
**Integrantes**

- Kelvin Peñafiel

- Darío Falconí


**framework de test**

Se utiliizo el framework JUnit para realizar los test de:

**Patrones implementados**

- Patro de diseño Creacionales Build

Implementamos el patron de diseño *Build* por que nos permite construir el objeto paciente que tiene varios atributos. El patrón nos permite producir distintos tipos y representaciones de un objeto empleando el mismo código de construcción.


**Principio SOLID Y Código Limpio**

la clase ControladorPaciente tiene una unica responsabilidad de validar los datos del paciente.
la clase ControladorHorario tiene una unica responsabilidad de validar el horario.
la clase UtilDate tiene una unica responsabilidad de conversion de tipos de fecha.

*En este ejemplo de codigo utilizamos para validar si la fecha de nacimiento del apoderado es mayor de edad usamos nombres descriptivos para declarar el nombre del metodo y las varables al igual que usamos conversion de fecha en una clase aparte y usamos constantes que tambien se encuentran en una clase distinta*

public boolean esMayorEdadApoderado(String fechaNacimiento) {
    LocalDate fechaLocalDate = UtilDate.convierteFechaStringtoLocalDate(fechaNacimiento);
    return ChronoUnit.YEARS.between(fechaLocalDate, UtilDate.obtieneFechaActualLocalDate()) >= Constantes.EDAD_ADULTO;
}

*En este otro ejemplo de codigo, utilizamos nombres descriptivos y reducimos la validacion a una sola linea que tiene una constante con el listado de dias de feriado*

public boolean esDiaFeriado(String fechaCita) {
    return Constantes.LISTA_DIAS_FERIADO.contains(fechaCita);
}

**Para Ejecutar Codigo en local**

Abrir el codigo en un editor de texto como Visual Studio Code o un IDE, y modificar unicamente el archivo PROYECTO_FINAL/inputs/med_input.txt
ingresar por consola a la ruta principal del proyecto y ejecutarlos los siguiente comandos:

*Para compilar el proyecto*

javac Main.java

*para ejecutar por consola*

java Main

