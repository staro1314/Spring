package com.example.spring.design.adapterPattern.model.impl;


import com.example.spring.design.adapterPattern.model.AdvancedMediaPlayer;

/**
 * @author: Staro
 * @date: 2019/11/29 15:54
 * @Description:
 */
public class Mp4Player implements AdvancedMediaPlayer {
    @Override
    public void playVlc(String fileName) {

    }

    @Override
    public void playMp4(String fileName) {
        System.out.println("Playing mp4 file. Name: "+ fileName);
    }
}
