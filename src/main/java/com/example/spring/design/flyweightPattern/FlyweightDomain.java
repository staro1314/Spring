package com.example.spring.design.flyweightPattern;

import java.util.stream.IntStream;

/**
 * @author: Staro
 * @date: 2019/12/5 17:51
 * @Description:
 */
public class FlyweightDomain {
    private static final String colors[] =
            {"Red", "Green", "Blue", "White", "Black"};

    public static void main(String[] args) {
        IntStream.range(0, 20).forEach(i -> {
            Circle circle= (Circle) ShapeFactory.getCircle(getRandomColor());
            circle.setX(getRandomX());
            circle.setY(getRandomY());
            circle.setRadius(100);
            circle.draw();
        });
    }

    private static String getRandomColor() {
        return colors[(int) (Math.random() * colors.length)];
    }

    private static int getRandomX() {
        return (int) (Math.random() * 100);
    }

    private static int getRandomY() {
        return (int) (Math.random() * 100);
    }
}
