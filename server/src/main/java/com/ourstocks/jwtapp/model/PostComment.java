package com.ourstocks.jwtapp.model;

import com.ourstocks.jwtapp.dto.postsDTO.PostDTO;
import com.ourstocks.jwtapp.dto.usersDTO.UserDTO;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "comments")
public class PostComment extends BaseEntity{
    @Column(name = "comment")
    @NotBlank(message = "Comment can not be empty")
    @Size(max = 200)
    private String comment;

    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.DETACH,
            CascadeType.REFRESH, CascadeType.MERGE})
    @JoinColumn(name = "author_id")
    private User author;

    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.DETACH,
            CascadeType.REFRESH, CascadeType.MERGE})
    @JoinColumn(name = "post_id")
    private Post post;

    public PostComment() {
    }

    public PostComment(String comment) {
        this.comment = comment;
    }

    public @NotBlank(message = "Comment can not be empty") @Size(max = 200) String getComment() {
        return this.comment;
    }

    public UserDTO getAuthor() {
        return UserDTO.fromUser(this.author);
    }

    public PostDTO getPost() {
        return PostDTO.fromPost(this.post);
    }

    public void setComment(@NotBlank(message = "Comment can not be empty") @Size(max = 200) String comment) {
        this.comment = comment;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof PostComment)) return false;
        final PostComment other = (PostComment) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$comment = this.getComment();
        final Object other$comment = other.getComment();
        if (this$comment == null ? other$comment != null : !this$comment.equals(other$comment)) return false;
        final Object this$author = this.getAuthor();
        final Object other$author = other.getAuthor();
        if (this$author == null ? other$author != null : !this$author.equals(other$author)) return false;
        final Object this$post = this.getPost();
        final Object other$post = other.getPost();
        if (this$post == null ? other$post != null : !this$post.equals(other$post)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof PostComment;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $comment = this.getComment();
        result = result * PRIME + ($comment == null ? 43 : $comment.hashCode());
        final Object $author = this.getAuthor();
        result = result * PRIME + ($author == null ? 43 : $author.hashCode());
        final Object $post = this.getPost();
        result = result * PRIME + ($post == null ? 43 : $post.hashCode());
        return result;
    }

    public String toString() {
        return "PostComment(comment=" + this.getComment() + ", author=" + this.getAuthor() + ", post=" + this.getPost() + ")";
    }
}
