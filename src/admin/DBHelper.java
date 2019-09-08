package admin;

import java.sql.DriverManager;
import java.sql.SQLException;

public class DBHelper {

	//"jdbc:mysql://127.0.0.1/infosystem"
	public static final String url = "jdbc:mysql://58.87.64.18:3306/stuscore?characterEncoding=UTF8&useSSL=false";  
    public static final String name = "com.mysql.jdbc.Driver";  
    public static final String user = "stuadmin";  
    public static final String password = "admin";
  
    public java.sql.Connection conn = null;  
    public java.sql.PreparedStatement pst = null;  
  
    public DBHelper(String sql) {  
        try {  
            Class.forName(name);//指定连接类型  
            conn = DriverManager.getConnection(url, user, password);//获取连接  
            pst = conn.prepareStatement(sql);//准备执行语句  
        } catch (Exception e) {  
            e.printStackTrace();  
        }
    }  
  
    public void close() {  
        try {  
            this.conn.close();  
            this.pst.close();  
        } catch (SQLException e) {  
            e.printStackTrace();  
        }  
    }  
}
