import com.github.javafaker.Faker;

import java.time.ZonedDateTime;
import java.util.*;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        Faker faker=new Faker();
        Random random=new Random();

        ProductRepo productRepo=new ProductRepo();
        IntStream.range(0,5).parallel().forEach(n-> productRepo.addProduct(new Product(UUID.randomUUID().toString(),faker.food().fruit())));
//        productRepo.getProducts().forEach(System.out::println);

        OrderMapRepo orderMapRepo=new OrderMapRepo();

        List<Product> productListCopy=productRepo.getProducts();
        for (int n = 0; n < 5; n++) {
            Collections.shuffle(productListCopy);
            orderMapRepo.addOrder(new Order(UUID.randomUUID().toString(),
                    //using new ArrayList to make sure no reference to the old productListCopy gets used
                    new ArrayList<>(productListCopy.subList(0, random.nextInt(5) + 1)),
                    OrderStatus.PROCESSING,
                    ZonedDateTime.now().minusMinutes(random.nextInt(10))));
        }
//        orderMapRepo.getOrders().forEach(System.out::println);

        ShopService shopService=new ShopService(productRepo,orderMapRepo);
        shopService.getOrdersByStatus(OrderStatus.PROCESSING).forEach(System.out::println);
    }
}
