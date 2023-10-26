package hello.core.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import static org.assertj.core.api.Assertions.*;

public class PrototypeTest {

    @Test
    void prototypeBeanFind() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(Prototypebean.class);
        System.out.println("find prototypeBean1");
        Prototypebean prototypebean1 = ac.getBean(Prototypebean.class);
        System.out.println("find prototypeBean2");
        Prototypebean prototypebean2 = ac.getBean(Prototypebean.class);
        System.out.println("prototypebean1 = " + prototypebean1);
        System.out.println("prototypebean2 = " + prototypebean2);
        assertThat(prototypebean1).isNotSameAs(prototypebean2);

        prototypebean1.destroy();
        prototypebean2.destroy();
        ac.close();
    }
    @Scope("prototype")
    static class Prototypebean{
        @PostConstruct
        public void init() {
            System.out.println("Prototypebean.init");
        }

        @PreDestroy
        public void destroy() {
            System.out.println("Prototypebean.destroy");
        }
    }

}
