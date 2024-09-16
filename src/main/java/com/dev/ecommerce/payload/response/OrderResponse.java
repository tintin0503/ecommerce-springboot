package com.dev.ecommerce.payload.response;

import com.dev.ecommerce.payload.OrderDto;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse extends PageResponse {

    private List<OrderDto> content;
}
