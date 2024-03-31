import java.io.Serializable;
import java.time.LocalDate;

public class Gasto implements Serializable {
    private LocalDate fecha;
    private double monto;
    private String descripcion;

    public Gasto(int dia, int mes, int anno, double monto, String descripcion) {
        fecha = LocalDate.of(anno, mes, dia);
        this.monto = monto;
        this.descripcion = descripcion;

    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public double getMonto() {
        return monto;
    }

    @Override
    public String toString() {
        return fecha + ", $" + monto + ", " + descripcion + ".";

    }

}
