import java.util.*;
import java.io.*;

public class LetterInventoryClient {
    public static void main(String[] args) {
        LetterInventory inventory1 = new LetterInventory("adcf");
        LetterInventory inventory2 = new LetterInventory("acd");
        LetterInventory subtract = inventory1.subtract(inventory2);
        System.out.println(inventory1.size());
        System.out.println(subtract);
    }
}

