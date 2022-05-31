package camelinaction.ch02;


import org.apache.camel.CamelContext;
// import org.apache.camel.RoutesBuilder;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.test.junit5.CamelTestSupport;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SimpleMockTest extends CamelTestSupport {

    /*
    @BeforeEach
    public void setUp() throws Exception {
        //start camel
        CamelContext context = new DefaultCamelContext();
        context.addRoutes(createRouteBuilder());
        context.start();
        template = context.createProducerTemplate();
    }
*/

    @Test
    void testMock() throws Exception {
        MockEndpoint resultEndpoint = resolveMandatoryEndpoint("mock:result", MockEndpoint.class);
        resultEndpoint.expectedBodiesReceived("Hello World");

//        getMockEndpoint("mock:result").expectedBodiesReceived("Hello World");

        template.sendBody("direct:start", "Hello World");

        assertMockEndpointsSatisfied();
    }

    @Override
    protected RouteBuilder createRouteBuilder() {
        return new RouteBuilder() {
            @Override
            public void configure() {
                from("direct:start").to("mock:result");
            }
        };
    }
}

