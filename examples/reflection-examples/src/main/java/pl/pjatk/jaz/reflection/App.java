package pl.pjatk.jaz.reflection;

//import pl.pjatk.jaz.reflection.model.Catalog;
import pl.pjatk.jaz.reflection.model.Catalog;
import pl.pjatk.jaz.reflection.model.Priced;
import pl.pjatk.jaz.reflection.model.Product;

public class App {
    public static void main(String[] args) throws ReflectiveOperationException {
//        var product = new Product("1", "Laptop", null, 10);
//        Class<?> productClass = product.getClass();
        Class<?> typeFromLiteral = Product.class;
        Class<?> typeFromName = Class.forName("pl.pjatk.jaz.reflection.model.Product");
        System.out.println("Ten sam obiekt Class: " + (typeFromLiteral == typeFromName));
        System.out.println("Product implementuje Priced: " + Priced.class.isAssignableFrom(Product.class));

//         ClassInspector.describe(Product.class);
//         ClassInspector.describe(Catalog.class);
//        ClassInspector.describe(int[].class);
//        ClassInspector.describe(Priced.class);

         Product product = ReflectionDemo.createProduct();
         System.out.println("\nUtworzono: " + product.name());
         System.out.println("Stan odczytany z pola: " + ReflectionDemo.readField(product, "stock"));

         Object reserved = ReflectionDemo.invoke(
                 product, "reserve", new Class<?>[]{int.class}, 3);
         System.out.println("Wynik reserve(3): " + reserved);
         System.out.println("Stan po rezerwacji: " + product.stock());

         ReflectionDemo.invoke(product, "internalCode", new Class<?>[]{});
    }
}
