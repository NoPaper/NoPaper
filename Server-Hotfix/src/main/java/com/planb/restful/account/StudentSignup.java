package com.planb.restful.account;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.planb.support.routing.Route;
import com.planb.support.utilities.MySQL;

import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;

@Route(uri = "/signup/student", method = HttpMethod.POST)
public class StudentSignup implements Handler<RoutingContext> {
	@Override
	public void handle(RoutingContext ctx) {
		String id = ctx.request().getFormAttribute("id");
		String pw = ctx.request().getFormAttribute("pw");
		String number = ctx.request().getFormAttribute("number");
		String name = ctx.request().getFormAttribute("name");
		
		ResultSet rs = MySQL.executeQuery("SELECT * FROM account_student WHERE id=?", id);
		try {
			if(rs.next()) {
				MySQL.executeQuery("INSERT INTO account_student VALUES(?, ?, ?, ?)", id, pw, number, name);
				ctx.response().setStatusCode(201).end();
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
