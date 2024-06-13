package hospital;

import java.io.FileInputStream;
import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class Controllerclass {
    Scanner sc1 = new Scanner(System.in);
    Scanner sc2 = new Scanner(System.in);
    int p_id, p_age, m_Id;
    String m_Name;
    int m_dosage;
    double fees;
    String p_name, p_disease, d_name;
    Connection conn;
    PreparedStatement prestate;

    // Constructor to create a connection
    public Controllerclass() {
        try {
            // Load properties and create a connection
            Properties prop = new Properties();
            prop.load(new FileInputStream("D:\\javaasignment\\Hospital\\connection.properties"));
            conn = DriverManager.getConnection(prop.getProperty("url"), prop.getProperty("username"), prop.getProperty("password"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int create() throws SQLException {
        prestate = conn.prepareStatement("CREATE TABLE hospital_details (p_id INT PRIMARY KEY, p_name VARCHAR(255) NOT NULL, p_age INT NOT NULL, p_disease VARCHAR(255) NOT NULL, d_name VARCHAR(255) NOT NULL, fees DOUBLE PRECISION);");
        prestate.executeUpdate();
        System.out.println("* * Table created * *");
        return 0;
    }

    public int insert() throws Exception {
        prestate = conn.prepareStatement("Insert into  hospital_details (p_id, p_name, p_age, p_disease, d_name, fees) values (?,?,?,?,?,?);");
        System.out.println("Enter Paitient Id");
        p_id = sc1.nextInt();

        System.out.println("Enter Paitient Full Name ");
        p_name = sc2.nextLine();
        System.out.println("Enter p_age of Paitient");
        p_age = sc1.nextInt();
        System.out.println(" Enter Paitient  p_disease ");
        p_disease = sc2.nextLine();
        System.out.println("Enter Doctor name ");
        d_name = sc1.next();
        System.out.println("Enter Fees ");
        fees= sc1.nextDouble();
        prestate.setInt(1, p_id);
        prestate.setString(2, p_name);
        prestate.setInt(3, p_age);
        prestate.setString(4, p_disease);
        prestate.setString(5, d_name);
        prestate.setDouble(6,fees);
        prestate.executeUpdate();
        System.out.println("Data Inserted Sucessfully");

        return 0;
    }

    public int find_doctor() throws SQLException {
        prestate = conn.prepareStatement("Select p_id,p_name,p_disease from hospital_details where d_name=?");
        System.out.println("Enter Under Doctor Name");
        d_name = sc1.next();
        prestate.setString(1,d_name);
        ResultSet rset = prestate.executeQuery();
        while(rset.next()) {
            System.out.println("|  P_id  |      P_name        | P_disease");
            System.out.println("   "+rset.getInt("p_id")+"          " + rset.getString("p_name")+"     " + rset.getString("p_disease"));
            System.out.println("De. Ajay"+d_name + " Paitent Details");
        }

        return 0;
    }

    public int change_dname() throws SQLException {
        prestate = conn.prepareStatement("update hospital_details set d_name = ? where p_id = ?");
        System.out.println("Enter the Name  of New  Doctor : ");
        d_name = sc1.next();
        System.out.println("Enter patient id whose dr needs to change : ");
        p_id = sc2.nextInt();
        prestate.setString(1, d_name);
        prestate.setInt(2, p_id);
        prestate.executeUpdate();
        System.out.println("New Doctor updated ");
        return 0;
    }

    public int find_patient() throws SQLException {
        prestate = conn.prepareStatement("select * from hospital_details where p_id = ?;");
        System.out.println("Enter the Patient ID : ");
        p_id = sc1.nextInt();
        prestate.setInt(1, p_id);
        ResultSet rset = prestate.executeQuery();
        if (rset.next()) {
            System.out.println("|  P_id  |    P_name     |    p_age     | p_disease");
            System.out.println("   "+rset.getInt("p_id") + "        " + rset.getString("p_name") + "       " + rset.getInt("p_age") + "         " + rset.getString("p_disease"));

        } else {
            System.out.println("No Patient or entered wrong ID");
        }
        return 0;
    }

    public int same_p_disease() throws SQLException {
        prestate = conn.prepareStatement("select * from hospital_details where p_disease = ?;");
        System.out.println("Enter p_disease : ");
        p_disease = sc1.next();
        prestate.setString(1, p_disease);
        ResultSet rset = prestate.executeQuery();
        boolean temp = false;
        System.out.println("|  P_id  |    P_name     |    p_age     |");
        while (rset.next()) {
            temp = true;
            System.out.println("    "+rset.getInt("p_id") + "          " + rset.getString("p_name")  + "       " + rset.getString("p_disease"));
        }
        if (!temp) {
            System.out.println(" Might spelling is incorrect ");
        }
        return 0;
    }
    public int discount() throws SQLException {
        prestate = conn.prepareStatement("update hospital_details set fees = fees-? where p_id = ?;");
        System.out.println("Enter the Id of Patient : ");
        p_id = sc1.nextInt();
        System.out.println("Enter Fees : ");
        fees = sc1.nextDouble();
        System.out.println("Enter Discount amount : ");
        double discount = sc1.nextDouble();
        prestate.setDouble(1, discount);
        prestate.setInt(2,p_id);
        prestate.executeUpdate();
        System.out.println("Discount of Rs. " + discount + " to patient " + p_id);
        return 0;
    }
    public int select_all() throws SQLException {
        prestate = conn.prepareStatement("select * from hospital_details;");
        ResultSet rset = prestate.executeQuery();
        System.out.println("| Patient Id |    Patient Name   |    Patient Age    |        Disease        |       Doctor Name    |    Fees   |");
        while (rset.next()){
            System.out.println("       " + rset.getInt("p_id") + "             " + rset.getString("p_name") + "             " + rset.getInt("p_age") + "              " + rset.getString("p_disease") + "                   " + rset.getString("d_name") + "         " + rset.getDouble("fees"));

        }
        System.out.println("All data Displayed ");
        return 0;
    }
    public int Medicine_table() throws SQLException{
        prestate = conn.prepareStatement("CREATE TABLE medicine_details (m_id INT PRIMARY KEY, m_name VARCHAR(255) NOT NULL, m_dosage INT NOT NULL, p_id INT, FOREIGN KEY (p_id) REFERENCES hospital_details(p_id));");
        prestate.executeUpdate();
        System.out.println("* * Medicine table created * *");
        return 0;
    }
    public int medicien_insert() throws SQLException{
        prestate = conn.prepareStatement("INSERT INTO medicine_details (m_Id, m_name, m_dosage, p_id) VALUES (?,?,?,?);");
        System.out.println("Enter Medicine Id");
        m_Id= sc2.nextInt();
        System.out.println("Enter Medicine Name");
        m_Name=sc1.nextLine();
        System.out.println("Enter No of Dosage");
        m_dosage=sc1.nextInt();
        System.out.println("Enter Pid");
        p_id=sc1.nextInt();
        prestate.setInt(1,m_Id);
        prestate.setString(2,m_Name);
        prestate.setInt(3,m_dosage);
        prestate.setInt(4, p_id);
        prestate.executeUpdate();
        System.out.println("Medicine details added successfully.");
        return 0;
    }
    public int select_medi() throws SQLException {
        prestate = conn.prepareStatement("SELECT * FROM medicine_details WHERE p_id = ?");
        System.out.println("Enter Paitent id :");
        p_id=sc1.nextInt();
        prestate.setInt(1, p_id);
        ResultSet rset = prestate.executeQuery();
        System.out.println("| Medicine ID | Medicine Name | Dosage | Patient ID |");
        while (rset.next()) {
            System.out.println(" " + rset.getInt("m_Id") + "          " + rset.getString("m_name") + "         " + rset.getInt("m_dosage") + "        " + rset.getInt("p_id"));
        }

        System.out.println("Medicine details for Patient ID " + p_id + " displayed.");
        return 0;
    }
    public int select_AllMedi() throws SQLException {
        prestate = conn.prepareStatement("SELECT * FROM medicine_details");
        ResultSet rset = prestate.executeQuery();
        System.out.println("| Medicine ID | Medicine Name | Dosage | Patient ID |");
        while (rset.next()) {
            System.out.println("   " + rset.getInt("m_Id")+"   " + rset.getString("m_name")+"    " + rset.getInt("m_dosage")+"     " + rset.getInt("p_id"));
        }

        System.out.println("All medicine details displayed.");
        return 0;
    }
    public void closeConnection() {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
    
