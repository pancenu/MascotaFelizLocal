/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import clases.*;
import java.util.ArrayList;
import modelo.MdPago;
/**
 *
 * @author leonardo
 */
public class CtPago {
    MdPago MdPago;
    
    public CtPago() {
        this.MdPago = new MdPago();
    }
    
    public boolean crearPago(Pago pg) {
        try{
            this.MdPago.crearPago(pg);
        }catch (Exception e){
        return false;
        }
        return true;
    }
    
    public Pago buscarPago(int idPago){
        Pago pg;
        pg = this.MdPago.buscarPago(idPago);
        return pg;
    }
    
    public boolean actualizarPago(Pago pg){
        return this.MdPago.actualizarPago(pg);
    }
    
    public boolean borrarPago(Pago pg){
        return this.MdPago.borrarPago(pg);
    }
   
    public ArrayList<Pago> ListarPagos(){
        return this.MdPago.ListarPagos();
    }

    public ArrayList<Registro> ListarRegistros(){
        return this.MdPago.ListarRegistros();
    }
    public ArrayList<ParejaDatos> ListarMascotasPorPlan(){
        return this.MdPago.ListarMascotasPorPlan();
    }
}
