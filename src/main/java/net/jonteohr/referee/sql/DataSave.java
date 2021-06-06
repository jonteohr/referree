package net.jonteohr.referee.sql;

import net.jonteohr.referee.core.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class DataSave {
	public static boolean saveCodes() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://" + Configuration.DB_HOST + ":3306/" + Configuration.DB_NAME + "?serverTimezone=UTC",
					Configuration.DB_USER,
					Configuration.DB_PASS);

			PreparedStatement pstmt;

			// TODO setup loop that adds to sql query for each code stored in list

			pstmt = con.prepareStatement("");

//			pstmt.setInt(1, coins);
//			pstmt.setString(2, user);

			pstmt.executeUpdate();

			con.close();
			pstmt.close();

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
