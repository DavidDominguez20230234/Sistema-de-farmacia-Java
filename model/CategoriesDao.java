
package model;

import java.sql.Timestamp;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class CategoriesDao {
    //Instancia de la conexión
    ConnectionMySQL cn = new ConnectionMySQL();
    Connection conn;
    PreparedStatement pst;
    ResultSet rs;
    
    //Registrar categorias
    public boolean registerCategoryQuery(Categories category){
        String query = "INSERT INTO categories (name, created, updated) "
                + "VALUES (?, ?, ?)";
        Timestamp datetime = new Timestamp(new Date().getTime());
        
        try{
            conn = cn.getConnection();
            pst = conn.prepareStatement(query);
            pst.setString(1, category.getName());
            pst.setTimestamp(2, datetime);
            pst.setTimestamp(3, datetime);
            pst.execute();
            return true;
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error al registrar la categoría: " + e);
        }return false;
    }
    
    //Listar categorias
    public List listCategoryQuery(String value){
        List<Categories> list_category = new ArrayList();
        String query = "SELECT * FROM categories";
        String seach_categories_query = "SELECT * FROM categories WHERE name LIKE '%" +value+ "%'";
        try{
            conn = cn.getConnection();
            if(value.equalsIgnoreCase("")){
                pst = conn.prepareStatement(query);
                rs = pst.executeQuery();
            }else{
                pst= conn.prepareStatement(seach_categories_query);
                rs =  pst.executeQuery();
            }
            while(rs.next()){
                Categories category = new Categories();
                category.setId(rs.getInt("id"));
                category.setName(rs.getString("name"));
                list_category.add(category);
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e.toString());
            
        }return list_category;
    }
    
    //Modificar categorias
    public boolean updateCategoryQuery(Categories category){
        String query = "UPDATE categories SET name = ?, updated = ? WHERE id = ";
        Timestamp datetime = new Timestamp(new Date().getTime());
        
        try{
            conn = cn.getConnection();
            pst = conn.prepareStatement(query);
            pst.setString(1, category.getName());
            pst.setTimestamp(2, datetime);
            pst.setInt(3, category.getId());
            pst.execute();
            return true;
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error al modificar la categoría: " + e);
        }return false;
    }
    
        //Elimiar categias
    public boolean deleteCategoryQuery(int id){
        String query = "DELETE FROM categories WHERE id = " + id;
        try{
            conn = cn.getConnection();
            pst = conn.prepareStatement(query);
            pst.execute();
            return true;
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "La categoría tiene relación con otra tabla, no es posible eliminarla.");
        }return false;
    }
    
}
