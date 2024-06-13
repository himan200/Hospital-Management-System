package hospital;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Objects;
import java.util.Properties;
import java.util.Scanner;

public class runhospital {
    public static void main(String[] args) throws Exception{
        Scanner new1=new Scanner(System.in);
        String str="Yes";
        int p_id,m_Id,M_dosage;
        double fees;
        String p_name,p_disease, d_name,m_name;
        Properties prop=new Properties();
        prop.load(new FileInputStream("D:\\javaasignment\\Hospital\\connection.properties"));
        Connection conn= DriverManager.getConnection(prop.getProperty("url"),prop.getProperty("username"),prop.getProperty("password"));
        PreparedStatement prestate;
        Controllerclass runhospital= new Controllerclass();

        do{
            System.out.println("Choice Operation Number you wnat to Perform");
            System.out.println("Enter 1 for Creating Table ");
            System.out.println("Enter 2 for Insert Patient details ");
            System.out.println("Enter 3 for Find Patient By Doctor ");
            System.out.println("Enter 4 for Change Doctor Name ");
            System.out.println("Enter 5 for find patient ");
            System.out.println("Enter 6 for Find Same Patient Disease ");
            System.out.println("Enter 7 for Discount in Fees ");
            System.out.println("Enter 8 Select All Patient Details ");
            System.out.println("Enter 9 for Creating Medicine Table ");
            System.out.println("Enter 10 for Insert Medicine ");
            System.out.println("Enter 11 Select Specific Medicine ");
            System.out.println("Enter 12 Display all Medicine ");
            System.out.println("Enter 13 exit ");
            int choice= new1.nextInt();

            switch (choice){
                case 1:{
                    runhospital.create();
                }break;
                case 2 : {
                    runhospital.insert();
                }break;
                case 3:{
                    runhospital.find_doctor();

                }break;
                case 4:{
                    runhospital.change_dname();

                }break;
                case 5:{
                    runhospital.find_patient();

                }
                break;
                case 6:{
                    runhospital.same_p_disease();

                }
                break;
                case 7:{
                    runhospital.discount();

                }
                break;
                case 8:{
                    runhospital.select_all();

                }break;
                case 9:{
                    runhospital.Medicine_table();

                }break;
                case 10:{
                    runhospital.medicien_insert();
                }break;
                case 11:{
                    runhospital.select_medi();
                }break;
                case 12:{
                    runhospital.select_AllMedi();
                }break;
                case 13:{
                    System.exit(0);
                }break;
                default:{
                    System.out.println("You entered Wrong Input Operation");
                }

            }
            System.out.println(" Do you want to more operations (y/n)");
            str=new1.next();
        }
        while (Objects.equals(str,"y") || Objects.equals(str, "Y"));

        conn.close();

    }
}
