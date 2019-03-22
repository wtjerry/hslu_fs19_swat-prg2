package connectfour.model;

import java.util.ArrayList;
import java.util.Random;

class PrimeNumberProvider {

    private static final ArrayList<Integer> alreadyUsedPrimes = new ArrayList<>();

    public static int nextPrimeNumber() {
        int prime;
        do {
            prime = generatePrimeNumber();
        } while (alreadyUsedPrimes.contains(prime));

        alreadyUsedPrimes.add(prime);
        return prime;
    }

    private static int generatePrimeNumber() {
        Random rand = new Random();
        int potentialPrimeNumber = rand.nextInt(1000) + 1;
        while (!isPrime(potentialPrimeNumber)) {
            potentialPrimeNumber = rand.nextInt(1000) + 1;
        }

        return potentialPrimeNumber;
    }

    private static boolean isPrime(int numberToCheck) {
        if (numberToCheck <= 3 || numberToCheck % 2 == 0) {
            return numberToCheck == 2 || numberToCheck == 3; //this returns false if number is <=1 & true if number = 2 or 3
        }
        int divisor = 3;
        while ((divisor <= Math.sqrt(numberToCheck)) && (numberToCheck % divisor != 0)) {
            divisor += 2; //iterates through all possible divisors
        }
        return numberToCheck % divisor != 0;
    }
}
