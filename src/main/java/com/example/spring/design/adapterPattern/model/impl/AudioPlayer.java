package com.example.spring.design.adapterPattern.model.impl;


import com.example.spring.design.adapterPattern.adapter.MediaAdapter;
import com.example.spring.design.adapterPattern.model.MediaPlayer;

/**
 * @author: Staro
 * @date: 2019/11/29 16:00
 * @Description:
 */
public class AudioPlayer implements MediaPlayer {

    @Override
    public void play(String audioType, String fileName) {
        MediaAdapter mediaAdapter=new MediaAdapter(audioType);
        switch (audioType) {
            case "mp3":
                System.out.println("Playing mp3 file. Name: " + fileName);
                break;
            case "vlc":
                mediaAdapter.play(audioType, fileName);
                break;
            case "mp4":
                mediaAdapter.play(audioType, fileName);
                break;
            default:
                System.out.println("Invalid media. " +
                        audioType + " format not supported");
        }
    }
}
