public class HorribleSteve {
    public static void main(String [] args) throws Exception {
        int i = 0;
        for (int j = 0; i < 500; ++i, ++j) {
            System.out.println("i: "+i+" j: "+j+" "+(i==j));
            System.out.println("is same number in main: "+Flik.isSameNumber(i, j));
            // so it is the difference between int and Integer
            // where int is te primitive type of integer values
            // BUT Integer is a class, and it uses the same instance for integer value -128~127
            // new instance is created for value out of the range
            // == is for object equality; should be replaced with a.equals(b)
            if (!Flik.isSameNumber(i, j)) {
                throw new Exception(
                        String.format("i:%d not same as j:%d ??", i, j));
            }
        }
        System.out.println("i is " + i);
    }
}
