import dominio.Musica;
import dominio.Album;
import dominio.Artista;

import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class App {
    public static void main(String[] args) throws Exception {


        //Criação de músicas
        Musica musica1 = new Musica("Diga sim para mim", 221, "assets\\Diga Sim pra Mim.wav", "forró");
        Musica musica2 = new Musica("Sorte", 200, "assets\\Sorte.wav", "forró");


        //Criando album e adicionando músicas no album
        Album album1 = new Album();
        album1.setNome("Músicas Desejo de menina");
        album1.addMusica(musica1);
        album1.addMusica(musica2);


        //Criando o Artista e adicionando o album a para ele
        Artista desejoDeMenina = new Artista();
        desejoDeMenina.setNome("Desejo de Menina");
        desejoDeMenina.addAlbum(album1);


        //Criando audio player
        AudioPlayer player = new AudioPlayer(album1);
        player.loadAudio(musica1.getArquivoAudio());

        
        //Criação de botões
        JButton prevButton = new JButton("<");
        JButton playStopButton = new JButton("Play");
        JButton nextButton = new JButton(">");


        //Ação de botôes play/stop
        playStopButton.addActionListener(e -> {
            if (player.isPlaying){
                player.stopAudio();
                playStopButton.setText("Play");
            } else {
                player.playAudio();
                playStopButton.setText("Stop");
            }
        });

        //Ação botão next
        nextButton.addActionListener(e -> player.nextTrack());


        //Ação botan Prev
        prevButton.addActionListener(e -> player.prevTrack());


        //Criação de interface gráfica
        ImageIcon icon = new ImageIcon("./assets./musica.png");
        JOptionPane.showOptionDialog(
            null,
            "Tocador de musga",
            "Vitrola vea",
            JOptionPane.DEFAULT_OPTION, 
            JOptionPane.PLAIN_MESSAGE,
            icon,
            new Object[]{prevButton, playStopButton, nextButton}, 
            nextButton);
    }
}
