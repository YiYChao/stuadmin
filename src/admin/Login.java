package admin;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Login extends JPanel {

	JPanel panel;
	JLabel title;
	JLabel lbuser;
	JTextField tfuser;
	JLabel lbpwd;
	JPasswordField pwd;
	
	JButton clear;
	JButton regist;
	JButton login;
	JLabel cprt;
	Demo demo;
	MianFace mf;
	//���캯��
	public Login() {

		panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(0, 0, 400, 300);
		panel.setBackground(Color.pink);
		
		//���ô���ı����ǩ
		title = new JLabel("��ӭ����ɼ�����ϵͳ");
		title.setForeground(Color.blue);
		title.setBounds(70, 20, 260, 30);
		title.setFont(new Font("����",0,26));
		
		//���ÿ��ű�ǩ
		lbuser = new JLabel("�û�����");
		lbuser.setBounds(70, 80, 80, 20);
		lbuser.setFont(new Font("����",0,16));
		
		//�����û��������ı���
		tfuser = new JTextField();
		tfuser.setBounds(140, 80, 180, 25);
		tfuser.setFont(new Font("����",0,16));
		
		//���������ǩ
		lbpwd = new JLabel("�� �룺");
		lbpwd.setBounds(70, 120, 80, 20);
		lbpwd.setFont(new Font("����",0,16));
		
		//�������������ı���
		pwd = new JPasswordField ();
		pwd.setBounds(140,120,180,25);
		pwd.setFont(new Font("����",0,16));
		
		//�������ð�ť
		clear  = new JButton("����");
		clear.setBounds(90, 170, 60, 30);
		clear.addActionListener(new ActLogin());//new ActReset()
		
		//����ע�ᰴť
		regist = new JButton("ע��");
		regist.setBounds(180, 170, 60, 30);
		regist.addActionListener(new ActLogin());//new ActRegist()
		
		//���õ�½��ť
		login = new JButton("��¼");
		login.setBounds(260, 170, 60, 30);
		login.addActionListener(new ActLogin());//new ActLogin()
		
		cprt = new JLabel("Copyright by Yi Chao 2017-2018");
		cprt.setBounds(80,230,220,20);
		cprt.setFont(new Font("����",2,14));
		
		panel.add(title);
		panel.add(lbuser);
		panel.add(tfuser);
		panel.add(lbpwd);
		panel.add(pwd);
		panel.add(clear);
		panel.add(regist);
		panel.add(login);
		panel.add(cprt);
		panel.setVisible(true);
		//this.add(panel);
		demo = new Demo();
		mf = new MianFace();
		
	}
	
	class ActLogin implements ActionListener{
		
		public void actionPerformed(ActionEvent e) {
			//�����á���ť����ʵ��
			if(e.getSource().equals(clear)) {
				tfuser.setText(null);
				pwd.setText(null);
			}else if(e.getSource().equals(regist)) {
				//��ע�ᡱ��ť����ʵ��
				File file = new File("./file","userid");
				FileInputStream fis;
				String bf="";
				try {
					fis = new FileInputStream(file);
					InputStreamReader isr = new InputStreamReader(fis);
					BufferedReader br = new BufferedReader(isr);
					bf = br.readLine();
//					System.out.println(bf);
					br.close();
					
					FileOutputStream fos = new FileOutputStream(file);		//���������
					OutputStreamWriter osw= new OutputStreamWriter(fos);
					BufferedWriter bw = new BufferedWriter(osw);
					bw.write(String.valueOf(Integer.parseInt(bf)+1));
					bw.close();
				} catch (IOException e2) {
					e2.printStackTrace();
				}			
				
				String sql = "insert into tb_user values ("+bf+",'"+tfuser.getText()+"','"+String.valueOf(pwd.getPassword())+"')";
				DBHelper db = new DBHelper(sql);
				int ud = 0;
				try {
					ud = db.pst.executeUpdate();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				if(ud == 1) {
					int confirmDialog = JOptionPane.showConfirmDialog(regist, "ȷ��Ҫע�᣿");
					System.out.println("ȷ����Ϣ" + confirmDialog);
					
				}else {
					JOptionPane.showMessageDialog(regist, "��Ϣ��д����");
				}
				db.close();
			}else if(e.getSource().equals(login)) {
				//����¼����ť����ʵ��
				String sql = "select * from tb_user ";
				DBHelper db = new DBHelper(sql);
				try {
					ResultSet rs = db.pst.executeQuery();
					boolean suc = false;
					String cpwd=String.valueOf(pwd.getPassword());
					while(rs.next()) {
						if(rs.getString(2).equals(tfuser.getText()) &&
								rs.getString(3).equals(String.valueOf(cpwd))) {
							suc = true;
							break;
						}
					}
					if(suc == false) {
						JOptionPane.showMessageDialog(login, "�û������������");
						tfuser.setText(null);
						pwd.setText(null);
					} else {
						demo.frame.remove(panel);
						demo.frame.setBounds(200, 30, 1000, 680);
						demo.frame.add(mf.jpmain);
						demo.frame.repaint();
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				db.close();
			}
		}
	}
}
