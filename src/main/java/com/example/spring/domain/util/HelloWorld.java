package com.example.spring.domain.util;

/**
 * @author: Staro
 * @date: 2020/6/15 10:33
 * @Description:
 */
public class HelloWorld {

    public static void main(String[] args) {
//        try{
//            String[] split = System.getProperty("java.class.path").split(";");
//            Arrays.asList(split).forEach(System.out::println);
//            System.out.println();
//            Class<?> forName = HelloWorld.class.getClassLoader().loadClass("com.example.spring.domain.util.TestBean");
//            System.out.println(forName.getClassLoader());
//        }catch(Exception e){
//            e.printStackTrace();
//        }

//        String url="F:\\java-projects\\spring\\target\\classes\\";
//        FileClassLoader fileClassLoader0=new FileClassLoader(url);
//        FileClassLoader fileClassLoader1=new FileClassLoader(url);
//        System.out.println(fileClassLoader0.hashCode());
//        System.out.println(fileClassLoader1.hashCode());
//        try {
//            Class<?> testBean2 = fileClassLoader0.loadClass("com.example.spring.domain.util.TestBean");
//            Class<?> testBean3 = fileClassLoader1.loadClass("com.example.spring.domain.util.TestBean");
//            System.out.println(testBean2.hashCode());
//            System.out.println(testBean3.hashCode());
//            System.out.println(TestBean.class.hashCode());
//
//
//            Class<?> testBean0 = fileClassLoader0.findClass("com.example.spring.domain.util.TestBean");
//            Class<?> testBean1 = fileClassLoader1.findClass("com.example.spring.domain.util.TestBean");
//            System.out.println(testBean0.hashCode());
//            System.out.println(testBean1.hashCode());
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }

//        String s = new String("1");
//        String s2 = "1";
//        s.intern();
//        System.out.println(s == s2);
//
//        String s3 = new String("1") + new String("1");
//        s3.intern();
//        String s4 = "11";
//        System.out.println(s3 == s4);

//
//        String s1 = new String("he") + new String("llo");
//        String s2 = new String("h") + new String("ello");
//        String s3 = s1.intern();
//        String s4 = s2.intern();
//        System.out.println(s1 == s3);// true
//        System.out.println(s1 == s4);// true
//
//        Short d1 = 128;
//        Short d2 = 128;
//        System.out.println(d1 == d2);

        new HelloWorld().new Dervied();
    }

    class Base {
        private String name = "aaaa";

        public Base() {
            tellName();
            printName();
        }
        public void tellName(){
            System.out.println("Base tell:"+name);
        }

        public void printName(){
            System.out.println("Base print:"+name);
        }
    }

    class Dervied extends Base{
        private String name="bbbb";
        private Dervied(){
            tellName();
            printName();
        }

        @Override
        public void tellName() {
            System.out.println("Dervied tell:"+name);
        }

        @Override
        public void printName() {
            System.out.println("Dervied print:"+name);
        }

    }
}
