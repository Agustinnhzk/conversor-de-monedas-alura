package com.conversormonedas.alura;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        ConsultaMoneda consulta = new ConsultaMoneda();
        int opcion = 0;

        String menu = """
                ***************************************************
                Sea bienvenido/a al Conversor de Monedas =]
                
                1 - Dólar => Peso argentino
                2 - Peso argentino => Dólar
                3 - Dólar => Real brasileño
                4 - Real brasileño => Dólar
                5 - Dólar => Peso colombiano
                6 - Peso colombiano => Dólar
                7 - Salir
                
                Elija una opción válida:
                ***************************************************
                """;

        while (opcion != 7) {
            System.out.println(menu);
            opcion = teclado.nextInt();

            // Si elige una opción del 1 al 6, entra acá a hacer la conversión
            if (opcion >= 1 && opcion <= 6) {
                String monedaBase = "";
                String monedaDestino = "";

                // El switch asigna las monedas según el número que eligió el usuario
                switch (opcion) {
                    case 1 -> { monedaBase = "USD"; monedaDestino = "ARS"; }
                    case 2 -> { monedaBase = "ARS"; monedaDestino = "USD"; }
                    case 3 -> { monedaBase = "USD"; monedaDestino = "BRL"; }
                    case 4 -> { monedaBase = "BRL"; monedaDestino = "USD"; }
                    case 5 -> { monedaBase = "USD"; monedaDestino = "COP"; }
                    case 6 -> { monedaBase = "COP"; monedaDestino = "USD"; }
                }

                System.out.println("Ingresá la cantidad que querés convertir:");
                double cantidad = teclado.nextDouble();

                System.out.println("Procesando cotización de " + monedaBase + " a " + monedaDestino + "...");

                try {
                    // Busca la moneda base elegida (ej: USD)
                    Moneda moneda = consulta.buscarMoneda(monedaBase);
                    // Extrae la tasa de la moneda destino elegida (ej: ARS)
                    double tasa = moneda.conversion_rates().get(monedaDestino);

                    // Hace el cálculo matemático
                    double resultado = cantidad * tasa;

                    // Muestra el resultado final prolijo (con solo 2 decimales)
                    System.out.printf("El valor de %.2f [%s] corresponde al valor final de =>> %.2f [%s]\n\n",
                            cantidad, monedaBase, resultado, monedaDestino);

                } catch (Exception e) {
                    System.out.println("Hubo un error al conectar con la API: " + e.getMessage());
                }

            } else if (opcion == 7) {
                System.out.println("Saliendo del programa... ¡Gracias por usar el conversor!");
            } else {
                System.out.println("Opción no válida. Por favor, elegí un número del 1 al 7.");
            }
        }
    }
}2