import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String[] products = {"Хлеб", "Молоко", "Сыр"};
        int[] prices = {50, 100, 250};
        int[] amount = new int[products.length];

        String[] saleProducts = {"Вино", "Кофе", "Шоколад"};
        int[] salePrices = {500, 300, 150};
        int[] saleAmount = new int[salePrices.length];

        System.out.println("Список возможных товаров для покупки: ");
        for (int i = 0; i < products.length; i++) {
            System.out.println((i + 1) + ". " + products[i] + " " + prices[i] + " руб/шт");
        }

        int productNumber;
        int productCount;
        int sum = 0;

        System.out.println("\n" + "Список возможных товаров по акции 3=2: ");
        for (int j = 0; j < saleProducts.length; j++) {
            System.out.println((j + 4) + ". " + saleProducts[j] + " " + salePrices[j] + " руб/шт");
        }

        int saleNumber;
        int saleCount;
        int saleTotal = 0;

        while (true) {
            System.out.println("\n" + "Введите номер продукта и его количество через пробел. Для оформления корзины введите \"end\".");
            String input = scanner.nextLine();
            if (input.equals("") || input.equals(" ")) {
                System.out.println("Внимание! Вводить необходимо только числа. Вы же ввели пробел или оставили пустую строку.");
                continue;
            }

            if (input.equals("end")) {
                break;
            }

            String[] parts = input.split(" ");
            if (parts.length != 2) {
                System.out.println("Внимание! Вводить необходимо только два числовых значения! Вы же ввели: " + input);
                continue;
            }

            try {
                productNumber = Integer.parseInt(parts[0]) - 1;
                productCount = Integer.parseInt(parts[1]);
                try {
                    if (amount[productNumber] + productCount < 0) {
                        System.out.println("Внимание! Количество товара не может быть отрицательным!");
                        continue;
                    }

                    if (productCount == 0) {
                        amount[productNumber] = 0;
                        sum -= prices[productNumber];

                    }
                } catch (ArrayIndexOutOfBoundsException e) {

                }

            } catch (NumberFormatException e) {
                System.out.println("Внимание! Вводить необходимо только числа. Вы же ввели: " + input);
                continue;
            }

            if ((productNumber + 1) > (products.length + saleProducts.length)) {
                System.out.println("Внимание! \"НОМЕР ПРОДУКТА\" в списке товаров не найден.");
                continue;
            }

            if ((productNumber + 1) <= 0) {
                System.out.println("Внимание! \"НОМЕР ПРОДУКТА\" может быть только положительным числом. Вы же ввели: " + (productNumber + 1));
                continue;
            }

            if (productNumber < products.length) {
                amount[productNumber] += productCount;
                int sumProducts = productCount * prices[productNumber];
                sum += sumProducts;
            } else {
                saleNumber = productNumber - products.length;
                saleCount = productCount;
                if (saleAmount[saleNumber] + saleCount < 0) {
                    System.out.println("Внимание! Количество товара не может быть отрицательным!");
                    continue;
                }

                if (saleCount == 0) {
                    saleAmount[saleNumber] = 0;
                }
                saleAmount[saleNumber] += saleCount;
            }
        }

        System.out.println("Ваша корзина: ");
        for (int i = 0; i < amount.length; i++) {
            if (amount[i] != 0) {
                System.out.println(products[i] + " " + amount[i] + " шт " + prices[i] + " руб/шт "
                        + (amount[i] * prices[i]) + " руб в сумме.");
            }
        }

        System.out.println("Товары по акции: ");
        for (int j = 0; j < saleAmount.length; j++) {
            if (saleAmount[j] != 0) {
                int saleSum = (saleAmount[j] - (saleAmount[j] / 3)) * salePrices[j];
                System.out.println(saleProducts[j] + " " + saleAmount[j] + " шт " +
                        salePrices[j] + " руб/шт " + saleSum + " руб в сумме.");
                saleTotal += saleSum;
            }
        }
        System.out.println("Итого: " + (sum + saleTotal));
    }
}
