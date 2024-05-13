/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import clases.*;
import java.util.ArrayList;
import modelo.MdCliente;

/**
 *
 * @author leonardo
 */
public class CtCliente {
    MdCliente MdCliente;

    public CtCliente() {
        this.MdCliente = new MdCliente();
    }

    public boolean crearCliente(Cliente c) {
        try{
            this.MdCliente.crearCliente(c);
        }catch (Exception e){
        return false;
        }
        return true;
    }

    public Cliente buscarCliente(String identificacion){
        Cliente c= this.MdCliente.buscarCliente(identificacion);
        return c;
    }

    public boolean actualizarCliente(Cliente c){
        return this.MdCliente.actualizarCliente(c);
    }

    public boolean borrarCliente(Cliente c){
        return this.MdCliente.borrarCliente(c);
    }

    public ArrayList<Cliente> ListarClientes(){
        return this.MdCliente.ListarClientes();
    }

    public int CatchIdCliente(String identificacion){
        return this.MdCliente.CatchIdCliente(identificacion);
    }

}