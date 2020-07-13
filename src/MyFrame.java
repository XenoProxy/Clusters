import javax.swing.*;
import java.awt.*;
import java.util.List;

public class MyFrame extends JFrame {
    public MyFrame(List<Point> points, boolean c, List<Point> listCPoints){
        int width = 1000;
        int height = 1000;
        setSize(width, height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        JPanel panel = new JPanel() {
            public void paint(Graphics g){
                super.paint(g);
                Graphics2D g2 = (Graphics2D) g;
                for(int k = 0; k < points.size(); k++) {
                    Point p = points.get(k);
                    g2.fillOval((p.getpX() + width /3), (p.getpY() + height /3), 4, 4);
                }
                if (c == true){
                    for(int i = 0; i <listCPoints.size(); i++) {
                        g2.drawOval((listCPoints.get(i).getpX() + width / 3), (listCPoints.get(i).getpY() + height / 3), 130, 130);
                    }
                }
            }
        };
        this.getContentPane().add(panel);
    }
}