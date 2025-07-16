import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ShopService {
    private ProductRepo productRepo = new ProductRepo();
    private OrderRepo orderRepo = new OrderMapRepo();

    public Order addOrder(List<String> productIds) {
        List<Product> products = new ArrayList<>();
        for (String productId : productIds) {
            Product productToOrder = productRepo.getProductById(productId).orElse(null);
            if (productToOrder == null) {
                    throw new ProductDoesNotExist("Product mit der Id: " + productId + " konnte nicht bestellt werden!");
            }
            products.add(productToOrder);
        }
        Order newOrder = new Order(UUID.randomUUID().toString(), products,OrderStatus.PROCESSING, ZonedDateTime.now());

        return orderRepo.addOrder(newOrder);
    }
    public List<Order> getOrdersByStatus(OrderStatus orderStatus){
        return orderRepo.getOrders().parallelStream()
                .filter(order ->
                    order.orderStatus().equals(orderStatus)
                ).toList();
    }

    public void updateOrder(String orderId,OrderStatus orderStatus){
        orderRepo.addOrder(orderRepo.getOrderById(orderId).withOrderStatus(orderStatus));
    }
}
