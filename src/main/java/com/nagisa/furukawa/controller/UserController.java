package com.nagisa.furukawa.controller;


import com.nagisa.furukawa.Service.UserService;
import com.nagisa.furukawa.Util.JwtUtil;
import com.nagisa.furukawa.VO.Response;
import com.nagisa.furukawa.VO.UserVO;
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
            return Response.buildFailure("Username already exists", 400);
        }
        if(userService.register(newUser)){
            return Response.buildSuccess(true);
        }
        else{
            return Response.buildFailure("Register failed, arguments may be invalid",400);
        }
    }

    /**用户登录方法
     */
    @GetMapping("/login")
    public Response<UserVO> login(@RequestBody UserVO toLoginUser){

    }

    /**用户查看自身信息
    */
    @GetMapping("/{userId}")
    public Response<UserVO> getPersonalInfo(@PathVariable Integer userId){}

    /**用户修改自身信息
     */
    @PutMapping("/{userId}")
    public Response<UserVO> setPersonalInfo(@PathVariable Integer userId,
                                            @RequestBody UserVO newUserInfo){}

    //todo: 查看所有报名

}
