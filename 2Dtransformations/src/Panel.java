import javax.swing.JPanel;
import java.awt.geom.Line2D;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

import Math.Matrix3;
import Math.Point3;

public class Panel extends JPanel implements KeyListener {

    Double[][] coordinates;
    Double apexes;
    Point3[] points2_;
    char dir;
    double angle;
    char lastKey;

    public Panel(Double[][] x) {
        setPreferredSize(new Dimension(600, 500));
        setBackground(Color.BLACK);
        this.requestFocusInWindow();
        this.setFocusable(true);
        this.addKeyListener(this);
        this.coordinates = x;
        this.apexes = this.coordinates[0][0];
        this.points2_ = this.makeApexes();
    }

    public void printCoords() {
        for (int i = 0; i < this.coordinates.length; i++) {
            if (this.coordinates[i].length >= 2) {
                System.out.println(this.coordinates[i][0] + " " + this.coordinates[i][1]);
            } else {
                System.out.println(this.coordinates[i][0]);
            }
        }
    }

    public Point3[] makeApexes() {
        int y = this.apexes.intValue();

        Point3[] points = new Point3[y];

        for (int i = 1; i <= apexes; i++) {
            points[i - 1] = new Point3(this.coordinates[i][0], this.coordinates[i][1], 1.0);
        }

        return points;
    }

    public void DrawForm(Graphics2D g, Point3[] point2s) {
        int y = this.apexes.intValue();

        for (int i = y + 2; i < this.coordinates.length; i++) {
            Line2D.Double line;
            line = new Line2D.Double();

            int pStart = this.coordinates[i][0].intValue();
            int pEnd = this.coordinates[i][1].intValue();

            line.x1 = getWidth() / 2 - point2s[pStart].getX();
            line.y1 = getHeight() / 2 - point2s[pStart].getY();
            line.x2 = getWidth() / 2 - point2s[pEnd].getX();
            line.y2 = getHeight() / 2 - point2s[pEnd].getY();

            g.setColor(Color.GREEN);
            g.draw(line);
        }

    }

    public void DrawAxis(Graphics g) {
        g.setColor(Color.WHITE);
        g.drawLine(getWidth() / 2, getHeight(), getWidth() / 2, 0);
        g.drawLine(0, getHeight() / 2, getWidth(), getHeight() / 2);
    }

    public static Matrix3 MakeOpMatrix(Double Cx, Double Cy, int opt) {
        Matrix3 answMatrix = new Matrix3();

        if (opt == 0) {
            answMatrix.getVector1().setZ(Cx);
            answMatrix.getVector2().setZ(Cy);
        } else if (opt == 1) {
            answMatrix.getVector1().setX(Cx);
            answMatrix.getVector2().setY(Cy);
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

        this.DrawForm(g2d, this.points2_);
        this.DrawAxis(g);
    }
    // ! I fucked up, this wasn´t challenge
    // public static void faceDir(Point3[] points, char opt, char dir) {
    // Point3 pCopy = new Point3(points[3].getX(), points[3].getY(), 1.0);

    // Matrix3 moveToZero = MakeOpMatrix(-1 * points[3].getX(), -1 *
    // points[3].getY(), 0);
    // translateObj(points, moveToZero);

    // if (opt == 'd') {
    // if (dir == 'i') {
    // Matrix3 rotateClk = MakeOpMatrix(Math.cos(-Math.PI), Math.sin(-Math.PI), 2);
    // translateObj(points, rotateClk);
    // } else if (dir == 'a') {
    // Matrix3 rotateClk = MakeOpMatrix(Math.cos(-Math.PI / 2), Math.sin(-Math.PI /
    // 2), 2);
    // translateObj(points, rotateClk);
    // } else {
    // Matrix3 rotateClk = MakeOpMatrix(Math.cos(Math.PI / 2), Math.sin(Math.PI /
    // 2), 2);
    // translateObj(points, rotateClk);
    // }
    // } else if (opt == 'u') {
    // if (dir == 'i') {
    // Matrix3 rotateCclk = MakeOpMatrix(Math.cos((Math.PI / 2)), Math.sin((Math.PI
    // / 2)), 2);
    // translateObj(points, rotateCclk);
    // } else if (dir == 'a') {
    // Matrix3 rotateClk = MakeOpMatrix(Math.cos(-1*(3 / 2) * Math.PI),
    // Math.sin(-1*(3 / 2) * Math.PI), 2);
    // translateObj(points, rotateClk);
    // } else {
    // Matrix3 rotateCclk = MakeOpMatrix(Math.cos(-1 * (Math.PI / 2)), Math.sin(-1 *
    // (Math.PI / 2)), 2);
    // translateObj(points, rotateCclk);
    // }
    // } else if (opt == 'i') {
    // if (dir == 'd') {
    // Matrix3 rotateClk = MakeOpMatrix(Math.cos(Math.PI), Math.sin(Math.PI), 2);
    // translateObj(points, rotateClk);
    // } else if (dir == 'a') {
    // Matrix3 rotateClk = MakeOpMatrix(Math.cos(Math.PI / 2), Math.sin(Math.PI /
    // 2), 2);
    // translateObj(points, rotateClk);
    // } else {
    // Matrix3 rotateClk = MakeOpMatrix(Math.cos(-Math.PI / 2), Math.sin(-Math.PI /
    // 2), 2);
    // translateObj(points, rotateClk);
    // }
    // } else if (opt == 'a') {
    // if (dir == 'd') {
    // Matrix3 rotateClk = MakeOpMatrix(Math.cos(-Math.PI / 2), Math.sin(-Math.PI /
    // 2), 2);
    // translateObj(points, rotateClk);
    // } else if (dir == 'i') {
    // Matrix3 rotateClk = MakeOpMatrix(Math.cos(Math.PI / 2), Math.sin(Math.PI /
    // 2), 2);
    // translateObj(points, rotateClk);
    // }
    // Matrix3 rotateClk = MakeOpMatrix(Math.cos((3 / 2) * Math.PI), Math.sin((3 /
    // 2) * Math.PI), 2);
    // translateObj(points, rotateClk);
    // }

    // Matrix3 moveBack = MakeOpMatrix(pCopy.getX(), pCopy.getY(), 0);
    // translateObj(points, moveBack);
    // }

    public static void translateObj(Point3[] points3, Matrix3 matrix) {
        for (int i = 0; i < points3.length; i++) {
            points3[i] = matrix.timesP3(points3[i]);
        }
    };

    @Override
    public void keyPressed(KeyEvent e) {
        int tecla = e.getKeyCode();
        // if (tecla == KeyEvent.VK_UP) {
        // if (this.dir != 'u') {
        // faceDir(points2_, 'u', this.dir);
        // this.dir = 'u';
        // }
        // Matrix3 tUp = MakeOpMatrix(0.0, 10.0, 0);
        // translateObj(points2_, tUp);
        // // } else if (tecla == KeyEvent.VK_DOWN) {
        // if (this.dir != 'a') {
        // faceDir(points2_, 'a', this.dir);
        // this.dir = 'a';
        // }
        // Matrix3 tDown = MakeOpMatrix(0.0, -10.0, 0);
        // translateObj(points2_, tDown);
        // } else if (tecla == KeyEvent.VK_RIGHT) {
        // if (this.dir != 'd') {
        // faceDir(points2_, 'd', this.dir);
        // this.dir = 'd';
        // }
        // Matrix3 tR = MakeOpMatrix(-10.0, 0.0, 0);
        // translateObj(points2_, tR);
        // } else if (tecla == KeyEvent.VK_LEFT) {
        // if (this.dir != 'i') {
        // faceDir(points2_, 'i', this.dir);
        // this.dir = 'i';
        // }
        // Matrix3 tL = MakeOpMatrix(10.0, 0.0, 0);
        // translateObj(points2_, tL);

        if (tecla == KeyEvent.VK_U) {
            Matrix3 scaleUp = MakeOpMatrix(1.2, 1.2, 1);
            translateObj(points2_, scaleUp);
        } else if (tecla == KeyEvent.VK_I) {
            Matrix3 scaleDown = MakeOpMatrix(0.9, 0.9, 1);
            translateObj(points2_, scaleDown);
        } else if (tecla == KeyEvent.VK_S) {
            Matrix3 rotateClk = MakeOpMatrix(Math.cos(.5), Math.sin(.5), 2);
            translateObj(points2_, rotateClk);
        } else if (tecla == KeyEvent.VK_E) {
            Matrix3 rotateCclk = MakeOpMatrix(Math.cos(-.5), Math.sin(-.5), 2);
            translateObj(points2_, rotateCclk);
        } else if (tecla == KeyEvent.VK_A) {
            Point3 pCopy = new Point3(points2_[3].getX(), points2_[3].getY(), 1.0);

            Matrix3 moveToZero = MakeOpMatrix(-1 * points2_[3].getX(), -1 * points2_[3].getY(), 0);
            translateObj(points2_, moveToZero);

            Matrix3 rotateClk = MakeOpMatrix(Math.cos(-.19), Math.sin(-.19), 2);
            translateObj(points2_, rotateClk);
            this.angle -= .19;

            Matrix3 moveBack = MakeOpMatrix(pCopy.getX(), pCopy.getY(), 0);
            translateObj(points2_, moveBack);

        } else if (tecla == KeyEvent.VK_D) {
            Point3 pCopy = new Point3(points2_[3].getX(), points2_[3].getY(), 1.0);

            Matrix3 moveToZero = MakeOpMatrix(-1 * points2_[3].getX(), -1 * points2_[3].getY(), 0);
            translateObj(points2_, moveToZero);

            Matrix3 rotateClk = MakeOpMatrix(Math.cos(.19), Math.sin(.19), 2);
            translateObj(points2_, rotateClk);
            this.angle += .19;

            Matrix3 moveBack = MakeOpMatrix(pCopy.getX(), pCopy.getY(), 0);
            translateObj(points2_, moveBack);
        } else if (tecla == KeyEvent.VK_W) {

            Matrix3 moveFront = MakeOpMatrix(-10. * Math.sin(this.angle), 10. * Math.cos(this.angle), 0);
            translateObj(points2_, moveFront);

        }

        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
}
