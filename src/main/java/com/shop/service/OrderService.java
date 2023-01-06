package com.shop.service;


import com.shop.model.LocalUser;
import com.shop.model.WebOrder;
import com.shop.model.repository.WebOrderDao;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    private WebOrderDao webOrderDAO;

    public OrderService(WebOrderDao webOrderDAO) {
        this.webOrderDAO = webOrderDAO;
    }
    public List<WebOrder> getOrders(LocalUser user){
    return webOrderDAO.findByUser(user);

    }
}
