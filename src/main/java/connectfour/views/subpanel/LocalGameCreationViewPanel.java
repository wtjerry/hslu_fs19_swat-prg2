package connectfour.views.subpanel;

import connectfour.views.interfaces.LocalGameCreationView;
import connectfour.views.interfaces.LocalGameCreationViewListener;
import java.awt.Dimension;
import java.text.DecimalFormat;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import javax.swing.LayoutStyle;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;

public class LocalGameCreationViewPanel extends JPanel implements LocalGameCreationView {

    private LocalGameCreationViewListener localGameCreationViewListener;

    public LocalGameCreationViewPanel() {
        this.initComponent();
    }

    private void initComponent() {
        JButton backButton = new JButton("back");
        JButton resumeGameButton = new JButton("resume");
        JButton newGameButton = new JButton("new");
        JFormattedTextField xSizeInput = new JFormattedTextField(new DefaultFormatterFactory(new NumberFormatter(new DecimalFormat("#0"))));
        JFormattedTextField ySizeInput = new JFormattedTextField(new DefaultFormatterFactory(new NumberFormatter(new DecimalFormat("#0"))));
        
        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        xSizeInput.setMaximumSize(new Dimension(100, 20));
        ySizeInput.setMaximumSize(new Dimension(100, 20));
        layout.setHorizontalGroup(
            layout.createParallelGroup(Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(xSizeInput)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ySizeInput))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(backButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(resumeGameButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(newGameButton))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(xSizeInput)
                    .addComponent(ySizeInput))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                //.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 100, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                    .addComponent(backButton)
                    .addComponent(resumeGameButton)
                    .addComponent(newGameButton))
                .addContainerGap())
        );
        
        xSizeInput.setText("6");
        ySizeInput.setText("4");

        backButton.addActionListener(x -> {
            if (this.localGameCreationViewListener != null) {
                this.localGameCreationViewListener.backPressed();
            }
        });
        
        resumeGameButton.addActionListener(x -> {
            if (this.localGameCreationViewListener != null) {
                this.localGameCreationViewListener.resumeGamePressed();
            }
        });
        
        newGameButton.addActionListener(x -> {
            if (this.localGameCreationViewListener != null) {
                this.localGameCreationViewListener.newGamePressed(Integer.parseInt(xSizeInput.getText()),
                        Integer.parseInt(ySizeInput.getText()));
            }
        });
        this.add(xSizeInput);
        this.add(ySizeInput);
        this.add(backButton);
        this.add(resumeGameButton);
        this.add(newGameButton);
    }

    @Override
    public void setListener(LocalGameCreationViewListener localGameCreationViewListener) {
        this.localGameCreationViewListener = localGameCreationViewListener;
    }
}
