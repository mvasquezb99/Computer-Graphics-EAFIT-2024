import javax.swing.JPanel;
import java.awt.geom.Line2D;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

import Math.Point4;
import Math.Matrix3;
import Math.Matrix4;
import Math.Point3;

public class Panel extends JPanel implements KeyListener {

    Double[][] coordinates;
    Double apexes;
    Point4[] points4_;
    double angle;

    public Panel(Double[][] x) {
        setPreferredSize(new Dimension(600, 500));
        setBackground(Color.BLACK);
        this.requestFocusInWindow();
        this.setFocusable(true);
        this.addKeyListener(this);
        this.coordinates = x;
        this.apexes = this.coordinates[0][0];
        this.points4_ = this.makeApexes();
    }

    public void printCoords() {
        for (int i = 0; i < this.coordinates.length; i++) {
            if (this.coordinates[i].length >= 3) {
                System.out
                        .println(this.coordinates[i][0] + " " + this.coordinates[i][1] + " " + this.coordinates[i][2]);
            } else {
                System.out.println(this.coordinates[i][0]);
            }
        }
    }

    public Point4[] makeApexes() {
        int y = this.apexes.intValue();

        Point4[] points = new Point4[y];

        for (int i = 1; i <= apexes; i++) {
            points[i - 1] = new Point4(this.coordinates[i][0], this.coordinates[i][1], this.coordinates[i][2], 1.0);
        }

        return points;
    }

    public void DrawForm(Graphics2D g, Point4[] point4s) {
        int y = this.apexes.intValue();

        for (int i = y + 2; i < this.coordinates.length; i++) {
            Line2D.Double line;
            line = new Line2D.Double();

            int pStart = this.coordinates[i][0].intValue();
            int pEnd = this.coordinates[i][1].intValue();

            Double x1, y1, y2, x2, z1, z2;

            z1 = point4s[pStart].getZ();
            z2 = point4s[pEnd].getZ();

            x1 = point4s[pStart].getX() / (z1 / -200);
            y1 = point4s[pStart].getY() / (z1 / -200);
            x2 = point4s[pEnd].getX() / (z2 / -200);
            y2 = point4s[pEnd].getY() / (z2 / -200);

            line.x1 = getWidth() / 2 - x1;
            line.y1 = getHeight() / 2 - y1;
            line.x2 = getWidth() / 2 - x2;
            line.y2 = getHeight() / 2 - y2;

            g.setColor(Color.orange);
            g.draw(line);
        }

    }

    public void DrawAxis(Graphics g) {
        g.setColor(Color.WHITE);
        g.drawLine(getWidth() / 2, getHeight(), getWidth() / 2, 0);
        g.drawLine(0, getHeight() / 2, getWidth(), getHeight() / 2);
    }

    public static Matrix4 MakeOpMatrix(Double Cx, Double Cy, Double Cz, int opt) {
        Matrix4 answMatrix = new Matrix4();

        if (opt == 0) {
            answMatrix.getVector1().setW(Cx);
            answMatrix.getVector2().setW(Cy);
            answMatrix.getVector3().setW(Cz);
        } else if (opt == 1) {
            answMatrix.getVector1().setX(Cx);
            answMatrix.getVector2().setY(Cy);
            answMatrix.getVector3().setZ(Cz);
        } else if (opt == 2) {
            answMatrix.getVector1().setX(Cx);
            answMatrix.getVector1().setY(-1. * Cy);

            answMatrix.getVector2().setX(Cy);
            answMatrix.getVector2().setY(Cx);
        }
        return answMatrix;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        this.DrawForm(g2d, this.points4_);
        this.DrawAxis(g);
    }

    public static void translateObj(Point4[] points4, Matrix4 matrix) {
        for (int i = 0; i < points4.length; i++) {
            points4[i] = matrix.timesP4(points4[i]);
        }
    };

    @Override
    public void keyPressed(KeyEvent e) {
        int tecla = e.getKeyCode();

        if (tecla == KeyEvent.VK_W) {
            Matrix4 move = MakeOpMatrix(-10. * Math.sin(this.angle), 10. * Math.cos(this.angle), 0.0, 0);
            translateObj(points4_, move);
        } else if (tecla == KeyEvent.VK_U) {
            Matrix4 scaleUp = MakeOpMatrix(1.2, 1.2, 1.2, 1);
            translateObj(points4_, scaleUp);
        } else if (tecla == KeyEvent.VK_A) {
            Point4 pCopy = new Point4(points4_[8].getX(), points4_[8].getY(), points4_[8].getZ(), 1.0);

            Matrix4 moveToZero = MakeOpMatrix(-1 * points4_[8].getX(), -1 * points4_[8].getY(), -1 * points4_[8].getZ(),
                    0);
            translateObj(points4_, moveToZero);

            Matrix4 rotZClkw = MakeOpMatrix(Math.cos(-.19), Math.sin(-.19), 0.0, 2);
            translateObj(points4_, rotZClkw);
            this.angle -= .19;
            System.out.println(this.angle);

            Matrix4 moveBack = MakeOpMatrix(pCopy.getX(), pCopy.getY(), pCopy.getZ(), 0);
            translateObj(points4_, moveBack);

        } else if (tecla == KeyEvent.VK_D) {
            Point4 pCopy = new Point4(points4_[8].getX(), points4_[8].getY(), points4_[8].getZ(), 1.0);
            
            Matrix4 moveToZero = MakeOpMatrix(-1 * points4_[8].getX(), -1 * points4_[8].getY(), -1 * points4_[8].getZ(),
                    0);
            translateObj(points4_, moveToZero);

            Matrix4 rotZCclkw = MakeOpMatrix(Math.cos(.19), Math.sin(.19), 0.0, 2);
            translateObj(points4_, rotZCclkw);
            this.angle += .19;

            System.out.println(this.angle);
            Matrix4 moveBack = MakeOpMatrix(pCopy.getX(), pCopy.getY(), pCopy.getZ(), 0);
            translateObj(points4_, moveBack);

        }
        repaint();
        // } else if (tecla == KeyEvent.VK_DOWN) {
        // Matrix3 tDown = MakeOpMatrix(0.0, -10.0, 0.0, 0);
        // translateObj(points4_, tDown);
        // }
        // else if (tecla == KeyEvent.VK_RIGHT) {
        // Matrix3 tR = MakeOpMatrix(-10.0, 0.0, 0);
        // translateObj(points4_, tR);
        // } else if (tecla == KeyEvent.VK_LEFT) {
        // Matrix3 tL = MakeOpMatrix(10.0, 0.0, 0);
        // translateObj(points4_, tL);
        // }

        // else if (tecla == KeyEvent.VK_I) {
        // Matrix3 scaleDown = MakeOpMatrix(0.9, 0.9, 1);
        // translateObj(points4_, scaleDown);
        // } else if (tecla == KeyEvent.VK_D) {
        // Matrix3 rotateClk = MakeOpMatrix(Math.cos(.5), Math.sin(.5), 2);
        // translateObj(points4_, rotateClk);
        // } else if (tecla == KeyEvent.VK_A) {
        // Matrix3 rotateCclk = MakeOpMatrix(Math.cos(-.5), Math.sin(-.5), 2);
        // translateObj(points4_, rotateCclk);
        // } else if (tecla == KeyEvent.VK_S) {
        // Point3 pCopy = new Point3(points4_[0].getX(), points4_[0].getY(), 1.0);

        // Matrix3 moveToZero = MakeOpMatrix(-1 * points4_[0].getX(), -1 *
        // points4_[0].getY(), 0);
        // translateObj(points4_, moveToZero);

        // Matrix3 rotateClk = MakeOpMatrix(Math.cos(.5), Math.sin(.5), 2);
        // translateObj(points4_, rotateClk);

        // Matrix3 moveBack = MakeOpMatrix(pCopy.getX(), pCopy.getY(), 0);
        // translateObj(points4_, moveBack);
        // } else if (tecla == KeyEvent.VK_W) {
        // Double midX = (points4_[0].getX()-points4_[1].getX())/2.0;
        // Double midY = (points4_[1].getY()-points4_[2] .getY())/2.0;

        // Point3 middle = new Point3(midX,midY,1.0);

        // Matrix3 moveToZero = MakeOpMatrix(-1 * middle.getX(), -1 * middle.getY(), 0);
        // translateObj(points4_, moveToZero);

        // }

    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
}
