package com.planb.support.utilities;

import java.util.Calendar;
import java.util.List;

import org.json.JSONArray;

import com.planb.parser.School;
import com.planb.parser.SchoolException;
import com.planb.parser.SchoolMenu;

public class Parser extends Thread {
	@Override
	public void run() {
		while(true) {
			Calendar cal = Calendar.getInstance();
			
			try {
				int curYear = cal.get(Calendar.YEAR);
				int curMonth = cal.get(Calendar.MONTH) + 1;
				
				School api = new School(School.Type.HIGH, School.Region.DAEJEON, "G100000170");
				List<SchoolMenu> menus = api.getMonthlyMenu(curYear, curMonth);
				
				int maxDate = cal.getActualMaximum(Calendar.DATE);
				
				for(int i = 0; i < maxDate; i++) {
					// 1일부터 끝까지
					SchoolMenu dayMenu = menus.get(i);
					JSONArray[] menuArray = new JSONArray[3];
					
					String[] meals = new String[3];
					
					meals[0] = dayMenu.breakfast;
					meals[1] = dayMenu.lunch;
					meals[2] = dayMenu.dinner;
					
					for(int j = 0; j < 3; j++) {
						menuArray[j] = new JSONArray();
						
						for(String time: meals[j].split("\n")) {
							String menu = time.split("\\*")[0];
							menuArray[j].put(menu);
						}
					}
					
					String today = String.format("%04d-%02d-%02d", curYear, curMonth, i + 1);
					MySQL.executeUpdate("DELETE FROM meal WHERE date=?", today);
					MySQL.executeUpdate("INSERT INTO meal(date, breakfast, lunch, dinner) VALUES(?, ?, ?, ?)", today, menuArray[0].toString(), menuArray[1].toString(), menuArray[2].toString());
				}
				
				Thread.sleep(1000 * 3600 * 24);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (SchoolException e) {
				e.printStackTrace();
			}
		}
	}
}
