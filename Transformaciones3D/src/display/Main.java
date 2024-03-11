package display;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import geometry.PolygonObject;
import geometry.ObjectTransformation;

import java.awt.Color;
import java.awt.Graphics;

public class Main extends JPanel
    implements KeyListener {

  static final int WIDTH = 800;
  static final int HEIGHT = 600;
  public char direction = 'u';

  Graphics g;

  PolygonObject po;

  public Main() {
    setFocusable(true);
    requestFocusInWindow();
    addKeyListener(this);
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);

    this.g = g;

    g.setColor(Color.GREEN);
    drawOneLine(-100, 0, 100, 0);

    g.setColor(Color.GREEN);
    drawOneLine(0, -100, 0, 100);

    g.setColor(Color.BLACK);
    po.transformObject();
    po.projectObject();
    po.draw();

  }

  public void drawOneLine(int x1, int y1, int x2, int y2) {
    x1 = x1 + WIDTH / 2;
    y1 = HEIGHT / 2 - y1;
    x2 = x2 + WIDTH / 2;
    y2 = HEIGHT / 2 - y2;
    g.drawLine(x1, y1, x2, y2);
  }

  public void faceDir(char dir, char opt) {
    switch (opt) {
      case 'd':
        if (dir == 'i') {
          po.ot.thetaZ += ObjectTransformation.PI_ROT;
        } else if (dir == 'a') {
          po.ot.thetaZ += ObjectTransformation.PIHALF_ROT;
        } else {
          po.ot.thetaZ -= ObjectTransformation.PIHALF_ROT;
        }
        direction = 'd';
        break;
      case 'i':
        if (dir == 'd') {
          po.ot.thetaZ -= ObjectTransformation.PI_ROT;
        } else if(dir == 'a'){
          po.ot.thetaZ -= ObjectTransformation.PIHALF_ROT;
        } 
        else {
          po.ot.thetaZ += ObjectTransformation.PIHALF_ROT;
        }
        direction = 'i';
        break;
      case 'u':
        if (dir == 'd') {
          po.ot.thetaZ += ObjectTransformation.PIHALF_ROT;
        } else if (dir == 'i') {
          po.ot.thetaZ -= ObjectTransformation.PIHALF_ROT;
        } else if (dir == 'a') {
          po.ot.thetaZ += (3 / 2) * ObjectTransformation.PI_ROT;
        }
        direction = 'u';
        break;
      case 'a':
        if (dir == 'd') {
          po.ot.thetaZ -= ObjectTransformation.PIHALF_ROT;
        } else if (dir == 'i') {
          po.ot.thetaZ += ObjectTransformation.PIHALF_ROT;
        } else if (dir == 'u') {
          po.ot.thetaZ += (3 / 2) * ObjectTransformation.PI_ROT;
        }
        direction = 'a';
        break;
      default:
        break;
    }
  }

  @Override
  public void keyTyped(KeyEvent e) {
  }

  @Override
  public void keyPressed(KeyEvent e) {
    int key = e.getKeyCode();
    if (key == KeyEvent.VK_D) {
      if (direction != 'd') {
        faceDir(this.direction, 'd');
      }
      po.ot.dx += ObjectTransformation.DELTA_TRANSL;
      repaint();
    } else if (key == KeyEvent.VK_A) {
      if (direction != 'i') {
        faceDir(this.direction, 'i');
      }
      po.ot.dx -= ObjectTransformation.DELTA_TRANSL;
      repaint();
    } else if (key == KeyEvent.VK_W) {
      if (direction != 'u') {
        faceDir(this.direction, 'u');
      }
      po.ot.dy += ObjectTransformation.DELTA_TRANSL;
      repaint();
    } else if (key == KeyEvent.VK_S) {
      if (direction != 'a') {
        faceDir(this.direction, 'a');
      }
      po.ot.dy -= ObjectTransformation.DELTA_TRANSL;
      repaint();
    } else if (key == KeyEvent.VK_Q) {
      po.ot.sx += ObjectTransformation.DELTA_SCAL;
      po.ot.sy += ObjectTransformation.DELTA_SCAL;
      po.ot.sz += ObjectTransformation.DELTA_SCAL;
      repaint();
    } else if (key == KeyEvent.VK_E) {
      po.ot.sx -= ObjectTransformation.DELTA_SCAL;
      po.ot.sy -= ObjectTransformation.DELTA_SCAL;
      po.ot.sz -= ObjectTransformation.DELTA_SCAL;
      repaint();
    } else if (key == KeyEvent.VK_R) {
      po.ot.thetaX += ObjectTransformation.DELTA_ROT;
      repaint();
    } else if (key == KeyEvent.VK_F) {
      po.ot.thetaX -= ObjectTransformation.DELTA_ROT;
      repaint();
    } else if (key == KeyEvent.VK_T) {
      po.ot.thetaY += ObjectTransformation.DELTA_ROT;
      repaint();
    } else if (key == KeyEvent.VK_G) {
      po.ot.thetaY -= ObjectTransformation.DELTA_ROT;
      repaint();
    } else if (key == KeyEvent.VK_Y) {
      po.ot.thetaZ += ObjectTransformation.DELTA_ROT;
      repaint();
    } else if (key == KeyEvent.VK_H) {
      po.ot.thetaZ -= ObjectTransformation.DELTA_ROT;
      repaint();
    } else if (key == KeyEvent.VK_Z) {
      po.resetVertices();
      repaint();
    }
  }

  @Override
  public void keyReleased(KeyEvent e) {
  }

  public static void main(String[] args) {
    // Crear un nuevo Frame (Ventana)
    JFrame frame = new JFrame("Transformaciones 3xxD");
    // Al cerrar el frame, termina la ejecución de este programa
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    // Agregar un JPanel que se llama Main (esta clase)
    Main main = new Main();
    // Create a PolygonObject
    main.po = new PolygonObject();
    // Reading takes a long time. Read the file before adding the
    // JPanel to the JFrame.
    main.po.readObject("casita3D.txt");
    main.po.setCanvas(main);
    // En true para que el objeto rote y se escale en torno a sí mismo
    // math.TranslScalRot4x4.CENTER_TRANFORMS = true;
    // Agregar el JPanel al frame
    frame.add(main);
    // Asignarle tamaño
    frame.setSize(WIDTH, HEIGHT);
    // Poner el frame en el centro de la pantalla
    frame.setLocationRelativeTo(null);
    // Mostrar el frame
    frame.setVisible(true);

  }
}
