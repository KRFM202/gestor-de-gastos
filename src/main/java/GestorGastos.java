import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;

public class GestorGastos {
    private static ArrayList<Gasto> listaGastos;

    public GestorGastos() {
        listaGastos = new ArrayList<>();
    }

    public boolean agregarGasto(int dia, int mes, int anno, double monto, String descripcion) {
        boolean esValido = false;
        if (validarDatos(dia, mes, anno, monto, descripcion)) {
            if (!estaVacioElRegistro()) {
                listaGastos.addAll(leerRegisto());
            }
            listaGastos.add(new Gasto(dia, mes, anno, monto, descripcion));
            escribirRegisto(listaGastos);
            listaGastos.clear();
            esValido = true;
        }
        return esValido;
    }

    public void mostrarRegistro() {
        if (!estaVacioElRegistro()) {
            listaGastos = leerRegisto();
            int index = 1;
            for (Gasto gasto : listaGastos) {
                System.out.println(index + ". " + gasto);
                index++;
            }
        }
        listaGastos.clear();
    }

    public boolean editarGasto(int index, int dia, int mes, int anno, double monto, String descripcion) {
        listaGastos.clear();
        boolean esValidaLaEdicion = false;
        if (validarDatos(dia, mes, anno, monto, descripcion)) {
            if (!estaVacioElRegistro()) {
                listaGastos.addAll(leerRegisto());
                listaGastos.set(index - 1, new Gasto(dia, mes, anno, monto, descripcion));
                escribirRegisto(listaGastos);
                esValidaLaEdicion = true;
            }
        }
        return esValidaLaEdicion;
    }

    public Boolean eliminarGasto(int index) {
        listaGastos.clear();
        boolean esValidaLaEliminacion = false;
        if (getGasto(index) != null) {
            if (!estaVacioElRegistro()) {
                listaGastos.addAll(leerRegisto());
                listaGastos.remove(index - 1);
                escribirRegisto(listaGastos);
                esValidaLaEliminacion = true;
            }
        }
        listaGastos.clear();
        return esValidaLaEliminacion;
    }

    public Gasto getGasto(int index) {
        Gasto gastoEncontrado = null;
        listaGastos.clear();
        try {
            if (!estaVacioElRegistro()) {
                listaGastos.addAll(leerRegisto());
            }
            if (!listaGastos.isEmpty()) {
                gastoEncontrado = listaGastos.get(index - 1);
            }
        } catch (Exception e) {
        }
        listaGastos.clear();
        return gastoEncontrado;
    }

    public void escribirRegisto(ArrayList<Gasto> registoAEscribir) {
        try (FileOutputStream fos = new FileOutputStream("gastos.bin");
             ObjectOutputStream oos = new ObjectOutputStream(fos);) {
            oos.writeObject(registoAEscribir);
            oos.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Gasto> leerRegisto() {
        ArrayList<Gasto> registroGastos = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream("gastos.bin");
             ObjectInputStream ois = new ObjectInputStream(fis);) {
            Object obj = ois.readObject();
            if (obj instanceof ArrayList<?>) {
                registroGastos = (ArrayList<Gasto>) obj;
            }
        } catch (EOFException e) {
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e.getCause());
        }
        return registroGastos;
    }

    public boolean estaVacioElRegistro() {
        boolean estaVacio = false;
        if (leerRegisto().isEmpty() || leerRegisto() == null) {
            estaVacio = true;
        }
        return estaVacio;
    }

    private boolean validarDatos(int dia, int mes, int anno, double monto, String descripcion) {
        boolean diaValido = dia > 0 && dia <= 31;
        boolean mesValido = mes > 0 && mes <= 12;
        boolean annoValido = anno > 1999 && anno <= 2100;
        boolean montoValido = monto > 0;
        boolean descripcionValida = !descripcion.isEmpty();
        return diaValido && mesValido && annoValido && montoValido && descripcionValida;
    }

    public double calcularTotal() {
        double total = 0.0;
        listaGastos.clear();
        if (!estaVacioElRegistro()) {
            listaGastos.addAll(leerRegisto());
            for (Gasto gasto : listaGastos) {
                total += gasto.getMonto();
            }
        }
        listaGastos.clear();
        return total;
    }

    public int contarRegistros() {
        int numRegistros = 0;
        if (!estaVacioElRegistro()) {
            numRegistros = leerRegisto().size();
        }
        return numRegistros;
    }

    public double calcularMaximo() {
        double maximo = 0;
        listaGastos.clear();
        if (!estaVacioElRegistro()) {
            listaGastos.addAll(leerRegisto());
            maximo = listaGastos.get(0).getMonto();
            for (Gasto gasto : listaGastos) {
                maximo = Math.max(maximo, gasto.getMonto());
            }
        }
        listaGastos.clear();
        return maximo;
    }

    public double calcularMinimo() {
        double minimo = 0;
        listaGastos.clear();
        if (!estaVacioElRegistro()) {
            listaGastos.addAll(leerRegisto());
            minimo = listaGastos.get(0).getMonto();
            for (Gasto gasto : listaGastos) {
                minimo = Math.min(minimo, gasto.getMonto());
            }
        }
        listaGastos.clear();
        return minimo;
    }

    public double calcularPromedio() {
        return (double) Math.round(calcularTotal() / contarRegistros() * 100) / 100;
    }


}
