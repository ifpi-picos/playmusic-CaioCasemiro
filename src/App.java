import java.util.ArrayList;
import dominio.Musica;
import dominio.Album;
import dominio.Artista;

import javax.swing.*;
import java.awt.*;

public class App {
    public static void main(String[] args) throws Exception {

        ArrayList<Artista> artistas = new ArrayList<>();

        // Criação de músicas
        Musica musica1 = new Musica("Call Out My Name", 238, "assets\\Call Out My Name.wav", "Sei lá KKKKKK");
        Musica musica2 = new Musica("Die For You", 260, "assets\\Die For You.wav", "Não sei KKKKKK");
        Musica musica3 = new Musica("Lost", 234, "assets\\Lost.wav", "Não sei KKKKKK");
        Musica musica4 = new Musica("Pink Matter", 268, "assets\\Pink Matter.wav", "Não sei KKKKKK");

        // Criando álbum e adicionando músicas no álbum
        Album album1 = new Album();
        album1.setNome("Músicas The Weeknd");
        album1.addMusica(musica1);
        album1.addMusica(musica2);

        Album album2 = new Album();
        album2.setNome("Músicas Frank Ocean");
        album2.addMusica(musica3);
        album2.addMusica(musica4);

        // Criando o Artista e adicionando o álbum a ele
        Artista theweeknd = new Artista();
        theweeknd.setNome("The Weeknd");
        theweeknd.addAlbum(album1);

        Artista FrankOcean = new Artista();
        FrankOcean.setNome("Frank Ocean");
        FrankOcean.addAlbum(album2);


        artistas.add(theweeknd);
        artistas.add(FrankOcean);


        class State{
        int currentArtistIndex = 0;
        int currentAlbumIndex = 0;
        Artista currentArtista = artistas.get(currentArtistIndex);
        Album currentAlbum = currentArtista.getAlbuns().get(currentAlbumIndex);
        }
        

        State state = new State();


        // Criando o AudioPlayer
        AudioPlayer player = new AudioPlayer(album1);
        player.loadAudio(state.currentAlbum.getMusicas().get(0).getArquivoAudio());

        // Criação da janela principal (JFrame)
        JFrame frame = new JFrame("Vitrola Vea");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 250);
        frame.setLocation(600, 330);
        frame.setLayout(new BorderLayout());
        

        JPanel painelPrincipal = new JPanel();
        painelPrincipal.setLayout(new BoxLayout(painelPrincipal, BoxLayout.Y_AXIS));

        // Criação do painel para os botões
        JPanel painelDeControle = new JPanel();
        JButton prevButton = new JButton("<");
        JButton playStopButton = new JButton("Play");
        JButton nextButton = new JButton(">");
        painelDeControle.add(prevButton);
        painelDeControle.add(playStopButton);
        painelDeControle.add(nextButton);
        
        JPanel painelSwitch = new JPanel();
        JButton switchArtistButton = new JButton("Trocar artista");
        JButton switchAlbumButton = new JButton("Trocar album");
        painelSwitch.add(switchAlbumButton);
        painelSwitch.add(switchArtistButton);

        painelPrincipal.add(painelDeControle);
        painelPrincipal.add(painelSwitch);

        // Criação do JLabel para exibir o nome da música
        JLabel trackLabel = new JLabel("<html> | Artista: "+state.currentArtista.getNome()+"<br> | Álbum: "+state.currentAlbum.getNome()+"<br> | Música: "+player.getCurrentTrackName() + "</html>", SwingConstants.CENTER);
        frame.add(new JLabel(new ImageIcon("assets\\musica.png")), BorderLayout.NORTH);
        frame.add(trackLabel, BorderLayout.CENTER);
        frame.add(painelPrincipal, BorderLayout.SOUTH);
        
        

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
            trackLabel.setText("<html> | Artista: " + state.currentArtista.getNome() + "<br> | Álbum: " + state.currentAlbum.getNome() + "<br> | Música: " + player.getCurrentTrackName()+ "</html>");
            playStopButton.setText("Play");
        });

        // Ação botão Prev
        prevButton.addActionListener(e -> {
            player.prevTrack();
            trackLabel.setText("<html> | Artista: " + state.currentArtista.getNome() + "<br> | Álbum: " + state.currentAlbum.getNome() + "<br> | Música: " + player.getCurrentTrackName()+"</html>");
            playStopButton.setText("Play");
        });



        // Ação para trocar de artista
        switchArtistButton.addActionListener(e ->{
            player.stopAudio();
            playStopButton.setText("Play");

            state.currentArtistIndex = (state.currentArtistIndex + 1) % artistas.size();
            state.currentArtista = artistas.get(state.currentArtistIndex);

            state.currentAlbumIndex = 0;
            state.currentAlbum = state.currentArtista.getAlbuns().get(state.currentAlbumIndex);

            player.setAlbum(state.currentAlbum);
            player.loadAudio(state.currentAlbum.getMusicas().get(0).getArquivoAudio());

            trackLabel.setText("<html> | Artista: " + state.currentArtista.getNome() + "<br> | Álbum: " + state.currentAlbum.getNome() + "<br> | Música: " + player.getCurrentTrackName() + "</html>");

        });


        switchAlbumButton.addActionListener(e -> {
            player.stopAudio();
            playStopButton.setText("Play");

            state.currentAlbumIndex = (state.currentAlbumIndex+1) % state.currentArtista.getAlbuns().size();
            state.currentAlbum = state.currentArtista.getAlbuns().get(state.currentAlbumIndex);
            player.loadAudio(state.currentAlbum.getMusicas().get(0).getArquivoAudio());

            trackLabel.setText("<html> | Artista: " + state.currentArtista.getNome() + "<br> | Álbum: " + state.currentAlbum.getNome() + "<br> | Música: " + player.getCurrentTrackName() + "</html>");
        });

        // Exibindo a janela
        frame.setVisible(true);
    }
}
