package recuperacion.edu.Dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderRequest {
    private String orderId;
    private String email;
    private String products;
}