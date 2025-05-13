package recuperacion.edu.Event;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class OrderCreaterEvent {
    private final String orderId;
    private final String email;
    private final String products;
}
