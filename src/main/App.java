package main;

import java.awt.*;
import java.awt.event.*;

public class App extends Frame implements Runnable {

    private boolean running = true;
    private Image buffer;
    private Graphics bufferG;
    private final Game game;

    public static int mouseX;
    public static int mouseY;
    public static boolean mousePressed;
    public static boolean prevMousePressed;

    public App() {
        super("Gra w oczko");

        Dimension windowSize = new Dimension(1080, 800);
        setSize(windowSize);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((screenSize.width - getWidth()) / 2, (screenSize.height - getHeight()) / 2);

        setVisible(true);
        setResizable(false);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                shutdown();
            }
        });

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                mousePressed = true;
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                mousePressed = false;
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                mouseX = e.getX();
                mouseY = e.getY();
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                mouseX = e.getX();
                mouseY = e.getY();
            }
        });
        game = new Game(this);
        Thread t = new Thread(this);
        t.setDaemon(true);
        t.start();
    }

    @Override
    public void run() {
        while (running) {
            repaint();
            try {
                Thread.sleep(16);
            } catch (InterruptedException e) {}
            game.update();
            prevMousePressed = mousePressed;
        }
        System.out.println("Loop ended");
    }

    public void shutdown() {
        running = false;
        dispose();
        System.exit(0);
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
        Graphics2D g2 = (Graphics2D) bufferG;
        game.draw(g2);
        g.drawImage(buffer, 0, 0, null);
    }

    public static void main(String[] args) {
        new App();
    }

}
