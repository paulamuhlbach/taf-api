package io.github.paulamuhlbach.taf.domain.model;

import java.time.OffsetDateTime;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Page {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 100)
    private String title;

    @NotNull
    @Size(max = 45)
    private String slug;

    @NotNull
    @Size(max = 45)
    private String url;

    @Size(max = 200)
    private String description;

    @NotNull
    @Column(name = "created_in")
    private OffsetDateTime createdIn;

    @Column(name = "last_modify")
    private OffsetDateTime lastModify;

    @Size(max = 1, min = 1)
    private String active;

    @NotNull
    @Column(name = "id_page_type")
    private Long idPageType;

    @ManyToOne
    @JoinColumn(name = "id_page_type", insertable = false, updatable = false)
    private PageType pageType;

    @NotNull
    @Column(name = "id_menu")
    private Long idMenu;

    @ManyToOne
    @JoinColumn(name = "id_menu", insertable = false, updatable = false)
    private Menu menu;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public Long getIdPageType() {
        return idPageType;
    }

    public void setIdPageType(Long idPageType) {
        this.idPageType = idPageType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
        // pegar o nome do grupo do menu, o nível e o slug!!!!
    }

    public Long getIdMenu() {
        return idMenu;
    }

    public void setIdMenu(Long idMenu) {
        this.idMenu = idMenu;
    }

    public PageType getPageType() {
        return pageType;
    }

    public void setPageType(PageType pageType) {
        this.pageType = pageType;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
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

}
