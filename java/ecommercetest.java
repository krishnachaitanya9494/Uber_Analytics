
public class ecommercetest {

    public static void main(String[] args) {
        customer c = new customer();
        c.username = "adi";
        c.email = " adi@gmail.com";
        c.login();
        c.productsbrowse();
        c.placeorder();
        seller s = new seller();
        s.username = " sahithi ";

        s.login();
        s.addproduct();
        s.viewsales();

        admin a = new admin();
        a.username = "bob";
        a.manageusers();
        a.otheroperations();

    }
}