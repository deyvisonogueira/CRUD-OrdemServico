package model;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author andrelima
 */
public class Servico implements Serializable {
    private Integer idServico;
    private String descricao;
    private Double valor;
    
    public Servico () {
        
    }

    public Integer getIdServico() {
        return idServico;
    }

    public void setIdServico(Integer idServico) {
        this.idServico = idServico;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + Objects.hashCode(this.idServico);
        return hash;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        
        if (obj == null) {
            return false;
        }
        
        if (getClass() != obj.getClass()) {
            return false;
        }
        
        final Servico other = (Servico) obj;
        if (!Objects.equals(this.idServico, other.idServico)) {
            return false;
        }
        
        return true;
    }
    
    @Override
    public String toString() {
        return this.descricao + " | R$" +this.valor; 
    }
}
