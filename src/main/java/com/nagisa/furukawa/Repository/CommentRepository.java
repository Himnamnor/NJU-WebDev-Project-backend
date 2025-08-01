package com.nagisa.furukawa.Repository;

import com.nagisa.furukawa.PO.Activity;
import com.nagisa.furukawa.PO.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {
    List<Comment> findAllByActivity(Activity activity);
}
