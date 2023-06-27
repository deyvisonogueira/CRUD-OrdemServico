package model;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author andrelima
 */
public class Produto implements Serializable {
    private Integer idProduto;
    private String nomeProduto;
    private Double valorProduto;
    
    public Produto () {
        
    } 

    public Integer getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(Integer idProduto) {
        this.idProduto = idProduto;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public Double getValorProduto() {
        return valorProduto;
    }

    public void setValorProduto(Double valorProduto) {
        this.valorProduto = valorProduto;
    }   
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + Objects.hashCode(this.idProduto);
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
        
        final Produto other = (Produto) obj;
        if (!Objects.equals(this.idProduto, other.idProduto)) {
            return false;
        }
        
        return true;
    }
    
    @Override
    public String toString() {
        return this.nomeProduto + " | R$" +this.valorProduto; 
    }
    
}
