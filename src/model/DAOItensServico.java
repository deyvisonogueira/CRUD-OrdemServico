package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author andrelima
 */
public class DAOItensServico {
    
    DAOVenda daoVenda = new DAOVenda();
    DAOServico daoServico = new DAOServico();
    DAOFuncionario daoFuncionario = new DAOFuncionario();
    
    public List<ItensServico> getListaItensServico(Integer id){
        String sql = "select * from itensservico where idservico=?";
        List<ItensServico> lista = new ArrayList<>();
        try {
            PreparedStatement pst = Conexao.getPreparedStatement(sql); // instrução sql
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                ItensServico obj = new ItensServico();
                obj.getObjServico().setIdServico(rs.getInt("idservico"));
                obj.setIdItensServico(rs.getInt("iditensservico"));
                obj.setObjFuncionario(daoFuncionario.localizar(rs.getInt("idfuncionario")));
                obj.setObjServico(daoServico.localizar(rs.getInt("idservico")));
                obj.getObjVenda().setIdVenda(rs.getInt("idvenda"));
                obj.setQuantidadeServico(rs.getInt("quantidade"));
                obj.getObjServico().setValor(rs.getDouble("valor"));
                lista.add(obj);
            }
            rs.close();
            pst.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro de SQL no getListaItensServico" + e.getMessage());

        }
        return lista;
    }
    
    public boolean incluir(ItensServico objItensServico) {

        String sql = "insert into itensservico (idfuncionario,idservico,idvenda,quantidade, valor) values (?,?,?,?,?)";
        try {
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
            pst.setInt(1, objItensServico.getObjFuncionario().getIdFuncionario());
            pst.setInt(2, objItensServico.getObjServico().getIdServico());
            pst.setInt(3, objItensServico.getObjVenda().getIdVenda());
            pst.setInt(4, objItensServico.getQuantidadeServico());
            pst.setDouble(5, objItensServico.getObjServico().getValor());

            if (pst.executeUpdate() > 0) {

               // JOptionPane.showMessageDialog(null, "Item de venda cadastrado com sucesso!");

            } else {
                JOptionPane.showMessageDialog(null, "Item de venda não cadastrado!");

            }
            pst.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro de SQL no getListaItensVenda" + e.getMessage());

        }
        return false;
    }
    
    public boolean remover(ItensServico objItensServico) {
        String sql = "delete from itensservico where iditensservico=?";
        
        try {
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
            pst.setInt(1, objItensServico.getIdItensServico());
            if (pst.executeUpdate() > 0) {
                // JOptionPane.showMessageDialog(null, "Item de venda removido com sucesso!");

            } else {
                 JOptionPane.showMessageDialog(null, "Item de serviço não removido!");

            }
            pst.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro de SQL no remover do ItensServico" + e.getMessage());

        }
        return false;
    }

}
