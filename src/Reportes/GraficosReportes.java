package Reportes;

import Modelo.Productos;
import java.util.List;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import javax.swing.JPanel;

public class GraficosReportes {

    public JPanel crearGraficoStockMinimo(List<Productos> productosStockMinimo) {
    try {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (Productos producto : productosStockMinimo) {
            dataset.addValue(producto.getCantidad(), "Stock Mínimo", producto.getDescripcion());
        }

        JFreeChart barChart = ChartFactory.createBarChart(
            "Productos con Stock Mínimo", // Título
            "Producto",                 // Eje X
            "Cantidad",                 // Eje Y
            dataset
        );

        return new ChartPanel(barChart);
    } catch (Exception e) {
        e.printStackTrace();
        return null;
    }
}

public JPanel crearGraficoMasVendidos(List<Productos> productosMasVendidos) {
    try {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (Productos producto : productosMasVendidos) {
            dataset.addValue(producto.getCantidad(), "Más Vendidos", producto.getDescripcion());
        }

        JFreeChart barChart = ChartFactory.createBarChart(
            "Productos Más Vendidos", // Título
            "Producto",              // Eje X
            "Cantidad",              // Eje Y
            dataset
        );

        return new ChartPanel(barChart);
    } catch (Exception e) {
        e.printStackTrace();
        return null;
    }
}




}
