package com.nagisa.furukawa.VO;

import com.nagisa.furukawa.PO.Activity;
import com.nagisa.furukawa.PO.Comment;
import com.nagisa.furukawa.PO.User;
import com.nagisa.furukawa.Repository.ActivityRepository;
import com.nagisa.furukawa.Repository.UserRepository;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Component
public class CommentVO {

    @Autowired
    UserRepository userRepository;
    @Autowired
    ActivityRepository activityRepository;

    Integer commenterId;
    Integer actId;
    String commenterName;
    Integer rate;
    String content;
    LocalDateTime createTime;

    public Comment toPO(){
        Comment comment=new Comment();
        User commenter=userRepository.findByUserId(commenterId);
        Activity activity=activityRepository.findByActId(actId);
        comment.setCommenter(commenter);
        comment.setActivity(activity);
        comment.setRate(rate);
        comment.setContent(content);
        return comment;
    }

}
