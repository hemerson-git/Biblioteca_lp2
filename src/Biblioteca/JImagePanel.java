package Biblioteca;

//import InterfaceGrafica.*;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class JImagePanel extends javax.swing.JPanel {

    private BufferedImage background;
    
    public JImagePanel() {
        initComponents();
    }

    public JImagePanel(BufferedImage img) {
        this();
        if (img == null)
            throw new NullPointerException("Buffered Image cannot be Null");
        this.background = img;
    }

    public JImagePanel(File fileImage) throws IOException {
        this(ImageIO.read(fileImage));
    }

    public JImagePanel(String fileNameImage) throws IOException {
        this(new File(fileNameImage));
    }

    public void updateBackground(BufferedImage img) {
        if (img == null)
            throw new NullPointerException("Buffered Image cannot be Null");
        this.background = img;
        repaint();
    }

    public void updateBackground(File fileImage) throws IOException {
        updateBackground(ImageIO.read(fileImage));
    }

    public void updateBackground(String fileNameImage) throws IOException {
        updateBackground(new File(fileNameImage));
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D gd = (Graphics2D)g.create();
        gd.drawImage(background, 0, 0, this.getWidth(), this.getHeight(), null);
        gd.dispose();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 634, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 365, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
