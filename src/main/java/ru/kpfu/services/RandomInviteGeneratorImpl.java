package ru.kpfu.services;

import org.springframework.stereotype.Service;

import java.util.Random;
@Service
public class RandomInviteGeneratorImpl implements RandomInviteGenerator {
    private static final String CHARACTERS = "abcdefghijklmnopqrstuwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
    private Random random;
    private static final int CODE_LENGTH = 15;

    public RandomInviteGeneratorImpl(){
        random = new Random();
    }

    @Override
    public String generate() {
        StringBuilder code = new StringBuilder();
        while (code.length() < CODE_LENGTH) {
            int index = (int) (random.nextFloat() * CHARACTERS.length());
            code.append(CHARACTERS.charAt(index));
        }
        return code.toString();
    }
}
