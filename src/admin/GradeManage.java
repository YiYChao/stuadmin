package admin;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;

public class GradeManage extends JPanel{

	JPanel mainPanel;
	JPanel menuPanel;
	JLabel location;
	JButton allstu;
	JButton clsstu;
	JScrollPane tabPanel;
	JTable jtstu;
	JLabel jlsearch;
	JTextField jtid;
	JButton jbsearch;
	JButton jbdelete;
	JButton jbadd;
	JButton jbupdate;
	JButton jbpass;
	JLabel jlpass;
	JButton jbfail;
	JLabel jlfail;
	JFrame frame;
	String[][] stu;
	String[] columnNames = new String[] {"���","ѧ��","ѧ������", "�γ�����", "����"};
	public GradeManage() {
		//ѧ���������
		mainPanel = new JPanel();
		mainPanel.setLayout(null);
		mainPanel.setBounds(0, 0, 920, 600);
		mainPanel.setBackground(Color.lightGray);
		
		//�˵������
		menuPanel = new JPanel();
		menuPanel.setLayout(null);
		menuPanel.setBounds(0, 0, 920, 30);
		menuPanel.setBackground(Color.CYAN);
		
		//����λ�ñ�ǩ
		location = new JLabel("��ǰλ�ã��ɼ�����");
		location.setBounds(10,5,180,20);
		location.setFont(new Font("����",2,18));
		location.setBackground(Color.magenta);
		menuPanel.add(location);

		String sql = "select stu_course.sno,sname,cname,score\r\n" + 
				"from tb_student,tb_course,stu_course\r\n" + 
				"where tb_student.sno=stu_course.sno and tb_course.cno=stu_course.cno;";
        DBHelper db = new DBHelper(sql);
        int count =0;
        stu = null;
        ResultSet rs;
    	try {
			rs = db.pst.executeQuery();
			while(rs.next()) {
				count++;
			}
			stu= new String[count][5];
			rs.beforeFirst();
			int i=0;
			while(rs.next()) {
				stu[i][0]=String.valueOf(i+1);
				for(int j=1;j<5;j++) {
					stu[i][j]=rs.getString(j);
				}
				i++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
        db.close();
        jtstu = new JTable(stu, columnNames);
        jtstu.setBounds(0,0,920,500);
        jtstu.setPreferredScrollableViewportSize(new Dimension(600, 100));
		//�����ʾ���
		tabPanel = new JScrollPane(jtstu);
		jtstu.getColumnModel().getColumn(0).setPreferredWidth(10);
		jtstu.getColumnModel().getColumn(1).setPreferredWidth(5);
		jtstu.getColumnModel().getColumn(2).setPreferredWidth(3);
		jtstu.setRowHeight(30);
		jtstu.validate();
		//���ñ�����ݾ�����ʾ
		DefaultTableCellRenderer r  = new DefaultTableCellRenderer();   
		r.setHorizontalAlignment(JLabel.CENTER);   
		jtstu.setDefaultRenderer(Object.class, r);
		tabPanel.setBounds(0,30,920,450);
		
		jlsearch = new JLabel("��Ϣ��ѯ(��������)��");
		jlsearch.setBounds(80,490,200,30);
		jlsearch.setFont(new Font("����",1,18));
		
		jtid = new JTextField();
		jtid.setBounds(290,490,150,30);
		
		jbsearch = new JButton("��ѯ");
		jbsearch.setBounds(500,490,100,30);
		jbsearch.setFont(new Font("����",1,20));
		jbsearch.addActionListener(new ActSearch());
		
		jbdelete = new JButton("ɾ����ѡ");
		jbdelete.setBounds(130,530,120,30);
		jbdelete.setFont(new Font("����",1,20));
		jbdelete.addActionListener(new ActDelete());
		
		jbadd = new JButton("�����Ϣ");
		jbadd.setBounds(300,530,120,30);
		jbadd.setFont(new Font("����",1,20));
		jbadd.addActionListener(new ActAdd());
		
		jbupdate = new JButton("������Ϣ");
		jbupdate.setBounds(450,530,120,30);
		jbupdate.setFont(new Font("����",1,20));
		jbupdate.addActionListener(new ActUpdate());

		jbpass = new JButton("����ѧ��");
		jbpass.setBounds(650,490,120,30);
		jbpass.setFont(new Font("����",1,20));
		jbpass.addActionListener(new ActPass());
		
		jlpass = new JLabel("**��");
		jlpass.setBounds(800,490,50,30);
		jlpass.setFont(new Font("����",2,18));
		jlpass.setBackground(Color.PINK);
		
		jbfail = new JButton("������ѧ��");
		jbfail.setBounds(630,530,140,30);
		jbfail.setFont(new Font("����",1,20));
		jbfail.addActionListener(new ActFail());
		
		jlfail = new JLabel("**��");
		jlfail.setBounds(800,530,50,30);
		jlfail.setFont(new Font("����",2,18));
		jlfail.setBackground(Color.PINK);
		
		mainPanel.add(menuPanel);
		mainPanel.add(tabPanel);
		mainPanel.add(jlsearch);
		mainPanel.add(jtid);
		mainPanel.add(jbsearch);
		mainPanel.add(jbdelete);
		mainPanel.add(jbadd);
		mainPanel.add(jbupdate);
		mainPanel.add(jbpass);
		mainPanel.add(jlpass);
		mainPanel.add(jbfail);
		mainPanel.add(jlfail);
		mainPanel.setVisible(true);
	}
	
	//��ѯ��ť���������¼�ʵ��
	class ActSearch implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			for(int i=0;i<jtstu.getRowCount();i++) {
				if(jtid.getText().equals(stu[i][1])) {
					JOptionPane.showMessageDialog(jbsearch, stu[i][0]+"~"+stu[i][1]+"~"+stu[i][2]+"~"+stu[i][3]+"~"+stu[i][4]);
				}
			}
		}	
	}
	
	//ɾ����ť���������¼�ʵ��
	class ActDelete implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			int row = jtstu.getSelectedRow();	//��ȡѡ�е���
			String selectsno =  (String) jtstu.getValueAt(row, 1);
			String sql1 = "select cno from tb_course where cname='"+(String) jtstu.getValueAt(row, 3)+"'";;
			String selectcno = getRow(sql1)[0];			//���ҿγ����ƶ�Ӧ�Ŀγ̺�
			String sql2 = "delete from stu_course where sno='"+selectsno+"'and cno='"+selectcno+"'";;
			DBHelper db = new DBHelper(sql2);
			int result = 0;
			try {
				result = db.pst.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if(result == 1) {
				JOptionPane.showMessageDialog(jbdelete, "ɾ���ɹ���");
			}else {
				JOptionPane.showMessageDialog(jbdelete, "ɾ��ʧ�ܣ�");
			}
			db.close();
			jtstu.validate();
			jtstu.repaint();
		}
	}
	
	//��Ӱ�ť���������¼�ʵ��
	class ActAdd implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			JDialog jdadd = new JDialog();
			jdadd.setLayout(null);
			jdadd.setTitle("���ѧ���ɼ�");
			jdadd.setBounds(500,300,300,300);
			
			JLabel jladd_sno = new JLabel("ѧ�ţ�");
			jladd_sno.setFont(new Font("����",1,20));
			jladd_sno.setBounds(30,20,70,30);
			
			JComboBox combox = new JComboBox();
			combox.setBounds(100,20,120,30);
			String sql = "select sno from tb_student";
			String snols[] = getRow(sql);
			for(int j=0;j<snols.length;j++) {
				combox.addItem(snols[j]);
			}
			
			JLabel jladd_snm = new JLabel("������");
			jladd_snm.setFont(new Font("����",1,20));
			jladd_snm.setBounds(30,60,70,30);
			
			JLabel jladd_sname = new JLabel();
			jladd_sname.setFont(new Font("����",2,20));
			jladd_sname.setBounds(100,60,120,30);
			//����������Ӽ����¼�
			combox.addItemListener( new ItemListener() {	
				public void itemStateChanged(ItemEvent e) {	
					//����ѡ�е�ѧ�Ų�ѯ��ȡ��Ӧ�����������õ���ǩ
					String sql1 = "select sname from tb_student where sno ='"+snols[combox.getSelectedIndex()]+"'";
					jladd_sname.setText(getRow(sql1)[0]);
				}
			});
			
			JLabel jladd_cs = new JLabel("�γ̣�");
			jladd_cs.setFont(new Font("����",1,20));
			jladd_cs.setBounds(30,100,70,30); 
			
			JComboBox jladd_course = new JComboBox();		//���ÿγ�������
			jladd_course.setBounds(100,100,120,30); 
			String sql2 = "select cname from tb_course";
			String course[] = getRow(sql2);
			for(int j=0;j<course.length;j++) {
				jladd_course.addItem(course[j]);
			}
			
			JLabel jlmarks = new JLabel("������");
			jlmarks.setFont(new Font("����",1,20));
			jlmarks.setBounds(30,140,70,30);
			
			JTextField jtmarks = new JTextField();
			jtmarks.setBounds(100,140,120,30);
			
			JButton certain = new JButton("ȷ��");
			certain.setFont(new Font("����",1,18));
			certain.setBounds(50,200,80,30);
			// ȷ����ť����¼�������Ϣ���뵽���ݿ���
			certain.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					String sql3 = "insert into stu_course values(?,?,?)";
					//��ȡ�γ̺�
					String sql4 = "select cno from tb_course where cname='"+course[jladd_course.getSelectedIndex()]+"'";
					DBHelper db3 = new DBHelper(sql3);
					int result = 0;
					try {
						db3.pst.setString(1, snols[combox.getSelectedIndex()]);
						db3.pst.setString(2, getRow(sql4)[0]);
						db3.pst.setString(3, jtmarks.getText());
						result = db3.pst.executeUpdate();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					if(result ==1) {	//��ȡִ�н����������ʾ
						JOptionPane.showMessageDialog(certain, "������Ϣ�ɹ���");
						jtstu.validate();
					}else {
						JOptionPane.showMessageDialog(certain, "��Ϣ��дʧ�ܣ�");
					}
					jdadd.setVisible(false);	//���ش���
				}
			});
			JButton cancel = new JButton("ȡ��");
			cancel.setFont(new Font("����",1,18));
			cancel.setBounds(180,200,80,30);
			//���ȡ����ť��������¼��������ش���
			cancel.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					jdadd.setVisible(false);
				}
			});
			
			jdadd.setVisible(true);
			jdadd.add(jladd_sno);
			jdadd.add(combox);
			jdadd.add(jladd_snm);
			jdadd.add(jladd_sname);
			jdadd.add(jladd_cs);
			jdadd.add(jladd_course);
			jdadd.add(jlmarks);
			jdadd.add(jtmarks);
			jdadd.add(certain);
			jdadd.add(cancel);
		}
	}
	
	//���°�ť���������¼�ʵ��
	class ActUpdate implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			JDialog jdadd = new JDialog();
			jdadd.setLayout(null);
			jdadd.setTitle("�޸�ѧ���ɼ�");
			jdadd.setBounds(500,300,300,300);
			
			JLabel jladd_sno = new JLabel("ѧ�ţ�");
			jladd_sno.setFont(new Font("����",1,20));
			jladd_sno.setBounds(30,20,70,30);
			
			JComboBox combox_sno = new JComboBox();
			combox_sno.setBounds(100,20,120,30);
			String sql = "select sno from stu_course";
			String snols[] = getRow(sql);
			for(int j=0;j<snols.length;j++) {
				combox_sno.addItem(snols[j]);
			}
			
			JLabel jladd_snm = new JLabel("������");
			jladd_snm.setFont(new Font("����",1,20));
			jladd_snm.setBounds(30,60,70,30);
			
			JLabel jladd_sname = new JLabel();
			jladd_sname.setFont(new Font("����",2,20));
			jladd_sname.setBounds(100,60,120,30);
			
			JLabel jladd_cs = new JLabel("�γ̣�");
			jladd_cs.setFont(new Font("����",1,20));
			jladd_cs.setBounds(30,100,70,30); 
			
			JComboBox jladd_course = new JComboBox();		//���ÿγ�������
			jladd_course.setBounds(100,100,120,30); 
			//��ѧ����������Ӽ����¼�
			combox_sno.addItemListener(new ItemListener() {	
				public void itemStateChanged(ItemEvent e) {	
					//����ѡ�е�ѧ�Ų�ѯ��ȡ��Ӧ�����������õ���ǩ
//					jladd_course.removeAllItems();	//���Ƴ�ԭ�ȵ���Ŀ
					String sql1 = "select sname from tb_student where sno ='"
							+(String)combox_sno.getSelectedItem()+"'";
					jladd_sname.setText(getRow(sql1)[0]);
					String sql2 = "select cname from tb_course where cno = "
							+ "(select cno from stu_course where stu_course.sno ='"
							+(String)combox_sno.getSelectedItem()+"')";
					String []course = getRow(sql2);
//					jladd_course.removeAllItems();		//���Ƴ�ԭ�ȵ���Ŀ
					for(int j=0;j<course.length;j++) {
						jladd_course.addItem(course[j]);
					}
				}
			});
		
			JLabel jlmarks = new JLabel("������");
			jlmarks.setFont(new Font("����",1,20));
			jlmarks.setBounds(30,140,70,30);
			
			JTextField jtmarks = new JTextField();
			String sql3 = "select score from stu_course where sno='"
						+snols[combox_sno.getSelectedIndex()]+"'";
			jtmarks.setBounds(100,140,120,30);
			
			//�γ����������
			jladd_course.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent e) {
					String sql2 = "select cname from tb_course where cno = "
							+ "(select cno from stu_course where stu_course.sno ='"
							+snols[combox_sno.getSelectedIndex()]+"')";
					String []course1 = getRow(sql2);
					String sql5 = "select score from stu_course where sno='"
							+snols[combox_sno.getSelectedIndex()]+"'and cno=(select cno from tb_course where cname='"
							+course1[jladd_course.getSelectedIndex()]+"')";
					jtmarks.setText(getRow(sql5)[0]);
				}
			});
			JButton certain = new JButton("ȷ��");
			certain.setFont(new Font("����",1,18));
			certain.setBounds(50,200,80,30);
			// ȷ����ť����¼�������Ϣ���µ����ݿ���
			certain.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					//ִ�и��µ����
					String sql4 = "update stu_course set score='"+jtmarks.getText()+"'where sno='"+snols[combox_sno.getSelectedIndex()]+"'";
					DBHelper db4 = new DBHelper(sql4);
					int result = 0;
					try {
						result = db4.pst.executeUpdate();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					if(result ==1) {	//��ȡִ�н����������ʾ
						JOptionPane.showMessageDialog(certain, "��Ϣ���³ɹ���");
						jtstu.validate();
					}else {
						JOptionPane.showMessageDialog(certain, "��Ϣ����ʧ�ܣ�");
					}
					jdadd.setVisible(false);	//���ش���
				}
			});
			JButton cancel = new JButton("ȡ��");
			cancel.setFont(new Font("����",1,18));
			cancel.setBounds(180,200,80,30);
			//���ȡ����ť��������¼��������ش���
			cancel.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					jdadd.setVisible(false);
				}
			});
			
			jdadd.setVisible(true);
			jdadd.add(jladd_sno);
			jdadd.add(combox_sno);
			jdadd.add(jladd_snm);
			jdadd.add(jladd_sname);
			jdadd.add(jladd_cs);
			jdadd.add(jladd_course);
			jdadd.add(jlmarks);
			jdadd.add(jtmarks);
			jdadd.add(certain);
			jdadd.add(cancel);
		}
	}
	
	//����������ť���������¼�ʵ��
	class ActPass implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			String sql = "select stu_course.sno,tb_student.sname,tb_course.cname,stu_course.score\r\n" + 
					"from tb_student,tb_course,stu_course\r\n" + 
					"where tb_student.sno=stu_course.sno and tb_course.cno=stu_course.cno and score>'60';";
			int c = showGrade(sql);
			jlpass.setText(String.valueOf(c)+"��");
		}
	}
	
	//������������ť���������¼�ʵ��
	class ActFail implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			String sql = "select stu_course.sno,tb_student.sname,tb_course.cname,stu_course.score\r\n" + 
					"from tb_student,tb_course,stu_course\r\n" + 
					"where tb_student.sno=stu_course.sno and tb_course.cno=stu_course.cno and score<'60';";
			int c = showGrade(sql);
			jlfail.setText(String.valueOf(c)+"��");
		}
	}
	
	//��ʾ�ɼ���
	public int showGrade(String sql) {
		DBHelper db = new DBHelper(sql);
        int count =0;
        ResultSet rs;
    	try {
			rs = db.pst.executeQuery();
			while(rs.next()) {
				count++;
			}
			stu= new String[count][5];
			rs.beforeFirst();
			int i=0;
			while(rs.next()) {
				stu[i][0]=String.valueOf(i+1);
				for(int j=1;j<5;j++) {
					stu[i][j]=rs.getString(j);
				}
				i++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
        db.close();
        tabPanel.remove(jtstu);
        JTable jtstu_fail = new JTable(stu, columnNames);
        jtstu_fail.setBounds(0,0,920,500);
        jtstu_fail.setPreferredScrollableViewportSize(new Dimension(600, 100));
		
        //�����ʾ���
		tabPanel.add(jtstu_fail);
		jtstu_fail.getColumnModel().getColumn(0).setPreferredWidth(10);
		jtstu_fail.getColumnModel().getColumn(1).setPreferredWidth(5);
		jtstu_fail.getColumnModel().getColumn(2).setPreferredWidth(3);
		jtstu_fail.setRowHeight(30);
		jtstu_fail.validate();
		//���ñ�����ݾ�����ʾ
		DefaultTableCellRenderer r  = new DefaultTableCellRenderer();   
		r.setHorizontalAlignment(JLabel.CENTER);   
		jtstu_fail.setDefaultRenderer(Object.class, r);
		tabPanel.setBounds(0,30,920,450);
		return count;
	}
	
	//��ȡһ��Ԫ��
	public String[] getRow(String sql) {
		String myNO[] = null;
		DBHelper db = new DBHelper(sql);
		try {
			ResultSet rs = db.pst.executeQuery();
			int count = 0;
			while(rs.next()) {
				count++;
			}
			myNO = new String[count];
			rs.beforeFirst();
			int i = 0;
			while(rs.next()) {
				myNO[i] = rs.getString(1);
				i++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		db.close();
		return myNO;
	}
}
