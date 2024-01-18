package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import model.Employees;
import model.EmployeesDao;
import view.LoginView;
import view.SystemView;

public class LoginController implements ActionListener {

    private Employees employee;
    private EmployeesDao employees_dao;
    private LoginView login_view;

    public LoginController(Employees employee, EmployeesDao employees_dao, LoginView login_view) {
        this.employee = employee;
        this.employees_dao = employees_dao;
        this.login_view = login_view;
        this.login_view.btn_enter.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String user = login_view.txt_username.getText().trim();
        String pass = String.valueOf(login_view.txt_password.getPassword());

        if (e.getSource() == login_view.btn_enter) {
            //Verificar que lo campos no estén vacíos 
            if (!user.equals("") || !pass.equals("")) {
                //Pasar los parámetros la método login
                employee = employees_dao.loginQuery(user, pass);
                //Verificar la existencia del usuario
                if (employee.getUsername() != null) {
                    if (employee.getRol().equals("Administrador")) {
                        SystemView admin = new SystemView();
                        admin.setVisible(true);
                    } else {
                        SystemView aux = new SystemView();
                        aux.setVisible(true);
                    }
                    this.login_view.dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrecto");
                }

            } else {
                JOptionPane.showMessageDialog(null, "Hay campos vacíos");
            }
        }
    }

}
