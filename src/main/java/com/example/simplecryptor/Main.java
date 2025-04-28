package com.example.simplecryptor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
	public static void main(String[] args) {
		if (args.length != 3){
			printUsage();
			System.exit(0);
		}

		byte[] input = readByteArray(args[0]);
		Cryptor cryptor = new Cryptor(args[2]);
		byte[] output = cryptor.transformData(input);
		writeByteArray(args[1], output);
	}

	private static void printUsage(){
		System.out.println("Usage: java -jar SimpleCryptor.jar <InputFileName> <OutputFileName> <Key>");
		System.out.println("<InputFileName>: Give the name of the file to be encrypted (or decrypted).");
		System.out.println("<OutputFileName>: Give a name to save the file after decrypting (or encrypting) it.");
		System.out.println("<Key>: Give the key when encrypting (or decrypting).");
	}

	private static byte[] readByteArray(String fileName){
		Path path = Paths.get(fileName);
		byte[] ans = null;

		try {
			ans = Files.readAllBytes(path);
		} catch (IOException e) {
			System.out.println("File could not be read.");
			System.exit(0);
		}

		return ans;
	}

	private static void writeByteArray(String fileName, byte[] data){
		Path path = Paths.get(fileName);

		try {
			Files.write(path, data);
		} catch (IOException e) {
			System.out.println("Could not write file.");
			System.exit(0);
		}
	}
}
