package com.ourstocks.jwtapp.model;

import com.ourstocks.jwtapp.dto.usersDTO.UserDTO;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "posts")
public class Post extends BaseEntity{
    @Column(name = "title")
    private String title;
    @Column(name = "anons")
    private String anons;
    @Column(name = "full_text")
    private String fullText;

    @OneToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST,CascadeType.DETACH,
            CascadeType.REFRESH,CascadeType.MERGE},
            mappedBy = "post")
    private List<PostComment> comments;

    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.DETACH,
            CascadeType.REFRESH, CascadeType.MERGE})
    @JoinColumn(name = "author_id")
    private User author;

    public Post() {
    }

    public Post(String title, String anons, String fullText) {
        this.title = title;
        this.anons = anons;
        this.fullText = fullText;
    }

    public String getTitle() {
        return this.title;
    }

    public String getAnons() {
        return this.anons;
    }

    public String getFullText() {
        return this.fullText;
    }

    public UserDTO getAuthor() {
        return UserDTO.fromUser(this.author);
    }

    public void setTitle(@NotBlank(message = "Post's title cannot be empty.") String title) {
        this.title = title;
    }

    public void setAnons(String anons) {
        this.anons = anons;
    }

    public void setFullText(@NotBlank(message = "Post's text cannot be empty.") String fullText) {
        this.fullText = fullText;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public List<PostComment> getComments() {
        return comments;
    }

    public void setComments(List<PostComment> comments) {
        this.comments = comments;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Post)) return false;
        final Post other = (Post) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$title = this.getTitle();
        final Object other$title = other.getTitle();
        if (this$title == null ? other$title != null : !this$title.equals(other$title)) return false;
        final Object this$anons = this.getAnons();
        final Object other$anons = other.getAnons();
        if (this$anons == null ? other$anons != null : !this$anons.equals(other$anons)) return false;
        final Object this$fullText = this.getFullText();
        final Object other$fullText = other.getFullText();
        if (this$fullText == null ? other$fullText != null : !this$fullText.equals(other$fullText)) return false;
        final Object this$author = this.getAuthor();
        final Object other$author = other.getAuthor();
        if (this$author == null ? other$author != null : !this$author.equals(other$author)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Post;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $title = this.getTitle();
        result = result * PRIME + ($title == null ? 43 : $title.hashCode());
        final Object $anons = this.getAnons();
        result = result * PRIME + ($anons == null ? 43 : $anons.hashCode());
        final Object $fullText = this.getFullText();
        result = result * PRIME + ($fullText == null ? 43 : $fullText.hashCode());
        final Object $author = this.getAuthor();
        result = result * PRIME + ($author == null ? 43 : $author.hashCode());
        return result;
    }

    public String toString() {
        return "Post(title=" + this.getTitle()
                + ", anons=" + this.getAnons()
                + ", fullText=" + this.getFullText()
                + ", author=" + this.getAuthor()
                + ")";
    }

    public void addCommentToPost(PostComment comment){
        if(comments == null){
            comments = new ArrayList<>();
        }
        comments.add(comment);
        comment.setPost(this);
    }

//    public void addImageToPost(Image image) {
//        image.setPost(this);
//        images.add(image);
//    }
}
