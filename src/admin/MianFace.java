package admin;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MianFace extends JPanel{

	JPanel jpmain;
	JPanel jpbanner;
	JLabel jlban_pic;
	JPanel jpnavi;
	JPanel jpshow;
	JLabel jlnavi;
	JPanel jpstu;
	JLabel jlstu_pic;
	JLabel jlstu_wrd;
	JPanel jptea;
	JLabel jltea_pic;
	JLabel jltea_wrd;
	JPanel jpcla;
	JLabel jlcla_pic;
	JLabel jlcla_wrd;
	JPanel jps_c;
	JLabel jls_c_pic;
	JLabel jls_c_wrd;
	JButton logout;
	Demo demo;
	public MianFace() {
		jpmain = new JPanel();
		jpmain.setLayout(null);
		jpmain.setBounds(0, 0, 1000,680);
		jpmain.setBackground(Color.magenta);
		
		jpbanner = new JPanel();
		jpbanner.setLayout(null);
		jpbanner.setBounds(0, 0, 1000, 80);
		jpbanner.setBackground(Color.gray);
		ImageIcon img = new ImageIcon("images/banner.png");
		jlban_pic = new JLabel();
		jlban_pic.setBounds(0, 0, 1000, 80);
		jlban_pic.setIcon(img);
		jpbanner.add(jlban_pic);
		
		jpnavi = new JPanel();
		jpnavi.setLayout(null);
		jpnavi.setBounds(0,80,80,600);
		jpnavi.setBackground(Color.PINK);
		
		jlnavi = new JLabel("<html>功能导航</html>");
		jlnavi.setFont(new Font("黑体",0,30));
		jlnavi.setBackground(Color.gray);
		jlnavi.setBounds(10,0,60,70);
		
		jpstu = new JPanel();
		jpstu.setBounds(10, 80, 60, 100);
		jpstu.setBackground(Color.orange);
		ImageIcon img1 = new ImageIcon("images/student.png");
		jlstu_pic = new JLabel();
		jlstu_pic.setIcon(img1);
		jlstu_wrd = new JLabel("学生管理");
		jpstu.add(jlstu_pic);
		jpstu.add(jlstu_wrd);
		jpstu.addMouseListener(new PanLisn());
		
		jptea = new JPanel();
		jptea.setBounds(10,190,60,100);
		jptea.setBackground(Color.orange);
		ImageIcon img2 = new ImageIcon("images/teacher.png");
		jltea_pic = new JLabel();
		jltea_pic.setIcon(img2);
		jltea_wrd = new JLabel("教师管理");
		jptea.add(jltea_pic);
		jptea.add(jltea_wrd);
		jptea.addMouseListener(new PanLisn());
		
		
		jpcla = new JPanel();
		jpcla.setBounds(10,300,60,100);
		jpcla.setBackground(Color.orange);
		ImageIcon img3 = new ImageIcon("images/class.png");
		jlcla_pic = new JLabel();
		jlcla_pic.setIcon(img3);
		jlcla_wrd = new JLabel("课程管理");
		jpcla.add(jlcla_pic);
		jpcla.add(jlcla_wrd);
		jpcla.addMouseListener(new PanLisn());
		
		jps_c = new JPanel();
		jps_c.setBounds(10,410,60,100);
		jps_c.setBackground(Color.orange);
		ImageIcon img4 = new ImageIcon("images/s_c.png");
		jls_c_pic = new JLabel();
		jls_c_pic.setIcon(img4);
		jls_c_wrd = new JLabel("成绩管理");
		jps_c.add(jls_c_pic);
		jps_c.add(jls_c_wrd);
		jps_c.addMouseListener(new PanLisn());
		

		logout = new JButton("退出");
		logout.setBounds(0,520,80,30);
		logout.setFont(new Font("黑体",1,18));
		logout.addActionListener(new ActLogout());
		
		jpnavi.add(jlnavi);
		jpnavi.add(jpstu);
		jpnavi.add(jptea);
		jpnavi.add(jpcla);
		jpnavi.add(jps_c);
		jpnavi.add(logout);
		
		//显示面板
		jpshow = new JPanel();
		jpshow.setLayout(null);
		jpshow.setBounds(80, 80, 920, 600);
		jpshow.setBackground(Color.lightGray);
		
		jpmain.add(jpbanner);
		jpmain.add(jpnavi);
		jpmain.add(jpshow);
		demo = new Demo();
	}
	
	class PanLisn implements MouseListener{

		StuManage stu = new StuManage();
		CourseManage cls = new CourseManage();
		TeaManager tea = new TeaManager();
		GradeManage grd = new GradeManage();
		public void mouseClicked(MouseEvent me) {
			if(me.getSource().equals(jpstu)) {
				jpshow.removeAll();
				jpshow.add(stu.mainPanel);
				jpshow.repaint();
			}
			if(me.getSource().equals(jptea)) {
				jpshow.removeAll();
				jpshow.validate();
				jpshow.add(tea.mainPanel);
				jpshow.repaint();
			}
			if(me.getSource().equals(jpcla)) {
				jpshow.removeAll();
				jpshow.validate();
				jpshow.add(cls.mainPanel);
				jpshow.repaint();
			}
			if(me.getSource().equals(jps_c)) {
				jpshow.removeAll();
				jpshow.validate();
				jpshow.add(grd.mainPanel);
				jpshow.repaint();
			}
		}

		
		public void mouseEntered(MouseEvent me) {
			
		}

		public void mouseExited(MouseEvent arg0) {
			
		}

		public void mousePressed(MouseEvent arg0) {
			
		}

		public void mouseReleased(MouseEvent arg0) {
			
		}
	}
	
	class ActLogout implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			Demo demo = new Demo();
			Login lg = new Login();
			demo.frame.remove(jpmain);
			demo.frame.setBounds(500, 200, 400, 300);
			demo.frame.add(lg.panel);
			demo.frame.repaint();
			lg.panel.repaint();
			lg.panel.setVisible(true);
//			lg.tfuser.setText(null);
//			lg.pwd.setText(null);
			
		}
		
	}
	
	
}
