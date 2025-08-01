package com.nagisa.furukawa.Service.Impl;

import com.nagisa.furukawa.PO.Activity;
import com.nagisa.furukawa.PO.Comment;
import com.nagisa.furukawa.Repository.ActivityRepository;
import com.nagisa.furukawa.Repository.CommentRepository;
import com.nagisa.furukawa.Service.CommentService;
import com.nagisa.furukawa.VO.CommentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentRepository commentRepository;
    @Autowired
    ActivityRepository activityRepository;

    @Override
    public Boolean makeComment(CommentVO commentVO){
        Comment comment=commentVO.toPO();
        commentRepository.save(comment);
        return true;
    }

    @Override
    public List<CommentVO> getActComment(Integer actId){
        Activity activity=activityRepository.findByActId(actId);
        List<Comment> comments=commentRepository.findAllByActivity(activity);
        List<CommentVO> commentVOs=comments.stream().map(Comment::toVO).collect(Collectors.toList());
        return commentVOs;
    }

}
