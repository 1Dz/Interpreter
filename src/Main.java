public class Main {

    public static void main(String[] args) {
        try {
            Interpret in = new Interpret("3 * 7 + 12 / 3");
            System.out.println(in.expr());
        } catch (InterpretException e) {
            System.out.println(e.getMessage());
        }
    }
}
