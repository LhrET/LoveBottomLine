package net.web.lblpack.push.service;

import net.web.lblpack.push.bean.User;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/account")
public class AccountService {
    @GET
    @Path("/login")
    @Produces("text/plain;charset=UTF-8")
    public String get(){
        String n = "你好啊";
        System.out.println(n);
        return n;
    }

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({ "application/json;charset=UTF-8" })
    public User post(){
        User user = new User();
        user.setName("刘海瑞");
        System.out.println(user);
        user.setSex(1);
        return user;
    }
}
