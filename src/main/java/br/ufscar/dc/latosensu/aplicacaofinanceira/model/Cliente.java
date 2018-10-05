package br.ufscar.dc.latosensu.aplicacaofinanceira.model;

import br.ufscar.dc.latosensu.aplicacaofinanceira.validation.ClienteStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "cliente")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo", length = 1)
public class Cliente implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name = "cliente_id", nullable = false)
    @SequenceGenerator(name="Cliente_Generator", sequenceName="cliente_sequence", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="Cliente_Generator")
    private Long id;
    
    @NotNull(message = "{clienteNomeNaoPodeSerNulo}")
    @Size(min = 2, max = 255, message = "{clienteNomeDeveTerEntreDoisEDuzentosECinquentaECincoCaracteres}")
    @Column(name = "nome", nullable = false, length = 255)
    private String nome;
    
    @NotNull(message = "{clienteStatusNaoPodeSerNulo}")
    @ClienteStatus(message = "{clienteStatusInvalido}")
    @Column(name = "status", nullable = false, length = 7)
    private String status;
    
    @OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY)
    private Collection<Endereco> enderecos;
    
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cliente", fetch = FetchType.LAZY)
    private Collection<Correntista> correntistas;

    public Cliente() {}   

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Collection<Endereco> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(Collection<Endereco> enderecos) {
        this.enderecos = enderecos;
    }
    
    public Collection<Correntista> getCorrentistas() {
        return correntistas;
    }

    public void setCorrentistas(Collection<Correntista> correntistas) {
        this.correntistas = correntistas;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Cliente)) {
            return false;
        }
        
        Cliente other = (Cliente) object;
        
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        
        return true;
    }
}
