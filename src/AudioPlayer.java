import javax.sound.sampled.*;
import java.io.File;


import dominio.*;

public class AudioPlayer {
    private Clip audioClip;
    public boolean isPlaying = false;
    private int currentTrack = 0; 
    private Album currentAlbum;  
    
    public AudioPlayer(Album album) {
        this.currentAlbum = album;
    }

    public void loadAudio(String filePath) {
        try {
            
            if (audioClip != null && isPlaying) {
                stopAudio();
            }

            File audioFile = new File(filePath);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);

            AudioFormat format = audioStream.getFormat();
            DataLine.Info info = new DataLine.Info(Clip.class, format);
            audioClip = (Clip) AudioSystem.getLine(info);

            audioClip.open(audioStream);
            
        } catch (Exception e) {
            System.out.println("Erro ao carregar o áudio: " + e.getMessage());
        }
    }

    public void playAudio() {
        if (audioClip != null && !isPlaying) {
            audioClip.setFramePosition(0);
            audioClip.start();
            isPlaying = true;
        }
    }

    public void stopAudio() {
        if (audioClip != null && isPlaying) {
            audioClip.stop();
            isPlaying = false;
        }
    }

    public void nextTrack() {
        if (currentTrack < currentAlbum.getMusicas().size() - 1) {
            currentTrack++;
            loadAudio(currentAlbum.getMusicas().get(currentTrack).getArquivoAudio());
        }
    }

    public void prevTrack() {
        if (currentTrack > 0) {
            currentTrack--;
            loadAudio(currentAlbum.getMusicas().get(currentTrack).getArquivoAudio());
        }
    }

    
    public String getCurrentTrackName() {
        return currentAlbum.getMusicas().get(currentTrack).getNome();
    }
}
