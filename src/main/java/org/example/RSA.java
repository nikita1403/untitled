package org.example;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RSA{

    // Функция для возведения числа в степень по модулю (modular exponentiation)
    public static BigInteger modExp(BigInteger base, BigInteger exp, BigInteger mod) {
        return base.modPow(exp, mod); // Используем встроенную функцию modPow
    }

    // Функция для шифрования сообщения
    public static List<BigInteger> encrypt(String message, BigInteger n) {
        BigInteger e = new BigInteger("65537");
        List<BigInteger> encryptedMessage = new ArrayList<>(); // Список для хранения зашифрованного сообщения
        byte[] bytes = message.getBytes(StandardCharsets.UTF_8); // Преобразуем строку в массив байтов
        for (byte b : bytes) { // Проходим по каждому байту
            BigInteger m = BigInteger.valueOf((b & 0xFF)); // Преобразуем байт в его числовое значение
            BigInteger encryptedChar = modExp(m, e, n); // Шифруем байт с использованием открытого ключа (e, n)
            encryptedMessage.add(encryptedChar); // Добавляем зашифрованный байт в список
        }
        return encryptedMessage; // Возвращаем зашифрованное сообщение
    }

    // Функция для дешифрования сообщения
    public static String decrypt(List<BigInteger> encryptedMessage, BigInteger n) {
        BigInteger d = new BigInteger("2753"); // Пример закрытой экспоненты (в реальной задаче вычисляется через алгоритм Евклида)
        byte[] decryptedBytes = new byte[encryptedMessage.size()]; // Массив для хранения расшифрованных байтов
        for (int i = 0; i < encryptedMessage.size(); i++) { // Проходим по каждому зашифрованному числу
            BigInteger decryptedChar = modExp(encryptedMessage.get(i), d, n); // Дешифруем число с использованием закрытого ключа (d, n)
            decryptedBytes[i] = decryptedChar.byteValue(); // Преобразуем число в байт и добавляем к расшифрованному сообщению
        }
        return new String(decryptedBytes, StandardCharsets.UTF_8); // Преобразуем массив байтов в строку
    }
    public static void main(String[] args) {
        // Пример использования функций шифрования и дешифрования
        String message = "Я не смог разобраться в Vivado!"; // Исходное сообщение
        BigInteger e = new BigInteger("65537"); // Общая открытая экспонента
        BigInteger n = new BigInteger("3233"); // Пример модуля (должен быть большим для реальной безопасности)
        BigInteger d = new BigInteger("2753"); // Пример закрытой экспоненты (в реальной задаче вычисляется через алгоритм Евклида)

        // Шифруем сообщение
        List<BigInteger> encryptedMessage = encrypt(message, n);

        // Выводим зашифрованное сообщение
        System.out.print("Зашифрованное сообщение: ");
        for (BigInteger c : encryptedMessage) {
            System.out.print(c + " "); // Выводим каждое зашифрованное число, разделяя пробелами
        }
        System.out.println();

        // Дешифруем сообщение
        String decryptedMessage = decrypt(encryptedMessage, n);

        // Выводим расшифрованное сообщение
        System.out.println("Расшифрованное сообщение: " + decryptedMessage);
    }
}