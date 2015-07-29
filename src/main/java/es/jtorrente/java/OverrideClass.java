package es.jtorrente.java;

/**
 * Playing around hiding memebers of a class. MyTest and MyTest3 do not compile because they make visibility of abstract method foo() more restrictive than the original declaration
 *
 * Created by jtorrente on 21/07/2015.
 */
public class OverrideClass {
    public abstract class Test {
        protected abstract void foo();
    }

    /*class MyTest extends Test {
        @Override
        void foo(){

        }
    }*/

    class MyTest2 extends Test{
        @Override
        public void foo(){

        }
    }

    /*class MyTest3 extends Test{
        @Override
        private void foo(){

        }
    }*/

    class MyTest4 extends Test{
        @Override
        protected final void foo(){

        }
    }

    class MyTest5 extends Test{
        @Override
        protected void foo(){

        }
    }
}
