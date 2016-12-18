package Views.subPanel;

import Views.Interfaces.LocalGameCreationView;
import Views.Interfaces.LocalGameCreationViewListener;
import javax.swing.JButton;
import javax.swing.JPanel;

public class LocalGameCreationViewPanel extends JPanel implements LocalGameCreationView {

    private LocalGameCreationViewListener localGameCreationViewListener;

    public LocalGameCreationViewPanel() {
        this.initComponent();
    }

    protected final void initComponent() {
        backButton = new JButton("back");
        resumeGameButton = new JButton("resume");
        newGameButton = new JButton("new");

        backButton.addActionListener(x -> {
            if (this.localGameCreationViewListener != null) {
                this.localGameCreationViewListener.BackPressed();
            }
        });
        
        resumeGameButton.addActionListener(x -> {
            if (this.localGameCreationViewListener != null) {
                this.localGameCreationViewListener.ResumeGamePressed();
            }
        });
        
        newGameButton.addActionListener(x -> {
            if (this.localGameCreationViewListener != null) {
                this.localGameCreationViewListener.NewGamePressed();
            }
        });
        
        this.add(this.backButton);
        this.add(this.resumeGameButton);
        this.add(this.newGameButton);
    }

    private JButton backButton;
    private JButton resumeGameButton;
    private JButton newGameButton;
    
    @Override
    public void setListener(LocalGameCreationViewListener localGameCreationViewListener) {
        this.localGameCreationViewListener = localGameCreationViewListener;
    }
}
