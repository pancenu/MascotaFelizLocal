/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clases;

import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author leonardo
 */
public class Registro {
    //pago.fechaPago, pago.numeroCuotas, mascota.codigo, plan.codigo
    private String fechaPago;
    private int numeroCuotas;
    private String codigoMascota;
    private String codigoPlan;
    private String identificacionCliente;

    public Registro(String fechaPago, int numeroCuotas, String codigoMascota, 
            String codigoPlan, String identificacionCliente) {
        this.fechaPago = fechaPago;
        this.numeroCuotas = numeroCuotas;
        this.codigoMascota = codigoMascota;
        this.codigoPlan = codigoPlan;
        this.identificacionCliente = identificacionCliente;
    }
    
    /**
     * @return the fechaPago
     */
    public String getFechaPago() {
        return fechaPago;
    }

    /**
     * @param fechaPago the fechaPago to set
     */
    public void setFechaPago(String fechaPago) {
        this.fechaPago = fechaPago;
    }

    /**
     * @return the numeroCuotas
     */
    public int getNumeroCuotas() {
        return numeroCuotas;
    }

    /**
     * @param numeroCuotas the numeroCuotas to set
     */
    public void setNumeroCuotas(int numeroCuotas) {
        this.numeroCuotas = numeroCuotas;
    }

    /**
     * @return the codigoMascota
     */
    public String getCodigoMascota() {
        return codigoMascota;
    }

    /**
     * @param codigoMascota the codigoMascota to set
     */
    public void setCodigoMascota(String codigoMascota) {
        this.codigoMascota = codigoMascota;
    }

    /**
     * @return the codigoPlan
     */
    public String getCodigoPlan() {
        return codigoPlan;
    }

    /**
     * @param codigoPlan the codigoPlan to set
     */
    public void setCodigoPlan(String codigoPlan) {
        this.codigoPlan = codigoPlan;
    }

    /**
     * @return the identificacionCliente
     */
    public String getIdentificacionCliente() {
        return identificacionCliente;
    }

    /**
     * @param identificacionCliente the identificacionCliente to set
     */
    public void setIdentificacionCliente(String identificacionCliente) {
        this.identificacionCliente = identificacionCliente;
    }
}
