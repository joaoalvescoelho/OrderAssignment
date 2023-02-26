package com.example.orderassignment.serviceImpl;

import com.example.orderassignment.dto.AllOrdersDTO;
import com.example.orderassignment.dto.OrderDTO;
import com.example.orderassignment.entity.Order;
import com.example.orderassignment.entity.Product;
import com.example.orderassignment.entity.User;
import com.example.orderassignment.entity.Users;
import com.example.orderassignment.exception.AlreadyOrdered;
import com.example.orderassignment.exception.EmailNotFound;
import com.example.orderassignment.exception.OrderNotFound;
import com.example.orderassignment.exception.ProductNotFound;
import com.example.orderassignment.mapper.OrderMapper;
import com.example.orderassignment.repository.OrderRepository;
import com.example.orderassignment.service.OrderService;
import com.example.orderassignment.service.ProductService;
import com.example.orderassignment.user.UserDataApi;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UserDataApi userDataApi;
    @Autowired
    private ProductService productService;
    @Autowired
    private OrderMapper orderMapper;

    /**
     * Returns a list of all the
     * orders found in the database
     * @return a list of the orders
     * @throws OrderNotFound when no orders are found
     */
    @Override
    public List<AllOrdersDTO> getAllOrders() {
        List<Order> ordersList = orderRepository.findAll();
        if(ordersList.isEmpty()) {
            throw new OrderNotFound("No orders were found!");
        }
        return orderMapper.toDto(ordersList);
    }

    /**
     * Returns an order by the given id
     * @param id is the id of an order
     * @return an order
     * @throws IllegalArgumentException when the id
     * is null
     */
    @Override
    public Order getOrderById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }
        return orderRepository.getReferenceById(id);
    }

    /**
     * Creates an object of the type order
     * in the database
     * @param orderDTO is the order information provided
     * @return th id of the order
     */
    @Override
    public Long createOrder(OrderDTO orderDTO) {
        List<Users> users = getUsersFromApi();
        Order ord = new Order();

        // b - Verify emai\l in API
        User user = verifyEmail(users, orderDTO.getEmail());

        // c - Verify if not previously ordered
        Product product = orderedBefore(orderDTO);

        //d - Save order in the database
        ord.setEmail(user.getEmail());
        ord.setFirstName(user.getFirstName());
        ord.setLastName(user.getLastName());
        ord.setProduct(product);

        product.addOrder(ord);
        orderRepository.save(ord);

        return ord.getOrderID();
    }

    /**
     * verify if the email is in the API
     * @param users is the user passed
     * @param email is the email to verify with the API
     * @return user
     */
    private User verifyEmail(List<Users> users, String email) {
        for (Users user : users) {
            Optional<User> u = user.getData()
                    .stream().
                    filter(usr -> usr.getEmail().equalsIgnoreCase(email)).findFirst();
            if (u.isPresent()) {
                return u.get();
            }
        }
        throw  new EmailNotFound("The email provided wasn't found");
    }

    /**
     * verify if the order has been ordered
     * by the user before
     *
     * @param orderDTO is the order being made
     * @return product
     * @throws AlreadyOrdered if the ordered is repeated
     */
    private Product orderedBefore(OrderDTO orderDTO) {
        Product product = productService.getProductById(orderDTO.getProductID());
        if(product == null) {
            throw new ProductNotFound("The product does not exist!");
        }
        if (product.getOrders().stream().noneMatch(ord -> ord.getEmail().equalsIgnoreCase(orderDTO.getEmail()))) {
            return product;
        }
        throw new AlreadyOrdered("The user as ordered before!");
    }

    /**
     * Gets the users from the API
     * @return list of users
     * @throws RuntimeException when JsonProcessingException is caught
     */
    public List<Users> getUsersFromApi() {
        List<Users> users;
        try {
            users = userDataApi.getUserDataApi();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return users;
    }
}
