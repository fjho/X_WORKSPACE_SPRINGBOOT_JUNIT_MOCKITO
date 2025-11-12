package com.ust.formacion.unit_testing.frameworks.jsonpath;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;

public class JsonPathTest {

    //Documentation: https://github.com/json-path/JsonPath
    @Test
    void ejemplosBasicosJsonPath() {
        String responseJson = "[\n" +
                "  {\n" +
                "    \"id\": \"1\",\n" +
                "    \"name\": \"pencil\",\n" +
                "    \"quantity\": 20,\n" +
                "    \"price\": 5,\n" +
                "    \"category\": \"office\",\n" +
                "    \"available\": true,\n" +
                "    \"tags\": [\"school\", \"office\", \"cheap\"]\n" +
                "  },\n" +
                "  {\n" +
                "    \"id\": \"2\",\n" +
                "    \"name\": \"pen\",\n" +
                "    \"quantity\": 10,\n" +
                "    \"price\": 15,\n" +
                "    \"category\": \"office\",\n" +
                "    \"available\": false,\n" +
                "    \"tags\": [\"office\", \"writing\"]\n" +
                "  },\n" +
                "  {\n" +
                "    \"id\": \"3\",\n" +
                "    \"name\": \"notebook\",\n" +
                "    \"quantity\": 5,\n" +
                "    \"price\": 50,\n" +
                "    \"category\": \"stationery\",\n" +
                "    \"available\": true,\n" +
                "    \"tags\": [\"school\", \"university\"]\n" +
                "  }\n" +
                "]";
        
        DocumentContext documentContext = JsonPath.parse(responseJson);

        // Ejemplos básicos
        int length = documentContext.read("$.length()");
        System.out.println("Número total de elementos: " + length);
        assertThat(length).isEqualTo(3);
        
        // Obtener primer elemento
        String firstId = documentContext.read("$[0].id");
        System.out.println("ID del primer elemento: " + firstId);
        assertThat(firstId).isEqualTo("1");
        
        // Obtener último elemento
        String lastId = documentContext.read("$[-1].id");
        System.out.println("ID del último elemento: " + lastId);
        assertThat(lastId).isEqualTo("3");
        
        // Suma de cantidades y precios
        Double totalQuantity = documentContext.read("$..quantity.sum()");
        System.out.println("Cantidad total: " + totalQuantity);
        assertThat(totalQuantity).isEqualTo(35);
        
        Double totalPrice = documentContext.read("$..price.sum()");    
        System.out.println("Precio total: " + totalPrice);
        assertThat(totalPrice).isEqualTo(70);
    }
   
    @Test
    void ejemplosAvanzadosJsonPath() {
        String complexJson = "{\n" +
                "  \"store\": {\n" +
                "    \"book\": [\n" +
                "      {\n" +
                "        \"title\": \"The Great Gatsby\",\n" +
                "        \"author\": \"F. Scott Fitzgerald\",\n" +
                "        \"price\": 8.95,\n" +
                "        \"isbn\": \"978-0743273565\",\n" +
                "        \"category\": \"fiction\",\n" +
                "        \"reviews\": {\n" +
                "          \"rating\": 4.5,\n" +
                "          \"count\": 150\n" +
                "        }\n" +
                "      },\n" +
                "      {\n" +
                "        \"title\": \"To Kill a Mockingbird\",\n" +
                "        \"author\": \"Harper Lee\",\n" +
                "        \"price\": 12.99,\n" +
                "        \"isbn\": \"978-0060935467\",\n" +
                "        \"category\": \"fiction\",\n" +
                "        \"reviews\": {\n" +
                "          \"rating\": 4.8,\n" +
                "          \"count\": 200\n" +
                "        }\n" +
                "      },\n" +
                "      {\n" +
                "        \"title\": \"1984\",\n" +
                "        \"author\": \"George Orwell\",\n" +
                "        \"price\": 9.99,\n" +
                "        \"isbn\": \"978-0451524935\",\n" +
                "        \"category\": \"dystopian\",\n" +
                "        \"reviews\": {\n" +
                "          \"rating\": 4.7,\n" +
                "          \"count\": 180\n" +
                "        }\n" +
                "      }\n" +
                "    ],\n" +
                "    \"bicycle\": {\n" +
                "      \"color\": \"red\",\n" +
                "      \"price\": 19.95\n" +
                "    }\n" +
                "  }\n" +
                "}";

        DocumentContext context = JsonPath.parse(complexJson);

        // 1. Obtener todos los títulos de libros
        List<String> allTitles = context.read("$.store.book[*].title");
        System.out.println("Todos los títulos: " + allTitles);
        assertThat(allTitles).hasSize(3);
        assertThat(allTitles).contains("The Great Gatsby", "To Kill a Mockingbird", "1984");

        // 2. Obtener el primer libro
        Map<String, Object> firstBook = context.read("$.store.book[0]");
        System.out.println("Primer libro: " + firstBook.get("title"));
        assertThat(firstBook.get("title")).isEqualTo("The Great Gatsby");

        // 3. Obtener libros por índice específico (segundo libro)
        String secondBookTitle = context.read("$.store.book[1].title");
        System.out.println("Título del segundo libro: " + secondBookTitle);
        assertThat(secondBookTitle).isEqualTo("To Kill a Mockingbird");

        // 4. Obtener todos los precios usando wildcard
        List<Double> allPrices = context.read("$.store.book[*].price");
        System.out.println("Todos los precios: " + allPrices);
        assertThat(allPrices).containsExactly(8.95, 12.99, 9.99);

        // 5. Obtener todos los autores con descendant operator (..)
        //El operador descendiente (..) permite buscar en todos los niveles de profundidad del JSON.
        List<String> allAuthors = context.read("$..author");
        System.out.println("Todos los autores: " + allAuthors);
        assertThat(allAuthors).hasSize(3);

        // 6. Filtrar libros por condición (precio menor a 10)
        List<Map<String, Object>> cheapBooks = context.read("$.store.book[?(@.price < 10)]");
        System.out.println("Libros baratos (< 10): " + cheapBooks.size() + " libros");
        assertThat(cheapBooks).hasSize(2);

        // 7. Filtrar por categoría
        List<String> fictionBooks = context.read("$.store.book[?(@.category == 'fiction')].title");
        System.out.println("Libros de ficción: " + fictionBooks);
        assertThat(fictionBooks).hasSize(2);

        // 8. Obtener rating de reviews anidadas
        List<Double> ratings = context.read("$.store.book[*].reviews.rating");
        System.out.println("Ratings: " + ratings);
        assertThat(ratings).containsExactly(4.5, 4.8, 4.7);

        // 9. Obtener el libro con mayor rating
        List<Map<String, Object>> bestRatedBooks = context.read("$.store.book[?(@.reviews.rating >= 4.7)]");
        System.out.println("Libros mejor valorados: " + bestRatedBooks.size() + " libros");
        assertThat(bestRatedBooks).hasSize(2);

        // 10. Obtener precio de la bicicleta
        Double bicyclePrice = context.read("$.store.bicycle.price");
        System.out.println("Precio de la bicicleta: " + bicyclePrice);
        assertThat(bicyclePrice).isEqualTo(19.95);
    }


    @Test 
    void ejemplosFiltrosYOperadoresJsonPath() {
        String productsJson = "{\n" +
                "  \"products\": [\n" +
                "    {\n" +
                "      \"id\": 1,\n" +
                "      \"name\": \"Laptop\",\n" +
                "      \"price\": 999.99,\n" +
                "      \"inStock\": true,\n" +
                "      \"specs\": {\n" +
                "        \"ram\": \"16GB\",\n" +
                "        \"storage\": \"512GB SSD\",\n" +
                "        \"processor\": \"Intel i7\"\n" +
                "      },\n" +
                "      \"tags\": [\"electronics\", \"computers\", \"portable\"]\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 2,\n" +
                "      \"name\": \"Smartphone\",\n" +
                "      \"price\": 699.99,\n" +
                "      \"inStock\": false,\n" +
                "      \"specs\": {\n" +
                "        \"ram\": \"8GB\",\n" +
                "        \"storage\": \"128GB\",\n" +
                "        \"processor\": \"Snapdragon 888\"\n" +
                "      },\n" +
                "      \"tags\": [\"electronics\", \"mobile\", \"communication\"]\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 3,\n" +
                "      \"name\": \"Tablet\",\n" +
                "      \"price\": 299.99,\n" +
                "      \"inStock\": true,\n" +
                "      \"specs\": {\n" +
                "        \"ram\": \"4GB\",\n" +
                "        \"storage\": \"64GB\",\n" +
                "        \"processor\": \"Apple A14\"\n" +
                "      },\n" +
                "      \"tags\": [\"electronics\", \"portable\", \"entertainment\"]\n" +
                "    }\n" +
                "  ]\n" +
                "}";

        DocumentContext context = JsonPath.parse(productsJson);

        // 1. Filtrar productos en stock
        List<String> inStockProducts = context.read("$.products[?(@.inStock == true)].name");
        System.out.println("Productos en stock: " + inStockProducts);
        assertThat(inStockProducts).containsExactly("Laptop", "Tablet");

        // 2. Filtrar por rango de precio
        List<String> affordableProducts = context.read("$.products[?(@.price < 500)].name");
        System.out.println("Productos económicos (< $500): " + affordableProducts);
        assertThat(affordableProducts).containsExactly("Tablet");

        // 3. Filtrar por precio entre rangos
        List<String> midRangeProducts = context.read("$.products[?(@.price >= 500 && @.price <= 800)].name");
        System.out.println("Productos rango medio ($500-$800): " + midRangeProducts);
        assertThat(midRangeProducts).containsExactly("Smartphone");

        // 4. Obtener productos que contienen una tag específica
        List<String> portableProducts = context.read("$.products[?(@.tags contains 'portable')].name");
        System.out.println("Productos portátiles: " + portableProducts);
        assertThat(portableProducts).hasSize(2);

        // 5. Obtener especificaciones de RAM
        List<String> ramSpecs = context.read("$.products[*].specs.ram");
        System.out.println("Especificaciones RAM: " + ramSpecs);
        assertThat(ramSpecs).containsExactly("16GB", "8GB", "4GB");

        // 6. Contar productos por condición
        List<Object> expensiveProducts = context.read("$.products[?(@.price > 500)]");
        System.out.println("Productos caros (> $500): " + expensiveProducts.size() + " productos");
        assertThat(expensiveProducts).hasSize(2);

        // 7. Obtener el producto más caro
        List<Double> allPrices = context.read("$.products[*].price");
        Double maxPrice = allPrices.stream().max(Double::compareTo).orElse(0.0);
        List<String> mostExpensive = context.read("$.products[?(@.price == " + maxPrice + ")].name");
        System.out.println("Producto más caro: " + mostExpensive.get(0) + " ($" + maxPrice + ")");
        assertThat(mostExpensive.get(0)).isEqualTo("Laptop");

        // 8. Verificar existencia de campos
        List<String> productsWithSpecs = context.read("$.products[?(@.specs)].name");
        System.out.println("Productos con especificaciones: " + productsWithSpecs);
        assertThat(productsWithSpecs).hasSize(3);

        // 9. Longitud de arrays
        Integer tagsCount = context.read("$.products[0].tags.length()");
        System.out.println("Número de tags del primer producto: " + tagsCount);
        assertThat(tagsCount).isEqualTo(3);
    }

    @Test
    void ejemplosOperacionesMatematicasJsonPath() {
        String salesJson = "{\n" +
                "  \"sales\": [\n" +
                "    {\"amount\": 100, \"quantity\": 2, \"discount\": 10},\n" +
                "    {\"amount\": 250, \"quantity\": 5, \"discount\": 25},\n" +
                "    {\"amount\": 75, \"quantity\": 1, \"discount\": 5},\n" +
                "    {\"amount\": 300, \"quantity\": 3, \"discount\": 30}\n" +
                "  ]\n" +
                "}";

        DocumentContext context = JsonPath.parse(salesJson);

        // 1. Suma total de ventas
        //Double totalSales = context.read("$.sales[*].amount.sum()");
        List<Number> amounts = context.read("$.sales[*].amount");
        Double totalSales = amounts.stream().mapToDouble(Number::doubleValue).sum();
        System.out.println("Total de ventas: $" + totalSales);
        assertThat(totalSales).isEqualTo(725.0);

        // 2. Promedio de ventas
        //Double averageSale = context.read("$.sales[*].amount.avg()");
        amounts = context.read("$.sales[*].amount");
        Double averageSale = amounts.stream().mapToDouble(Number::doubleValue).average().orElse(0.0);
        System.out.println("Promedio de ventas: $" + averageSale);
        assertThat(averageSale).isEqualTo(181.25);

        // 3. Valor mínimo
        amounts = context.read("$.sales[*].amount");
        Double minSale = amounts.stream().mapToDouble(Number::doubleValue).min().orElse(0.0);
        System.out.println("Venta mínima: $" + minSale);
        assertThat(minSale).isEqualTo(75.0);

        // 4. Valor máximo
        amounts = context.read("$.sales[*].amount");
        Double maxSale = amounts.stream().mapToDouble(Number::doubleValue).max().orElse(0.0);
        System.out.println("Venta máxima: $" + maxSale);
        assertThat(maxSale).isEqualTo(300.0);

        // 5. Cantidad total de productos vendidos
        List<Number> quantities = context.read("$.sales[*].quantity");
        Double totalQuantity = quantities.stream().mapToDouble(Number::doubleValue).sum();
        System.out.println("Cantidad total vendida: " + totalQuantity);
        assertThat(totalQuantity).isEqualTo(11.0);

        // 6. Total de descuentos aplicados
        List<Number> discounts = context.read("$.sales[*].discount");
        Double totalDiscounts = discounts.stream().mapToDouble(Number::doubleValue).sum();
        System.out.println("Total descuentos: $" + totalDiscounts);
        assertThat(totalDiscounts).isEqualTo(70.0);
    }


    @Test
    void ejemplosJsonPathConArraysAnidados() {
        String ordersJson = "{\n" +
                "  \"orders\": [\n" +
                "    {\n" +
                "      \"id\": \"ORD001\",\n" +
                "      \"customer\": \"Alice\",\n" +
                "      \"items\": [\n" +
                "        {\"name\": \"Book\", \"price\": 15.99, \"qty\": 2},\n" +
                "        {\"name\": \"Pen\", \"price\": 2.99, \"qty\": 5}\n" +
                "      ]\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": \"ORD002\",\n" +
                "      \"customer\": \"Bob\",\n" +
                "      \"items\": [\n" +
                "        {\"name\": \"Notebook\", \"price\": 8.99, \"qty\": 3},\n" +
                "        {\"name\": \"Marker\", \"price\": 4.99, \"qty\": 2},\n" +
                "        {\"name\": \"Eraser\", \"price\": 1.99, \"qty\": 1}\n" +
                "      ]\n" +
                "    }\n" +
                "  ]\n" +
                "}";

        DocumentContext context = JsonPath.parse(ordersJson);

        // 1. Obtener todos los nombres de productos de todas las órdenes
        List<String> allItemNames = context.read("$..items[*].name");
        System.out.println("Todos los productos: " + allItemNames);
        assertThat(allItemNames).hasSize(5);

        // 2. Obtener productos de la primera orden
        List<String> firstOrderItems = context.read("$.orders[0].items[*].name");
        System.out.println("Productos primera orden: " + firstOrderItems);
        assertThat(firstOrderItems).containsExactly("Book", "Pen");

        // 3. Obtener precios de todos los productos
        List<Double> allPrices = context.read("$..items[*].price");
        System.out.println("Todos los precios: " + allPrices);
        assertThat(allPrices).hasSize(5);

        // 4. Calcular valor total de la primera orden
        List<Double> firstOrderPrices = context.read("$.orders[0].items[*].price");
        List<Integer> firstOrderQtys = context.read("$.orders[0].items[*].qty");
        double firstOrderTotal = 0;
        for (int i = 0; i < firstOrderPrices.size(); i++) {
            firstOrderTotal += firstOrderPrices.get(i) * firstOrderQtys.get(i);
        }
        System.out.println("Total primera orden: $" + firstOrderTotal);
        assertThat(firstOrderTotal).isEqualTo(46.93);

        // 5. Obtener productos con precio mayor a $3
        List<String> expensiveItems = context.read("$..items[?(@.price > 3)].name");
        System.out.println("Productos caros (> $3): " + expensiveItems);
        assertThat(expensiveItems).hasSize(3);

        // 6. Contar items por orden
        Integer firstOrderItemCount = context.read("$.orders[0].items.length()");
        Integer secondOrderItemCount = context.read("$.orders[1].items.length()");
        System.out.println("Items en primera orden: " + firstOrderItemCount);
        System.out.println("Items en segunda orden: " + secondOrderItemCount);
        assertThat(firstOrderItemCount).isEqualTo(2);
        assertThat(secondOrderItemCount).isEqualTo(3);
    }

}
