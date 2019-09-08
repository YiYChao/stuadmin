package admin;

import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class Demo extends JFrame{

	static protected JFrame frame;
	public static void main(String[] args) throws Exception {
		frame = new JFrame("学生成绩管理系统");
		frame.setLayout(null);
		frame.setBounds(500, 200, 400, 300);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		Login lg = new Login();
		frame.add(lg.panel);
		frame.repaint();
	}
}
