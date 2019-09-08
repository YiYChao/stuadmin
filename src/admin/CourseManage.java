package admin;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;

public class CourseManage extends JPanel{

	JPanel mainPanel;
	JPanel menuPanel;
	JLabel location;
	JButton allstu;
	JButton depcls;
	JScrollPane tabPanel;
	JTable jtcourse;
	JLabel jlsearch;
	JTextField jtid;
	JButton jbsearch;
	JButton jbdelete;
	JButton jbadd;
	JButton jbupdate;
	String[][] course;
	public CourseManage() {
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
		location = new JLabel("��ǰλ�ã��γ̹���");
		location.setBounds(10,5,180,20);
		location.setFont(new Font("����",2,18));
		location.setBackground(Color.magenta);
		menuPanel.add(location);
		
		String sql = "select cno,cname,credit,period,semester,tname\r\n" + 
				"from tb_course,tb_teacher\r\n" + 
				"where tb_course.tno=tb_teacher.tno;";
        DBHelper db = new DBHelper(sql);
        int count = 0;
        course = null;
        ResultSet rs;
    	try {
			rs = db.pst.executeQuery();
			while(rs.next()) {
				count++;
			}
			course= new String[count][7];
			rs.beforeFirst();
			int i=0;
			while(rs.next()) {
				course[i][0]=String.valueOf(i+1);
				for(int j=1;j<7;j++) {
					course[i][j]=rs.getString(j);
				}
				i++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
        db.close();
		String[] columnNames = new String[] {"���", "�γ̺�", "�γ�����", "ѧ��","ѧʱ","����ѧ��","�ον�ʦ"};
        jtcourse = new JTable(course, columnNames);
        jtcourse.setBounds(0,0,920,520);
        jtcourse.setPreferredScrollableViewportSize(new Dimension(600, 100));
		//�����ʾ���
		tabPanel = new JScrollPane(jtcourse);
		jtcourse.getColumnModel().getColumn(0).setPreferredWidth(5);
		jtcourse.getColumnModel().getColumn(1).setPreferredWidth(10);
		jtcourse.getColumnModel().getColumn(2).setPreferredWidth(20);
		jtcourse.setRowHeight(30);
		jtcourse.validate();
		//���ñ�����ݾ�����ʾ
		DefaultTableCellRenderer r  = new DefaultTableCellRenderer();   
		r.setHorizontalAlignment(JLabel.CENTER);   
		jtcourse.setDefaultRenderer(Object.class, r);
		tabPanel.setBounds(0,30,920,450);
		
		jlsearch = new JLabel("��Ϣ��ѯ(����ѧ��)��");
		jlsearch.setBounds(180,490,200,30);
		jlsearch.setFont(new Font("����",1,18));
		
		jtid = new JTextField();
		jtid.setBounds(390,490,150,30);
		
		jbsearch = new JButton("��ѯ");
		jbsearch.setBounds(600,490,100,30);
		jbsearch.setFont(new Font("����",1,20));
		jbsearch.addActionListener(new ActSearch());
		
		jbdelete = new JButton("ɾ����ѡ");
		jbdelete.setBounds(230,530,120,30);
		jbdelete.setFont(new Font("����",1,20));
		jbdelete.addActionListener(new ActDelete());
		
		jbadd = new JButton("�����Ϣ");
		jbadd.setBounds(400,530,120,30);
		jbadd.setFont(new Font("����",1,20));
		jbadd.addActionListener(new ActAdd());
		
		jbupdate = new JButton("������Ϣ");
		jbupdate.setBounds(550,530,120,30);
		jbupdate.setFont(new Font("����",1,20));
		jbupdate.addActionListener(new ActUpdate());

		mainPanel.add(menuPanel);
		mainPanel.add(tabPanel);
		mainPanel.add(jlsearch);
		mainPanel.add(jtid);
		mainPanel.add(jbsearch);
		mainPanel.add(jbdelete);
		mainPanel.add(jbadd);
		mainPanel.add(jbupdate);
		mainPanel.setVisible(true);
	}	
	
	//��ѯ��ť���������¼�ʵ��
	class ActSearch implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			for(int i=0;i<jtcourse.getRowCount();i++) {
				if(jtid.getText().equals(course[i][1])) {
					JOptionPane.showMessageDialog(jbsearch, course[i][1]+"~"+course[i][2]+"~"
							+course[i][3]+"~"+course[i][4]+"~"+course[i][5]+"~"+course[i][6]);
				}
			}
		}	
	}
		
	//ɾ����ť���������¼�ʵ��
	class ActDelete implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			int row = jtcourse.getSelectedRow();	//��ȡѡ�е���
			String selectsno =  (String) jtcourse.getValueAt(row, 1);
			String sql1 = "delete from tb_course where cno='"+(String) jtcourse.getValueAt(row, 1)+"'";;
			DBHelper db = new DBHelper(sql1);
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
			jtcourse.validate();
			jtcourse.repaint();
		}
	}
	
	//��Ӱ�ť���������¼�ʵ��
	class ActAdd implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			JDialog jdadd = new JDialog();
			jdadd.setLayout(null);
			jdadd.setTitle("��ӿγ�");
			jdadd.setBounds(500,200,320,350);
			
			JLabel jladd_cno = new JLabel("�γ̺ţ�");
			jladd_cno.setFont(new Font("����",1,20));
			jladd_cno.setBounds(30,20,100,30);
			
			JTextField jtadd_cno = new JTextField();
			jtadd_cno.setFont(new Font("����",2,20));
			jtadd_cno.setBounds(130,20,130,30);
			
			JLabel jladd_cname = new JLabel("�γ�����:");
			jladd_cname.setFont(new Font("����",1,20));
			jladd_cname.setBounds(30,60,100,30);
			
			JTextField jtadd_cname = new JTextField();
			jtadd_cname.setFont(new Font("����",2,20));
			jtadd_cname.setBounds(130,60,130,30);
			
			JLabel jladd_credit = new JLabel("ѧ   ��:");
			jladd_credit.setFont(new Font("����",1,20));
			jladd_credit.setBounds(30,100,100,30);
			
			JTextField jtadd_credit = new JTextField();
			jtadd_credit.setFont(new Font("����",2,20));
			jtadd_credit.setBounds(130,100,130,30);
			
			JLabel jladd_period = new JLabel("ѧ   ʱ:");
			jladd_period.setFont(new Font("����",1,20));
			jladd_period.setBounds(30,140,100,30);
			
			JTextField jtadd_period = new JTextField();
			jtadd_period.setFont(new Font("����",2,20));
			jtadd_period.setBounds(130,140,130,30);
			
			JLabel jladd_semester = new JLabel("����ѧ��:");
			jladd_semester.setFont(new Font("����",1,20));
			jladd_semester.setBounds(30,180,100,30);
			
			JTextField jtadd_semester = new JTextField();
			jtadd_semester.setFont(new Font("����",2,20));
			jtadd_semester.setBounds(130,180,130,30);
			
			JLabel jladd_cdep = new JLabel("�ον�ʦ:");
			jladd_cdep.setFont(new Font("����",1,20));
			jladd_cdep.setBounds(30,220,100,30);
			
			JComboBox combox_cdep = new JComboBox();
			combox_cdep.setFont(new Font("����",2,20));
			combox_cdep.setBounds(130,220,130,30);
			String sql2 = "select tno from tb_teacher";
			String dnos[] = getRow(sql2);
			for(int j=0;j<dnos.length;j++) {
				combox_cdep.addItem(dnos[j]);
			}
			
			JButton certain = new JButton("ȷ��");
			certain.setFont(new Font("����",1,18));
			certain.setBounds(50,270,80,30);
			// ȷ����ť����¼�������Ϣ���뵽���ݿ���
			certain.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					String sql3 = "insert into tb_course values(?,?,?,?,?,?)";
					DBHelper db3 = new DBHelper(sql3);
					int result = 0;
					try {
						db3.pst.setString(1, jtadd_cno.getText());
						db3.pst.setString(2, jtadd_cname.getText());
						db3.pst.setString(3, jtadd_credit.getText());
						db3.pst.setString(4, jtadd_period.getText());
						db3.pst.setString(5, jtadd_semester.getText());
						db3.pst.setString(6, (String)combox_cdep.getSelectedItem());
						result = db3.pst.executeUpdate();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					if(result ==1) {	//��ȡִ�н����������ʾ
						JOptionPane.showMessageDialog(certain, "������Ϣ�ɹ���");
						jtcourse.validate();
					}else {
						JOptionPane.showMessageDialog(certain, "��Ϣ��дʧ�ܣ�");
					}
					jdadd.setVisible(false);	//���ش���
				}
			});
			
			JButton cancel = new JButton("ȡ��");
			cancel.setFont(new Font("����",1,18));
			cancel.setBounds(180,270,80,30);
			//���ȡ����ť��������¼��������ش���
			cancel.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					jdadd.setVisible(false);
				}
			});
			
			jdadd.setVisible(true);
			jdadd.add(jladd_cno);
			jdadd.add(jtadd_cno);
			jdadd.add(jladd_cname);
			jdadd.add(jtadd_cname);
			jdadd.add(jladd_credit);
			jdadd.add(jtadd_credit);
			jdadd.add(jladd_period);
			jdadd.add(jtadd_period);
			jdadd.add(jladd_semester);
			jdadd.add(jtadd_semester);
			jdadd.add(jladd_cdep);
			jdadd.add(combox_cdep);
			jdadd.add(certain);
			jdadd.add(cancel);
		}
	}
	
	//���°�ť���������¼�ʵ��
	class ActUpdate implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			JDialog jdadd = new JDialog();
			jdadd.setLayout(null);
			jdadd.setTitle("���¿γ�");
			jdadd.setBounds(500,200,320,350);
			
			JLabel jladd_cno = new JLabel("�γ̺ţ�");
			jladd_cno.setFont(new Font("����",1,20));
			jladd_cno.setBounds(30,20,100,30);
			
			JComboBox cb_cno = new JComboBox();
			cb_cno.setFont(new Font("����",2,20));
			cb_cno.setBounds(130,20,130,30);
			String sql4 = "select cno from tb_course";
			String[] cnos = getRow(sql4);
			for(int i=0;i<cnos.length;i++) {
				cb_cno.addItem(cnos[i]);
			}
			
			JLabel jladd_cname = new JLabel("�γ�����:");
			jladd_cname.setFont(new Font("����",1,20));
			jladd_cname.setBounds(30,60,100,30);
			
			JTextField jtadd_cname = new JTextField();
			jtadd_cname.setFont(new Font("����",2,20));
			jtadd_cname.setBounds(130,60,130,30);
			
			JLabel jladd_credit = new JLabel("ѧ   ��:");
			jladd_credit.setFont(new Font("����",1,20));
			jladd_credit.setBounds(30,100,100,30);
			
			JTextField jtadd_credit = new JTextField();
			jtadd_credit.setFont(new Font("����",2,20));
			jtadd_credit.setBounds(130,100,130,30);
			
			JLabel jladd_period = new JLabel("ѧ   ʱ:");
			jladd_period.setFont(new Font("����",1,20));
			jladd_period.setBounds(30,140,100,30);
			
			JTextField jtadd_period = new JTextField();
			jtadd_period.setFont(new Font("����",2,20));
			jtadd_period.setBounds(130,140,130,30);
			
			JLabel jladd_semester = new JLabel("����ѧ��:");
			jladd_semester.setFont(new Font("����",1,20));
			jladd_semester.setBounds(30,180,100,30);
			
			JTextField jtadd_semester = new JTextField();
			jtadd_semester.setFont(new Font("����",2,20));
			jtadd_semester.setBounds(130,180,130,30);
			
			JLabel jladd_ctno = new JLabel("�ον�ʦ:");
			jladd_ctno.setFont(new Font("����",1,20));
			jladd_ctno.setBounds(30,220,100,30);
			
			JComboBox combox_ctno = new JComboBox();
			combox_ctno.setFont(new Font("����",2,20));
			combox_ctno.setBounds(130,220,130,30);
			String sql2 = "select tno from tb_teacher";
			String dnos[] = getRow(sql2);
			for(int j=0;j<dnos.length;j++) {
				combox_ctno.addItem(dnos[j]);
			}
			cb_cno.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					//��ʼ���γ̸��±�
					String sql5 = "select * from tb_course where cno='"+(String)cb_cno.getSelectedItem()+"'";
					DBHelper db5 = new DBHelper(sql5);
					ResultSet rs;
					String cse[] = new String[6];
					try {
						rs = db5.pst.executeQuery();
						while(rs.next()) {
							for(int i=0; i<6; i++) {
								cse[i] = rs.getString(i+1);
							}
						}
					} catch (SQLException e) {
						e.printStackTrace();
					}
					jtadd_cname.setText(cse[1]);
					jtadd_credit.setText(cse[2]);
					jtadd_period.setText(cse[3]);
					jtadd_semester.setText(cse[4]);
					combox_ctno.setSelectedItem(cse[5]);
				}
			});
			
			JButton certain = new JButton("ȷ��");
			certain.setFont(new Font("����",1,18));
			certain.setBounds(50,270,80,30);
			// ȷ����ť����¼�������Ϣ���뵽���ݿ���
			certain.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					String sql3 = "update tb_course set cname=?,credit=?,period=?,semester=?,tno=? "
							+ "where cno='"+(String)cb_cno.getSelectedItem()+"'";
					DBHelper db3 = new DBHelper(sql3);
					int result = 0;
					try {
						db3.pst.setString(1, jtadd_cname.getText());
						db3.pst.setString(2, jtadd_credit.getText());
						db3.pst.setString(3, jtadd_period.getText());
						db3.pst.setString(4, jtadd_semester.getText());
						db3.pst.setString(5, (String)combox_ctno.getSelectedItem());
						result = db3.pst.executeUpdate();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					if(result ==1) {	//��ȡִ�н����������ʾ
						JOptionPane.showMessageDialog(certain, "������Ϣ�ɹ���");
						jtcourse.validate();
					}else {
						JOptionPane.showMessageDialog(certain, "��Ϣ��дʧ�ܣ�");
					}
					jdadd.setVisible(false);	//���ش���
				}
			});
			
			JButton cancel = new JButton("ȡ��");
			cancel.setFont(new Font("����",1,18));
			cancel.setBounds(180,270,80,30);
			//���ȡ����ť��������¼��������ش���
			cancel.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					jdadd.setVisible(false);
				}
			});
			
			jdadd.setVisible(true);
			jdadd.add(jladd_cno);
			jdadd.add(cb_cno);
			jdadd.add(jladd_cname);
			jdadd.add(jtadd_cname);
			jdadd.add(jladd_credit);
			jdadd.add(jtadd_credit);
			jdadd.add(jladd_period);
			jdadd.add(jtadd_period);
			jdadd.add(jladd_semester);
			jdadd.add(jtadd_semester);
			jdadd.add(jladd_ctno);
			jdadd.add(combox_ctno);
			jdadd.add(certain);
			jdadd.add(cancel);
		}
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
