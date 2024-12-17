
package Modelo;

public class Productos {

    private int id;
    private String codigo;
    private String Descripcion;
    private int cantidad;
    private double precio_compra;
    private double precio_venta;
    private String nombre_proveedor; // Cambiado de id_proveedor
    private String nombre_medida;    // Cambiado de id_medida
    private String nombre_categoria; // Cambiado de id_categoria
    private String estado;
    private String proveedor;        // Para el nombre del proveedor
    private String medida;           // Para el nombre de la medida
    private String cat;              // Para el nombre de la categoría

    public Productos() {
    }

    public Productos(int id, String codigo, String Descripcion, int cantidad, double precio_compra, double precio_venta, String nombre_proveedor, String nombre_medida, String nombre_categoria, String estado, String proveedor, String medida, String cat) {
        this.id = id;
        this.codigo = codigo;
        this.Descripcion = Descripcion;
        this.cantidad = cantidad;
        this.precio_compra = precio_compra;
        this.precio_venta = precio_venta;
        this.nombre_proveedor = nombre_proveedor;
        this.nombre_medida = nombre_medida;
        this.nombre_categoria = nombre_categoria;
        this.estado = estado;
        this.proveedor = proveedor;
        this.medida = medida;
        this.cat = cat;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecio_compra() {
        return precio_compra;
    }

    public void setPrecio_compra(double precio_compra) {
        this.precio_compra = precio_compra;
    }

    public double getPrecio_venta() {
        return precio_venta;
    }

    public void setPrecio_venta(double precio_venta) {
        this.precio_venta = precio_venta;
    }

    public String getNombre_proveedor() { // Nuevo getter para nombre_proveedor
        return nombre_proveedor;
    }

    public void setNombre_proveedor(String nombre_proveedor) { // Nuevo setter para nombre_proveedor
        this.nombre_proveedor = nombre_proveedor;
    }

    public String getNombre_medida() { // Nuevo getter para nombre_medida
        return nombre_medida;
    }

    public void setNombre_medida(String nombre_medida) { // Nuevo setter para nombre_medida
        this.nombre_medida = nombre_medida;
    }

    public String getNombre_categoria() { // Nuevo getter para nombre_categoria
        return nombre_categoria;
    }

    public void setNombre_categoria(String nombre_categoria) { // Nuevo setter para nombre_categoria
        this.nombre_categoria = nombre_categoria;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public String getMedida() {
        return medida;
    }

    public void setMedida(String medida) {
        this.medida = medida;
    }

    public String getCat() {
        return cat;
    }

    public void setCat(String cat) {
        this.cat = cat;
    }
}

