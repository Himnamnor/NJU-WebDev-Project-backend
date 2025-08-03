package com.nagisa.furukawa.controller;

import com.nagisa.furukawa.Enum.RegStatus;
import com.nagisa.furukawa.PO.User;
import com.nagisa.furukawa.Service.RegService;
import com.nagisa.furukawa.Util.JwtUtil;
import com.nagisa.furukawa.VO.RegInfoVO;
import com.nagisa.furukawa.VO.Response;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RegController {

    @Autowired
    RegService regService;
    @Autowired
    JwtUtil jwtUtil;

    @PostMapping("/sign_up/{actId}")
    public Response<Boolean> signUp(@PathVariable Integer actId, HttpServletRequest request){
        RegInfoVO regInfoVO = new RegInfoVO();
        String token=request.getHeader("Authorization");
        Integer userId;
        try{
            userId=jwtUtil.extractUserId(token);
        }catch (RuntimeException e){
            return Response.buildFailure(false, "Invalid token or token parse error", 401);
        }
        regInfoVO.setActId(actId);
        regInfoVO.setUserId(userId);
        regInfoVO.setStatus(RegStatus.YET);
        try{
            regService.signUp(regInfoVO);
            return Response.buildSuccess(true);
        } catch (RuntimeException e) {
            return Response.buildFailure(false, e.getMessage(), 400);
        }
    }

    @GetMapping("/get_user_reg_info")
    public Response<List<RegInfoVO>> getUserRegInfo(HttpServletRequest request){
        String token=request.getHeader("Authorization");
        Integer userId;
        try{
            userId=jwtUtil.extractUserId(token);
        }catch (RuntimeException e){
            return Response.buildFailure(null, "Invalid token or token parse error", 401);
        }
        try{
            List<RegInfoVO>ret=regService.getUserRegInfo(userId);
            return Response.buildSuccess(ret);
        } catch (RuntimeException e) {
            return Response.buildFailure(null, e.getMessage(), 400);
        }
    }

    @DeleteMapping("/delete_reg_info/{regId}")
    public Response<Boolean> deleteRegInfo(@PathVariable Integer regId, HttpServletRequest request){
        String token=request.getHeader("Authorization");
        try{
            jwtUtil.extractUserId(token);
        }catch (RuntimeException e){
            return Response.buildFailure(false, "Invalid token or token parse error", 401);
        }
        try{
            Boolean ret=regService.deleteRegInfo(regId);
            return Response.buildSuccess(ret);
        } catch (RuntimeException e) {
            return Response.buildFailure(false, e.getMessage(), 400);
        }
    }

}
