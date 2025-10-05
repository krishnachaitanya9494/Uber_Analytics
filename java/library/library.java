package library;
// inhertiting from the previous class from user to library
public class library extends user {
    //method 
    void member () {
        System.out.println( username + " " +  email +  " is taking a book ");

    }
    class librarian extends user {
        void librarian1 () {
            System.out.println(username + "is managing the library" );
        }
        void worker() {
            System.out.println( username + " is a worker in a library ");
        }
    
        
    }
    class admin extends user {
        void admin1 () {
            System.out.println(username + " admin is managin the library");
        }
        void observer(){
            System.out.println(username + "is observing the library ");
        }
    }
    
}
