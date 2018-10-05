package br.ufscar.dc.latosensu.aplicacaofinanceira.model;

import br.ufscar.dc.latosensu.aplicacaofinanceira.model.Cliente;
import br.ufscar.dc.latosensu.aplicacaofinanceira.model.Conta;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "correntista")
public class Correntista implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @JsonIgnore
    @EmbeddedId
    protected CorrentistaPK correntistaPK;
    
    @NotNull
    @Column(name = "titularidade")
    private boolean titularidade;
    
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JoinColumn(name = "conta_id", referencedColumnName = "conta_id", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Conta conta;
    
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JoinColumn(name = "cliente_id", referencedColumnName = "cliente_id", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Cliente cliente;

    public Correntista() {}

    public Correntista(CorrentistaPK correntistaPK, boolean titularidade, Conta conta, Cliente cliente) {
        this.correntistaPK = correntistaPK;
        this.titularidade = titularidade;
        this.conta = conta;
        this.cliente = cliente;
    }

    public CorrentistaPK getCorrentistaPK() {
        return correntistaPK;
    }

    public void setCorrentistaPK(CorrentistaPK correntistaPK) {
        this.correntistaPK = correntistaPK;
    }

    public boolean getTitularidade() {
        return titularidade;
    }

    public void setTitularidade(boolean titularidade) {
        this.titularidade = titularidade;
    }

    public Conta getConta() {
        return conta;
    }

    public void setConta(Conta conta) {
        this.conta = conta;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (correntistaPK != null ? correntistaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Correntista)) {
            return false;
        }
        
        Correntista other = (Correntista) object;
        
        if ((this.correntistaPK == null && other.correntistaPK != null) || (this.correntistaPK != null && !this.correntistaPK.equals(other.correntistaPK))) {
            return false;
        }
        
        return true;
    }
}