import java.awt.*;
import java.awt.event.*;

public class App extends Frame implements Runnable {

    private boolean running = true;
    private Image buffer;
    private Graphics bufferG;

    private Font baseFont;


    public App() {
        super("Gra w oczko");

        Dimension windowSize = new Dimension(1080, 800);
        setSize(windowSize);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((screenSize.width - getWidth()) / 2, (screenSize.height - getHeight()) / 2);

        setVisible(true);
        setResizable(false);

        // Zamknięcie okna
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                running = false;
                dispose();
            }
        });

        baseFont = new Font("Arial", Font.BOLD, 32);

        // Start pętli
        new Thread(this).start();
    }

    @Override
    public void run() {
        while (running) {

            repaint();
            try {
                Thread.sleep(16); // ~60 FPS
            } catch (InterruptedException e) {}
        }
    }

    @Override
    public void update(Graphics g) {
        paint(g);
    }

    @Override
    public void paint(Graphics g) {
        if (buffer == null) {
            buffer = createImage(getWidth(), getHeight());
            bufferG = buffer.getGraphics();
        }
        bufferG.setFont(baseFont);
        bufferG.setColor(Color.BLACK);
        bufferG.fillRect(0, 0, getWidth(), getHeight());

        bufferG.setColor(Color.WHITE);
        bufferG.drawString("Gra w oczko", 10, 70);

        g.drawImage(buffer, 0, 0, null);
    }

    public static void main(String[] args) {
        new App();
    }
}
