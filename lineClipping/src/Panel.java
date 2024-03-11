import javax.swing.JPanel;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Line2D;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Panel extends JPanel implements MouseListener {
    Line2D.Double linea;
    Line2D.Double lineaFinal;
    Line2D.Double lineaErronea;
    Line2D.Double xMin;
    Line2D.Double xMax;
    Line2D.Double yMin;
    Line2D.Double yMax;

    public Panel() {
        addMouseListener(this);
        linea = new Line2D.Double();
        xMin = new Line2D.Double();
        xMax = new Line2D.Double();
        yMin = new Line2D.Double();
        yMax = new Line2D.Double();
        lineaErronea = new Line2D.Double();
        lineaFinal = new Line2D.Double();
        this.addMouseListener(this);
        setPreferredSize(new Dimension(600, 500));
        setBackground(Color.WHITE);
    }

    Double maxValue(Double[] a) {
        Double max = a[0];

        for (int i = 0; i < a.length; i++) {
            if(a[i] != null){
                max = Math.max(a[i], max);
            }
        }
        return max;
    }

    Double minValue(Double[] a) {
        Double min = a[0];

        for (int i = 0; i < a.length; i++) {
            if(a[i] != null){
                min = Math.min(a[i], min);
            }
        }

        return min;
    }

    void drawClippingPanel(Graphics g) {
        g.setColor(Color.BLACK);

        xMin.x1 = 63.0; // const
        xMin.y1 = 0;
        xMin.x2 = 63.0; // const
        xMin.y2 = getHeight();

        xMax.x1 = getWidth() - 63.0; // const
        xMax.y1 = 0;
        xMax.x2 = getWidth() - 63.0; // const
        xMax.y2 = getHeight();

        yMin.x1 = 0;
        yMin.y1 = 125.0; // const
        yMin.x2 = getWidth();
        yMin.y2 = 125.0; // const

        yMax.x1 = 0;
        yMax.y1 = getHeight() - 125; // const
        yMax.x2 = getWidth();
        yMax.y2 = getHeight() - 125; // const

        g.drawLine((int) xMin.x1, (int) xMin.y1, (int) xMin.x2, (int) xMin.y2);
        g.drawLine((int) xMax.x1, (int) xMax.y1, (int) xMax.x2, (int) xMax.y2);
        g.drawLine((int) yMin.x1, (int) yMin.y1, (int) yMin.x2, (int) yMin.y2);
        g.drawLine((int) yMax.x1, (int) yMax.y1, (int) yMax.x2, (int) yMax.y2);
    }

    public void lineclipping(Double x1, Double y1, Double x2, Double y2, Double xMin, Double xMax, Double yMin,
            Double yMax, Graphics2D g2d) {
        Double tmin = 0.0;
        Double tmax = 1.0;

        Double[] ps = new Double[4];
        Double[] qs = new Double[4];

        Double p1 = -(x2 - x1);
        Double q1 = x1 - xMin;

        Double p2 = p1 * -1;
        Double q2 = xMax - x1;

        Double p3 = -(y2 - y1);
        Double q3 = y1 - yMin;

        Double p4 = p3 * -1;
        Double q4 = yMax - y1;

        ps[0] = p1;
        ps[1] = p2;
        ps[2] = p3;
        ps[3] = p4;

        qs[0] = q1;
        qs[1] = q2;
        qs[2] = q3;
        qs[3] = q4;

        if (p1 == 0 && q1 < 0 || p2 == 0 && q2 < 0 || p3 == 0 && q3 < 0 || p4 == 0 && q4 < 0) {
            System.out.println("Discard 1");
            lineaErronea.x1 = x1;
            lineaErronea.y1 = y1;
            lineaErronea.x2 = x2;
            lineaErronea.y2 = y2;
            g2d.setColor(Color.RED);
            g2d.draw(lineaErronea);
        } else {
            System.out.println("Accepted");
            Double[] vals = new Double[4];
            Double[] entries = new Double[3];
            Double[] exits = new Double[3];

            Double u1;
            Double u2;

            Double rk1 = q1 / p1;
            Double rk2 = q2 / p2;
            Double rk3 = q3 / p3;
            Double rk4 = q4 / p4;

            vals[0] = rk1;
            vals[1] = rk2;
            vals[2] = rk3;
            vals[3] = rk4;

            int jen = 0;
            int jex = 0;
            for (int i = 0; i < ps.length; i++) {
                if (ps[i] < 0 && jen < entries.length) {
                    entries[jen] = vals[i];
                    jen++;
                } else if (ps[i] > 0 && jex < exits.length) {
                    exits[jex] = vals[i];
                    jex++;
                }
            }

            entries[2] = tmin;
            exits[2] = tmax;

            u1 = this.maxValue(entries);
            u2 = this.minValue(exits);

            System.out.println("u1 =>" + u1);
            System.out.println("u2 =>" + u2);

            // System.out.println("-------------------Max");

            // for (int i = 0; i < entries.length; i++) {
            //     System.out.println(entries[i]);
            // }
            // System.out.println("-------------------Min");

            // for (int i = 0; i < exits.length; i++) {
            //     System.out.println(exits[i]);
            // }

            // System.out.println("Comparacion");
            // System.out.println(Double.compare(u1, u2));

            if (Double.compare(u1, u2) == -1) {

                Double x1Final = x1 + u1 * (x2 - x1);
                Double y1Final = y1 + u1 * (y2 - y1);
                Double x2Final = x1 + u2 * (x2 - x1);
                Double y2Final = y1 + u2 * (y2 - y1);

                lineaFinal.x1 = x1Final;
                lineaFinal.y1 = y1Final;
                lineaFinal.x2 = x2Final;
                lineaFinal.y2 = y2Final;

                g2d.setColor(Color.green);
                g2d.draw(lineaFinal);
                
                if (u1 != 0.0){
                    Line2D.Double SemiLine;
                    SemiLine = new Line2D.Double();
                    
                    SemiLine.x1 = x1;
                    SemiLine.y1 = y1;
                    SemiLine.x2 = x1Final;
                    SemiLine.y2 = y1Final;
                    
                    g2d.setColor(Color.RED);
                    g2d.draw(SemiLine);
                }
                
                if (u2 != 1.0){
                    Line2D.Double SemiLine2;
                    SemiLine2 = new Line2D.Double();
                    
                    SemiLine2.x1 = x2Final;
                    SemiLine2.y1 = y2Final;
                    SemiLine2.x2 = x2;
                    SemiLine2.y2 = y2;
                    
                    g2d.setColor(Color.RED);
                    g2d.draw(SemiLine2);
                } 

            } else {
                System.out.println("Discard 2");

                lineaErronea.x1 = x1;
                lineaErronea.y1 = y1;
                lineaErronea.x2 = x2;
                lineaErronea.y2 = y2;
                g2d.setColor(Color.RED);
                g2d.draw(lineaErronea);
            }

            // System.out.println("t1 " + rk1 + " q " + q1 + " p " + p1);
            // System.out.println("t2 " + rk2 + " q " + q2 + " p " + p2);
            // System.out.println("t3 " + rk3 + " q " + q3 + "p " + p3);
            // System.out.println("t4 " + rk4 + " q " + q4 + " p " + p4);
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        this.drawClippingPanel(g);

        Double x1 = linea.x1;
        Double x2 = linea.x2;
        Double y1 = linea.y1;
        Double y2 = linea.y2;

        lineclipping(x1, y1, x2, y2, xMin.x1, xMax.x1, yMin.y1, yMax.y1, g2d);

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
