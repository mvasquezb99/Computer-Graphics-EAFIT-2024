import javax.swing.JPanel;
import java.awt.geom.Line2D;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Panel extends JPanel {

    Double[][] coordinates;
    Double apexes;

    public Panel(Double[][] x) {
        setPreferredSize(new Dimension(600, 500));
        setBackground(Color.WHITE);
        this.coordinates = x;
        this.apexes = this.coordinates[0][0];
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

    public Point2[] makeApexes() {
        int y = this.apexes.intValue();

        Point2[] points = new Point2[y];

        for (int i = 1; i <= apexes; i++) {
            points[i - 1] = new Point2(this.coordinates[i][0], this.coordinates[i][1]);
        }

        return points;
    }

    public void DrawForm(Graphics2D g) {
        int y = this.apexes.intValue();

        Point2[] point2s = this.makeApexes();

        for (int i = y + 2; i < this.coordinates.length; i++) {
            Line2D.Double line;
            line = new Line2D.Double();

            int pStart = this.coordinates[i][0].intValue();
            int pEnd = this.coordinates[i][1].intValue();

            line.x1 = getWidth()/2 - point2s[pStart].getX() ;
            line.y1 = getHeight()/2 - point2s[pStart].getY();
            line.x2 = getWidth()/2 - point2s[pEnd].getX();
            line.y2 = getHeight()/2 - point2s[pEnd].getY();

            g.setColor(Color.ORANGE);
            g.draw(line);
        }
    }

    public void DrawAxis(Graphics g) {
        g.setColor(Color.BLACK);
        g.drawLine(getWidth() / 2, getHeight(), getWidth() / 2, 0);
        g.drawLine(0, getHeight() / 2, getWidth(), getHeight() / 2);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        this.DrawForm(g2d);
        this.DrawAxis(g);
    }
}
