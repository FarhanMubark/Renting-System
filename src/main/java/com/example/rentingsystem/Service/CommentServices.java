package com.example.rentingsystem.Service;

import com.example.rentingsystem.Api.ApiException;
import com.example.rentingsystem.Model.Comment;
import com.example.rentingsystem.Model.User;
import com.example.rentingsystem.Repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentServices {
    private final CommentRepository commentRepository;

    public void updateComment(User user, Integer comment_id, String comment){
        Comment comment1 = commentRepository.getCommentById(comment_id);
        if(comment1.getUsername().equals(user.getUsername()) || user.getRole().equals("ADMIN")){
            comment1.setComment(comment);
            commentRepository.save(comment1);
        }else{
            throw new ApiException("this comment not yours");
        }
        }

    public void deleteComment(User user,Integer comment_id){

        Comment comment = commentRepository.getCommentById(comment_id);
        if(comment.getUsername().equals(user.getUsername()) || user.getRole().equals("ADMIN")){
            commentRepository.delete(comment);
        }

    }
}
