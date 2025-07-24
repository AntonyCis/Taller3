package app.vista;

import app.controlador.Servicio;
import app.modelo.Producto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.util.Map;

public class catalogoProductos extends JFrame {
    private JPanel panelCatalogo;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JButton nuevoButton;
    private JButton mostrarButton;
    private JButton actualizarButton;
    private JButton eliminarButton;
    private JTable tableProductos;
    private JButton btnReporte;

    private Object[] columnas = {"ID", "Codigo", "Nombre", "Precio"};
    private Object[] row = new Object[4];
    private Map<Integer, Producto> mapa = null;
    private Servicio controlador = new Servicio();
    private DefaultTableModel model = null;
    private String clave;

    public catalogoProductos() {
        setTitle("Catálogo de Productos");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(panelCatalogo);
        setLocationRelativeTo(null);

        obtenerRegistrisTabla();

        tableProductos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int i = tableProductos.getSelectedRow();
                clave = model.getValueAt(i, 0).toString();
                textField2.setText(model.getValueAt(i, 1).toString());
                textField1.setText(model.getValueAt(i, 2).toString());
                textField3.setText(model.getValueAt(i, 3).toString());
            }
        });

        mostrarButton.addActionListener(e -> obtenerRegistrisTabla());

        nuevoButton.addActionListener(e -> {
            try {
                String codigo = textField2.getText().trim();
                String nombre = textField1.getText().trim();
                String precioStr = textField3.getText().trim().replace(",", ".");

                if (codigo.isEmpty() || nombre.isEmpty() || precioStr.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios.");
                    return;
                }

                double precio = Double.parseDouble(precioStr);
                Producto nuevoProducto = new Producto(codigo, nombre, precio);
                controlador.insertar(nuevoProducto);
                limpiarCampos();

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Precio inválido. Usa formato numérico (ej: 10.99)");
            }
        });


        eliminarButton.addActionListener(e -> {
            if (clave == null) {
                JOptionPane.showMessageDialog(null, "Selecciona un producto de la tabla para eliminar.");
                return;
            }
            int id = Integer.parseInt(clave);
            controlador.eliminar(id);
            obtenerRegistrisTabla();
            limpiarCampos();
        });

        btnReporte.addActionListener(e -> {
            try {
                tableProductos.print();
            } catch (Exception e2) {
                System.out.println(e2.getMessage());
            }
        });
        actualizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(clave);
                String codigo = textField2.getText();
                String nombre = textField1.getText();
                String precioStr=textField3.getText().replace(",", ".");
                double precio = Double.parseDouble(precioStr);
                controlador.actualizar(new Producto(id, codigo, nombre, precio));
                obtenerRegistrisTabla();
            }
        });
    }

    public void obtenerRegistrisTabla() {
        model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int filas, int columnas) {
                return false;
            }
        };

        model.setColumnIdentifiers(columnas);
        model.setRowCount(0); // Limpia la tabla

        mapa = controlador.seleccionarTodo();
        for (Map.Entry<Integer, Producto> entry : mapa.entrySet()) {
            row[0] = entry.getKey();
            row[1] = entry.getValue().getCodigo();
            row[2] = entry.getValue().getNombre();
            row[3] = String.format("%.2f", entry.getValue().getPrecio());
            model.addRow(row.clone()); // Usar clone para evitar referencias
        }

        tableProductos.setModel(model);

        tableProductos.getColumnModel().getColumn(0).setPreferredWidth(30);
        tableProductos.getColumnModel().getColumn(1).setPreferredWidth(60);
        tableProductos.getColumnModel().getColumn(2).setPreferredWidth(180);
        tableProductos.getColumnModel().getColumn(3).setPreferredWidth(60);
    }

    private void limpiarCampos() {
        textField1.setText("");
        textField2.setText("");
        textField3.setText("");
        clave = null;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new catalogoProductos().setVisible(true));
    }
}
