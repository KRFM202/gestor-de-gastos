import java.util.ArrayList;
import java.util.Random;

public class PruebaGestorGastos {
    public static void main(String[] args) {
//        ArrayList<Gasto> list = new ArrayList<>();
        GestorGastos gestorGastos = new GestorGastos();
//        list.add(new Gasto(1, 1, 2022, 50.0, "Comida"));
//        list.add(new Gasto(15, 3, 2022, 30.5, "Transporte"));
//        list.add(new Gasto(20, 5, 2022, 100.75, "Entretenimiento"));
//        list.add(new Gasto(10, 8, 2022, 75.25, "Compras"));
//        list.add(new Gasto(5, 12, 2022, 200.0, "Regalos"));

        //gestorGastos.agregarLista(list);
        //gestorGastos.escribirRegisto(list);

//        gestorGastos.agregarGasto(2, 3, 2024, 3, "prueba2");
//        gestorGastos.agregarGasto(2, 3, 2024, 3, "pruebaKISS");
//        gestorGastos.mostrarRegistro();
//        //System.out.println(gestorGastos.getGasto(15));
//        System.out.println(gestorGastos.calcularTotal());
//
//        System.out.println(gestorGastos.contarRegistros());
//        System.out.println(gestorGastos.calcularMaximo());
//        System.out.println(gestorGastos.calcularMinimo());
//        System.out.println(gestorGastos.calcularPromedio());


        gestorGastos.escribirRegisto(generarListaGastos(25));

    }

    public static ArrayList<Gasto> generarListaGastos(int cantidad) {
        ArrayList<Gasto> listaGastos = new ArrayList<>();
        Random rand = new Random();

        // Generamos gastos aleatorios y los añadimos a la lista
        for (int i = 0; i < cantidad; i++) {
            int dia = rand.nextInt(28) + 1; // Día aleatorio entre 1 y 28
            int mes = rand.nextInt(12) + 1; // Mes aleatorio entre 1 y 12
            int anio = 2024; // Año fijo en 2024
            double cantidadGasto = Math.round(rand.nextDouble() * 1000 * 100.0) / 100.0; // Cantidad aleatoria entre 0 y 1000
            String descripcion = generarDescripcionAleatoria(); // Generar descripción aleatoria

            Gasto gasto = new Gasto(dia, mes, anio, cantidadGasto, descripcion);
            listaGastos.add(gasto);
        }

        return listaGastos;
    }

    // Método para generar descripciones aleatorias
    public static String generarDescripcionAleatoria() {
        String[] descripciones = {"Comida", "Transporte", "Ropa", "Entretenimiento", "Salud", "Hogar", "Educación"};
        Random rand = new Random();
        int index = rand.nextInt(descripciones.length);
        return descripciones[index];
    }


}
