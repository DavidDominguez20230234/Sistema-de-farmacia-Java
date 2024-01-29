
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
import model.Employees;
import model.EmployeesDao;
import static model.EmployeesDao.id_user;
import static model.EmployeesDao.rol_user;
import view.SystemView;


public class EmployeesController implements ActionListener, MouseListener, KeyListener {
    private Employees employee;
    private EmployeesDao employees_dao;
    private SystemView views;
    //Rol
    String rol = rol_user;
    DefaultTableModel model = new DefaultTableModel();

    public EmployeesController(Employees employee, EmployeesDao employees_dao, SystemView views) {
        this.employee = employee;
        this.employees_dao = employees_dao;
        this.views = views;
        //Boton de registrar empleados
        this.views.btn_register_employee.addActionListener(this);
        //Botón de modificar empleados
        this.views.btn_update_employee.addActionListener(this);
        //Botón de eliminar empleado
        this.views.btn_delete_employee.addActionListener(this);
        //Botón de cancelar
        this.views.btn_cancel_employee.addActionListener(this);
        //Botón de cambiar la contraseña
        this.views.btn_modify_data.addActionListener(this);
        //Label el menú en escucha
        this.views.lbl_Employees.addMouseListener(this);
        this.views.employee_table.addMouseListener(this);
        //Buscador
        this.views.txt_employee_search.addKeyListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == views.btn_register_employee){
            //Verificar si los campos estan vacios
            if(views.txt_employee_id.getText().equals("")
                    || views.txt_employee_fullname.getText().equals("")
                    || views.txt_employee_username.getText().equals("")
                    || views.txt_employee_address.getText().equals("")
                    || views.txt_employee_telephone.getText().equals("")
                    || views.txt_employee_email.getText().equals("")
                    || views.cmb_employee_roll.getSelectedItem().toString().equals("")
                    ||String.valueOf(views.txt_employee_password.getPassword()).equals("")){
                JOptionPane.showMessageDialog(null, "Todos los datos son obligatorios");
            }else{
                //Insertar los datos
                employee.setId(Integer.parseInt(views.txt_employee_id.getText().trim()));
                employee.setFull_name(views.txt_employee_fullname.getText().trim());
                employee.setUsername(views.txt_employee_username.getText().trim());
                employee.setAddress(views.txt_employee_address.getText().trim());
                employee.setTelephone(views.txt_employee_telephone.getText().trim());
                employee.setEmail(views.txt_employee_email.getText().trim());
                employee.setPassword(String.valueOf(views.txt_employee_password.getPassword()));
                employee.setRol(views.cmb_employee_roll.getSelectedItem().toString());
                
                if(employees_dao.registerEmployeeQuery(employee)){
                    clearTable();
                    clearFields();
                    listAllEmployees();
                    JOptionPane.showMessageDialog(null, "Empleado registrado exitosamente");
                }else{
                    JOptionPane.showMessageDialog(null, "Ha ocurrido un error al registrar al empleado");
                }
                
            }
                
                        
        }else if (e.getSource() == views.btn_update_employee){
            //Verificar si se ha seleccionado alguna fila
            if(views.txt_employee_id.equals("")){
                JOptionPane.showMessageDialog(null, "Debe seleccionar una fila para continuar");
                
            }else {
                //Verificar si los campos estan vacios
                if(views.txt_employee_id.getText().equals("")
                        ||views.txt_employee_fullname.getText().equals("")
                        ||views.cmb_employee_roll.getSelectedItem().toString().equals("")){
                        
                    JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios");
            }else{
                //Insertar los datos
                employee.setId(Integer.parseInt(views.txt_employee_id.getText().trim()));
                employee.setFull_name(views.txt_employee_fullname.getText().trim());
                employee.setUsername(views.txt_employee_username.getText().trim());
                employee.setAddress(views.txt_employee_address.getText().trim());
                employee.setTelephone(views.txt_employee_telephone.getText().trim());
                employee.setEmail(views.txt_employee_email.getText().trim());
                employee.setPassword(String.valueOf(views.txt_employee_password.getPassword()));
                employee.setRol(views.cmb_employee_roll.getSelectedItem().toString());  
                
                if(employees_dao.updateEmployeeQuery(employee)){
                    clearTable();
                    clearFields();
                    listAllEmployees();
                    views.btn_register_employee.setEnabled(true);
                    JOptionPane.showMessageDialog(null, "Los datos han sido modificados exitosamente");    
                }else{
                    JOptionPane.showMessageDialog(null, "Ha ocurrido un error al modificar al empleado");
                }
            }
            
         }
      }else if(e.getSource() == views.btn_delete_employee){
          int row = views.employee_table.getSelectedRow();
          //Verificar si se ha seleccionado alguna fila
          if (row == -1){
              JOptionPane.showMessageDialog(null, "No se ha seleccionado ningún empleado para eliminar");
              //Verificar si se ha su mismo usuario
          }else if(views.employee_table.getValueAt(row, 0).equals(id_user)){
              JOptionPane.showMessageDialog(null, "No puede eliminar su usuario");     
          }else{
              int id = Integer.parseInt(views.employee_table.getValueAt(row, 0).toString());
              //Mensaje de confirmación
              int confirm = JOptionPane.showConfirmDialog(null, "¿Desea eliminar este empleado?");
              
              if(confirm == 0 && employees_dao.deleteEmployeeQuery(id) != false){
                clearTable();
                clearFields();
                views.btn_register_employee.setEnabled(true);
                views.txt_employee_password.setEnabled(true);
                listAllEmployees();
                JOptionPane.showMessageDialog(null, "Empleado eliminado exitosamente");
                }
              
          }  
       }else if(e.getSource() == views.btn_cancel_employee){
           clearFields();
           views.btn_register_employee.setEnabled(true);
           views.txt_employee_password.setEnabled(true);
           views.txt_employee_id.setEnabled(true);
       }else if(e.getSource() == views.btn_modify_data){
           //Obtener información de text field password
           String password = String.valueOf(views.txt_password_midify.getPassword());
           String confirm_password = String.valueOf(views.txt_password_modify_confirmed.getPassword());
           //Verificar que los campos no estén vacíos 
           if(!password.equals("") || !confirm_password.equals("")){
               //Verificar que la confiamción conicida con la contraseña
               if(password.equals(confirm_password)){
                   employee.setPassword(String.valueOf(views.txt_password_midify.getPassword()));
                   //Confirmar que la contraseña ha sido modificada
                   if(employees_dao.updateEmployeePasswordQuery(employee) != false){
                       JOptionPane.showMessageDialog(null, "La contraseña ha sido modificada exitosamente");
                   }else {
                       JOptionPane.showMessageDialog(null, "Ha ocurrido un error al modificar la contraseña");
                   } 
               }else{
                   JOptionPane.showMessageDialog(null, "Las contraseñas no coinciden");
               }
           }else{
               JOptionPane.showMessageDialog(null, "Ambos campos son obligatorios");
           }
       }
    }
    
    //Lista empleados
    public void listAllEmployees(){
        if(rol.equals("Administrador")){
            List<Employees> list = employees_dao.listEmployeeQuery(views.txt_employee_search.getText());
            model = (DefaultTableModel) views.employee_table.getModel();
            Object [] row = new Object [7];
            for(int i = 0; i < list.size(); i++){
                row[0] = list.get(i).getId();
                row[1] = list.get(i).getFull_name();
                row[2] = list.get(i).getUsername();
                row[3] = list.get(i).getAddress();
                row[4] = list.get(i).getTelephone();
                row[5] = list.get(i).getEmail();
                row[6] = list.get(i).getRol();
                model.addRow(row);
            }
            
            
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        //Mostrar datos seleccionados de la tabla
        if(e.getSource() == views.employee_table){
            int row = views.employee_table.rowAtPoint(e.getPoint());
            views.txt_employee_id.setText(views.employee_table.getValueAt(row, 0).toString());
            views.txt_employee_fullname.setText(views.employee_table.getValueAt(row, 1).toString());
            views.txt_employee_username.setText(views.employee_table.getValueAt(row, 2).toString());
            views.txt_employee_address.setText(views.employee_table.getValueAt(row, 3).toString());
            views.txt_employee_telephone.setText(views.employee_table.getValueAt(row, 4).toString());
            views.txt_employee_email.setText(views.employee_table.getValueAt(row, 5).toString());
            views.cmb_employee_roll.setSelectedItem(views.employee_table.getValueAt(row, 6).toString());
            
            //Deshabilitar
            views.txt_employee_id.setEditable(false);
            views.txt_employee_password.setEnabled(false);
            views.btn_register_employee.setEnabled(false);

        }else if (e.getSource() == views.lbl_Employees){
            if(rol.equals("Administrador")){
                views.jTabbedPane1.setSelectedIndex(7);
                clearTable();
                clearFields();
                listAllEmployees();
   
            }else{
                views.jTabbedPane1.setEnabledAt(7, false);
                views.lbl_Employees.setEnabled(false);
                JOptionPane.showMessageDialog(null, "Debe ser administrador para acceder a esta vista");
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
    public void keyReleased(KeyEvent e) {
        if(e.getSource() == views.txt_employee_search){
            clearTable();
            listAllEmployees();
        }
    }
    //Limpiar campos
    public void clearFields(){
        views.txt_employee_id.setText("");
        views.txt_employee_id.setEditable(true);
        views.txt_employee_address.setText("");
        views.txt_employee_email.setText("");
        views.txt_employee_fullname.setText("");
        views.txt_employee_password.setText("");
        views.txt_employee_telephone.setText("");
        views.txt_employee_username.setText("");
        views.cmb_employee_roll.setSelectedItem(0);
    }
    //Metodo para limpiar la tablarTable(){
    public void clearTable(){
        for(int i = 0; i < model.getRowCount(); i++){
            model.removeRow(i);
            i = i -1;
        }
    }
}
