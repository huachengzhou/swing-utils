package gr

class exampleA {

    static void main(String[] args) {
        // x is defined as a variable
        String x = "Hello";

        // The value of the variable is printed to the console
        println(x);
    }




}

class Callee{
     void hello(){
        println "hello, world" ;
    }
}

def c = new Callee() ;
c.hello();
