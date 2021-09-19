package io.github.paulamuhlbach.taf.domain.model;

import javax.persistence.*;

@Entity
@Table(name = "imagem")
public class Imagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String type;

    @Lob
    private byte[] data;

    @Column(name = "url_file")
    private String urlFile;

    public Imagem() {
    }

    public Imagem(String name, String type, byte[] data) {

        // this.id = id;
        this.name = name;
        this.type = type;
        this.data = data;
        // this.urlFile = this.getUrlFile();
    }

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public String getUrlFile() {
        return urlFile;
    }

    public void setUrlFile(String urlFile) {

        String fileExtension = getFileExtension(name);

        if (fileExtension != null)
            this.urlFile = this.name + '.' + fileExtension;
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
