package com.example.spring.design.adapterPattern;


import com.example.spring.design.adapterPattern.model.impl.AudioPlayer;

/**
 * @author: Staro
 * @date: 2019/11/29 16:06
 * @Description: 适配器模式
 */
public class AdapterDomain {
    public static void main(String[] args) {
        AudioPlayer audioPlayer=new AudioPlayer();
        audioPlayer.play("mp3", "beyond the horizon.mp3");
        audioPlayer.play("mp4", "alone.mp4");
        audioPlayer.play("vlc", "far far away.vlc");
        audioPlayer.play("avi", "mind me.avi");
    }
}
