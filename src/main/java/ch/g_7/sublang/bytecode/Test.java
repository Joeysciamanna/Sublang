package ch.g_7.sublang.bytecode;

import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import me.qmx.jitescript.CodeBlock;
import me.qmx.jitescript.JiteClass;
import static me.qmx.jitescript.CodeBlock.newCodeBlock;
import static me.qmx.jitescript.util.CodegenUtils.c;
import static me.qmx.jitescript.util.CodegenUtils.ci;
import static me.qmx.jitescript.util.CodegenUtils.p;
import static me.qmx.jitescript.util.CodegenUtils.sig;

public class Test {

	
    public static class DynamicClassLoader extends ClassLoader {
        public Class<?> define(JiteClass jiteClass) {
            byte[] classBytes = jiteClass.toBytes();
            return super.defineClass(c(jiteClass.getClassName()), classBytes, 0, classBytes.length);
        }
    }
	
	public static void main(String[] args) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		 final String className = "helloTest";
	        JiteClass jiteClass = new JiteClass(className) {
	            {
	                // you can use the pre-constructor style
	                defineMethod("main", ACC_PUBLIC | ACC_STATIC, sig(void.class, String[].class), new CodeBlock() {
	                    {
	                        ldc("helloWorld");
	                        getstatic(p(System.class), "out", ci(PrintStream.class));
	                        swap();
	                        invokevirtual(p(PrintStream.class), "println", sig(void.class, Object.class));
	                        voidreturn();
	                        
	                    }
	                });
	                // or use chained api
	                defineMethod("hello", ACC_PUBLIC | ACC_STATIC, sig(String.class), newCodeBlock().ldc("helloWorld").areturn());
	                
	            }
	        };
	        
	        Class<?> clazz = new DynamicClassLoader().define(jiteClass);
	        Method helloMethod = clazz.getMethod("hello");
	        Object result = helloMethod.invoke(null);
	      

	        Method mainMethod = clazz.getMethod("main", String[].class);
	        mainMethod.invoke(null, (Object) new String[]{});
	}
}
