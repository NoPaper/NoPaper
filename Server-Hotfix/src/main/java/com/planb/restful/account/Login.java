package com.planb.restful.account;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.planb.support.routing.Route;
import com.planb.support.utilities.MySQL;

import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;

@Route(uri = "/login", method = HttpMethod.POST)
public class Login implements Handler<RoutingContext> {
	@Override
	public void handle(RoutingContext ctx) {
		String id = ctx.request().getFormAttribute("id");
		String pw = ctx.request().getFormAttribute("pw");
		
		ResultSet rs;
		rs = MySQL.executeQuery("SELECT * FROM account_student WHERE id=? AND pw=?", id, pw);
		try {
			if(rs.next()) {
				ctx.response().setStatusCode(200).end();
				ctx.response().close();
			} else {
				rs = MySQL.executeQuery("SELECT * FROM account_teacher WHERE id=? AND pw=?", id, pw);
				if(rs.next()) {
					ctx.response().setStatusCode(201).end();
					ctx.response().close();
				} else {
					ctx.response().setStatusCode(204).end();
					ctx.response().close();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}