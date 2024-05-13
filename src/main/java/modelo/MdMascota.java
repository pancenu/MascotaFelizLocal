/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author leonardo
 */

import clases.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class MdMascota {    
DbData dbData;

    public MdMascota() {
        this.dbData = new DbData();
    }
    
    public boolean crearMascota(Mascota m) {
        
        try ( Connection conn = DriverManager.getConnection(dbData.getUrl(), 
                dbData.getUser(), dbData.getPassword())) {
            String query = "INSERT INTO mascota (codigo, nombre, anioNace, peso, "
                    + " especie, idCliente) VALUES (?,?,?,?,?,?)";
            PreparedStatement statement = conn.prepareStatement(query, 
                    Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, m.getCodigo());
            statement.setString(2, m.getNombre());
            statement.setInt(3, m.getAnioNace());
            statement.setFloat(4, m.getPeso());
            statement.setString(5, m.getEspecie());
            statement.setInt(6, m.getIdCliente());
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

    public Mascota buscarMascota(String codigo) {
        Mascota m = null;
        try ( Connection conn = DriverManager.getConnection(dbData.getUrl(), 
                dbData.getUser(), dbData.getPassword())) {
            String consulta = "SELECT * FROM mascota AS m "
                    + "WHERE m.codigo = ?";
            PreparedStatement statement = conn.prepareStatement(consulta, 
                    Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, codigo);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                //int id = result.getInt(1);
                String ncodigo = result.getString(2);
                String nombre = result.getString(3);
                int  anioNace = result.getInt(4);
                float  peso = result.getFloat(5);
                String  especie = result.getString(6);
                int idCliente = result.getInt(7);
                m = new Mascota(ncodigo, nombre, anioNace, 
                        peso, especie, idCliente );
            }
            return m;
        } catch (Exception e) {
        }
        return m;
    }

    public boolean actualizarMascota(Mascota m) {
        try ( Connection conn = DriverManager.getConnection(dbData.getUrl(), 
                dbData.getUser(), dbData.getPassword())) {
            String consulta = "UPDATE mascota SET codigo = ?, nombre = ?, "
                    + "anioNace = ?, peso = ?, especie = ?, idCliente= ? "
                    + "WHERE codigo = ?";
            PreparedStatement statement = conn.prepareStatement(consulta);
            statement.setString(1, m.getCodigo());
            statement.setString(2, m.getNombre());
            statement.setInt(3, m.getAnioNace());
            statement.setFloat(4, m.getPeso());
            statement.setString(5, m.getEspecie());
            statement.setInt(6, m.getIdCliente());
            // review identicacion when it changes
            //statement.setString(idCliente);
            statement.setString(7, m.getCodigo());
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

    public boolean borrarMascota(Mascota m) {
        try ( Connection conn = DriverManager.getConnection(dbData.getUrl(), 
                dbData.getUser(), dbData.getPassword())) {
            String consulta = "DELETE FROM mascota WHERE codigo = ?";
            PreparedStatement statement = conn.prepareStatement(consulta);
            statement.setString(1, m.getCodigo());
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
    
     public ArrayList<Mascota>  ListarMascotas() {
        ArrayList<Mascota> Mascotas = new ArrayList<>();
        Mascota m = null;
        try ( Connection conn = DriverManager.getConnection(dbData.getUrl(), 
                dbData.getUser(), dbData.getPassword())) {
            String consulta = "SELECT * FROM mascota ";
            //PreparedStatement statement = conn.prepareStatement(consulta);
            PreparedStatement statement = conn.prepareStatement(consulta); //,
//                    Statement.RETURN_GENERATED_KEYS);
            //statement.setString(1, identificacion);
            ResultSet result;
            result = statement.executeQuery();

            while (result.next()) {
                //int idMascota = result.getInt(1);
                String codigo = result.getString(2);
                String nombre = result.getString(3);
                int anioNace = result.getInt(4);
                float peso = result.getFloat(5);
                String especie = result.getString(6);
                int idCliente = result.getInt(7);
                
                m = new Mascota(codigo, nombre, anioNace, 
                        peso, especie, idCliente);
                m.setIdMascota(result.getInt(1));
                
                
                Mascotas.add(m);
            //statement.setString(1, c.getIdentificacion());
            }
        } catch (Exception e) {
            return Mascotas; //
        } 
        return Mascotas;
    }

}

