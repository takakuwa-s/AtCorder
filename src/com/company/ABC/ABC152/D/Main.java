package com.company.ABC.ABC152.D;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.NoSuchElementException;

public class Main {
    public static void main(String[] args) {
        PrintWriter out = new PrintWriter(System.out);
        FastScanner sc = new FastScanner();
        Strategy strategy = new MyStrategy();
        strategy.solve(out, sc);
        out.flush();
    }
}

class MyStrategy implements Strategy {
    private int N;
    private String nStr;
    private int digit;

    @Override
    public void solve(PrintWriter out, FastScanner sc) {
        N = sc.nextInt();
    }

    private int getCount(int i, int j, int inputDigit) {
        int cnt = 0;
        for (int k = 2; k<=digit; k++) {
            if (k < inputDigit) {
                continue;
            } else if (k > (inputDigit+1)) {
                cnt += Math.pow(10, k-2);
            }
        }


        if (digit >= 6 && i == 1) {
            cnt += (N - 100_000) % 10;
        }

        if (digit >= 5) {
            if (digit >= 6) {
                cnt += 1000;
            } else {
                cnt += (N - 10_000) % 10;
            }
        }

        if (digit >= 4) {
            if (digit >= 5) {
                cnt += 100;
            } else {
                cnt += (N - 1_000) % 10;
            }
        }

        if (digit >= 3) {
            if (digit >= 4) {
                cnt += 10;
            } else {
                cnt += (N - 100) % 10;
            }
        }

        if (digit >= 2 & ((10*i + i) <= N)) {
            cnt++;
        }

        if (digit >= 1 & (i <= N)) {
            cnt++;
        }
        return cnt;
    }
}

interface Strategy {
    void solve(PrintWriter out, FastScanner sc);
}

class FastScanner {
    private final InputStream in = System.in;
    private final byte[] buffer = new byte[1024];
    private int ptr = 0;
    private int buflen = 0;
    private boolean hasNextByte() {
        if (ptr < buflen) {
            return true;
        }else{
            ptr = 0;
            try {
                buflen = in.read(buffer);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (buflen <= 0) {
                return false;
            }
        }
        return true;
    }
    private int readByte() { if (hasNextByte()) return buffer[ptr++]; else return -1;}
    private static boolean isPrintableChar(int c) { return 33 <= c && c <= 126;}
    public boolean hasNext() { while(hasNextByte() && !isPrintableChar(buffer[ptr])) ptr++; return hasNextByte();}
    public String next() {
        if (!hasNext()) throw new NoSuchElementException();
        StringBuilder sb = new StringBuilder();
        int b = readByte();
        while(isPrintableChar(b)) {
            sb.appendCodePoint(b);
            b = readByte();
        }
        return sb.toString();
    }
    public long nextLong() {
        if (!hasNext()) throw new NoSuchElementException();
        long n = 0;
        boolean minus = false;
        int b = readByte();
        if (b == '-') {
            minus = true;
            b = readByte();
        }
        if (b < '0' || '9' < b) {
            throw new NumberFormatException();
        }
        while(true){
            if ('0' <= b && b <= '9') {
                n *= 10;
                n += b - '0';
            }else if(b == -1 || !isPrintableChar(b)){
                return minus ? -n : n;
            }else{
                throw new NumberFormatException();
            }
            b = readByte();
        }
    }
    public int nextInt() {
        long nl = nextLong();
        if (nl < Integer.MIN_VALUE || nl > Integer.MAX_VALUE) throw new NumberFormatException();
        return (int) nl;
    }
    public double nextDouble() { return Double.parseDouble(next());}
}