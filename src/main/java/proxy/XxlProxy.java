package proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 增强器
 */
public class XxlProxy implements InvocationHandler {
    private Girl girl;

    public XxlProxy(Girl girl) {
        super();
        this.girl = girl;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        doSomethingBefore();
        Object ret = method.invoke(girl, args);
        doSomethingAfter();
        return ret;
    }

    public void doSomethingBefore() {
        System.out.println("xxl的妈妈说，我要先调查一下这个男孩的背景");
    }

    public void doSomethingAfter() {
        System.out.println("xxl的妈妈说，和他相处感觉怎么样啊");
    }

    /***
     * jvm动态代理
     * 使用jdk的反射机制，创建对象的能力， 创建的是代理类的对象。 而不用你创建代理类文件。不用写java文件。
     * @return
     */
    public Object getProxyInstance() {
        return Proxy.newProxyInstance(girl.getClass().getClassLoader(), girl.getClass().getInterfaces(), this);
    }
}
