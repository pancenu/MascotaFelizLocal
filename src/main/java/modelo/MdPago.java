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
import javax.swing.table.DefaultTableModel;
import java.lang.Object;
/**
 *
 * @author leonardo
 */
public class MdPago {
    DbData dbData;

    public MdPago() {
        this.dbData = new DbData();
    }

    public boolean crearPago(Pago pg) {
        try ( Connection conn = DriverManager.getConnection(dbData.getUrl(), 
                dbData.getUser(), dbData.getPassword())) {
            String query = "INSERT INTO pago (fechaPago, numeroCuotas, idMascota, "
                    + "idPlan) VALUES (?,?,?,?)";
            PreparedStatement statement = conn.prepareStatement(query, 
                    Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, pg.getFechaPago());
            statement.setInt(2, pg.getNumeroCuotas());
            statement.setInt(3, pg.getIdMascota());
            statement.setInt(4, pg.getIdPlan());
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

    public Pago buscarPago(int idPago) {
        Pago pg = null;
        try ( Connection conn = DriverManager.getConnection(dbData.getUrl(), 
                dbData.getUser(), dbData.getPassword())) {
            String consulta = "SELECT * FROM pago AS pg "
                    + "WHERE pg.idPago= ?";
            PreparedStatement statement = conn.prepareStatement(consulta, 
                    Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, idPago);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                // int idPago = result.getInt(1);
                String fechaPago = result.getString(2);
                int numeroCuotas = result.getInt(3);
                int idMascota = result.getInt(4);
                int idPlan = result.getInt(5);
                
                pg = new Pago(fechaPago, numeroCuotas, idMascota, idPlan );
                pg.setIdPago(idPago);
                //pg.setIdMascota(idMascota);
                //pg.setIdPlan(idPlan);
            }
            return pg;
        } catch (Exception e) {
        }
        return pg;
    }

    public boolean actualizarPago(Pago pg) {
        try ( Connection conn = DriverManager.getConnection(dbData.getUrl(), 
                dbData.getUser(), dbData.getPassword())) {
            String consulta = "UPDATE pago "
                    + "SET fechaPago = ?, numeroCuotas= ?, idMascota= ?, "
                    + "idPlan = ? "
                    + "WHERE idPago= ?";
            PreparedStatement statement = conn.prepareStatement(consulta);
            statement.setString(1, pg.getFechaPago());
            statement.setInt(2, pg.getNumeroCuotas());
            statement.setInt(3, pg.getIdMascota());
            statement.setInt(4, pg.getIdPlan());
            statement.setInt(5, pg.getIdPago());
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

    public boolean borrarPago(Pago pg) {
        try ( Connection conn = DriverManager.getConnection(dbData.getUrl(), 
                dbData.getUser(), dbData.getPassword())) {
            String consulta = "DELETE FROM pago WHERE idPago = ?";
            PreparedStatement statement = conn.prepareStatement(consulta);
            statement.setInt(1, pg.getIdPago());
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
    
    public ArrayList<Pago>  ListarPagos() {
        ArrayList<Pago> Pagos = new ArrayList<>();
        Pago pg = null;
        try ( Connection conn = DriverManager.getConnection(dbData.getUrl(), 
                dbData.getUser(), dbData.getPassword())) {
            String consulta = "SELECT * FROM pago ";
            //PreparedStatement statement = conn.prepareStatement(consulta);
            PreparedStatement statement = conn.prepareStatement(consulta); //,
//                    Statement.RETURN_GENERATED_KEYS);
            //statement.setString(1, identificacion);
            ResultSet result;
            result = statement.executeQuery();

            while (result.next()) {
                //int idMascota = result.getInt(1);
                String fechaPago = result.getString(2);
                int numeroCuotas = result.getInt(3);
                int idMascota = result.getInt(4);
                int idPlan = result.getInt(5);
                //String especie = result.getString(6);
                //int idCliente = result.getInt(7);
                
                pg = new Pago(fechaPago, numeroCuotas, idMascota, idPlan);
                pg.setIdPago(result.getInt(1));
                
                Pagos.add(pg);
            //statement.setString(1, c.getIdentificacion());
            }
        } catch (Exception e) {
            return Pagos; //
        } 
        return Pagos;
    }
    
    public ArrayList<Registro> ListarRegistros(){
        ArrayList<Registro> Registros = new ArrayList<>();
        Registro r = null;
        String consulta = ""
            + "SELECT pago.fechaPago, pago.numeroCuotas, mascota.nombre, plan.nombre, cliente.identificacion  "
            + "FROM pago, mascota, plan, cliente "
            + "WHERE pago.idMascota = mascota.idMascota "
            + "AND pago.idPlan = plan.idPlan "
            + "AND cliente.idCLiente = mascota.idCliente "
            + "ORDER BY mascota.nombre";
            
        try { Connection conn = DriverManager.getConnection(dbData.getUrl(), 
                dbData.getUser(), dbData.getPassword());
            PreparedStatement statement = conn.prepareStatement(consulta,
                Statement.RETURN_GENERATED_KEYS);
            //statement.setString(1, identificacion);
            ResultSet result = statement.executeQuery();
            
            while (result.next()) {
                //int id = result.getInt(1);
                String fechaPago=result.getString(1);
                int numeroCuotas=result.getInt(2);
                String codigoMascota = result.getString(3);
                String codigoPlan=result.getString(4);
                String identificacionCliente=result.getString(5);
                r = new Registro(fechaPago, numeroCuotas, codigoMascota, codigoPlan, identificacionCliente);
                
                Registros.add(r);
            
            }
            
        }catch (Exception e){
        System.err.println("Error, Sentencia no ejecutada error:" + e.getMessage() );
        }
        return Registros;
    }
    
    public ArrayList<ParejaDatos> ListarMascotasPorPlan(){
        ArrayList<ParejaDatos> datos = new ArrayList<>();
        ParejaDatos dato = null;
        String consulta = ""
            + "SELECT plan.nombre , COUNT(pago.idMascota) "
            + "FROM  pago, plan "
            + "WHERE pago.idPlan = plan.idPlan "
            + "GROUP BY plan.nombre ";
        
        try { Connection conn = DriverManager.getConnection(dbData.getUrl(), 
                dbData.getUser(), dbData.getPassword());
            PreparedStatement statement = conn.prepareStatement(consulta,
                Statement.RETURN_GENERATED_KEYS);
            //statement.setString(1, identificacion);
            ResultSet result = statement.executeQuery();
            
            while (result.next()) {
                //int id = result.getInt(1);
                String clase =result.getString(1);
                int cantidad =result.getInt(2);
                dato = new ParejaDatos(clase,cantidad);
                datos.add(dato);
            }
            
        }catch (Exception e){
        System.err.println("Error, Sentencia no ejecutada error:" + e.getMessage() );
        }
        return datos;    
        /*
        "SELECT especie, count(especie) FROM mascota GROUP BY especie"
        */
    }
    
}
