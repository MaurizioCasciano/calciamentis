package paydesk;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import database.Database;

public class allPurch {
	public allPurch(String username){
		String query="SELECT acquisti.idAcquisti FROM acquisti WHERE username=?;";
		PreparedStatement psID =Database.getPreparedStatement(query);
		try {
			psID.setString(1, username);
			ResultSet rs=psID.executeQuery();
			while(rs.next()){
				Integer e=rs.getInt("idAcquisti");
				allIdPurch.add(e);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for (Integer i:allIdPurch){
			purchasedCart pCart=new purchasedCart(i);
			allP.add(pCart);
		}
		
		
		String queryDate="SELECT acquisti.data FROM acquisti WHERE acquisti.username=?;";
		PreparedStatement psDate=Database.getPreparedStatement(queryDate);
		try {
			psDate.setString(1, username);
			ResultSet rsDate=psDate.executeQuery();
			while(rsDate.next()){
				Timestamp t=rsDate.getTimestamp("data");
				System.out.println(t);
				allDate.add(t);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public ArrayList<purchasedCart> getAllPurch(){
		return allP;
	}
	public int getSize(){
		return allP.size();
	}
	public purchasedCart getCart(int index){
		return allP.get(index);
	}
	public Timestamp getDate(int index){
		return allDate.get(index);
	}
	
	
	ArrayList <Timestamp> allDate=new ArrayList<>();
	ArrayList <purchasedCart> allP=new ArrayList<>();
	ArrayList <Integer> allIdPurch=new ArrayList<>(); 
}
