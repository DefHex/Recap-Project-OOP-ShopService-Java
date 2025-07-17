import org.junit.jupiter.api.Test;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ProductDoesNotExistTest {
    @Test
    void throwExceptionWhenAddingNonExisingProductToOrder(){
        Product testProd=new Product(UUID.randomUUID().toString(),"TestProd");
        ProductRepo productRepo=new ProductRepo();
        productRepo.addProduct(testProd);
        Order testOrder=new Order(UUID.randomUUID().toString(),productRepo.getProducts(),OrderStatus.PROCESSING, ZonedDateTime.now());
        OrderRepo orderRepo=new OrderMapRepo();
        orderRepo.addOrder(testOrder);
        ShopService shopService = new ShopService(productRepo,orderRepo);

        assertThrows(ProductDoesNotExist.class,()->shopService.addOrder(List.of("NonExistingId")));
    }

}