
public class SqueakyCheeseMain
{
    public static void main(String[] args)
    {
        SqueakyCheese database = new SqueakyCheese();
        // database.selectBookById(11);
        // database.login("Reese.Rognsvoog", "1234"); // for testing purposes, this will display a LOGIN SUCCESS to the console when this part works!
        LibraryApp.launch(LibraryApp.class, args);
    }
}
