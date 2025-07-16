import org.junit.jupiter.api.Test;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ShopServiceTest {
    ZonedDateTime zdt = ZonedDateTime.of(
            2025, 7, 16,    // year, month, day
            14, 30, 0, 0,    // hour, minute, second, nanosecond
            ZoneId.of("Europe/Berlin") // time zone
    );
    @Test
    void addOrderTest() {
        //GIVEN
        ShopService shopService = new ShopService();
        List<String> productsIds = List.of("1");

        //WHEN
        Order actual = shopService.addOrder(productsIds);

        //THEN
        Order expected = new Order("-1", List.of(new Product("1", "Apfel")),OrderStatus.PROCESSING,zdt);
        assertEquals(expected.products(), actual.products());
        assertNotNull(expected.id());
    }

    @Test
    void addOrderTest_whenInvalidProductId_ThrowException() {
        //GIVEN
        ShopService shopService = new ShopService();
        List<String> productsIds = List.of("1", "2");

        assertThrows(ProductDoesNotExist.class,()->shopService.addOrder(productsIds));
    }
}
