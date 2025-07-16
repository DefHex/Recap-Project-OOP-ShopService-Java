import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProductDoesNotExistTest {
    @Test
    void throwExceptionWhenAddingNonExisingProductToOrder(){
        ShopService shopService = new ShopService();
        assertThrows(ProductDoesNotExist.class,()->shopService.addOrder(List.of("NonExistingId")));
    }

}