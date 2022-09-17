/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reto2;

import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * @author maria
 */
public class Reto2 {

    private static final Scanner scanner = new Scanner(System.in);

    /**
     * Este método es usado para solicitar datos al usuario
     *
     * @return Lectura de la siguiente linea del teclado
     */
    public static String read() {
        return scanner.nextLine();
    }

    public static void main(String[] args) {
        run();
        // TODO code application logic here
    }

    public static void run() {
        BaseDatosProductos baseDatos = new BaseDatosProductos();
        String operador = read();
        String[] parametros = read().split(" ");
        Producto producto = new Producto(Integer.parseInt(parametros[0]), parametros[1], Double.parseDouble(parametros[2]), Integer.parseInt(parametros[3]));

        /**
         * Condicion para el operador AGREGAR
         */
        if (operador.equals("AGREGAR") && !baseDatos.verificarExistencia(producto)) {
            baseDatos.agregar(producto);
            baseDatos.generarInforme();
            /**
             * Condicion para el operador ACTUALIZAR
             */
        } else if (operador.equals("ACTUALIZAR") && baseDatos.verificarExistencia(producto)) {
            baseDatos.actualizar(producto);
            baseDatos.generarInforme();
            /**
             * Condicion para el operador ACTUALIZAR cuando no existe
             */
        } else if (operador.equals("ACTUALIZAR") && !baseDatos.verificarExistencia(producto)) {
            System.out.println("ERROR");

            /**
             * Condicion para el operador BORRAR
             */
        } else if (operador.equals("BORRAR") && baseDatos.verificarExistencia(producto)) {
            baseDatos.eliminar(producto);
            baseDatos.generarInforme();
            /**
             * Condicion para el operador AGREGAR cuando ya existe
             */
        } else if (operador.equals("BORRAR") && !baseDatos.verificarExistencia(producto)) {
            System.out.println("ERROR");
        }
    }
}
    /**
     * Clase productos
     *
     * @author maria
     */
    class Producto {

        private Integer codigo;
        private String nombre;
        private double precio;
        private Integer inventario;

        /**
         * Constructor vacio de la clase producto
         */
        public Producto() {
        }

        public Producto(Integer codigo, String nombre, double precio, Integer inventario) {
            this.codigo = codigo;
            this.nombre = nombre;
            this.precio = precio;
            this.inventario = inventario;
        }

        public Integer getCodigo() {
            return codigo;
        }

        public void setCodigo(Integer codigo) {
            this.codigo = codigo;
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public double getPrecio() {
            return precio;
        }

        public void setPrecio(double precio) {
            this.precio = precio;
        }

        public Integer getInventario() {
            return inventario;
        }

        public void setInventario(Integer inventario) {
            this.inventario = inventario;
        }
    }

    class BaseDatosProductos {

        private HashMap<Integer, Producto> listaProductos = new HashMap<Integer, Producto>();

        public BaseDatosProductos() {
            listaProductos.put(1, new Producto(1, "Manzanas", 5000.0, 25));
            listaProductos.put(2, new Producto(2, "Limones", 2300.0, 15));
            listaProductos.put(3, new Producto(3, "Peras", 2700.0, 33));
            listaProductos.put(4, new Producto(4, "Arandanos", 9300.0, 5));
            listaProductos.put(5, new Producto(5, "Tomates", 2100.0, 42));
            listaProductos.put(6, new Producto(6, "Fresas", 4100.0, 3));
            listaProductos.put(7, new Producto(7, "Helado", 4500.0, 41));
            listaProductos.put(8, new Producto(8, "Galletas", 500.0, 8));
            listaProductos.put(9, new Producto(9, "Chocolates", 3500.0, 80));
            listaProductos.put(10, new Producto(10, "Jamon", 15000.0, 10));

        }

        public void agregar(Producto producto) {
            listaProductos.put(producto.getCodigo(), producto);
        }

        public void actualizar(Producto producto) {
            listaProductos.replace(producto.getCodigo(), producto);
        }

        public void eliminar(Producto producto) {
            listaProductos.remove(producto.getCodigo());
        }

        public void generarInforme() {
            System.out.println(String.format("%.1f", total()));
        }
        
        public double total() {
            double resultado = 0;
            for (Producto producto : listaProductos.values()) {
                resultado += producto.getPrecio() * producto.getInventario();
            }
            return resultado;
        }
        
               
        public boolean verificarExistencia(Producto producto) {
            return listaProductos.containsKey(producto.getCodigo());
        }
    }

