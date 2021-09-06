package io.github.paulamuhlbach.taf.domain.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.time.OffsetDateTime;

@Entity
@Table(name = "page_content")
public class PageContent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column
    private String title;

    @NotNull
    @Column
    @Lob
    private String content;

    @NotNull
    @Column(name = "created_in")
    private OffsetDateTime createdIn;

    @Column(name = "last_modify")
    private OffsetDateTime lastModify;

    @Column(name = "approved")
    private String approved;

    @Column(name = "id_user_approved")
    private Long idUserApproved;

    @Column(name = "date_approved")
    private OffsetDateTime approvedIn;

    @Column(name = "publish")
    private String publishContent;

    @Column(name = "date_publish")
    private OffsetDateTime publishedIn;

    @Column(name = "id_user_publish")
    private Long idUserPublish;

    @ManyToOne
    @JoinColumn(name = "id_user_publish", insertable = false, updatable = false)
    private ContentUser publisher;

    @NotNull
    @Column(name = "id_user_created")
    private Long idUserCreated;

    @ManyToOne
    @JoinColumn(name = "id_user_created", insertable = false, updatable = false)
    private ContentUser userCreateContent;

    @Column(name = "id_user_modify")
    private Long idUserModify;

    @ManyToOne
    @JoinColumn(name = "id_user_modify", insertable = false, updatable = false)
    private ContentUser userModifyContent;

    @NotNull
    @Column(name = "id_page")
    private Long idPage;

    @ManyToOne
    @JoinColumn(name = "id_page", insertable = false, updatable = false)
    private Page page;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdPage() {
        return idPage;
    }

    public void setIdPage(Long idPage) {
        this.idPage = idPage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getIdUserCreated() {
        return idUserCreated;
    }

    public void setIdUserCreated(Long idUserCreated) {
        this.idUserCreated = idUserCreated;
    }

    public Long getIdUserModify() {
        return idUserModify;
    }

    public void setIdUserModify(Long idUserModify) {
        this.idUserModify = idUserModify;
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

        if (getIdUserModify() != null) {
            this.lastModify = OffsetDateTime.now();
        } else {
            this.lastModify = lastModify;
        }
    }

    public String getApproved() {
        return approved;
    }

    public void setApproved(String approved) {
        this.approved = approved;
    }

    public Long getIdUserApproved() {
        return idUserApproved;
    }

    public void setIdUserApproved(Long idUserApproved) {
        this.idUserApproved = idUserApproved;
    }

    public OffsetDateTime getApprovedIn() {
        return approvedIn;
    }

    public void setApprovedIn(OffsetDateTime approvedIn) {
        if (getApprovedIn() != null) {
            this.approvedIn = OffsetDateTime.now();
        } else {
            this.approvedIn = approvedIn;
        }
    }

    public String getPublishContent() {
        return publishContent;
    }

    public void setPublishContent(String publishContent) {
        this.publishContent = publishContent;
    }

    public Long getIdUserPublish() {
        return idUserPublish;
    }

    public void setIdUserPublish(Long idUserPublish) {
        this.idUserPublish = idUserPublish;
    }

    public OffsetDateTime getPublishedIn() {
        return publishedIn;
    }

    public void setPublishedIn(OffsetDateTime publishedIn) {
        if (getPublishedIn() != null) {
            this.publishedIn = OffsetDateTime.now();
        } else {
            this.publishedIn = publishedIn;
        }
    }

    public ContentUser getPublisher() {
        return publisher;
    }

    public void setPublisher(ContentUser publisher) {
        this.publisher = publisher;
    }

    public ContentUser getUserCreateContent() {
        return userCreateContent;
    }

    public void setUserCreateContent(ContentUser userCreateContent) {
        this.userCreateContent = userCreateContent;
    }

    public ContentUser getUserModifyContent() {
        return userModifyContent;
    }

    public void setUserModifyContent(ContentUser userModifyContent) {
        this.userModifyContent = userModifyContent;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

}
