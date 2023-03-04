package tests.jm;

import static org.apache.http.entity.ContentType.APPLICATION_JSON;
import static us.abstracta.jmeter.javadsl.JmeterDsl.*;
import java.io.IOException;
import org.junit.jupiter.api.Test;
import us.abstracta.jmeter.javadsl.core.TestPlanStats;

public class JmeterTest3 {
    @Test
    public void testPerformance() throws IOException {
        TestPlanStats stats = testPlan(
                threadGroup(2, 10,
                        httpSampler("http://my.service")
                                .post("{\"name\": \"test\"}", APPLICATION_JSON)
                ),
                //this is just to log details of each request stats
                jtlWriter("test.jtl")
        ).run();
    }

}