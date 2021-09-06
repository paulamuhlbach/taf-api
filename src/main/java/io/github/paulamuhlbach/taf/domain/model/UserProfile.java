package io.github.paulamuhlbach.taf.domain.model;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "user_profile")
public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 200)
    @Column(name = "primeiro_nome")
    private String primeiroNome;

    @NotNull
    @Size(max = 200)
    @Column(name = "sobrenome")
    private String sobrenome;

    @NotNull
    @Size(max = 200)
    @Column(name = "login")
    private String userLogin;

    @NotNull
    @Size(max = 20)
    @Column(name = "ptafsenha")
    private String ptafSenha;

    @Column
    @Size(max = 11, min = 11)
    private String telefone;

    @Column(name = "id_image")
    private Long idUserImage;

    @ManyToOne
    @JoinColumn(name = "id_image", insertable = false, updatable = false)
    private Imagem userImage;

    @Column(name = "id_endereco")
    private Long idEndereco;

    @ManyToOne
    @JoinColumn(name = "id_endereco", insertable = false, updatable = false)
    private Endereco endereco;

    @Column(name = "id_instituicao")
    private Long idInstituicao;

    @ManyToOne
    @JoinColumn(name = "id_instituicao", insertable = false, updatable = false)
    private Instituicao instituicao;

    @NotNull
    @Column
    @Size(max = 11, min = 11)
    private String cpf;

    @NotNull
    @Email
    @Column
    @Size(max = 200)
    private String email;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPrimeiroNome() {
        return primeiroNome;
    }

    public void setPrimeiroNome(String primeiroNome) {
        this.primeiroNome = primeiroNome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public String getPtafSenha() {
        return ptafSenha;
    }

    public void setPtafSenha(String ptafSenha) {
        this.ptafSenha = ptafSenha;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Long getIdUserImage() {
        return idUserImage;
    }

    public void setIdUserImage(Long idUserImage) {
        this.idUserImage = idUserImage;
    }

    public Imagem getUserImage() {
        return userImage;
    }

    public void setUserImage(Imagem userImage) {
        this.userImage = userImage;
    }

    public Long getIdEndereco() {
        return idEndereco;
    }

    public void setIdEndereco(Long idEndereco) {
        this.idEndereco = idEndereco;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Long getIdInstituicao() {
        return idInstituicao;
    }

    public void setIdInstituicao(Long idInstituicao) {
        this.idInstituicao = idInstituicao;
    }

    public Instituicao getInstituicao() {
        return instituicao;
    }

    public void setInstituicao(Instituicao instituicao) {
        this.instituicao = instituicao;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
