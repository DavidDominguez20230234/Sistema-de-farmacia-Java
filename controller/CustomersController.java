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
import model.Customers;
import model.CustomersDao;
import view.SystemView;

public class CustomersController implements ActionListener, MouseListener, KeyListener {

    private Customers customer;
    private CustomersDao customerDao;
    private SystemView views;

    DefaultTableModel model = new DefaultTableModel();

    public CustomersController(Customers customer, CustomersDao customerDao, SystemView views) {
        this.customer = customer;
        this.customerDao = customerDao;
        this.views = views;
        //Botón registrar cliente 
        this.views.btn_register_customer.addActionListener(this);
        //Botón modificar cliente
        this.views.btn_update_customer.addActionListener(this);
        //Botón eliminar cliente
        this.views.btn_delete_customer.addActionListener(this);
        //Botón cancelar cliente
        this.views.btn_cancel_customer.addActionListener(this);
        //Buscador
        this.views.txt_search_customer.addKeyListener(this);
        //Label cliente
        this.views.lbl_Customers.addMouseListener(this);
        this.views.customer_table.addMouseListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == views.btn_register_customer) {
            //Verificar si algún campo está vacío 
            if (views.txt_customer_id.getText().equals("")
                    || views.txt_customer_address.getText().equals("")
                    || views.txt_customer_fullname.getText().equals("")
                    || views.txt_customer_telephone.getText().equals("")
                    || views.txt_customer_email.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Todos los datos son obligatorios");
            } else {
                //Insertar datos
                customer.setId(Integer.parseInt(views.txt_customer_id.getText().trim()));
                customer.setFullname(views.txt_customer_fullname.getText().trim());
                customer.setAddress(views.txt_customer_address.getText().trim());
                customer.setEmail(views.txt_customer_email.getText().trim());
                customer.setTelephone(views.txt_customer_telephone.getText().trim());

                if (customerDao.registerCustomerQuery(customer)) {
                    clearTable();
                    listAllCustomers();
                    JOptionPane.showMessageDialog(null, "Cliente registrado exitosamente");
                } else {
                    JOptionPane.showMessageDialog(null, "Ha ocurrido un error al registrar al cliente");
                }
            }

        }else if(e.getSource() == views.btn_update_customer){
            //Verificar si ha seleccionado alguna fila
            if(views.txt_customer_id.equals("")){
                JOptionPane.showMessageDialog(null, "Debe seleccionar una fila para continuar");
            
            }else{
                //Verificar si los campos están vacíos
                if (views.txt_customer_id.getText().equals("")
                    || views.txt_customer_address.getText().equals("")
                    || views.txt_customer_fullname.getText().equals("")
                    || views.txt_customer_telephone.getText().equals("")
                    || views.txt_customer_email.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios");
                    //Insertar datos
                }else{
                    customer.setId(Integer.parseInt(views.txt_customer_id.getText().trim()));
                    customer.setFullname(views.txt_customer_fullname.getText().trim());
                    customer.setAddress(views.txt_customer_address.getText().trim());
                    customer.setEmail(views.txt_customer_email.getText().trim());
                    customer.setTelephone(views.txt_customer_telephone.getText().trim());
                    
                    if(customerDao.updateCustomerQuery(customer)){
                        clearTable();
                        clearFields();
                        listAllCustomers();
                        views.btn_register_customer.setEnabled(true);
                        JOptionPane.showMessageDialog(null, "Clienten modificado exitósamente");
                    }else{
                        JOptionPane.showMessageDialog(null, "Ha ocurrido un error al modificar al cliente");
                    }
                }   
            }
        
        }else if(e.getSource() == views.btn_delete_customer){
            int row = views.customer_table.getSelectedRow();
            //Verificar si ha seleccionado una fila
            if(row == -1){
                JOptionPane.showMessageDialog(null, "No se ha seleccionado ningún cliente para eliminar");
            }else{
                int id = Integer.parseInt(views.customer_table.getValueAt(row, 0).toString());
                //Mensaje de confirmación
                int confirm = JOptionPane.showConfirmDialog(null, "¿Desea eliminar este cliente?");
                if(confirm == 0 && customerDao.deleteCustomerQuery(id) != false ){
                    clearTable();
                    clearFields();
                    views.btn_register_customer.setEnabled(true);
                    listAllCustomers();
                    JOptionPane.showMessageDialog(null, "Cliente eliminado exitósamente");
                }
            }
            
        }else if(e.getSource() == views.btn_cancel_customer){
            clearFields();
            views.btn_register_customer.setEnabled(true);
            views.txt_customer_id.setEnabled(true);  
        }

    }

    //Listar clientes
    public void listAllCustomers() {
        List<Customers> list = customerDao.listCustomerQuery(views.txt_search_customer.getText());
        model = (DefaultTableModel) views.customer_table.getModel();
        Object[] row = new Object[5];
        for (int i = 0; i < list.size(); i++) {
            row[0] = list.get(i).getId();
            row[1] = list.get(i).getFullname();
            row[2] = list.get(i).getTelephone();
            row[3] = list.get(i).getAddress();
            row[4] = list.get(i).getEmail();
            model.addRow(row);
        }

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        //Mostrar datos seleccionados de la tabla
        if (e.getSource() == views.customer_table) {
            int row = views.customer_table.rowAtPoint(e.getPoint());
            views.txt_customer_id.setText(views.customer_table.getValueAt(row, 0).toString());
            views.txt_customer_fullname.setText(views.customer_table.getValueAt(row, 1).toString());
            views.txt_customer_telephone.setText(views.customer_table.getValueAt(row, 2).toString());
            views.txt_customer_address.setText(views.customer_table.getValueAt(row, 3).toString());
            views.txt_customer_email.setText(views.customer_table.getValueAt(row, 4).toString());

            //Deshabilitar
            views.txt_customer_id.setEditable(false);
            views.btn_register_customer.setEnabled(false);

        }else if(e.getSource() == views.lbl_Customers){
            views.jTabbedPane1.setSelectedIndex(1);
            clearTable();
            clearFields();
            listAllCustomers();
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
    public void keyReleased(KeyEvent e) {
        if (e.getSource() == views.txt_search_customer) {
            clearTable();
            listAllCustomers();
        }
    }
    public void clearFields(){
        views.txt_customer_id.setText("");
        views.txt_customer_id.setEditable(true);
        views.txt_customer_fullname.setText("");
        views.txt_customer_address.setText("");
        views.txt_customer_telephone.setText("");
        views.txt_customer_email.setText("");
    }

    public void clearTable() {
        for (int i = 0; i < model.getRowCount(); i++) {
            model.removeRow(i);
            i = i - 1;
        }
    }
}
