/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alexprom.uppg_reports;

import java.io.File;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.EntityManager;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;
import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;

/**
 *
 * @author Lenovo
 */
public class UPPG_ShiftReport extends javax.swing.JPanel {

    private String reportSource = /*System.getProperty("user.dir")+File.separator+*/"report_templates"+File.separator+"report1.jrxml";
    //private String reportDest = "./report_results/simple.html";
    private Map<String, Object> params;
    
    public UPPG_ShiftReport(EntityManager em, long id) {
        initComponents();
        
        this.params = new HashMap<>();
        try{
            JasperReport jasperReport = JasperCompileManager.compileReport(reportSource);
            
                em.getTransaction().begin();
            
            Connection connection = em.unwrap(Connection.class);
            
            params.put("actID", id);            
            params.put("SUBREPORT_DIR", "report_templates"+File.separator);            
            try {
                JasperPrint jasperPrint;
                jasperPrint = JasperFillManager.fillReport(jasperReport, params, connection); 
                JasperViewer jasperView = new JasperViewer(jasperPrint, false);
                jasperView.setTitle("Печать акта переработки за смену");
                jasperView.setVisible(true);
                //this.add(viewer);
            } catch (Exception ex) {
                NotifyDescriptor d = new NotifyDescriptor.Message(ex, NotifyDescriptor.ERROR_MESSAGE);
                Object result = DialogDisplayer.getDefault().notify(d);
            }
            em.getTransaction().rollback();
            
        }catch (JRException ex){
            NotifyDescriptor d = new NotifyDescriptor.Message(ex, NotifyDescriptor.ERROR_MESSAGE);
                Object result = DialogDisplayer.getDefault().notify(d);
        }
    
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
