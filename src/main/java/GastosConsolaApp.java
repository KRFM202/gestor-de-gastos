import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GastosConsolaApp {
    private static final GestorGastos gestorGastos = new GestorGastos();
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) {
        menuPrincipal();
        System.out.println("Saliendo del programa...");
    }

    private static void menuPrincipal() {
        boolean seguirEnMenuPrinc = true;
        while (seguirEnMenuPrinc) {
            System.out.print("""
                                        
                    --GESTOR DE GASTOS DIARIOS--
                                    
                    <<<Menú Principal>>>
                    1. Registrar gastos
                    2. Mostrar gastos
                    3. Modificar gastos
                    4. Ver estadisticas
                    5. Salir
                    Ingrese el número de opción que desea:\s""");
            int opc;
            try {
                opc = Integer.parseInt(br.readLine());
                switch (opc) {
                    case 1 -> formularioRegistro();
                    case 2 -> {
                        mostrarGastosSiContieneElementos();
                        System.out.println("Preiona ENTER para continuar...");
                        br.readLine();
                    }
                    case 3 -> menuModificar();
                    case 4 -> menuEstadisticas();
                    case 5 -> seguirEnMenuPrinc = false;
                    default -> System.out.println(errorOpcionIngresada());


                }
            } catch (Exception e) {
                System.out.println(errorOpcionIngresada());
            }
        }

    }

    private static void menuModificar() {
        boolean seguirEnMenuMod = true;
        int opc;
        while (seguirEnMenuMod) {
            System.out.print("""
                                        
                    --GESTOR DE GASTOS DIARIOS--
                                    
                    <<<Menú Modificar>>>
                    1. Editar un gasto
                    2. Eliminar un gasto
                    3. Volver al menú principal
                    Ingrese el número de opción que desea:\s""");
            try {
                opc = Integer.parseInt(br.readLine());
                switch (opc) {
                    case 1 -> formularioEditar();
                    case 2 -> formularioEliminar();
                    case 3 -> seguirEnMenuMod = false;
                    default -> System.out.println(errorOpcionIngresada());
                }
            } catch (Exception e) {
                System.out.println(errorOpcionIngresada());
            }
        }
    }

    private static void menuEstadisticas() {
        boolean seguirEnEstadisticas = true;
        int opc;
        while (seguirEnEstadisticas) {
            System.out.print("""
                                        
                    --GESTOR DE GASTOS DIARIOS--
                                    
                    <<<Menú Estadisticas>>>
                    1. Calcular el total
                    2. Calcular el máximo y minímo
                    3. Calcular el promedio
                    4. Ver todas las estadisticas
                    5. Volver al menú principal
                    Ingrese el número de opción que desea:\s""");
            try {
                opc = Integer.parseInt(br.readLine());
                switch (opc) {
                    case 1 -> calcularElTotal();
                    case 2 -> calcularMaxYMin();
                    case 3 -> calcularPromedio();
                    case 4 -> {
                        calcularElTotal();
                        calcularMaxYMin();
                        calcularPromedio();
                    }
                    case 5 -> seguirEnEstadisticas = false;
                    default -> System.out.println(errorOpcionIngresada());
                }
            } catch (Exception e) {
                System.out.println(errorOpcionIngresada());
            }
        }
    }

    private static void formularioRegistro() {
        int dia, mes, anno;
        String descripcion;
        double monto;
        boolean seguirRegistrando = true;
        while (seguirRegistrando) {
            try {
                System.out.print("""
                                                
                        --GESTOR DE GASTOS DIARIOS--
                                  
                        <<<Registrar gasto>>>
                                                
                        Ingrese la fecha de su gasto
                        Digite el día:\s""");
                dia = Integer.parseInt(br.readLine());
                System.out.print("Digite el mes: ");
                mes = Integer.parseInt(br.readLine());
                System.out.print("Digite el año: ");
                anno = Integer.parseInt(br.readLine());
                System.out.print("Ingrese la descripción de su gasto: ");
                descripcion = br.readLine();
                System.out.print("Ingrese el monto del gasto $: ");
                monto = Double.parseDouble(br.readLine());
                registrarGasto(dia, mes, anno, monto, descripcion);
                seguirRegistrando = menuFuncionalidad("¿Quieres seguir registrando gastos?");
            } catch (Exception e) {
                System.out.println(errorTipoDeDatoIncorrecto());
                seguirRegistrando = menuFuncionalidad("¿Quieres volver a intentarlo?");
            }
        }
    }

    public static void formularioEditar() {
        int dia, mes, anno, index;
        String descripcion;
        double monto;
        Gasto gastoSeleccionado;
        boolean seguirEditando = true;
        while (seguirEditando) {
            try {
                if (mostrarGastosSiContieneElementos()) {
                    System.out.print("\nBusque e ingrese el numero del gasto que desee editar: ");
                    index = Integer.parseInt(br.readLine());
                    System.out.println("\n<<<Editar gasto>>>");
                    gastoSeleccionado = obtenerGasto(index);
                    if (gastoSeleccionado != null) {
                        System.out.print("Dia: ");
                        dia = Integer.parseInt(br.readLine());
                        System.out.print("Mes: ");
                        mes = Integer.parseInt(br.readLine());
                        System.out.print("Año: ");
                        anno = Integer.parseInt(br.readLine());
                        System.out.print("Descripcion: ");
                        descripcion = br.readLine();
                        System.out.print("Monto $: ");
                        monto = Double.parseDouble(br.readLine());
                        if (editarGasto(index, dia, mes, anno, monto, descripcion)) {
                            System.out.println("Gasto anterior: [" + gastoSeleccionado + "]");
                            gastoSeleccionado = obtenerGasto(index);
                            System.out.println("Gasto actualizado: [" + gastoSeleccionado + "]");
                        }
                    } else {
                        System.out.println("\nEl gasto que desea editar no existe");
                    }
                    seguirEditando = menuFuncionalidad("¿Quieres seguir editando gastos?");

                }
            } catch (Exception e) {
                System.out.println(errorTipoDeDatoIncorrecto());
                seguirEditando = menuFuncionalidad("¿Quieres volver a intentarlo?");
            }
        }

    }

    public static void formularioEliminar() {
        int index;
        Gasto gastoSeleccionado;
        boolean seguirEliminando = true;
        while (seguirEliminando) {
            try {
                if (mostrarGastosSiContieneElementos()) {
                    System.out.println("\n<<<Eliminar gasto>>>");
                    System.out.print("\nBusque e ingrese el numero del gasto que desee eliminar: ");
                    index = Integer.parseInt(br.readLine());
                    gastoSeleccionado = obtenerGasto(index);
                    if (gastoSeleccionado != null) {
                        System.out.println("\nGasto seleccionado: [" + gastoSeleccionado + "]");
                        if (menuFuncionalidad("¿Desea eliminar el gasto seleccionado?")) {
                            if (eliminarGasto(index)) {
                                System.out.println("Gasto eliminado: [" + gastoSeleccionado + "]");
                            }
                        }
                    } else {
                        System.out.println("\nEl gasto que desea eliminar no existe");
                    }
                    seguirEliminando = menuFuncionalidad("¿Quieres seguir eliminando gastos?");

                }
            } catch (Exception e) {
                System.out.println(errorTipoDeDatoIncorrecto());
                seguirEliminando = menuFuncionalidad("¿Quieres volver a intentarlo?");
            }
        }

    }

    private static boolean menuFuncionalidad(String mensaje) {
        int opc;
        boolean esAceptado = false, seguirPreguntando = true;
        while (seguirPreguntando) {
            try {
                System.out.print("\n" + mensaje + " \n" + """
                        1.Si
                        2.No
                        Ingrese el número de opción que desea:\s""");
                opc = Integer.parseInt(br.readLine());
                switch (opc) {
                    case 1 -> {
                        esAceptado = true;
                        seguirPreguntando = false;
                    }
                    case 2 -> {
                        esAceptado = false;
                        seguirPreguntando = false;
                    }
                    default -> System.out.println(errorOpcionIngresada());
                }
            } catch (Exception e) {
                System.out.println(errorOpcionIngresada());
            }
        }
        return esAceptado;
    }

    private static void registrarGasto(int dia, int mes, int anno, double monto, String descripcion) {
        if (gestorGastos.agregarGasto(dia, mes, anno, monto, descripcion)) {
            System.out.println("\nGasto ingresado satisfactoriamente");
        } else {
            System.out.println(errorDatosInconsistentes());
        }
    }

    private static boolean mostrarGastosSiContieneElementos() {
        boolean contieneElementos = true;
        System.out.println("\nGastos registrados:");
        if (gestorGastos.estaVacioElRegistro()) {
            System.out.println("\nEl registro esta vacío...");
            contieneElementos = false;
        } else {
            gestorGastos.mostrarRegistro();
        }
        return contieneElementos;
    }

    public static boolean editarGasto(int index, int dia, int mes, int anno, double monto, String descripcion) {
        boolean seEdito = false;
        if (gestorGastos.editarGasto(index, dia, mes, anno, monto, descripcion)) {
            System.out.println("\nGasto editado satisfactoriamente");
            seEdito = true;
        } else {
            System.out.println(errorDatosInconsistentes());
        }
        return seEdito;
    }

    public static boolean eliminarGasto(int index) {
        boolean seElimino = false;
        if (gestorGastos.eliminarGasto(index)) {
            System.out.println("\nGasto eliminado satisfactoriamente");
            seElimino = true;
        }
        return seElimino;
    }

    public static Gasto obtenerGasto(int index) {
        Gasto gastoObtenido = null;
        if (!estaVacioElRegistroDelGestor()) {
            gastoObtenido = gestorGastos.getGasto(index);
        }
        return gastoObtenido;
    }

    public static boolean estaVacioElRegistroDelGestor() {
        return gestorGastos.estaVacioElRegistro();
    }

    private static void calcularPromedio() {
        System.out.println("\n<<<Calcular máximo y mínimo>>>");
        if (!estaVacioElRegistroDelGestor()) {
            double promedio = gestorGastos.calcularPromedio();
            int numRegisto = gestorGastos.contarRegistros();
            System.out.println("\nRegistros promediados: " + numRegisto);
            System.out.println("El valor promedio de todos los gastos es de: $" + promedio);
        } else {
            System.out.println("No se puede obtener el máximo ni el mínimo debido a que el registro esta vacío");
        }
        System.out.println("\nPresione ENTER para continuar...");
        try {
            br.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private static void calcularMaxYMin() {
        System.out.println("\n<<<Calcular máximo y mínimo>>>");
        if (!estaVacioElRegistroDelGestor()) {
            double max = gestorGastos.calcularMaximo();
            double min = gestorGastos.calcularMinimo();
            int numRegisto = gestorGastos.contarRegistros();
            System.out.println("\nRegistros contados: " + numRegisto);
            System.out.println("El valor del gasto más costoso es de: $" + max);
            System.out.println("El valor del gasto menos costoso es de: $" + min);
        } else {
            System.out.println("No se puede obtener el máximo ni el mínimo debido a que el registro esta vacío");
        }
        System.out.println("\nPresione ENTER para continuar...");
        try {
            br.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private static void calcularElTotal() {
        System.out.println("\n<<<Calcular total>>>");
        if (!estaVacioElRegistroDelGestor()) {
            double total = gestorGastos.calcularTotal();
            int numRegisto = gestorGastos.contarRegistros();
            System.out.println("\nRegistros contados: " + numRegisto);
            System.out.println("La suma total de todos los gastos es: $" + total);
        } else {
            System.out.println("No se puede obtener el total debido a que el registro esta vacío");
        }
        System.out.println("\nPresione ENTER para continuar...");
        try {
            br.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private static String errorOpcionIngresada() {
        return "\nError, la opción ingresada no existe. Intente de nuevo...";
    }

    private static String errorTipoDeDatoIncorrecto() {
        return "\nError, el valor ingresado es inconsistente con el dato esperado\n" +
                "Intente de nuevo...";
    }

    private static String errorDatosInconsistentes() {
        return "\nError, no se pudo agregar el gasto debido a inconsistencias en los datos proporcionados\n" +
                "Intente de nuevo...";
    }


}
