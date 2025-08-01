package com.nagisa.furukawa.Service;

import com.nagisa.furukawa.VO.CommentVO;

import java.util.List;

public interface CommentService {

    Boolean makeComment(CommentVO commentVO);
    List<CommentVO> getActComment(Integer actId);

}
