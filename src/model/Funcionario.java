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

public class Funcionario implements Serializable {    
    private Integer idFuncionario;
    private String nomeFunc;
    private Calendar dataAdm, dataSaida;
    private Double salario;  
    
    public Funcionario () {
        
    }

    public Integer getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(Integer idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public String getNomeFunc() {
        return nomeFunc;
    }

    public void setNomeFunc(String nomeFunc) {
        this.nomeFunc = nomeFunc;
    }

    public Calendar getDataAdm() {
        return dataAdm;
    }

    public void setDataAdm(Calendar dataAdm) {
        this.dataAdm = dataAdm;
    }

    public Calendar getDataSaida() {
        return dataSaida;
    }

    public void setDataSaida(Calendar dataSaida) {
        this.dataSaida = dataSaida;
    }
      
    public Double getSalario() {
        return salario;
    }

    public void setSalario(Double salario) {
        this.salario = salario;
    }
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + Objects.hashCode(this.idFuncionario);
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
        
        final Funcionario other = (Funcionario) obj;
        if (!Objects.equals(this.idFuncionario, other.idFuncionario)) {
            return false;
        }
        
        return true;
    }
    
    @Transient
    public String getAdmissaoFormatado(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(dataAdm.getTime());
    }
    
    @Transient
    public String getSaidaFormatado(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(dataSaida.getTime());
    }
    
    @Override
    public String toString() {
        return this.nomeFunc; //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
