/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import clases.*;
import java.util.ArrayList;
import modelo.MdMascota;

/**
 *
 * @author leonardo
 */
public class CtMascota {
    MdMascota MdMascota;

    public CtMascota() {
        this.MdMascota = new MdMascota();
    }
    

    public boolean crearMascota(Mascota m) {
        try{
            this.MdMascota.crearMascota(m);
        }catch (Exception e){
        return false;
        }
        return true;
    }
    
    public Mascota buscarMascota(String codigo){
        Mascota m;
        m = this.MdMascota.buscarMascota(codigo);
        return m;
    }
    
    public boolean actualizarMascota(Mascota m){
        return this.MdMascota.actualizarMascota(m);
    }
    
    public boolean borrarMascota(Mascota m){
        return this.MdMascota.borrarMascota(m);
    }
   
    public ArrayList<Mascota> ListarMascotas(){
        return this.MdMascota.ListarMascotas();
    }
    
}