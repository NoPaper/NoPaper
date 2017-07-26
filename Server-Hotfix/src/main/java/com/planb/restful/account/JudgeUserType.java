package com.planb.restful.account;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.planb.support.routing.Route;
import com.planb.support.utilities.MySQL;

import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;

@Route(uri = "/judge_user", method = HttpMethod.POST)
public class JudgeUserType implements Handler<RoutingContext> {
	@Override
	public void handle(RoutingContext ctx) {
		String id = ctx.request().getFormAttribute("id");
		// 이 URI에 접근하는 모든 클라이언트는 로그인된 상태라고 판단
		
		ResultSet rs = MySQL.executeQuery("SELECT * FROM account_student WHERE id=? AND pw=?", id);
		try {
			if(rs.next()) {
				ctx.response().setStatusCode(201).end();
				// 학생일 때 201
				ctx.response().close();
			} else {
				ctx.response().setStatusCode(200).end();
				// 선생님일 때 200
				ctx.response().close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}