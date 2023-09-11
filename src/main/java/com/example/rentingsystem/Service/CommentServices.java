package com.example.rentingsystem.Service;

import com.example.rentingsystem.Model.Comment;
import com.example.rentingsystem.Repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentServices {
    private final CommentRepository commentRepository;

    public void updateComment(Integer comment_id, String comment){
        Comment comment1 = commentRepository.getCommentById(comment_id);
        comment1.setComment(comment);
        commentRepository.save(comment1);
    }

    public void deleteComment(Integer comment_id){
        Comment comment = commentRepository.getCommentById(comment_id);
        commentRepository.delete(comment);
    }
}
