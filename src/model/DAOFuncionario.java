package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author andrelima
 */
public class DAOFuncionario {
    public List<Funcionario> getLista(){
        String sql = "select * from funcionario";
        List<Funcionario> lista = new ArrayList<>();
        try {
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Funcionario obj = new Funcionario();
                obj.setIdFuncionario(rs.getInt("idFuncionario"));
                obj.setNomeFunc(rs.getString("nomeFunc"));                
                java.sql.Date dtA = rs.getDate("dataAdmissao");
                java.sql.Date dtS = rs.getDate("dataSaida");
                obj.setSalario(rs.getDouble("salario"));
                Calendar c1 = Calendar.getInstance();
                Calendar c2 = Calendar.getInstance();
                c1.setTime(dtA);
                c2.setTime(dtS);
                obj.setDataAdm(c1);
                obj.setDataSaida(c2);
                lista.add(obj);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro de SQL no getLista()" + e.getMessage());
        }
        return lista;
    }

    
    public boolean salvar(Funcionario obj) {
        if (obj.getIdFuncionario()== null) {
            return incluir(obj);
        } else {
            return alterar(obj);
        }

    }

    public boolean incluir(Funcionario obj) {
        String sql = "insert into funcionario (nomeFunc,dataAdmissao,dataSaida,salario) values(?,?,?,?)";
        try {
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
            pst.setString(1, obj.getNomeFunc());
            pst.setDate(2, new java.sql.Date(obj.getDataAdm().getTimeInMillis()));
            pst.setDate(3, new java.sql.Date(obj.getDataSaida().getTimeInMillis()));
            pst.setDouble(4, obj.getSalario());
            if (pst.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Funcionário cadastrado");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Funcionário não cadastrado");
                return false;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro de SQL no incluir do DAOFuncionario" + e.getMessage());

        }
        return false;
    }

    public boolean alterar(Funcionario obj) {
        String sql = "update funcionario set nomeFunc=?, dataAdmissao=?, dataSaida=?, salario=? where idFuncionario=?";
        try {
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
            pst.setString(1, obj.getNomeFunc());
            pst.setDate(2, new java.sql.Date(obj.getDataAdm().getTimeInMillis()));
            pst.setDate(3, new java.sql.Date(obj.getDataSaida().getTimeInMillis()));
            pst.setDouble(4, obj.getSalario());
            pst.setInt(5, obj.getIdFuncionario());
            if (pst.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Funcionário alterado");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Funcionário não alterado");
                return false;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro de SQL no alterar do DAOFuncionario" + e.getMessage());

        }
        return false;
    }

    public boolean remover(Funcionario obj) {
        String sql = "delete from funcionario where idFuncionario=?";
        try {
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
            pst.setInt(1, obj.getIdFuncionario());
            if (pst.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Funcionário excluído");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Funcionário não excluído");
                return false;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro de SQL no excluir do DAOFuncionario" + e.getMessage());

        }
        return false;
    }
    
    public Funcionario localizar(Integer id){
        String sql = "select * from funcionario where idFuncionario=?";
        Funcionario obj = new Funcionario();
        try{
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                obj.setIdFuncionario(rs.getInt("idFuncionario"));
                obj.setNomeFunc(rs.getString("nomeFunc"));                
                java.sql.Date dtA = rs.getDate("dataAdmissao");
                java.sql.Date dtS = rs.getDate("dataSaida");
                obj.setSalario(rs.getDouble("salario"));
                Calendar c = Calendar.getInstance();
                c.setTime(dtA);
                c.setTime(dtS);
                obj.setDataAdm(c);
                obj.setDataSaida(c);
                return obj;
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog
        (null,"Erro de SQL Localizar"+e.getMessage());
    }
        return null;
    }
}
