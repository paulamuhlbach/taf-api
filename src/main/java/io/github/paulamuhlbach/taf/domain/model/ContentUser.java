package io.github.paulamuhlbach.taf.domain.model;

import java.time.OffsetDateTime;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "content_user")
public class ContentUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "created_in")
    private OffsetDateTime createdIn;

    @Column(name = "last_modify")
    private OffsetDateTime lastModify;

    @Column
    @Size(max = 1, min = 1)
    private String active;

    @NotNull
    @Column(name = "id_user_profile")
    private Long idUserProfile;

    @ManyToOne
    @JoinColumn(name = "id_content_user_role", insertable = false, updatable = false)
    private UserProfile userProfile;

    @NotNull
    @Column(name = "id_content_user_role")
    private Long idContentUserRole;

    @ManyToOne
    @JoinColumn(name = "id_content_user_role", insertable = false, updatable = false)
    private ContentUserRole contentUserRole;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OffsetDateTime getCreatedIn() {
        return createdIn;
    }

    public void setCreatedIn(OffsetDateTime createdIn) {
        if (getCreatedIn() != null) {
            this.createdIn = getCreatedIn();
        } else {
            this.createdIn = OffsetDateTime.now();
        }

    }

    public OffsetDateTime getLastModify() {
        return lastModify;
    }

    public void setLastModify(OffsetDateTime lastModify) {

        this.createdIn = OffsetDateTime.now();
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public Long getIdUserProfile() {
        return idUserProfile;
    }

    public void setIdUserProfile(Long idUserProfile) {
        this.idUserProfile = idUserProfile;
    }

    public UserProfile getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }

    public Long getIdContentUserRole() {
        return idContentUserRole;
    }

    public void setIdContentUserRole(Long idContentUserRole) {
        this.idContentUserRole = idContentUserRole;
    }

    public ContentUserRole getContentUserRole() {
        return contentUserRole;
    }

    public void setContentUserRole(ContentUserRole contentUserRole) {
        this.contentUserRole = contentUserRole;
    }

}
