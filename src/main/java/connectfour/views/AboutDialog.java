package connectfour.views;

import java.awt.FlowLayout;

class AboutDialog extends javax.swing.JDialog {
    AboutDialog(java.awt.Frame parent) {
        super(parent, true);
        initComponents();
    }
	
    private void initComponents() {
        javax.swing.JLabel jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("About");
        this.setSize(200, 150);
        setAlwaysOnTop(true);
        setResizable(false);
        FlowLayout layout = new FlowLayout();
        getContentPane().setLayout(layout);
		
        jLabel1.setText("Lorem ipsum dolor sit amet");

        add(jLabel1);

        pack();
    }
}
