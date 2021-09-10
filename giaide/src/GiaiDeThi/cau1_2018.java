package GiaiDeThi;

import java.sql.*;
import java.util.ArrayList;

//dky driver -> tao connection = driverManager -> su dung cac loai statement roi exceute -> close
public class cau1_2018 {
    public static void main(String[] args) throws SQLException {

        // Create the JDBC driver name string
        String driverName = "com.mysql.jdbc.Driver"; // MySQL MM JDBC driver

        // Create a connection to the database
        String serverName = "localhost";
        String mydatabase = "sinhvien1";
        String url = "jdbc:mysql://" + serverName +  "/" + mydatabase +"?characterEncoding=UTF-8"; // a JDBC url
        String username = "hoang";
        String password = "laptrinhjava";


        ConnectionPool myPool = new ConnectionPool(1, driverName, url, username, password);

        //user1
        Connection con1 = myPool.getAConnection();
        Statement stm = con1.createStatement();

        ResultSet rs = stm.executeQuery("select * from sinhvien2");

        while (rs.next())
        {
            System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getInt(3));
        }

        //return
        System.out.println("Return the current connection for con2");
        myPool.returnAConnectionToPool(con1);

        //user2
        Connection con2 = myPool.getAConnection();
        Statement stm2 = con2.createStatement();

        ResultSet rs2 = stm2.executeQuery("select * from sinhvien2");

        while (rs2.next())
        {
            System.out.println("Tao tim dua nay " + rs2.getString(2) + " " + rs2.getInt(3));
        }

        myPool.releasePool();
    }
}

class ConnectionPool {
    private ArrayList<ModifiedConnection> list;
    private String driverName;

    ConnectionPool(int numOfCon, String driverName, String url, String username, String password) {
        list = new ArrayList<>(numOfCon);

        try {
            // 1. Load the JDBC driver
            this.driverName = driverName; // MySQL MM JDBC driver
            Class.forName(this.driverName);



        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        for (int i = 0; i<numOfCon; i++) {
            list.add(new ModifiedConnection("" + i, url, username, password));
        }
    }

    Connection getAConnection(){
        for (ModifiedConnection c : list) {
            if (c.isAvailable()) {
                c.setAvailable(false);
                return c.getConnection();
            }
        }
        //if not found
        //co 3 cach tra ve ma loi
        //cach 1 -> dung excption co san, cach 2 throw+throws excption tu dinh nghia, cach 3, throw + finally return null

        //throw new NullPointerException("There is no available connection in the pool");
        try {
            throw new NoReadyConnectionException("There is no available connection in the pool");
        } catch (NoReadyConnectionException e) {
            e.printStackTrace();
        }
        finally {
            return null;
        }
    }


    public void returnAConnectionToPool(Connection c1) {
        for (ModifiedConnection c : list) {
            if (c.getConnection().equals(c1)) {
                c.returnAvailable();
            }
        }
    }

    public void releasePool() {
        for (ModifiedConnection c : list) {
                c.release();
        }
    }
}

class ModifiedConnection{
    private String name;
    private boolean isAvailable;
    private Connection con;

    ModifiedConnection(String nameOfConnection, String url, String username, String password) {

        //do something with JDBC
        //Create a connection to the database
        try {
            this.con = DriverManager.getConnection(url, username, password);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        isAvailable=true;
    }

    public boolean isAvailable(){return isAvailable;}
    public void setAvailable(boolean flag){isAvailable=flag;}

    public Connection getConnection(){return con;}
    public void setConnection(Connection connectToken){con=connectToken;}

    public void returnAvailable(){ isAvailable=true;}
    public void release() {
        try {
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

class NoReadyConnectionException  extends Exception
{
    public NoReadyConnectionException (String str)
    {
        // calling the constructor of parent Exception
        super(str);
    }
}

//kien thuc ve arraylist, collection, trarverse + [[jdbc driver, connection, release + exception hand
