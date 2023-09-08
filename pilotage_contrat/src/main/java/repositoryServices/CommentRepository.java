package repositoryServices;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fms.entity.Comment;

import jakarta.transaction.Transactional;

public interface CommentRepository extends JpaRepository<Comment, Long>{
    List<Comment> findByUserId(Long userId);
    
    @Transactional
    void deleteByUserId(long userId);

}
