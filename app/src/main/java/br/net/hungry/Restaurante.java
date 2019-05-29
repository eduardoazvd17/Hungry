package br.net.hungry;

import java.io.Serializable;

public class Restaurante implements Serializable {

    private String	id;
    private String	nome;
    private String	precoMedio;
    private boolean	fechado;
    private double	classificacao;
    private double	distancia;
    private String	local;
    private String	telefone;
    private String	imagem;
    private int	    avaliacoes;

    public Restaurante(String id, String nome, String precoMedio, boolean fechado, double classificacao, double distancia, String local, String telefone, String imagem, int avaliacoes) {
        super();
        this.id = id;
        this.nome = nome;
        this.precoMedio = precoMedio;
        this.fechado = fechado;
        this.classificacao = classificacao;
        this.distancia = distancia;
        this.local = local;
        this.telefone = telefone;
        this.imagem = imagem;
        this.avaliacoes = avaliacoes;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPrecoMedio() {
        if (precoMedio == null || precoMedio.isEmpty()) {
            return "N/A";
        } else {
            return precoMedio;
        }
    }

    public void setPrecoMedio(String precoMedio) {
        this.precoMedio = precoMedio;
    }

    public double getDistancia() {
        // Convertendo a distancia recebida em metros para KM.
        return distancia / 1000;
    }

    public void setDistancia(double distancia) {
        this.distancia = distancia;
    }

    public double getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(int classificacao) {
        this.classificacao = classificacao;
    }

    public String getLocal() {
        if (local == null || local.isEmpty()) {
            return "Não informado";
        } else {
            return local;
        }
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getTelefone() {
        if (telefone == null || telefone.isEmpty()) {
            return "Não informado.";
        } else {
            return telefone;
        }
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public String getFechado() {
        if (fechado == false) {
            return "Aberto";
        } else {
            return "Fechado";
        }
    }

    public void setFechado(boolean fechado) {
        this.fechado = fechado;
    }

    public int getAvaliacoes() {
        return avaliacoes;
    }

    public void setAvaliacoes(int avaliacoes) {
        this.avaliacoes = avaliacoes;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Restaurante other = (Restaurante) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

}
