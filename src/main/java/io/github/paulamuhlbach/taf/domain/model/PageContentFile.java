package io.github.paulamuhlbach.taf.domain.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "page_content_file")
public class PageContentFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "id_page_content")
    private Long idPageContent;

    @ManyToOne
    @JoinColumn(name = "id_page_content", insertable = false, updatable = false)
    private PageContent pageContent;

    @NotNull
    @Column
    private String filename;

    @NotNull
    @Column
    private String mimetype;

    @Lob
    @Column(name = "file")
    private byte[] data;

    @NotNull
    @Column(name = "url_file")
    private String urlFile;

    public PageContentFile() {
    }

    public PageContentFile(String filename, String mimetype, byte[] data) {

        // this.id = id;
        this.filename = filename;
        this.mimetype = mimetype;
        this.data = data;
        this.urlFile = this.getUrlFile();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getMimetype() {
        return mimetype;
    }

    public void setMimetype(String mimetype) {
        this.mimetype = mimetype;
    }

    public byte[] getFile() {
        return data;
    }

    public void setFile(byte[] data) {
        this.data = data;
    }

    public String getUrlFile() {
        return urlFile;
    }

    public void setUrlFile(String urlFile) {

        String fileExtension = getFileExtension(filename);

        if (fileExtension != null)
            this.urlFile = this.filename + '.' + fileExtension;
        else
            this.urlFile = null;

    }

    public String getFileExtension(String filename) {
        if (filename.contains("."))
            return filename.substring(filename.lastIndexOf(".") + 1);
        else
            return "";
    }

    public Long getIdPageContent() {
        return idPageContent;
    }

    public void setIdPageContent(Long idPageContent) {
        this.idPageContent = idPageContent;
    }

    public PageContent getPageContent() {
        return pageContent;
    }

    public void setPageContent(PageContent pageContent) {
        this.pageContent = pageContent;
    }

}
