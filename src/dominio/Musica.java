package dominio;

public class Musica {
    private String nome;
    private int duracao;
    private String arquivoAudio;
    private String genero;

    public Musica(String nome, int duracao, String arquivoAudio, String genero){
        this.nome = nome;
        this.duracao = duracao;
        this.arquivoAudio = arquivoAudio;
        this.genero = genero;
    }

    public String getNome(){
        return nome;
    }

    public int getDuracao(){
        return duracao;
    }

    public String getGenero(){
        return genero;
    }
    public String getArquivoAudio(){
        return arquivoAudio;
    }

}
