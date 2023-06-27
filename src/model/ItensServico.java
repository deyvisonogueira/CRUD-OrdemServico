package model;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author andrelima
 */
    public class ItensServico implements Serializable{
    private Integer idItensServico;
    private Venda objVenda;
    private Servico objServico;
    private Funcionario objFuncionario;
    private Integer quantidadeServico;
    private Double valor;
    
    public ItensServico() {
    }

    public Integer getIdItensServico() {
        return idItensServico;
    }

    public void setIdItensServico(Integer idItensServico) {
        this.idItensServico = idItensServico;
    }

    public Venda getObjVenda() {
        return objVenda;
    }

    public void setObjVenda(Venda objVenda) {
        this.objVenda = objVenda;
    }

    public Servico getObjServico() {
        return objServico;
    }

    public void setObjServico(Servico objServico) {
        this.objServico = objServico;
    }

    public Funcionario getObjFuncionario() {
        return objFuncionario;
    }

    public void setObjFuncionario(Funcionario objFuncionario) {
        this.objFuncionario = objFuncionario;
    }

    public Integer getQuantidadeServico() {
        return quantidadeServico;
    }

    public void setQuantidadeServico(Integer quantidadeServico) {
        this.quantidadeServico = quantidadeServico;
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
        hash = 29 * hash + Objects.hashCode(this.idItensServico);
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
        final ItensServico other = (ItensServico) obj;
        if (!Objects.equals(this.idItensServico, other.idItensServico)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return objFuncionario.getNomeFunc() + " Servico: " + objServico.getDescricao()+ " Quant.: " + String.valueOf(quantidadeServico); 
    }   
    
}
