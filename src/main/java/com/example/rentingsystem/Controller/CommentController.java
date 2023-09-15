package com.example.rentingsystem.Controller;

import com.example.rentingsystem.Api.ApiResponse;
import com.example.rentingsystem.Model.User;
import com.example.rentingsystem.Service.CommentServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentServices commentServices;


    @PutMapping("/update/{comment_id}/{comment}")
    public ResponseEntity updateComment (@AuthenticationPrincipal User user,Integer comment_id,String comment){
        commentServices.updateComment(user,comment_id,comment);
        return ResponseEntity.status(200).body(new ApiResponse("Comment updated successfully"));
    }

    @DeleteMapping("/delete/{comment_id}")
    public ResponseEntity deleteComment(@AuthenticationPrincipal User user,Integer comment_id){
        commentServices.deleteComment(user,comment_id);
        return ResponseEntity.status(200).body(new ApiResponse("Comment deleted successfully"));
    }
}
