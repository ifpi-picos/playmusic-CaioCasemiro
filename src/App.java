import dominio.Musica;
import dominio.Album;
import dominio.Artista;

import javax.swing.*;
import java.awt.*;

public class App {
    public static void main(String[] args) throws Exception {

        // Criação de músicas
        Musica musica1 = new Musica("Call Out My Name", 238, "assets\\Call Out My Name.wav", "Sei lá KKKKKK");
        Musica musica2 = new Musica("Die For You", 260, "assets\\Die For You.wav", "Não sei KKKKKK");

        // Criando álbum e adicionando músicas no álbum
        Album album1 = new Album();
        album1.setNome("Músicas The Weeknd");
        album1.addMusica(musica1);
        album1.addMusica(musica2);

        // Criando o Artista e adicionando o álbum a ele
        Artista theweeknd = new Artista();
        theweeknd.setNome("The Weeknd");
        theweeknd.addAlbum(album1);

        // Criando o AudioPlayer
        AudioPlayer player = new AudioPlayer(album1);
        player.loadAudio(musica1.getArquivoAudio());

        // Criação da janela principal (JFrame)
        JFrame frame = new JFrame("Vitrola Vea");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 150);
        frame.setLocation(600, 330);
        frame.setLayout(new BorderLayout());
        

        // Criação do painel para os botões
        JPanel panel = new JPanel();
        JButton prevButton = new JButton("<");
        JButton playStopButton = new JButton("Play");
        JButton nextButton = new JButton(">");
        panel.add(prevButton);
        panel.add(playStopButton);
        panel.add(nextButton);

        
        // Criação do JLabel para exibir o nome da música
        JLabel trackLabel = new JLabel("Tocando agora: " + musica1.getNome(), SwingConstants.CENTER);
        frame.add(new JLabel(new ImageIcon("assets\\musica.png")), BorderLayout.NORTH);
        frame.add(trackLabel, BorderLayout.CENTER);
        frame.add(panel, BorderLayout.SOUTH);
        
        

        // Ação dos botões Play/Stop
        playStopButton.addActionListener(e -> {
            if (player.isPlaying) {
                player.stopAudio();
                playStopButton.setText("Play");
            } else {
                player.playAudio();
                playStopButton.setText("Stop");
            }
        });

        // Ação botão Next
        nextButton.addActionListener(e -> {
            player.nextTrack();  
            trackLabel.setText("Tocando agora: " + player.getCurrentTrackName());
        });

        // Ação botão Prev
        prevButton.addActionListener(e -> {
            player.prevTrack();
            trackLabel.setText("Tocando agora: " + player.getCurrentTrackName());
        });

        // Exibindo a janela
        frame.setVisible(true);
    }
}
