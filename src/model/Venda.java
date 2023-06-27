package model;

import java.beans.Transient;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Objects;

/**
 *
 * @author andrelima
 */
public class Venda implements Serializable {
    private Integer idVenda;
    private Cliente cliente;
    private Funcionario funcionarioVenda;
    private Calendar dataVenda;
    
    public Venda () {
        
    }

    public Integer getIdVenda() {
        return idVenda;
    }

    public void setIdVenda(Integer idVenda) {
        this.idVenda = idVenda;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Funcionario getFuncionarioVenda() {
        return funcionarioVenda;
    }

    public void setFuncionarioVenda(Funcionario funcionarioVenda) {
        this.funcionarioVenda = funcionarioVenda;
    }

    public Calendar getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(Calendar dataVenda) {
        this.dataVenda = dataVenda;
    }
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + Objects.hashCode(this.idVenda);
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
        
        final Venda other = (Venda) obj;
        if (!Objects.equals(this.idVenda, other.idVenda)) {
            return false;
        }
        
        return true;
    }
    
    @Transient
    public String getDataVendaFormatado (){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(dataVenda.getTime());
    }
    
    @Override
    public String toString() {
        return this.idVenda +" | "+ this.cliente; 
    }
    
}
