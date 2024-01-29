package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import static model.EmployeesDao.rol_user;
import model.Suppliers;
import model.SuppliersDao;
import view.SystemView;

public class SuppliersController implements ActionListener, KeyListener, MouseListener {

    private Suppliers supplier;
    private SuppliersDao supplierDao;
    private SystemView views;
    String roll = rol_user;
    DefaultTableModel model = new DefaultTableModel();

    public SuppliersController(Suppliers supplier, SuppliersDao supplierDao, SystemView views) {
        this.supplier = supplier;
        this.supplierDao = supplierDao;
        this.views = views;
        //Botón registrar suplidor
        this.views.btn_register_supplier.addActionListener(this);
        //Botón modificar suplidor
        this.views.btn_update_supplier.addActionListener(this);
        //Botón modificar suplidor
        this.views.btn_delete_supplier.addActionListener(this);
        //Botón modificar suplidor
        this.views.btn_cancel_supplier.addActionListener(this);
        //Buscador
        this.views.txt_search_supplier.addKeyListener(this);
        //Tabla de datos
        this.views.supplier_table.addMouseListener(this);
        //Lable del menú
        this.views.lbl_Suppliers.addMouseListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == views.btn_register_supplier) {
            //Verificar si algún campo está vacío
            if (views.txt_supplier_name.getText().equals("")
                    || views.txt_supplier_address.getText().equals("")
                    || views.txt_supplier_description.getText().equals("")
                    || views.txt_supplier_email.getText().equals("")
                    || views.txt_supplier_telephone.getText().equals("")
                    || views.cmb_supplier_city.getSelectedItem().toString().equals("")) {
                JOptionPane.showMessageDialog(null, "Todos los datos son obligartorios");
            } else {
                //Insertar datos
                supplier.setName(views.txt_supplier_name.getText().trim());
                supplier.setAddress(views.txt_supplier_address.getText().trim());
                supplier.setEmail(views.txt_supplier_email.getText().trim());
                supplier.setTelephone(views.txt_supplier_telephone.getText().trim());
                supplier.setDescription(views.txt_supplier_description.getText().trim());
                supplier.setCity(views.cmb_supplier_city.getSelectedItem().toString());

                if (supplierDao.registerSupplierQuery(supplier)) {
                    clearTable();
                    clearFields();
                    listAllSuppliers();
                    JOptionPane.showMessageDialog(null, "Suplidor registrado exitósamente");
                } else {
                    JOptionPane.showMessageDialog(null, "Ha ocurrido un error al registrar el suplidor");
                }
            }
        } else if (e.getSource() == views.btn_update_supplier) {
            //Verificar si ha seleccionado algún campo
            if (views.txt_supplier_id.equals("")) {
                JOptionPane.showMessageDialog(null, "Debe seleccionar una fila para continuar");
                //Verificar si algún campo está vacío
            } else {
                if (views.txt_supplier_name.getText().equals("")
                        || views.txt_supplier_address.getText().equals("")
                        || views.txt_supplier_description.getText().equals("")
                        || views.txt_supplier_email.getText().equals("")
                        || views.txt_supplier_telephone.getText().equals("")
                        || views.cmb_supplier_city.getSelectedItem().toString().equals("")) {
                    JOptionPane.showMessageDialog(null, "Todos los datos son obligartorios");
                    //Insertar datos
                } else {
                    supplier.setName(views.txt_supplier_name.getText().trim());
                    supplier.setAddress(views.txt_supplier_address.getText().trim());
                    supplier.setEmail(views.txt_supplier_email.getText().trim());
                    supplier.setTelephone(views.txt_supplier_telephone.getText().trim());
                    supplier.setDescription(views.txt_supplier_description.getText().trim());
                    supplier.setCity(views.cmb_supplier_city.getSelectedItem().toString());
                    supplier.setId(Integer.parseInt(views.txt_supplier_id.getText().trim()));

                    if (supplierDao.updateSupplierQuery(supplier)) {
                        clearTable();
                        clearFields();
                        listAllSuppliers();
                        views.btn_register_supplier.setEnabled(true); 
                        JOptionPane.showMessageDialog(null, "Los datos ha sido actualizado exitósamente");
                    } else {
                        JOptionPane.showMessageDialog(null, "Ha ocurrido un error al actualizar los datos");
                    }
                }
            }
        } else if (e.getSource() == views.btn_delete_supplier) {
            int row = views.supplier_table.getSelectedRow();
            //Verificar si ha seleccionado alguna fila
            if (row == -1) {
                JOptionPane.showMessageDialog(null, "No se ha seleccionado ningúna fila para eliminar");
            } else {
                int id = Integer.parseInt(views.supplier_table.getValueAt(row, 0).toString());
                //Mensaje de confirmación 
                int confirm = JOptionPane.showConfirmDialog(null, "¿Desea eliminar este supplidor?");
                if (confirm == 0 && supplierDao.deleteSupplierQuery(id) != false) {
                    clearTable();
                    clearFields();
                    views.btn_register_supplier.setEnabled(true);
                    listAllSuppliers();
                    JOptionPane.showMessageDialog(views, "Suplidor eliminado exitósamente");
                }
            }
        } else if (e.getSource() == views.btn_cancel_supplier) {
            clearFields();
            views.btn_register_supplier.setEnabled(true);
        }

    }

    //Listar Suplidores
    public void listAllSuppliers() {
        List<Suppliers> list = supplierDao.listSupplierQuery(views.txt_search_supplier.getText());
        model = (DefaultTableModel) views.supplier_table.getModel();
        Object[] row = new Object[7];
        for (int i = 0; i < list.size(); i++) {
            row[0] = list.get(i).getId();
            row[1] = list.get(i).getName();
            row[2] = list.get(i).getDescription();
            row[3] = list.get(i).getAddress();
            row[4] = list.get(i).getTelephone();
            row[5] = list.get(i).getEmail();
            row[6] = list.get(i).getCity();
            model.addRow(row);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        //Mostrar datos seleccionados de la tabla
        if (e.getSource() == views.supplier_table) {
            int row = views.supplier_table.rowAtPoint(e.getPoint());
            views.txt_supplier_id.setText(views.supplier_table.getValueAt(row, 0).toString());
            views.txt_supplier_name.setText(views.supplier_table.getValueAt(row, 1).toString());
            views.txt_supplier_description.setText(views.supplier_table.getValueAt(row, 2).toString());
            views.txt_supplier_address.setText(views.supplier_table.getValueAt(row, 3).toString());
            views.txt_supplier_telephone.setText(views.supplier_table.getValueAt(row, 4).toString());
            views.txt_supplier_email.setText(views.supplier_table.getValueAt(row, 5).toString());
            views.cmb_supplier_city.setSelectedItem(views.supplier_table.getValueAt(row, 6).toString());

            //Deshabilitar
            views.btn_register_supplier.setEnabled(false);
            views.txt_supplier_id.setEditable(false);
        }else if(e.getSource() == views.lbl_Suppliers){
            if(roll.equals("Administrador")){
                views.jTabbedPane1.setSelectedIndex(2);
                clearTable();
                clearFields();
                listAllSuppliers();
            }else{
                views.jTabbedPane1.setEnabledAt(2, false);
                views.lbl_Suppliers.setEnabled(false);
                JOptionPane.showMessageDialog(null, "Debe ser administrados para acceder a esta vista");
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    //Buscador
    public void keyReleased(KeyEvent e) {
        if (e.getSource() == views.txt_search_supplier) {
            clearTable();
            listAllSuppliers();
        }
    }

    public void clearFields() {
        views.txt_supplier_id.setText("");
        views.txt_supplier_name.setText("");
        views.txt_supplier_address.setText("");
        views.txt_supplier_description.setText("");
        views.txt_supplier_telephone.setText("");
        views.txt_supplier_email.setText("");
    }

    //Limpiar tabla
    public void clearTable() {
        for (int i = 0; i < model.getRowCount(); i++) {
            model.removeRow(i);
            i = i - 1;
        }
    }

}
