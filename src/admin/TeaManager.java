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
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;

import admin.StuManage.ActAdd;
import admin.StuManage.ActDelete;
import admin.StuManage.ActSearch;
import admin.StuManage.ActUpdate;

public class TeaManager extends JPanel{

	JPanel mainPanel;
	JPanel menuPanel;
	JLabel location;
	JButton allstu;
	JButton clsstu;
	JScrollPane tabPanel;
	JTable jttea;
	JLabel jlsearch;
	JTextField jtid;
	JButton jbsearch;
	JButton jbdelete;
	JButton jbadd;
	JButton jbupdate;
	String[][] tea;
	
	public TeaManager() {
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
		location = new JLabel("��ǰλ�ã���ʦ����");
		location.setBounds(10,5,180,20);
		location.setFont(new Font("����",2,18));
		location.setBackground(Color.magenta);
		menuPanel.add(location);
		
		String sql = "select tno,tname,tsex,tbirthday,dname,tnative,thome,ttel\r\n" + 
				" from tb_teacher,tb_department\r\n" + 
				" where tb_teacher.dno=tb_department.dno;";
        DBHelper db = new DBHelper(sql);
        int count =0;
        tea = null;
        ResultSet rs;
    	try {
			rs = db.pst.executeQuery();
			while(rs.next()) {
				count++;
			}
			tea= new String[count][9];
			rs.beforeFirst();
			int i =0;
			while(rs.next()) {
				tea[i][0]=String.valueOf(i+1);
				for(int j=1;j<9;j++) {
					tea[i][j]=rs.getString(j);
				}
				i++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
        db.close();
		String[] columnNames = new String[] { "���","����", "����", "�Ա�", "��������" ,"����ѧԺ","����","סַ","�绰"};
        jttea = new JTable(tea, columnNames);
        jttea.setBounds(0,0,920,520);
        jttea.setPreferredScrollableViewportSize(new Dimension(600, 100));
		//�����ʾ���
		tabPanel = new JScrollPane(jttea);
		jttea.getColumnModel().getColumn(0).setPreferredWidth(3);
		jttea.getColumnModel().getColumn(1).setPreferredWidth(10);
		jttea.getColumnModel().getColumn(2).setPreferredWidth(10);
		jttea.getColumnModel().getColumn(3).setPreferredWidth(3);
		jttea.getColumnModel().getColumn(4).setPreferredWidth(20);
		jttea.getColumnModel().getColumn(5).setPreferredWidth(50);
		jttea.getColumnModel().getColumn(6).setPreferredWidth(30);
		jttea.getColumnModel().getColumn(7).setPreferredWidth(150);
		jttea.getColumnModel().getColumn(8).setPreferredWidth(80);
		jttea.setRowHeight(30);
		jttea.validate();
		//���ñ�����ݾ�����ʾ
		DefaultTableCellRenderer r  = new DefaultTableCellRenderer();   
		r.setHorizontalAlignment(JLabel.CENTER);   
		jttea.setDefaultRenderer(Object.class, r);
		tabPanel.setBounds(0,30,920,450);
		
		jlsearch = new JLabel("��Ϣ��ѯ(���빤��)��");
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
				for(int i=0;i<jttea.getRowCount();i++) {
					if(jtid.getText().equals(tea[i][1])) {
						JOptionPane.showMessageDialog(jbsearch, tea[i][1]+"~"+tea[i][2]+"~"+tea[i][3]
								+"~"+tea[i][4]+"~"+tea[i][5]+"~"+tea[i][6]+"~"+tea[i][7]+"~"+tea[i][8]);
					}
				}
			}	
		}
		
		//ɾ����ť���������¼�ʵ��
		class ActDelete implements ActionListener{
			public void actionPerformed(ActionEvent arg0) {
				int row = jttea.getSelectedRow();	//��ȡѡ�е���
				String selectsno =  (String) jttea.getValueAt(row, 1);
				String sql1 = "delete from tb_teacher where tno='"+(String) jttea.getValueAt(row, 1)+"'";;
				System.out.println(sql1);
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
				jttea.validate();
				jttea.repaint();
			}
		}
		
		//��Ӱ�ť���������¼�ʵ��
		class ActAdd implements ActionListener{
			public void actionPerformed(ActionEvent arg0) {
				JDialog jdadd = new JDialog();
				jdadd.setLayout(null);
				jdadd.setTitle("��ӽ�ʦ��Ϣ");
				jdadd.setBounds(500,150,320,500);
				
				JLabel jladd_sno = new JLabel("��  �ţ�");
				jladd_sno.setFont(new Font("����",1,20));
				jladd_sno.setBounds(30,20,90,30);
				
				JTextField jtadd_sno = new JTextField();
				jtadd_sno.setFont(new Font("����",1,20));
				jtadd_sno.setBounds(130,20,130,30);
				
				JLabel jladd_sname = new JLabel("��  ��:");
				jladd_sname.setFont(new Font("����",1,20));
				jladd_sname.setBounds(30,60,90,30);
				
				JTextField jtadd_sname = new JTextField();
				jtadd_sname.setFont(new Font("����",2,20));
				jtadd_sname.setBounds(130,60,130,30);
				
				JLabel jladd_ssex = new JLabel("��  ��:");
				jladd_ssex.setFont(new Font("����",1,20));
				jladd_ssex.setBounds(30,100,90,30);
				
				JComboBox combox_sex = new JComboBox();
				combox_sex.setFont(new Font("����",2,20));
				combox_sex.setBounds(130,100,130,30);
				combox_sex.addItem("��");
				combox_sex.addItem("Ů");
				
				JLabel jladd_sbirth = new JLabel("��������:");
				jladd_sbirth.setFont(new Font("����",1,20));
				jladd_sbirth.setBounds(30,140,100,30);
				
				JTextField jtadd_sbirth = new JTextField();
				jtadd_sbirth.setFont(new Font("����",2,20));
				jtadd_sbirth.setBounds(130,140,130,30);
				
				JLabel jladd_snative = new JLabel("��  ��:");
				jladd_snative.setFont(new Font("����",1,20));
				jladd_snative.setBounds(30,180,90,30);
				
				JTextField jtadd_snative = new JTextField();
				jtadd_snative.setFont(new Font("����",2,20));
				jtadd_snative.setBounds(130,180,130,30);
				
				JLabel jladd_sdep = new JLabel("ѧ  Ժ:");
				jladd_sdep.setFont(new Font("����",1,20));
				jladd_sdep.setBounds(30,220,90,30);
				
				JComboBox combox_sdep = new JComboBox();
				combox_sdep.setFont(new Font("����",2,20));
				combox_sdep.setBounds(130,220,130,30);
				String sql2 = "select dno from tb_department";
				String dnos[] = getRow(sql2);
				for(int j=0;j<dnos.length;j++) {
					combox_sdep.addItem(dnos[j]);
				}
				
				JLabel jladd_stel = new JLabel("��  ��:");
				jladd_stel.setFont(new Font("����",1,20));
				jladd_stel.setBounds(30,260,90,30);
				
				JTextField jtadd_stel = new JTextField();
				jtadd_stel.setFont(new Font("����",2,20));
				jtadd_stel.setBounds(130,260,130,30);
				
				JLabel jladd_saddress = new JLabel("ס  ַ:");
				jladd_saddress.setFont(new Font("����",1,20));
				jladd_saddress.setBounds(30,300,90,30);
				
				JTextArea jtadd_saddress = new JTextArea();
				jtadd_saddress.setFont(new Font("����",2,20));
				jtadd_saddress.setBounds(130,300,130,60);
				
				JButton certain = new JButton("ȷ��");
				certain.setFont(new Font("����",1,18));
				certain.setBounds(50,400,80,30);
				// ȷ����ť����¼�������Ϣ���뵽���ݿ���
				certain.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						String sql3 = "insert into tb_teacher values(?,?,?,?,?,?,?,?)";
						DBHelper db3 = new DBHelper(sql3);
						int result = 0;
						try {
							db3.pst.setString(1, jtadd_sno.getText());
							db3.pst.setString(2, jtadd_sname.getText());
							db3.pst.setString(3, (String)combox_sex.getSelectedItem());
							db3.pst.setString(4, jtadd_sbirth.getText());
							db3.pst.setString(5, (String)combox_sdep.getSelectedItem());
							db3.pst.setString(6, jtadd_snative.getText());
							db3.pst.setString(7, jtadd_saddress.getText());
							db3.pst.setString(8, jtadd_stel.getText());
							result = db3.pst.executeUpdate();
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
						if(result ==1) {	//��ȡִ�н����������ʾ
							JOptionPane.showMessageDialog(certain, "������Ϣ�ɹ���");
							jttea.validate();
						}else {
							JOptionPane.showMessageDialog(certain, "��Ϣ��дʧ�ܣ�");
						}
						jdadd.setVisible(false);	//���ش���
					}
				});
				
				JButton cancel = new JButton("ȡ��");
				cancel.setFont(new Font("����",1,18));
				cancel.setBounds(180,400,80,30);
				//���ȡ����ť��������¼��������ش���
				cancel.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						jdadd.setVisible(false);
					}
				});
				
				jdadd.setVisible(true);
				jdadd.add(jladd_sno);
				jdadd.add(jtadd_sno);
				jdadd.add(jladd_sname);
				jdadd.add(jtadd_sname);
				jdadd.add(jladd_ssex);
				jdadd.add(combox_sex);
				jdadd.add(jladd_sbirth);
				jdadd.add(jtadd_sbirth);
				jdadd.add(jladd_snative);
				jdadd.add(jtadd_snative);
				jdadd.add(jladd_sdep);
				jdadd.add(combox_sdep);
				jdadd.add(jladd_stel);
				jdadd.add(jtadd_stel);
				jdadd.add(jladd_saddress);
				jdadd.add(jtadd_saddress);
				jdadd.add(certain);
				jdadd.add(cancel);
			}
		}
		
		//���°�ť���������¼�ʵ��
		class ActUpdate implements ActionListener{
			public void actionPerformed(ActionEvent arg0) {
				JDialog jdadd = new JDialog();
				jdadd.setLayout(null);
				jdadd.setTitle("���½�ʦ��Ϣ");
				jdadd.setBounds(500,150,320,500);
				
				JLabel jladd_sno = new JLabel("��  �ţ�");
				jladd_sno.setFont(new Font("����",1,20));
				jladd_sno.setBounds(30,20,90,30);
				
				JComboBox jtadd_sno = new JComboBox();
				jtadd_sno.setFont(new Font("����",1,20));
				jtadd_sno.setBounds(130,20,140,30);
				String sql4 = "select tno from tb_teacher";
				String snos[] = getRow(sql4);
				for(int j=0;j<snos.length;j++) {
					jtadd_sno.addItem(snos[j]);
				}
				
				JLabel jladd_sname = new JLabel("��  ��:");
				jladd_sname.setFont(new Font("����",1,20));
				jladd_sname.setBounds(30,60,90,30);
				
				JTextField jtadd_sname = new JTextField();
				jtadd_sname.setFont(new Font("����",2,20));
				jtadd_sname.setBounds(130,60,130,30);
				
				JLabel jladd_ssex = new JLabel("��  ��:");
				jladd_ssex.setFont(new Font("����",1,20));
				jladd_ssex.setBounds(30,100,90,30);
				
				JComboBox combox_tsex = new JComboBox();
				combox_tsex.setFont(new Font("����",2,20));
				combox_tsex.setBounds(130,100,130,30);
				combox_tsex.addItem("��");
				combox_tsex.addItem("Ů");
				
				JLabel jladd_sbirth = new JLabel("��������:");
				jladd_sbirth.setFont(new Font("����",1,20));
				jladd_sbirth.setBounds(30,140,100,30);
				
				JTextField jtadd_sbirth = new JTextField();
				jtadd_sbirth.setFont(new Font("����",2,20));
				jtadd_sbirth.setBounds(130,140,130,30);
				
				JLabel jladd_snative = new JLabel("��  ��:");
				jladd_snative.setFont(new Font("����",1,20));
				jladd_snative.setBounds(30,180,90,30);
				
				JTextField jtadd_snative = new JTextField();
				jtadd_snative.setFont(new Font("����",2,20));
				jtadd_snative.setBounds(130,180,130,30);
				
				JLabel jladd_sdep = new JLabel("ѧ  Ժ:");
				jladd_sdep.setFont(new Font("����",1,20));
				jladd_sdep.setBounds(30,220,90,30);
				
				JComboBox combox_tdep = new JComboBox();
				combox_tdep.setFont(new Font("����",2,20));
				combox_tdep.setBounds(130,220,130,30);
				String sql2 = "select dno from department";
				String dnos[] = getRow(sql2);
				for(int j=0;j<dnos.length;j++) {
					combox_tdep.addItem(dnos[j]);
				}
				
				JLabel jladd_stel = new JLabel("��  ��:");
				jladd_stel.setFont(new Font("����",1,20));
				jladd_stel.setBounds(30,260,90,30);
				
				JTextField jtadd_stel = new JTextField();
				jtadd_stel.setFont(new Font("����",2,20));
				jtadd_stel.setBounds(130,260,130,30);
				
				JLabel jladd_saddress = new JLabel("ס  ַ:");
				jladd_saddress.setFont(new Font("����",1,20));
				jladd_saddress.setBounds(30,300,90,30);
				
				JTextArea jtadd_saddress = new JTextArea();
				jtadd_saddress.setFont(new Font("����",2,20));
				jtadd_saddress.setBounds(130,300,130,60);
				jtadd_sno.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						String sql5 ="select * from tb_teacher where tno='"
									+(String)jtadd_sno.getSelectedItem()+"'";
						DBHelper db5 = new DBHelper(sql5);
						//���һ�м�¼
						String teacher[] = new String[8];
						try {
							ResultSet rs = db5.pst.executeQuery();
							//����ѯ����д������
							while(rs.next()) {
								for(int i=0; i<8; i++) {
									teacher[i] = rs.getString(i+1);
								}
							}
						} catch (SQLException e) {
							e.printStackTrace();
						}
						
						jtadd_sname.setText(teacher[1]);
						combox_tsex.setSelectedItem(teacher[2]);
						jtadd_sbirth.setText(teacher[3]);
						jtadd_snative.setText(teacher[5]);
						combox_tdep.setSelectedItem(teacher[4]);
						jtadd_saddress.setText(teacher[6]);
						jtadd_stel.setText(teacher[7]);
					}
				});
				
				JButton certain = new JButton("ȷ��");
				certain.setFont(new Font("����",1,18));
				certain.setBounds(50,400,80,30);
				// ȷ����ť����¼�������Ϣ���뵽���ݿ���
				certain.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						String sql3 = "update tb_teacher set tname=?,tsex=?,tbirthday=?,dno=?,tnative=?"
								+ ",thome=?,ttel=? where tno='"+(String)jtadd_sno.getSelectedItem()+"'";
						DBHelper db3 = new DBHelper(sql3);
						int result = 0;
						try {
							db3.pst.setString(1, jtadd_sname.getText());
							db3.pst.setString(2, (String)combox_tsex.getSelectedItem());
							db3.pst.setString(3, jtadd_sbirth.getText());
							db3.pst.setString(4, (String)combox_tdep.getSelectedItem());
							db3.pst.setString(5, jtadd_snative.getText());
							db3.pst.setString(6, jtadd_saddress.getText());
							db3.pst.setString(7, jtadd_stel.getText());
							result = db3.pst.executeUpdate();
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
						if(result ==1) {	//��ȡִ�н����������ʾ
							JOptionPane.showMessageDialog(certain, "������Ϣ�ɹ���");
							jttea.validate();
						}else {
							JOptionPane.showMessageDialog(certain, "������Ϣʧ�ܣ�");
						}
						jdadd.setVisible(false);	//���ش���
					}
				});
				
				JButton cancel = new JButton("ȡ��");
				cancel.setFont(new Font("����",1,18));
				cancel.setBounds(180,400,80,30);
				//���ȡ����ť��������¼��������ش���
				cancel.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						jdadd.setVisible(false);
					}
				});
				
				jdadd.setVisible(true);
				jdadd.add(jladd_sno);
				jdadd.add(jtadd_sno);
				jdadd.add(jladd_sname);
				jdadd.add(jtadd_sname);
				jdadd.add(jladd_ssex);
				jdadd.add(combox_tsex);
				jdadd.add(jladd_sbirth);
				jdadd.add(jtadd_sbirth);
				jdadd.add(jladd_snative);
				jdadd.add(jtadd_snative);
				jdadd.add(jladd_sdep);
				jdadd.add(combox_tdep);
				jdadd.add(jladd_stel);
				jdadd.add(jtadd_stel);
				jdadd.add(jladd_saddress);
				jdadd.add(jtadd_saddress);
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
