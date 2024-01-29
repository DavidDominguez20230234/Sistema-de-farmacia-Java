
package controller;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import static model.EmployeesDao.address_user;
import static model.EmployeesDao.email_user;
import static model.EmployeesDao.full_name_user;
import static model.EmployeesDao.id_user;
import static model.EmployeesDao.telephone_user;
import view.SystemView;


public class SettingsControllers implements MouseListener{
    
    private SystemView view;
    
    public SettingsControllers(SystemView view){
        this.view = view;
        this.view.lbl_Products.addMouseListener(this);
        this.view.lbl_Purchases.addMouseListener(this);
        this.view.lbl_Customers.addMouseListener(this);
        this.view.lbl_Employees.addMouseListener(this);
        this.view.lbl_Suppliers.addMouseListener(this);
        this.view.lbl_Reports.addMouseListener(this);
        this.view.lbl_Categories.addMouseListener(this);
        this.view.lbl_Settings.addMouseListener(this);
        Profile();
    }
    //Asignar el perfil del usuario 
    public void Profile(){
        this.view.txt_id_profile.setText("" + id_user);
        this.view.txt_name_profile.setText("" + full_name_user);
        this.view.txt_address_profile.setText("" + address_user);
        this.view.txt_telephone_profile.setText("" + telephone_user);
        this.view.txt_email_profile.setText("" + email_user);
        
    }
    

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if(e.getSource() == view.lbl_Products){
            view.jPanelProducts.setBackground(new Color(152, 202, 63));
        }else if (e.getSource() == view.lbl_Purchases){
            view.jPanelPurchases.setBackground(new Color(152, 202, 63));
        }else if (e.getSource() == view.lbl_Employees){
            view.jPanelEmployees.setBackground(new Color(152, 202, 63));
        }else if (e.getSource() == view.lbl_Reports){
            view.jPanelReports.setBackground(new Color(152, 202, 63));
        }else if (e.getSource() == view.lbl_Categories){
            view.jPanelCategories.setBackground(new Color(152, 202, 63));
        }else if (e.getSource() == view.lbl_Settings){
            view.jPanelSettings.setBackground(new Color(152, 202, 63));
        }else if (e.getSource() == view.lbl_Suppliers){
            view.jPanelSuppliers.setBackground(new Color(152, 202, 63));
        }else if (e.getSource() == view.lbl_Customers){
            view.jPanelCustomers.setBackground(new Color(152, 202, 63));
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if(e.getSource() == view.lbl_Products){
            view.jPanelProducts.setBackground(new Color(0,24,64));
        }else if (e.getSource() == view.lbl_Purchases){
            view.jPanelPurchases.setBackground(new Color(0,24,64));
        }else if (e.getSource() == view.lbl_Employees){
            view.jPanelEmployees.setBackground(new Color(0,24,64));
        }else if (e.getSource() == view.lbl_Reports){
            view.jPanelReports.setBackground(new Color(0,24,64));
        }else if (e.getSource() == view.lbl_Categories){
            view.jPanelCategories.setBackground(new Color(0,24,64));
        }else if (e.getSource() == view.lbl_Settings){
            view.jPanelSettings.setBackground(new Color(0,24,64));
        }else if (e.getSource() == view.lbl_Suppliers){
            view.jPanelSuppliers.setBackground(new Color(0,24,64));
        }else if (e.getSource() == view.lbl_Customers){
            view.jPanelCustomers.setBackground(new Color(0,24,64));
        }
    }
    
}
