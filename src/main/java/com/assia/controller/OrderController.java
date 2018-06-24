package com.assia.controller;

import com.assia.exception.NotFoundException;
import com.assia.model.order.OrderModel;
import com.assia.model.order.OrderForm;
import com.assia.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigInteger;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @RequestMapping(method = RequestMethod.GET)
    public List<OrderModel> getAll(){
        return this.orderService.getAllOrder();
    }
    @RequestMapping(value ="/{id}" ,method = RequestMethod.GET)
    public OrderModel getById(@PathVariable("id") BigInteger id){
        return this.orderService.getById(id).map(com.assia.domain.order.Order::toOrder).orElseThrow(NotFoundException::new);
    }
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") BigInteger id){
        this.orderService.delete(id);
    }

    @RequestMapping(value ="/{id}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public OrderModel update(@PathVariable("id") BigInteger id, @Valid @RequestBody OrderForm orderForm){
        return this.orderService.update(id,orderForm).map(com.assia.domain.order.Order::toOrder).orElseThrow(NotFoundException::new);
    }
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public OrderModel insert(@Valid @RequestBody OrderForm orderForm){
        return this.orderService.create(orderForm).toOrder();
    }
}
