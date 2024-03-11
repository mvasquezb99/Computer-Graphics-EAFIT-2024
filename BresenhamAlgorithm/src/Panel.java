import javax.swing.JPanel;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Line2D;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

public class Panel extends JPanel implements MouseListener {
    Line2D.Double linea;
    private final int pixelSize = 1;

    public Panel() {
        linea = new Line2D.Double();
        this.addMouseListener(this);
        setPreferredSize(new Dimension(600, 500));
        setBackground(Color.WHITE);
    }

    public void DrawAxis(Graphics g ){
        g.drawLine(getWidth()/2, getHeight(), getWidth()/2, 0);
        g.drawLine(0, getHeight()/2, getWidth(), getHeight()/2);
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.DrawAxis(g);

        int x1 = (int) (linea.x1 - (getWidth() / 2));
        int x2 = (int) (linea.x2 - (getWidth() / 2));
        int y1 = (int) ( linea.y1-(getHeight() / 2) );
        int y2 = (int) (linea.y2-(getHeight() / 2) );


        drawLine(g,x1, y1, x2, y2);
    }

    private void plot(Graphics g, int x, int y) {
        int w = (getWidth() - 1) / pixelSize;
        int h = (getHeight() - 1) / pixelSize;
        int maxX = (w - 1) / 2;
        int maxY = (h - 1) / 2;

        int borderX = getWidth() - ((2 * maxX + 1) * pixelSize + 1);
        int borderY = getHeight() - ((2 * maxY + 1) * pixelSize + 1);
        int left = (x + maxX) * pixelSize + borderX / 2;
        int top = (y + maxY) * pixelSize + borderY / 2;

        g.setColor(Color.RED);
        g.drawOval(left, top, pixelSize, pixelSize);
    }

    private void drawLine(Graphics g, int x1, int y1, int x2, int y2) {

        int d = 0;

        int dx = Math.abs(x2 - x1);
        int dy = Math.abs(y2 - y1);

        int dx2 = 2 * dx; 
        int dy2 = 2 * dy; 

        int ix = x1 < x2 ? 1 : -1;
        int iy = y1 < y2 ? 1 : -1;

        int x = x1;
        int y = y1;

        if (dx >= dy) {
            while (true) {
                plot(g, x, y);
                if (x == x2)
                    break;
                x += ix;
                d += dy2;
                if (d > dx) {
                    y += iy;
                    d -= dx2;
                }
            }
        } else {
            while (true) {
                plot(g, x, y);
                if (y == y2)
                    break;
                y += iy;
                d += dx2;
                if (d > dy) {
                    x += ix;
                    d -= dy2;
                }
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        linea.x1 = e.getX();
        linea.y1 = e.getY();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        linea.x2 = e.getX();
        linea.y2 = e.getY();
        repaint();
    }
}
