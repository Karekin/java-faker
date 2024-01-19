package com.github.javafaker.service;

import java.util.Random;

/**
 * RandomService类提供了生成随机数和字符串的服务。
 * 包含对整数、长整数、双精度浮点数、布尔值和十六进制字符串的随机生成方法。
 *
 * 该类使用了Random实例来生成随机数，可以通过构造方法指定自定义的Random实例。
 * 默认情况下，使用一个共享的默认Random实例。
 *
 * @author [作者姓名]
 */
public class RandomService {
    private static final Random SHARED_RANDOM = new Random();
    private final Random random;

    /**
     * 使用默认的共享Random实例构造RandomService。
     */
    public RandomService() {
        this(SHARED_RANDOM);
    }

    /**
     * 使用指定的Random实例构造RandomService。
     *
     * @param random 如果传入null，将分配一个默认的Random实例
     */
    public RandomService(Random random) {
        this.random = random != null ? random : SHARED_RANDOM;
    }

    /**
     * 生成一个介于0（包含）和给定整数n（不包含）之间的随机整数。
     *
     * @param n 上限（不包含）的整数
     * @return 随机整数
     */
    public int nextInt(int n) {
        return random.nextInt(n);
    }

    /**
     * 生成一个随机长整数。
     *
     * @return 随机长整数
     */
    public long nextLong() {
        return random.nextLong();
    }

    /**
     * 生成一个介于0（包含）和给定长整数n（不包含）之间的随机长整数。
     *
     * @param n 上限（不包含）的长整数
     * @return 随机长整数
     */
    public long nextLong(long n) {
        if (n <= 0) {
            throw new IllegalArgumentException("bound must be positive");
        }

        long bits, val;
        do {
            long randomLong = random.nextLong();
            bits = (randomLong << 1) >>> 1;
            val = bits % n;
        } while (bits - val + (n - 1) < 0L);
        return val;
    }

    /**
     * 生成一个随机双精度浮点数。
     *
     * @return 随机双精度浮点数
     */
    public double nextDouble() {
        return random.nextDouble();
    }

    /**
     * 生成一个随机布尔值。
     *
     * @return 随机布尔值
     */
    public Boolean nextBoolean() {
        return random.nextBoolean();
    }

    /**
     * 生成一个介于min（包含）和max（包含）之间的随机整数。
     *
     * @param min 下限（包含）的整数
     * @param max 上限（包含）的整数
     * @return 随机整数
     */
    public Integer nextInt(int min, int max) {
        return random.nextInt((max - min) + 1) + min;
    }

    public String hex() {
        return hex(8);
    }


    /**
     * 生成一个指定长度的随机十六进制字符串。
     *
     * @param length 字符串长度
     * @return 随机十六进制字符串
     */
    public String hex(int length) {
        if (length <= 0) {
            return ""; // 保留现有行为，而不是抛出错误。
        }
        final char[] hexChars = new char[length];
        for (int i = 0; i < length; i++) {
            final int nextHex = nextInt(16);
            if (nextHex < 10) {
                hexChars[i] = (char) ('0' + nextHex);
            } else {
                hexChars[i] = (char) ('A' + nextHex - 10);
            }
        }
        return new String(hexChars);
    }
}
