package com.postgres;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Biblioteca {

	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		
		Class.forName("org.postgresql.Driver");

		Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "1234");  
			
		Statement stmt = conn.createStatement();
		 ResultSet rs = stmt.executeQuery("SELECT * FROM libros");
		 
			
		 while(rs.next()) {
				System.out.println("Titulo " + rs.getString("titulo"));
				System.out.println("Autor " + rs.getString("autor"));
				System.out.println("Precio " + rs.getFloat("precio"));
				System.out.println("Fecha de publicacion " + rs.getDate("fecha"));
				System.out.println("---------------------------------------------------------------");
				
				
			}
			
		 int insertar = stmt.executeUpdate("INSERT INTO libros (id, titulo, autor, precio, fecha) VALUES( 9, 'jdrnjydg', 'HOLIII', 21.99, '1209-05-07')");
			System.out.println();
			System.out.println("*****Filas insertadas correctamente " + insertar + "*****");
			System.out.println();
		 
			PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM libros WHERE titulo = ?");
			pstmt.setString(1, "jdrnjydg");
			ResultSet rs1 = pstmt.executeQuery();
			while(rs1.next()) {
				System.out.println("Titulo " + rs1.getString("titulo"));
				System.out.println("Autor " + rs1.getString("autor"));
				System.out.println("Precio " + rs1.getFloat("precio"));
				System.out.println("Fecha de publicacion " + rs1.getDate("fecha"));
				System.out.println("---------------------------------------------------------------");
			}
			
			System.out.println();
			System.out.println("***** Procedure *****");
			System.out.println();
			CallableStatement cstmt = conn.prepareCall("{CALL libros_list()}");
			ResultSet rs2 = cstmt.executeQuery();
			while(rs2.next()) {
				System.out.println("Titulo " + rs2.getString("titulo"));
				System.out.println("Autor " + rs2.getString("autor"));
				System.out.println("Precio " + rs2.getFloat("precio"));
				System.out.println("Fecha de publicacion " + rs2.getDate("fecha"));
				System.out.println("---------------------------------------------------------------");
				
			}
		
		}
		// TODO Auto-generated method stub

	}


