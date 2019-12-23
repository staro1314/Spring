package com.example.spring.design.adapterPattern.adapter;


import com.example.spring.design.adapterPattern.model.AdvancedMediaPlayer;
import com.example.spring.design.adapterPattern.model.MediaPlayer;
import com.example.spring.design.adapterPattern.model.impl.Mp4Player;
import com.example.spring.design.adapterPattern.model.impl.VlcPlayer;

/**
 * @author: Staro
 * @date: 2019/11/29 15:55
 * @Description:
 */
public class MediaAdapter implements MediaPlayer {
    private AdvancedMediaPlayer advancedMediaPlayer;

    public MediaAdapter(String audioType) {
        switch (audioType){
            case "vlc":
                advancedMediaPlayer=new VlcPlayer();
                break;
            case "mp4":
                advancedMediaPlayer=new Mp4Player();
        }
    }

    @Override
    public void play(String audioType, String fileName) {
        switch (audioType){
            case "vlc":
                advancedMediaPlayer.playVlc(fileName);
                break;
            case "mp4":
                advancedMediaPlayer.playMp4(fileName);
        }
    }
}
