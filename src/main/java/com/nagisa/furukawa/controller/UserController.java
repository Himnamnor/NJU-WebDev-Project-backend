package com.nagisa.furukawa.controller;

import com.nagisa.furukawa.Service.UserService;
import com.nagisa.furukawa.Util.JwtUtil;
import com.nagisa.furukawa.VO.Response;
import com.nagisa.furukawa.VO.UserVO;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController("/api/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    JwtUtil jwtUtil;

    /**用户注册方法
     */
    @PostMapping("/register")
    public Response<Boolean> register(@RequestBody UserVO newUser){
        if(userService.containUsername(newUser.getUserName())){
            return Response.buildFailure(false,"Username already exists", 400);
        }
        if(userService.register(newUser)){
            return Response.buildSuccess(true);
        }
        else{
            return Response.buildFailure(false,"Register failed, arguments may be invalid",400);
        }
    }

    /**用户登录方法
     */
    @PostMapping("/login")
    public Response<UserVO> login(@RequestBody UserVO toLoginUser, HttpServletResponse response){
        if(toLoginUser.getUserName() == null || toLoginUser.getPassword() == null){
            return Response.buildFailure(null,"Username or password cannot be null", 400);
        }
        UserVO retUser=userService.login(toLoginUser);
        if(retUser==null){
            return Response.buildFailure(null,"Login failed, username does not exist or password is incorrect",400);
        }
        String token=jwtUtil.generateToken(retUser.getUserId());
        response.setHeader("Authorization", token);
        return Response.buildSuccess(retUser);
    }

    /**用户查看自身信息
    */
    @GetMapping("/{userId}")
    public Response<UserVO> getPersonalInfo(@PathVariable Integer userId){
        UserVO retVO= userService.getPersonalInfo(userId);
        if(retVO==null){
            return Response.buildFailure(null,"Cannot find user info according to given id",400);
        }
        return Response.buildSuccess(retVO);
    }

    /**用户修改自身信息
     */
    @PutMapping("/{userId}")
    public Response<UserVO> setPersonalInfo(@PathVariable Integer userId,
                                            @RequestBody UserVO newUserInfo){
        UserVO ret=userService.setPersonalInfo(userId,newUserInfo);
        if(ret==null){
            return Response.buildFailure(null,"Cannot find user info according to given id",400);
        }
        return Response.buildSuccess(ret);
    }

    //todo: 查看所有报名

}
