package com.example.rentingsystem.Repository;

import com.example.rentingsystem.Model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment,Integer>
{
    Comment getCommentById(Integer id);
}
