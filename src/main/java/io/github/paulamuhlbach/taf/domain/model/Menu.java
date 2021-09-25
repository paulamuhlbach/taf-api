package io.github.paulamuhlbach.taf.domain.model;

import java.time.OffsetDateTime;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "menu_name")
    @Size(max = 50)
    private String name;

    @NotNull
    @Size(max = 200)
    private String description;

    @Column(name = "created_in")
    private OffsetDateTime createdIn;

    @Column(name = "last_modify")
    private OffsetDateTime lastModify;

    @Column
    private Long level;

    @Column
    @Size(max = 1, min = 1)
    private String active;

    @NotNull
    @Column(name = "id_menu_group")
    private Long idMenuGroup;

    @Column(name = "id_submenu")
    private Long idSubmenu;

    @Column(name = "id_image_icon")
    private Long idIcon;

    @ManyToOne
    @JoinColumn(name = "id_menu_group", insertable = false, updatable = false)
    private MenuGroup menuGroup;

    @ManyToOne
    @JoinColumn(name = "id_submenu", insertable = false, updatable = false)
    private Menu subMenu;

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

    @ManyToOne
    @JoinColumn(name = "id_image_icon", insertable = false, updatable = false)
    private Imagem icon;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public Long getLevel() {
        return level;
    }

    public void setLevel(Long level) {
        this.level = level;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public Long getIdMenuGroup() {
        return idMenuGroup;
    }

    public void setIdMenuGroup(Long idMenuGroup) {
        this.idMenuGroup = idMenuGroup;
    }

    public Long getIdSubmenu() {
        return idSubmenu;
    }

    public void setIdSubmenu(Long idSubmenu) {
        this.idSubmenu = idSubmenu;
    }

    public Long getIdIcon() {
        return idIcon;
    }

    public void setIdIcon(Long idIcon) {
        this.idIcon = idIcon;
    }

    public MenuGroup getMenuGroup() {
        return menuGroup;
    }

    public void setMenuGroup(MenuGroup menuGroup) {
        this.menuGroup = menuGroup;
    }

    public Menu getSubMenu() {
        return subMenu;
    }

    public void setSubMenu(Menu subMenu) {
        this.subMenu = subMenu;
    }

    public Long getIdUserCreated() {
        return idUserCreated;
    }

    public void setIdUserCreated(Long idUserCreated) {
        this.idUserCreated = idUserCreated;
    }

    public ContentUser getUserCreateContent() {
        return userCreateContent;
    }

    public void setUserCreateContent(ContentUser userCreateContent) {
        this.userCreateContent = userCreateContent;
    }

    public Long getIdUserModify() {
        return idUserModify;
    }

    public void setIdUserModify(Long idUserModify) {
        this.idUserModify = idUserModify;
    }

    public ContentUser getUserModifyContent() {
        return userModifyContent;
    }

    public void setUserModifyContent(ContentUser userModifyContent) {
        this.userModifyContent = userModifyContent;
    }

    public Imagem getIcon() {
        return icon;
    }

    public void setIcon(Imagem icon) {
        this.icon = icon;
    }

}
