/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;
import clases.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author leonardo
 */
public class MdCliente {
    DbData dbData;

    public MdCliente() {
        this.dbData = new DbData();
    }

    public boolean crearCliente(Cliente c) {
        try ( Connection conn = DriverManager.getConnection(dbData.getUrl(), 
                dbData.getUser(), dbData.getPassword())) {
            String query = "INSERT INTO cliente (identificacion,nombres,apellidos,"
                    + "direccion,telefono) VALUES (?,?,?,?,?)";
            PreparedStatement statement = conn.prepareStatement(query, 
                    Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, c.getIdentificacion());
            statement.setString(2, c.getNombres());
            statement.setString(3, c.getApellidos());
            statement.setString(4, c.getDireccion());
            statement.setString(5, c.getTelefono());
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {

            return false;
        }

    }

    public Cliente buscarCliente(String identificacion) {
        Cliente c = null;
        try ( Connection conn = DriverManager.getConnection(dbData.getUrl(), 
                dbData.getUser(), dbData.getPassword())) {
            String consulta = "SELECT * FROM cliente AS c "
                    + "WHERE c.identificacion = ?";
            PreparedStatement statement = conn.prepareStatement(consulta, 
                    Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, identificacion);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                //int id = result.getInt(1);
                String identi = result.getString(2);
                String nombres = result.getString(3);
                String apellidos = result.getString(4);
                String direccion = result.getString(5);
                String telefono = result.getString(6);
                c = new Cliente(identi, nombres, apellidos, 
                        direccion, telefono );
            }
            return c;
        } catch (Exception e) {
        }
        return c;
    }

    public boolean actualizarCliente(Cliente c) {
        try ( Connection conn = DriverManager.getConnection(dbData.getUrl(), 
                dbData.getUser(), dbData.getPassword())) {
            String consulta = "UPDATE cliente "
                    + "SET identificacion = ?, nombres = ?, apellidos = ?, "
                    + "direccion = ?, telefono = ? "
                    + "WHERE identificacion = ?";
            PreparedStatement statement = conn.prepareStatement(consulta);
            statement.setString(1, c.getIdentificacion());
            statement.setString(2, c.getNombres());
            statement.setString(3, c.getApellidos());
            statement.setString(4, c.getDireccion());
            statement.setString(5, c.getTelefono());
            // review identicacion when it changes
            statement.setString(6, c.getIdentificacion());
            int filasActualizadas = statement.executeUpdate();
            if (filasActualizadas > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    public boolean borrarCliente(Cliente c) {
        try ( Connection conn = DriverManager.getConnection(dbData.getUrl(), 
                dbData.getUser(), dbData.getPassword())) {
            String consulta = "DELETE FROM cliente WHERE identificacion = ?";
            PreparedStatement statement = conn.prepareStatement(consulta);
            statement.setString(1, c.getIdentificacion());
            int filasActualizadas = statement.executeUpdate();
            if (filasActualizadas > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }
    
    public ArrayList<Cliente>  ListarClientes() {
        ArrayList<Cliente> Clientes = new ArrayList<>();
        Cliente c = null;
        try ( Connection conn = DriverManager.getConnection(dbData.getUrl(), 
                dbData.getUser(), dbData.getPassword())) {
            String consulta = "SELECT * FROM cliente ";
            //PreparedStatement statement = conn.prepareStatement(consulta);
            PreparedStatement statement = conn.prepareStatement(consulta); //,
//                    Statement.RETURN_GENERATED_KEYS);
            //statement.setString(1, identificacion);
            ResultSet result;
            result = statement.executeQuery();
            
            
            while (result.next()) {
                //int id = result.getInt(1);
                String identi = result.getString(2);
                String nombres = result.getString(3);
                String apellidos = result.getString(4);
                String direccion = result.getString(5);
                String telefono = result.getString(6);
                
                c = new Cliente(identi, nombres, apellidos, 
                        direccion, telefono);
                c.setIdCliente(result.getInt(1));
                Clientes.add(c);
            //statement.setString(1, c.getIdentificacion());
            }
        } catch (Exception e) {
            return Clientes; //
        } 
        return Clientes;
    }

    /*  
    Al incluir el IdCLiente en la clase cliente, 
    ya no sería necesario el siguiente método 
    */
    
    public int CatchIdCliente( String identificacion){
        int IdCliente = 0;
        try ( Connection conn = DriverManager.getConnection(dbData.getUrl(), 
                dbData.getUser(), dbData.getPassword())) {
            String consulta = "SELECT idCliente FROM cliente "
                    + "WHERE identificacion = ?";
            //PreparedStatement statement = conn.prepareStatement(consulta);
            PreparedStatement statement = conn.prepareStatement(consulta); //,
//                    Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, identificacion);
            ResultSet result;
            result = statement.executeQuery();

            while (result.next()) {
                //int id = result.getInt(1);
                IdCliente = result.getInt(1);            
            }
        } catch (Exception e) {
            return IdCliente; //
        } 
        return IdCliente;
    }
    
}
