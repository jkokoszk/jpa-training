package pl.spring.demo.web.jetty;

import org.eclipse.jetty.util.component.LifeCycle;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

public class EmbeddedJettyTest {

    @Test
    public void embeddedJettyShouldStart() throws Exception {
        // given
        EmbeddedJetty embeddedJetty = new EmbeddedJetty() {
            @Override
            protected List<LifeCycle.Listener> createListeners() {
                return Collections.singletonList(new LifeCycle.Listener() {

                    @Override
                    public void lifeCycleStarted(LifeCycle lifeCycle)  {
                        try {
                            lifeCycle.stop();
                        }
                        catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }

                    @Override
                    public void lifeCycleStarting(LifeCycle lifeCycle) {}

                    @Override
                    public void lifeCycleFailure(LifeCycle lifeCycle, Throwable throwable) {}

                    @Override
                    public void lifeCycleStopping(LifeCycle lifeCycle) {}

                    @Override
                    public void lifeCycleStopped(LifeCycle lifeCycle) {}
                });
            }
        };
        // when
        embeddedJetty.startJetty(9876);
        // then no exception expected
    }
}