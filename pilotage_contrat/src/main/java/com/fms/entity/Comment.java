package com.fms.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

import enums.entity_enums.CommentTypeEnum;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.PrePersist;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


//@NoArgsConstructor
//@AllArgsConstructor
@Getter
@Setter
@Entity

//@NamedQuery(name = Comment.FIND_ALL_COMMENTS, query = "Select c from Comment orderby c.createdOn")
//@NamedQuery(name = Comment.FIND_ALL_COMMENTS_BY_ID, query = "Select c from Comment where c.id = :id")
//@NamedQuery(name = Comment.FIND_ALL_COMMENTS_BY_PROFIL, query = "Select c from Comment where c.profil = :profil")
//@NamedQuery(name = Comment.FIND_ALL_COMMENTS_BY_USER, query = "Select c from Comment where c.user = :user")
//@NamedQuery(name = Comment.FIND_ALL_COMMENTS_BY_DATE, query = "Select c from Comment where c.date = :date")
//@NamedQuery(name = Comment.FIND_ALL_COMMENTS_BY_Type, query = "Select c from Comment where c.type = :type")


public class Comment extends AbstractElementsEntity{
	
	/**
	 * <p>les constantes des requêtes <p>
	 */
	
//	public static final String FIND_ALL_COMMENTS = "Comment.findAllComments";
//	public static final String FIND_ALL_COMMENTS_BY_ID = "Comment.findAllCommentsById";
//	public static final String FIND_ALL_COMMENTS_BY_USER = "Comment.findAllCommentsByUser";
//	public static final String FIND_ALL_COMMENTS_BY_PROFIL = "Comment.findAllCommentsByProfil";
//	public static final String FIND_ALL_COMMENTS_BY_DATE = "Comment.findAllCommentsByDate";
//	public static final String FIND_ALL_COMMENTS_BY_Type = "Comment.findAllCommentsByType";
	/**
	 * <p>Date de creation du Comment <p>
	 */	
	private LocalDateTime date;
	
	/**
	 * <p>L'utilisateur qui a enregistre le commentaire <p>
	 */	
	@NotNull(message = "Profil doit pas etre vide")
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "user_id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
	private Users profil;
	
	//Possibly can be setup as an ENUM
	/**
	 * <p>Le type Issue/Risk <p>
	 */
	@NotNull(message = "Type de commentaire non selectionnes")
	@Enumerated(EnumType.STRING)
	private CommentTypeEnum type;
	
	/**
	 * <p>Le commentaire <p>
	 */
	@NotNull(message = "Commentaire doit pas etre vide")
	@Size(min = 2, max = 500, message = "Commentaire est trop court.")
	private String commentaire;
	
	/**
	 * <p>L'utlisatuer qui doit gere le commenatire... je sais pas trops pour cette donnee <p>
	 */
	//Might be @ManyToMany need to clarify
	@ManyToOne
	private Users user;
	
	/**
	 * <p>Creation de date automatiquement. <p>
	 */
	@PrePersist
	private void init() {
		setDate(LocalDateTime.now());
	}

}
