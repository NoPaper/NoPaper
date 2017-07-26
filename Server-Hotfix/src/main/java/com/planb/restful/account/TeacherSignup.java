package com.planb.restful.account;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.planb.support.routing.Route;
import com.planb.support.utilities.MySQL;

import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;

@Route(uri = "/signup/teacher", method = HttpMethod.POST)
public class TeacherSignup implements Handler<RoutingContext> {
	@Override
	public void handle(RoutingContext ctx) {
		String id = ctx.request().getFormAttribute("id");
		String pw = ctx.request().getFormAttribute("pw");
		String secret = ctx.request().getFormAttribute("secret");

		ResultSet rs;
		try {
			rs = MySQL.executeQuery("SELECT * FROM secret_keys WHERE secret=?", secret);
			if(!rs.next()) {
				ctx.response().setStatusCode(100).end();
				ctx.response().close();
				return;
			}

			rs = MySQL.executeQuery("SELECT * FROM account_teacher WHERE id=?", id);
			if(rs.next()) {
				MySQL.executeQuery("INSERT INTO account_teacher VALUES(?, ?)", id, pw);
				ctx.response().setStatusCode(201).end();
				ctx.response().close();
			} else {
				ctx.response().setStatusCode(204).end();
				ctx.response().close();
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
