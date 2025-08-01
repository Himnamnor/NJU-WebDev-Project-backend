package com.nagisa.furukawa.PO;

import com.nagisa.furukawa.VO.CommentVO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Setter
@Getter
public class Comment {

    @Id
    @GeneratedValue(strategy =jakarta.persistence.GenerationType.IDENTITY)
    Integer commentId;

    @Column
    String content;

    @ManyToOne
    @JoinColumn(
            name="user_id",
            nullable = false
    )
    User commenter;

    @OneToOne
    @JoinColumn(
            name="act_id",
            nullable = false
    )
    Activity activity;

    @Column(nullable = false)
    Integer rate;

    @Column(nullable = false,updatable = false)
    @CreationTimestamp
    LocalDateTime createTime;

    public CommentVO toVO(){
        CommentVO commentVO = new CommentVO();
        commentVO.setCommenterId(this.commenter.getUserId());
        commentVO.setActId(this.activity.getActId());
        commentVO.setCommenterName(this.commenter.getUserName());
        commentVO.setRate(this.rate);
        commentVO.setContent(this.content);
        commentVO.setCreateTime(this.createTime);
        return commentVO;
    }

}
