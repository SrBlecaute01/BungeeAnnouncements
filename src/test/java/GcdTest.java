import br.com.blecaute.announcement.util.MathUtil;

public class GcdTest {

    public static void main(String[] args) {
        System.out.println(MathUtil.getGCD(16, 8, 24));
        System.out.println(MathUtil.getGCD(16, 32, 64));
        System.out.println(MathUtil.getGCD(15, 20, 50, 65));
    }

}
