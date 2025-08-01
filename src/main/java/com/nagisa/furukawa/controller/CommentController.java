package com.nagisa.furukawa.controller;

import com.nagisa.furukawa.Repository.UserRepository;
import com.nagisa.furukawa.Service.CommentService;
import com.nagisa.furukawa.Util.JwtUtil;
import com.nagisa.furukawa.VO.CommentVO;
import com.nagisa.furukawa.VO.Response;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController("/api/comment")
public class CommentController {

    @Autowired
    CommentService commentService;
    @Autowired
    JwtUtil jwtUtil;
    @Autowired
    UserRepository userRepository;

    @PostMapping("/make_comment/{actId}")
    public Response<Boolean> makeComment(@PathVariable Integer actId,
                                         @RequestBody CommentVO commentVO,
                                         HttpServletRequest request){
        Integer commenterId=jwtUtil.extractUserId(request.getHeader("Authorization"));
        String commentName=userRepository.findByUserId(commenterId).getUserName();
        commentVO.setCommenterId(commenterId);
        commentVO.setCommenterName(commentName);
        commentVO.setActId(actId);
        commentService.makeComment(commentVO);
        return Response.buildSuccess(true);
    }

    @GetMapping("/get_act_comment/{actId}")
    public Response<List<CommentVO>> getActComment(@PathVariable Integer actId){
        return Response.buildSuccess(commentService.getActComment(actId));
    }

}
