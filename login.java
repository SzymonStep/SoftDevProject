import javax.swing.SwingUtilities;

public class login {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable(){
            public void run(){
                new guiTest().setVisible(true);
            }
        });
    }
}