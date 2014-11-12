package apachecon.demo.router.inendpoint;

import org.apache.camel.Exchange;
import org.apache.camel.support.ExpressionAdapter;

import java.util.UUID;

public class RandomUUIDExpression extends ExpressionAdapter {

    public static RandomUUIDExpression randomUUID() {
        return new RandomUUIDExpression();
    }

    @Override
    public Object evaluate(Exchange exchange) {
        return UUID.randomUUID().toString();
    }

}