import java.awt.event.*;
/**
 * WindowEventListener
 */
public class WindowEventListener implements WindowListener {
    @Override
    public void windowActivated(WindowEvent e) {
        // TODO Auto-generated method stub
        
    }
    @Override
    public void windowClosed(WindowEvent e) {
        // TODO Auto-generated method stub
        
    }
    @Override
    public void windowClosing(WindowEvent e) {
        // TODO Auto-generated method stub
        System.out.println("The window is closing");
        System.exit(0);
    }
    @Override
    public void windowDeactivated(WindowEvent e) {
        // TODO Auto-generated method stub
        
    }
    @Override
    public void windowDeiconified(WindowEvent e) {
        // TODO Auto-generated method stub
        
    }
    @Override
    public void windowIconified(WindowEvent e) {
        // TODO Auto-generated method stub
        
    }
    @Override
    public void windowOpened(WindowEvent e) {
        // TODO Auto-generated method stub
        
    }
}