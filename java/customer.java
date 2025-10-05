public  class customer extends user {
    void productsbrowse() {
        System.out.println(username + "browsing products");
    }

    void placeorder() {
        System.out.println(username + "placed order");
    }
}

     class seller extends user {
        void addproduct() {
            System.out.println(username + "adding products");
        }

        void viewsales() {
            System.out.println(username + "is viewing sales");
        }
    }

         class admin extends user {
            void manageusers() {
                System.out.println(username + "is managing users");
            }

            void otheroperations() {
                System.out.println(username + " is managing other operations ");
            }

        }

    
