package com.planb.restful.school;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONArray;
import org.json.JSONObject;

import com.planb.support.routing.Route;
import com.planb.support.utilities.MySQL;

import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;

@Route(uri = "/meal", method = HttpMethod.GET)
public class Meal implements Handler<RoutingContext> {
	@Override
	public void handle(RoutingContext ctx) {
		String date = ctx.request().getParam("date");
		
		ResultSet rs = MySQL.executeQuery("SELECT * FROM meal WHERE date=?", date);
		try {
			if(rs.next()) {
				JSONObject resp = new JSONObject();
				resp.put("breakfast", new JSONArray(rs.getString("breakfast")));
				resp.put("lunch", new JSONArray(rs.getString("lunch")));
				resp.put("dinner", new JSONArray(rs.getString("dinner")));
				
				ctx.response().setStatusCode(200).end(resp.toString());
				ctx.response().close();
			} else {
				ctx.response().setStatusCode(204).end();
				ctx.response().close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
