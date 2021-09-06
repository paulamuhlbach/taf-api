package io.github.paulamuhlbach.taf.domain.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Imagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column
    private String filename;

    @NotNull
    @Column
    private String mimetype;

    @Lob
    @Column(name = "file")
    private byte[] data;

    @Column(name = "url_file")
    private String urlFile;

    public Imagem() {
    }

    public Imagem(String filename, String mimetype, byte[] data) {

        // this.id = id;
        this.filename = filename;
        this.mimetype = mimetype;
        this.data = data;
        this.urlFile = this.getUrlFile();
    }

    public Imagem(String fileName2, String contentType, String urlFile2, byte[] bytes) {
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

}
