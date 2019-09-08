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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;

public class StuManage extends JPanel{
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
	String[][] stu;
	
	public StuManage() {
		//学生管理面板
		mainPanel = new JPanel();
		mainPanel.setLayout(null);
		mainPanel.setBounds(0, 0, 920, 600);
		mainPanel.setBackground(Color.lightGray);
		
		//菜单栏面板
		menuPanel = new JPanel();
		menuPanel.setLayout(null);
		menuPanel.setBounds(0, 0, 920, 30);
		menuPanel.setBackground(Color.CYAN);
		
		//设置位置标签
		location = new JLabel("当前位置：学生管理");
		location.setBounds(10,5,180,20);
		location.setFont(new Font("黑体",2,18));
		location.setBackground(Color.RED);
		menuPanel.add(location);
		
		String sql = "select sno,sname,ssex,sbirthday,snative,classname,dname,shome,stel\r\n" + 
				"from tb_class,tb_department,tb_student\r\n" + 
				" where tb_student.classno=tb_class.classno and tb_student.dno=tb_department.dno;";
        DBHelper db = new DBHelper(sql);
        int count =0;
        stu = null;
    	try {
    		ResultSet rs = db.pst.executeQuery();
			while(rs.next()) {
				count++;
			}
			stu= new String[count][10];
			rs.beforeFirst();
			int i=0;
			while(rs.next()) {
				stu[i][0]=String.valueOf(i+1);
				for(int j=1;j<10;j++) {
					stu[i][j]=rs.getString(j);
				}
				i++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	db.close();
		String[] columnNames = new String[] { "序号","学号", "姓名", "性别", "出生日期" ,
				"籍贯","所属班级","所属学院","住址","电话"};
        jtstu = new JTable(stu, columnNames);
        jtstu.setBounds(0,0,920,500);
        jtstu.setPreferredScrollableViewportSize(new Dimension(600, 100));
		//表格显示面板
		tabPanel = new JScrollPane(jtstu);
		jtstu.getColumnModel().getColumn(0).setPreferredWidth(3);
		jtstu.getColumnModel().getColumn(1).setPreferredWidth(40);
		jtstu.getColumnModel().getColumn(2).setPreferredWidth(20);
		jtstu.getColumnModel().getColumn(3).setPreferredWidth(5);
		jtstu.getColumnModel().getColumn(4).setPreferredWidth(30);
		jtstu.getColumnModel().getColumn(5).setPreferredWidth(20);
		jtstu.getColumnModel().getColumn(6).setPreferredWidth(40);
		jtstu.getColumnModel().getColumn(7).setPreferredWidth(80);
		jtstu.getColumnModel().getColumn(8).setPreferredWidth(150);
		jtstu.getColumnModel().getColumn(9).setPreferredWidth(120);
		jtstu.setRowHeight(30);
		jtstu.validate();
		//设置表格内容居中显示
		DefaultTableCellRenderer r  = new DefaultTableCellRenderer();   
		r.setHorizontalAlignment(JLabel.CENTER);   
		jtstu.setDefaultRenderer(Object.class, r);
		tabPanel.setBounds(0,30,920,450);
		
		jlsearch = new JLabel("信息查询(输入学号)：");
		jlsearch.setBounds(180,490,200,30);
		jlsearch.setFont(new Font("宋体",1,18));
		
		jtid = new JTextField();
		jtid.setBounds(390,490,150,30);
		
		jbsearch = new JButton("查询");
		jbsearch.setBounds(600,490,100,30);
		jbsearch.setFont(new Font("宋体",1,20));
		jbsearch.addActionListener(new ActSearch());
		
		jbdelete = new JButton("删除所选");
		jbdelete.setBounds(230,530,120,30);
		jbdelete.setFont(new Font("宋体",1,20));
		jbdelete.addActionListener(new ActDelete());
		
		jbadd = new JButton("添加信息");
		jbadd.setBounds(400,530,120,30);
		jbadd.setFont(new Font("宋体",1,20));
		jbadd.addActionListener(new ActAdd());
		
		jbupdate = new JButton("更新信息");
		jbupdate.setBounds(550,530,120,30);
		jbupdate.setFont(new Font("宋体",1,20));
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
	
	//查询按钮动作监听事件实现
	class ActSearch implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			for(int i=0;i<jtstu.getRowCount();i++) {
				if(jtid.getText().equals(stu[i][1])) {
					JOptionPane.showMessageDialog(jbsearch, stu[i][1]+"~"+stu[i][2]+"~"+stu[i][3]
							+"~"+stu[i][4]+"~"+stu[i][5]+"~"+stu[i][6]+"~"+stu[i][7]+"~"+stu[i][8]
							+"~"+stu[i][9]);
				}
			}
		}	
	}
	
	//删除按钮动作监听事件实现
	class ActDelete implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			int row = jtstu.getSelectedRow();	//获取选中的列
			String selectsno =  (String) jtstu.getValueAt(row, 1);
			String sql1 = "delete from tb_student where sno='"+(String) jtstu.getValueAt(row, 1)+"'";;
			DBHelper db = new DBHelper(sql1);
			int result = 0;
			try {
				result = db.pst.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if(result == 1) {
				JOptionPane.showMessageDialog(jbdelete, "删除成功！");
			}else {
				JOptionPane.showMessageDialog(jbdelete, "删除失败！");
			}
			db.close();
			jtstu.validate();
			jtstu.repaint();
		}
	}
	
	//添加按钮动作监听事件实现
	class ActAdd implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			JDialog jdadd = new JDialog();
			jdadd.setLayout(null);
			jdadd.setTitle("添加学生信息");
			jdadd.setBounds(500,150,320,500);
			
			JLabel jladd_sno = new JLabel("学  号：");
			jladd_sno.setFont(new Font("黑体",1,20));
			jladd_sno.setBounds(30,20,90,30);
			
			JTextField jtadd_sno = new JTextField();
			jtadd_sno.setFont(new Font("宋体",1,20));
			jtadd_sno.setBounds(130,20,130,30);
			
			JLabel jladd_sname = new JLabel("姓  名:");
			jladd_sname.setFont(new Font("黑体",1,20));
			jladd_sname.setBounds(30,60,90,30);
			
			JTextField jtadd_sname = new JTextField();
			jtadd_sname.setFont(new Font("宋体",2,20));
			jtadd_sname.setBounds(130,60,130,30);
			
			JLabel jladd_ssex = new JLabel("性  别:");
			jladd_ssex.setFont(new Font("黑体",1,20));
			jladd_ssex.setBounds(30,100,90,30);
			
			JComboBox combox_sex = new JComboBox();
			combox_sex.setFont(new Font("黑体",2,20));
			combox_sex.setBounds(130,100,130,30);
			combox_sex.addItem("男");
			combox_sex.addItem("女");
			
			JLabel jladd_sbirth = new JLabel("出生日期:");
			jladd_sbirth.setFont(new Font("黑体",1,20));
			jladd_sbirth.setBounds(30,140,100,30);
			
			JTextField jtadd_sbirth = new JTextField();
			jtadd_sbirth.setFont(new Font("黑体",2,20));
			jtadd_sbirth.setBounds(130,140,130,30);
			
			JLabel jladd_snative = new JLabel("籍  贯:");
			jladd_snative.setFont(new Font("黑体",1,20));
			jladd_snative.setBounds(30,180,90,30);
			
			JTextField jtadd_snative = new JTextField();
			jtadd_snative.setFont(new Font("宋体",2,20));
			jtadd_snative.setBounds(130,180,130,30);
			
			JLabel jladd_sdep = new JLabel("学  院:");
			jladd_sdep.setFont(new Font("黑体",1,20));
			jladd_sdep.setBounds(30,220,90,30);
			
			JComboBox combox_sdep = new JComboBox();
			combox_sdep.setFont(new Font("宋体",2,20));
			combox_sdep.setBounds(130,220,130,30);
			String sql2 = "select dno from tb_department";
			String dnos[] = getRow(sql2);
			for(int j=0;j<dnos.length;j++) {
				combox_sdep.addItem(dnos[j]);
			}
			
			JLabel jladd_sclass = new JLabel("班  级:");
			jladd_sclass.setFont(new Font("黑体",1,20));
			jladd_sclass.setBounds(30,260,90,30);
			
			JComboBox combox_sclass = new JComboBox();
			combox_sclass.setFont(new Font("宋体",2,20));
			combox_sclass.setBounds(130,260,130,30);
			combox_sdep.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					combox_sclass.removeAllItems();
					String sql1 = "select classno from tb_class where dno='"
								+(String)combox_sdep.getSelectedItem()+"'";
					String cnos[] = getRow(sql1);
					for(int j=0;j<cnos.length;j++) {
						combox_sclass.addItem(cnos[j]);
					}
				}
			});
			
			JLabel jladd_stel = new JLabel("电  话:");
			jladd_stel.setFont(new Font("黑体",1,20));
			jladd_stel.setBounds(30,300,90,30);
			
			JTextField jtadd_stel = new JTextField();
			jtadd_stel.setFont(new Font("宋体",2,20));
			jtadd_stel.setBounds(130,300,130,30);
			
			JLabel jladd_saddress = new JLabel("住  址:");
			jladd_saddress.setFont(new Font("黑体",1,20));
			jladd_saddress.setBounds(30,340,90,30);
			
			JTextArea jtadd_saddress = new JTextArea();
			jtadd_saddress.setFont(new Font("黑体",2,20));
			jtadd_saddress.setBounds(130,340,130,60);
			
			JButton certain = new JButton("确定");
			certain.setFont(new Font("黑体",1,18));
			certain.setBounds(50,420,80,30);
			// 确定按钮添加事件，将信息插入到数据库中
			certain.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					String sql3 = "insert into tb_student values(?,?,?,?,?,?,?,?,?)";
					DBHelper db3 = new DBHelper(sql3);
					int result = 0;
					try {
						db3.pst.setString(1, jtadd_sno.getText());
						db3.pst.setString(2, jtadd_sname.getText());
						db3.pst.setString(3, (String)combox_sex.getSelectedItem());
						db3.pst.setString(4, jtadd_sbirth.getText());
						db3.pst.setString(5, jtadd_snative.getText());
						db3.pst.setString(6, jtadd_saddress.getText());
						db3.pst.setString(7, jtadd_stel.getText());
						db3.pst.setString(8, (String)combox_sdep.getSelectedItem());
						db3.pst.setString(9, (String)combox_sclass.getSelectedItem());
						result = db3.pst.executeUpdate();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					if(result ==1) {	//获取执行结果并给出提示
						JOptionPane.showMessageDialog(certain, "插入信息成功！");
						jtstu.validate();
					}else {
						JOptionPane.showMessageDialog(certain, "信息填写失败！");
					}
					jdadd.setVisible(false);	//隐藏窗口
				}
			});
			
			JButton cancel = new JButton("取消");
			cancel.setFont(new Font("黑体",1,18));
			cancel.setBounds(180,420,80,30);
			//添加取消按钮的鼠标点击事件，即隐藏窗口
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
			jdadd.add(jladd_sclass);
			jdadd.add(combox_sclass);
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
	
	//更新按钮动作监听事件实现
	class ActUpdate implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			JDialog jdadd = new JDialog();
			jdadd.setLayout(null);
			jdadd.setTitle("更新学生信息");
			jdadd.setBounds(500,150,320,500);
			
			JLabel jladd_sno = new JLabel("学  号：");
			jladd_sno.setFont(new Font("黑体",1,20));
			jladd_sno.setBounds(30,20,90,30);
			
			JComboBox jtadd_sno = new JComboBox();
			jtadd_sno.setFont(new Font("宋体",1,20));
			jtadd_sno.setBounds(130,20,140,30);
			String sql4 = "select sno from tb_student";
			String snos[] = getRow(sql4);
			for(int j=0;j<snos.length;j++) {
				jtadd_sno.addItem(snos[j]);
			}
			
			JLabel jladd_sname = new JLabel("姓  名:");
			jladd_sname.setFont(new Font("黑体",1,20));
			jladd_sname.setBounds(30,60,90,30);
			
			JTextField jtadd_sname = new JTextField();
			jtadd_sname.setFont(new Font("宋体",2,20));
			jtadd_sname.setBounds(130,60,130,30);
			
			JLabel jladd_ssex = new JLabel("性  别:");
			jladd_ssex.setFont(new Font("黑体",1,20));
			jladd_ssex.setBounds(30,100,90,30);
			
			JComboBox combox_sex = new JComboBox();
			combox_sex.setFont(new Font("黑体",2,20));
			combox_sex.setBounds(130,100,130,30);
			combox_sex.addItem("男");
			combox_sex.addItem("女");
			
			JLabel jladd_sbirth = new JLabel("出生日期:");
			jladd_sbirth.setFont(new Font("黑体",1,20));
			jladd_sbirth.setBounds(30,140,100,30);
			
			JTextField jtadd_sbirth = new JTextField();
			jtadd_sbirth.setFont(new Font("黑体",2,20));
			jtadd_sbirth.setBounds(130,140,130,30);
			
			JLabel jladd_snative = new JLabel("籍  贯:");
			jladd_snative.setFont(new Font("黑体",1,20));
			jladd_snative.setBounds(30,180,90,30);
			
			JTextField jtadd_snative = new JTextField();
			jtadd_snative.setFont(new Font("宋体",2,20));
			jtadd_snative.setBounds(130,180,130,30);
			JLabel jladd_sdep = new JLabel("学  院:");
			jladd_sdep.setFont(new Font("黑体",1,20));
			jladd_sdep.setBounds(30,220,90,30);
			
			JComboBox combox_sdep = new JComboBox();
			combox_sdep.setFont(new Font("宋体",2,20));
			combox_sdep.setBounds(130,220,130,30);
			String sql2 = "select dno from tb_department";
			String dnos[] = getRow(sql2);
			for(int j=0;j<dnos.length;j++) {
				combox_sdep.addItem(dnos[j]);
			}
			
			JLabel jladd_sclass = new JLabel("班  级:");
			jladd_sclass.setFont(new Font("黑体",1,20));
			jladd_sclass.setBounds(30,260,90,30);
			
			JComboBox combox_sclass = new JComboBox();
			combox_sclass.setFont(new Font("宋体",2,20));
			combox_sclass.setBounds(130,260,130,30);
			combox_sdep.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					combox_sclass.removeAllItems();
					String sql1 = "select classno from tb_class where dno='"
								+(String)combox_sdep.getSelectedItem()+"'";
					String cnos[] = getRow(sql1);
					for(int j=0;j<cnos.length;j++) {
						combox_sclass.addItem(cnos[j]);
					}
				}
			});
			
			JLabel jladd_stel = new JLabel("电  话:");
			jladd_stel.setFont(new Font("黑体",1,20));
			jladd_stel.setBounds(30,300,90,30);
			
			JTextField jtadd_stel = new JTextField();
			jtadd_stel.setFont(new Font("宋体",2,20));
			jtadd_stel.setBounds(130,300,130,30);
			
			JLabel jladd_saddress = new JLabel("住  址:");
			jladd_saddress.setFont(new Font("黑体",1,20));
			jladd_saddress.setBounds(30,340,90,30);
			
			JTextArea jtadd_saddress = new JTextArea();
			jtadd_saddress.setFont(new Font("宋体",2,20));
			jtadd_saddress.setBounds(130,340,130,60);
			jtadd_sno.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					String sql5 ="select * from tb_student where sno='"
								+(String)jtadd_sno.getSelectedItem()+"'";
					DBHelper db5 = new DBHelper(sql5);
					//存放一行记录
					String student[] = new String[9];
					try {
						ResultSet rs = db5.pst.executeQuery();
						//将查询数据写入数组
						while(rs.next()) {
							for(int i=0;i<9;i++) {
								student[i]=rs.getString(i+1);
							}
						}
					} catch (SQLException e) {
						e.printStackTrace();
					}
					jtadd_sname.setText(student[1]);
					combox_sex.setSelectedItem(student[2]);
					jtadd_sbirth.setText(student[3]);
					jtadd_snative.setText(student[4]);
					combox_sclass.setSelectedItem(student[5]);
					combox_sdep.setSelectedItem(student[6]);
					jtadd_saddress.setText(student[7]);
					jtadd_stel.setText(student[8]);
				}
			});
			
			
			JButton certain = new JButton("确定");
			certain.setFont(new Font("黑体",1,18));
			certain.setBounds(50,420,80,30);
			// 确定按钮添加事件，将信息插入到数据库中
			certain.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					String sql3 = "update tb_student set sname=?,ssex=?,sbirthday=?,snative=?,classno=?"
							+ ",dno=?,shome=?,stel=? where sno='"+(String)jtadd_sno.getSelectedItem()+"'";
					DBHelper db3 = new DBHelper(sql3);
					int result = 0;
					try {
						db3.pst.setString(1, jtadd_sname.getText());
						db3.pst.setString(2, (String)combox_sex.getSelectedItem());
						db3.pst.setString(3, jtadd_sbirth.getText());
						db3.pst.setString(4, jtadd_snative.getText());
						db3.pst.setString(5, (String)combox_sclass.getSelectedItem());
						db3.pst.setString(6, (String)combox_sdep.getSelectedItem());
						db3.pst.setString(7, jtadd_saddress.getText());
						db3.pst.setString(8, jtadd_stel.getText());
						result = db3.pst.executeUpdate();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					if(result ==1) {	//获取执行结果并给出提示
						JOptionPane.showMessageDialog(certain, "更新信息成功！");
						jtstu.validate();
					}else {
						JOptionPane.showMessageDialog(certain, "更新信息失败！");
					}
					jdadd.setVisible(false);	//隐藏窗口
				}
			});
			
			JButton cancel = new JButton("取消");
			cancel.setFont(new Font("黑体",1,18));
			cancel.setBounds(180,420,80,30);
			//添加取消按钮的鼠标点击事件，即隐藏窗口
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
			jdadd.add(jladd_sclass);
			jdadd.add(combox_sclass);
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
	
	//获取一列元素
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
