package io.github.paulamuhlbach.taf.domain.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity

public class Instituicao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 100)
    @Column
    private String nome;

    @NotNull
    @Size(max = 14, min = 14)
    @Column
    private String cnpj;

    @NotNull
    @Column(name = "id_tipo_instituicao")
    private Long idTipoInstituicao;

    @Column(name = "id_endereco")
    private Long idEndereco;

    @ManyToOne
    @JoinColumn(name = "id_tipo_instituicao", insertable = false, updatable = false)
    private TipoInstituicao tipoInstituicao;

    @ManyToOne
    @JoinColumn(name = "id_endereco", insertable = false, updatable = false)
    private Endereco endereco;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public Long getIdTipoInstituicao() {
        return idTipoInstituicao;
    }

    public void setIdTipoInstituicao(Long idTipoInstituicao) {
        this.idTipoInstituicao = idTipoInstituicao;
    }

    public Long getIdEndereco() {
        return idEndereco;
    }

    public void setIdEndereco(Long idEndereco) {
        this.idEndereco = idEndereco;
    }

    public TipoInstituicao getTipoInstituicao() {
        return tipoInstituicao;
    }

    public void setTipoInstituicao(TipoInstituicao tipoInstituicao) {
        this.tipoInstituicao = tipoInstituicao;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

}
