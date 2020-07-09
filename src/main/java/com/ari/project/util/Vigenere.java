package com.ari.project.util;

public class Vigenere {
    
    public static String cypherVigenere(String entry, String keyword){
        String key = generateKey(entry, keyword);
        StringBuilder result = new StringBuilder(0);
        for (int i = 0; i < entry.length(); i++){
            int x = (entry.charAt(i)-48 + (int)key.charAt(i)-48);
            x+='0';
            if(x>'9') x-=10;
            result.append((char)(x));
        }
        return result.toString();
    }
    public static String decypherVigenere(String entry, String keyword){
        String key = generateKey(entry, keyword);
        StringBuilder result = new StringBuilder(0);
        for (int i = 0; i < entry.length(); i++){
            int x = (Math.abs((entry.charAt(i)+48 - key.charAt(i)+48)));
            x-='0';
            if(x>'9') x-=10;
            if(x<'0') x+=10;
            result.append((char)(x));
        }
        return result.toString();
    }
    
    
    private static String generateKey(String entry, String keyword)
    {
        StringBuilder key = new StringBuilder(keyword);
        int x = entry.length();
        for (int i = 0; ; i++)
        {
            if (x == i)
                i = 0;
            if (key.length() == entry.length())
                break;
            key.append(key.charAt(i));
        }
        return key.toString();
    }
    
}