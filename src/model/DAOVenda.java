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
public class DAOVenda {
    ConverteDataWEB converte = new ConverteDataWEB();
    private int lastId;

    public int getLastId() {
        return lastId;
    }
    
    DAOCliente daoCliente = new DAOCliente();
    DAOFuncionario daoFuncionario = new DAOFuncionario();
    
    
    public List<Venda> getLista(){
        String sql = "select * from venda";
        List<Venda> lista = new ArrayList<>();
        try {
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {                
                Venda obj = new Venda();
                obj.setIdVenda(rs.getInt("idVenda"));
                java.sql.Date dt = rs.getDate("dataVenda");
                obj.setCliente(daoCliente.localizar(rs.getInt("idClientes")));
                obj.setFuncionarioVenda(daoFuncionario.localizar(rs.getInt("idFuncionario")));
                Calendar c = Calendar.getInstance();
                c.setTime(dt);
                obj.setDataVenda(c);
                
                lista.add(obj);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro de SQL no getLista()" + e.getMessage());
        }
        return lista;
    }
    
    public boolean salvar(Venda obj) {
        if (obj.getIdVenda() == null) {
            return incluir(obj);
        } else {
            return alterar(obj);
        }

    }

    public boolean incluir(Venda obj) {
        String sql = "insert into venda (idClientes,idFuncionario,dataVenda) values(?,?,?)";
        try {
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
            pst.setInt(1, obj.getCliente().getIdCliente());
            pst.setInt(2, obj.getFuncionarioVenda().getIdFuncionario());
            pst.setDate(3, new java.sql.Date(obj.getDataVenda().getTimeInMillis()));
            
            if (pst.executeUpdate() > 0) {
                //JOptionPane.showMessageDialog(null, "Venda cadastrada");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Venda não cadastrada");
                return false;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro de SQL no incluir do DAOVenda" + e.getMessage());

        }
        return false;
    }

    public boolean alterar(Venda obj) {
        String sql = "update venda set idClientes=?, idFuncionario=?, dataVenda=? where idVenda=?";
        try {
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
            pst.setInt(1, obj.getCliente().getIdCliente());
            pst.setInt(2, obj.getFuncionarioVenda().getIdFuncionario());
            pst.setDate(3, new java.sql.Date(obj.getDataVenda().getTimeInMillis()));;
            pst.setInt(4, obj.getIdVenda());
            
            if (pst.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Venda alterada");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Venda não alterada");
                return false;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro de SQL no alterar do DAOVenda" + e.getMessage());

        }
        return false;
    }

    public boolean remover(Venda obj) {
        String sql = "delete from venda where idVenda=?";
        try {
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
            pst.setInt(1, obj.getIdVenda());
            if (pst.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Venda excluída");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Venda não excluída");
                return false;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro de SQL no excluir do DAOFuncionario" + e.getMessage());

        }
        return false;
    }
    
    public Venda localizar(Integer id){
        String sql = "select * from venda where idVenda=?";
        Venda obj = new Venda();
        try{
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                obj.setIdVenda(rs.getInt("idVenda"));
                java.sql.Date dt = rs.getDate("dataVenda");
                obj.setCliente(daoCliente.localizar(rs.getInt("idCliente")));
                obj.setFuncionarioVenda(daoFuncionario.localizar(rs.getInt("idFuncionario")));
                Calendar c = Calendar.getInstance();
                c.setTime(dt);
                obj.setDataVenda(c);
                return obj;
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog
        (null,"Erro de SQL Localizar"+e.getMessage());
    }
        return null;
    }

}