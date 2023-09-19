package com.fms.repositoryServices.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fms.entity.Comment;
import com.fms.entity.Users;
import com.fms.repositoryServices.CommentRepository;
import com.fms.repositoryServices.UserRepository;

import lombok.extern.slf4j.Slf4j;


@Service
@Transactional
@Slf4j
public class CommentRepositoryImplementation {
    
    @Autowired
    private CommentRepository commentRepository;
    
    
    public Comment create(Comment comment) {
        log.info("Saving new comment");
        return commentRepository.save(comment);
       
    }
     
    public List<Comment> getComments(){
        log.info("Fetching comments");
        return commentRepository.findAll();
    }
    
    public Comment getById(long id) {
        log.info("Getting the comment with " + id);
        return commentRepository.findById(id).get();
    }
    
    public List<Comment> findByUserId(Long id){
        log.info("Getting the comment with user id:" + id);
        return commentRepository.findByActionId(id);
    }
    
    public List<Comment> findByContractId(Long id){
        log.info("Getting the comment with contract id:" + id);
        return commentRepository.findByContractId(id);
    }
    
    @Transactional
    public Comment updateComment(Comment comment) {
        log.info("Updating comment ", comment);
        return commentRepository.save(comment);
    }
    
    public Boolean delete(long id) {
        log.info("Deleting comment by id: ", id );
        commentRepository.deleteById(id);
        return true;
    }
    
    public Boolean deleteByUserId(Long id) {
        log.info("Deleting comment by id: ", id );
        commentRepository.deleteByActionId(id);
        return true;
    }    
    
    
    
}


















