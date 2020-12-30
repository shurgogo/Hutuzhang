package gui.panel;

import javax.swing.*;

public abstract class WorkingPanel extends JPanel {
    public abstract void updateData();
    abstract void addListener();
}
