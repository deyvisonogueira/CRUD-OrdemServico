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
public class DAOCliente {
    public List<Cliente> getLista(){
        String sql = "select * from cliente";
        List<Cliente> lista = new ArrayList<>();
        try {
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Cliente obj = new Cliente();
                obj.setIdCliente(rs.getInt("idClientes"));
                obj.setNomeCliente(rs.getString("nomeCliente"));
                obj.setTelefone(rs.getString("telefone"));
                obj.setEndereco(rs.getString("endereco"));
                lista.add(obj);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro de SQL no getLista()" + e.getMessage());
        }
        return lista;
    }
    public boolean salvar(Cliente obj) {
        if (obj.getIdCliente() == null) {
            return incluir(obj);
        } else {
            return alterar(obj);
        }

    }

    public boolean incluir(Cliente obj) {
        String sql = "insert into cliente (nomeCliente,telefone,endereco) values(?,?,?)";
        try {
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
            pst.setString(1, obj.getNomeCliente());
            pst.setString(2, obj.getTelefone());
            pst.setString(3, obj.getEndereco());
            if (pst.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Cliente incluido");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Cliente não incluido");
                return false;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro de SQL no incluir do DAOCliente" + e.getMessage());

        }
        return false;
    }

    public boolean alterar(Cliente obj) {
        String sql = "update cliente set nomeCliente=?, telefone=?, endereco=? where idClientes=?";
        try {
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
            pst.setString(1, obj.getNomeCliente());
            pst.setString(2, obj.getTelefone());
            pst.setString(3, obj.getEndereco());
            pst.setInt(4, obj.getIdCliente());
            if (pst.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Cliente alterado");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Cliente não alterado");
                return false;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro de SQL no alterar do DAOCliente" + e.getMessage());

        }
        return false;
    }
    public boolean remover(Cliente obj) {
        String sql = "delete from cliente where idClientes=?";
        try {
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
            pst.setInt(1, obj.getIdCliente());
            if (pst.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Cliente excluído");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Cliente não excluído");
                return false;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro de SQL no excluir do DAOCliente" + e.getMessage());

        }
        return false;
    }
    
    public Cliente localizar(Integer id){
        String sql = "select * from cliente where idClientes=?";
        Cliente obj = new Cliente();
        try{
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                obj.setIdCliente(rs.getInt("idClientes"));
                obj.setNomeCliente(rs.getString("nomeCliente"));
                obj.setTelefone(rs.getString("telefone"));
                obj.setEndereco(rs.getString("endereco"));
                return obj;
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog
        (null,"Erro de SQL Localizar"+e.getMessage());
    }
        return null;
    }
   

}
