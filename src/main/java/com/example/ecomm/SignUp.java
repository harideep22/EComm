package com.example.ecomm;

import java.sql.ResultSet;

public class SignUp {
    public static boolean customerSignUp(String user,String password,String email,int phn,String ad) {



        String encryptedPassword=Login.getEncryptedPassword(password);



        try{
            // insert into customer(name,email,mobile,password) values('Angad Dubey','angad@gmail.com',12345,'abc');
            String placeOrder=   "insert into customer(name,email,mobile,password) values( '"+user+"','"+email+"',"+phn+",'"+encryptedPassword+"')";
            DatabaseConnection dbConn=new DatabaseConnection();
            return dbConn.insertUpdate(placeOrder);
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }
}
