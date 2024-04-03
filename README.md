# Gestor de Gastos - Aplicación de Consola

Este es un proyecto de gestor de gastos realizado en Java 17, diseñado como una aplicación de consola para practicar y repasar conceptos básicos de Java, incluyendo POO, Java IO, colecciones, excepciones, entre otros.

## Funcionalidades

- **Ingresar Gastos:** Permite al usuario ingresar nuevos gastos con detalles como descripcion, monto y fecha.

- **Modificar Gastos:** Brinda la opción de editar o eliminar gastos existentes en el registro.

- **Mostrar Gastos:** Muestra todos los gastos registrados hasta el momento.

- **Estadísticas de Gastos:**
  - Total de Gastos
  - Gasto Máximo
  - Gasto Mínimo
  - Promedio de Gastos

## Persistencia de Datos

Para la persistencia de los gastos, se utiliza Java IO, específicamente ObjectInputStream y ObjectOutputStream, permitiendo guardar y cargar los elementos en forma de objetos. La clase Gasto extiende Serializable para facilitar este proceso. Todos los registros de gastos se almacenan en un archivo `gastos.bin`.

## Uso

1. **Iniciar la Aplicación:** Ejecuta `GastosConsolaApp.java` para iniciar la aplicación de consola.
   
2. **Menú de Opciones:** Una vez iniciada la aplicación, se mostrará un menú con las siguientes opciones:
   - 1: Ingresar Nuevo Gasto
   - 2: Modificar Gasto
   - 3: Mostrar Todos los Gastos
   - 4: Mostrar Estadísticas
   - 5: Salir
   
3. **Persistencia de Datos:** Todos los cambios realizados se guardarán automáticamente en el archivo `gastos.bin` para futuras sesiones.

## Requisitos

- Java 17 instalado en el sistema.
- IDE de Java (Eclipse, IntelliJ, NetBeans, etc.) para compilar y ejecutar el código.

## Instrucciones de Ejecución

1. Clona el repositorio a tu máquina local.
2. Abre el proyecto en tu IDE de Java.
3. Compila y ejecuta `GastosConsolaApp.java` para iniciar la aplicación.

