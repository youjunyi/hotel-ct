package school.libenhe.hotel.servlet;



import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.*;

    public class OnlineExamUtil {
        public static void putStringIntoFile(String strCode){
            String path = new String("E:/test/Code.java");
            boolean flag = (new File(path)).delete();
            try {
                PrintWriter out = new PrintWriter(new File(path));
                out.print(strCode);
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        public static void executeFile(){
            JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
            int result = compiler.run(null, null, null,"E:/test/Code.java");
            System.out.println("Compile result code = " + result);

        }
        public static void main(String[] agrs) throws IOException {
            putStringIntoFile("    public class Code {public static void main(String[] agrs){" +
                    "System.out.println(3);" +
                    "System.out.println(\"2\");" +
                    "}}");
            //Process child = Runtime.getRuntime().exec("Code.java");
            String cmd = "javac E:/test/Code.java";
            String cmd1 = "java -cp E:/test Code";
            Process p1  = Runtime.getRuntime().exec(cmd);

            BufferedReader in1 = new BufferedReader(new InputStreamReader(
                    p1.getErrorStream()));
            String line1 = null;
            while ((line1 = in1.readLine()) != null) {
                System.out.println(line1);
            }

            Process p = Runtime.getRuntime().exec(cmd1);
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    p.getInputStream()));
            String line = null;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
        }
    }

