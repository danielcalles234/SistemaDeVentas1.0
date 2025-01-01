package Modelo;

public class Ventas {
    private int id;
    private String nombre_cliente;
    private Double total;
    private String fecha;

    // Constructor con parámetros
    public Ventas(int id, String nombre_cliente, Double total, String fecha) {
        this.id = id;
        this.nombre_cliente = nombre_cliente;
        this.total = total;
        this.fecha = fecha;
    }

    // Constructor vacío
    public Ventas() {
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre_cliente() {
        return nombre_cliente;
    }

    public void setNombre_cliente(String nombre_cliente) {
        this.nombre_cliente = nombre_cliente;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
