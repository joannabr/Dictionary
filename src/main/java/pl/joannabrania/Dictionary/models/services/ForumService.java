package pl.joannabrania.Dictionary.models.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.joannabrania.Dictionary.models.CommentEntity;
import pl.joannabrania.Dictionary.models.repositories.ForumRepository;

import java.util.Optional;

@Service
public class ForumService {

    final ForumRepository forumRepository;
    final SessionService sessionService;

    @Autowired
    public ForumService(ForumRepository forumRepository, SessionService sessionService) {
        this.forumRepository = forumRepository;
        this.sessionService = sessionService;
    }

    public Iterable<CommentEntity> getAllComents(){
        return forumRepository.findAll();
    }

    public void addComment(String comment){
        CommentEntity commentEntity = createCommentEntity(comment);
        forumRepository.save(commentEntity);
    }

    private CommentEntity createCommentEntity(String comment){
        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setUser(sessionService.getUserEntity());
        commentEntity.setComment(comment);
        commentEntity.setOpinion(0);
        return  commentEntity;
    }

    public void deleteComment(int commentId) {
        Optional<CommentEntity> commentEntity = forumRepository.findById(commentId);
        forumRepository.delete(commentEntity.get());

    }

    public void addOpinion(int commentId) {
        Optional<CommentEntity> commentEntity = forumRepository.findById(commentId);
        CommentEntity commentEntity1 = createOpinionCommentEntity(commentEntity.get());
        forumRepository.save(commentEntity1);
    }

    private CommentEntity createOpinionCommentEntity(CommentEntity commentEntity){
        CommentEntity commentEntity1 = new CommentEntity();
        commentEntity.setId(commentEntity.getId());
        commentEntity.setUser(commentEntity.getUser());
        commentEntity.setComment(commentEntity.getComment());
        commentEntity.setCommentData(commentEntity.getCommentData());
        commentEntity.setOpinion(commentEntity.getOpinion()+1);
        return  commentEntity;
    }
}
