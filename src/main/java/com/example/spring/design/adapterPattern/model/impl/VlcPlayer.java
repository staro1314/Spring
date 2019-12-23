package com.example.spring.design.adapterPattern.model.impl;


import com.example.spring.design.adapterPattern.model.AdvancedMediaPlayer;

/**
 * @author: Staro
 * @date: 2019/11/29 15:53
 * @Description:
 */
public class VlcPlayer implements AdvancedMediaPlayer {
    @Override
    public void playVlc(String fileName) {
        System.out.println("Playing vlc file. Name: "+ fileName);
    }

    @Override
    public void playMp4(String fileName) {

    }
}
