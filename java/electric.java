public class electric {
    String brand = "tesla"; 
    void engine() {
        System.out.println("Engine starts");
    }

    void features() {
        System.out.println("V* engine");
        System.out.println("Nitros");
        System.out.println("200 miles mileage");
    }

    public static void main(String[] args) {
        electric obj = new electric();
          System.out.println(obj.brand);
        obj.features();
        obj.engine();
      
    }
}
