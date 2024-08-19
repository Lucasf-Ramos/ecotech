package ecotech.tcc.demo.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;



@Entity
@Table(name = "EcoPonto")
public class Local {
	
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    
    private String email;
    private String senha;
    private String cnpj;
    private byte[] foto;
    private String descricao;
    private String website;
    private String logradouro;
    private String cep;
    private String numResid;
    private String bairro;
    private String cidade;
    private String uf;
    private String complemento; 
    private String pontoRef; 
    private String telefone;
    private String horarioFunc;
    private boolean statusPonto;   
    @ManyToOne
    @JoinColumn(name = "gruporesiduo_id")
    private GrupoResiduos grupoResiduos;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public String getLogradouro() {
		return logradouro;
	}
	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public String getNumResid() {
		return numResid;
	}
	public void setNumResid(String numResid) {
		this.numResid = numResid;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getUf() {
		return uf;
	}
	public void setUf(String uf) {
		this.uf = uf;
	}
	public String getComplemento() {
		return complemento;
	}
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	public String getPontoRef() {
		return pontoRef;
	}
	public void setPontoRef(String pontoRef) {
		this.pontoRef = pontoRef;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getHorarioFunc() {
		return horarioFunc;
	}
	public void setHorarioFunc(String horarioFunc) {
		this.horarioFunc = horarioFunc;
	}
	public boolean isStatusPonto() {
		return statusPonto;
	}
	public void setStatusPonto(boolean statusPonto) {
		this.statusPonto = statusPonto;
	}
	public GrupoResiduos getGrupoResiduos() {
		return grupoResiduos;
	}
	public void setGrupoResiduos(GrupoResiduos grupoResiduos) {
		this.grupoResiduos = grupoResiduos;
	}
	public byte[] getFoto() {
		return foto;
	}
	public void setFoto(byte[] foto) {
		this.foto = foto;
	}


	

	
	
}
