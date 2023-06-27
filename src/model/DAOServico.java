package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author andrelima
 */
public class DAOServico {
    ConverteDataWEB converte = new ConverteDataWEB();
    private int lastId;

    public int getLastId() {
        return lastId;
    }
    
    public List<Servico> getLista(){
     String sql = "select * from servico";
        List<Servico> lista = new ArrayList<>();
        try {
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Servico obj = new Servico();
                obj.setIdServico(rs.getInt("idservico"));
                obj.setDescricao(rs.getString("descricao"));
                obj.setValor(rs.getDouble("valor"));
                lista.add(obj);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro de SQL no getLista()" + e.getMessage());
        }
        return lista;
    }
    
    public boolean salvar(Servico obj) {
        if (obj.getIdServico() == null) {
            return incluir(obj);
        } else {
            return alterar(obj);
        }

    }

    public boolean incluir(Servico obj) {
        String sql = "insert into servico (descricao,valor) values(?,?)";
        try {
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
            pst.setString(1, obj.getDescricao());
            pst.setDouble(2, obj.getValor());
            if (pst.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Serviço incluido");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Serviço não incluido");
                return false;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro de SQL no incluir do DAOServico" + e.getMessage());

        }
        return false;
    }

    public boolean alterar(Servico obj) {
        String sql = "update servico set descricao=?, valor=? where idservico=?";
        try {
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
            pst.setString(1, obj.getDescricao());
            pst.setDouble(2, obj.getValor());
            pst.setInt(3, obj.getIdServico());
            if (pst.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Serviço alterado");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Serviço não alterado");
                return false;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro de SQL no alterar do DAOServico" + e.getMessage());

        }
        return false;
    }
    public boolean remover(Servico obj) {
        String sql = "delete from servico where idservico=?";
        try {
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
            pst.setInt(1, obj.getIdServico());
            if (pst.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Serviço excluído");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Serviço não excluído");
                return false;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro de SQL no excluir do DAOServico" + e.getMessage());

        }
        return false;
    }
    
    public Servico localizar(Integer id){
        String sql = "select * from servico where idservico=?";
        Servico obj = new Servico();
        try{
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                obj.setIdServico(rs.getInt("idservico"));
                obj.setDescricao(rs.getString("descricao"));
                obj.setValor(rs.getDouble("valor"));
                return obj;
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog
        (null,"Erro de SQL Localizar"+e.getMessage());
    }
        return null;
    }
   

}

