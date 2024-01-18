
package controller;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
