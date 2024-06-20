package com.example.backend.controller;


import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.backend.entity.Cart;
import com.example.backend.entity.Item;
import com.example.backend.repository.CartRepository;
import com.example.backend.repository.ItemRepository;
import com.example.backend.service.JwtService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;



@RestController
public class CartController {
  @Autowired
  JwtService jwtService;
  @Autowired
  CartRepository cartRepository;
  @Autowired
  ItemRepository itemRepository;

  @GetMapping("/api/cart/items")
  public ResponseEntity getCartItems(@CookieValue(value = "token", required = false) String token) {

    if (!jwtService.isValid(token)) {
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
    }

    int memberId = jwtService.getId(token);
    List<Cart> carts = cartRepository.findByMemberId(memberId);
    List<Integer> itemIds = carts.stream().map(Cart::getItemId).toList();
    List<Item> items = itemRepository.findByIdIn(itemIds);

    return new ResponseEntity<>(items, HttpStatus.OK);
  }

  @PostMapping("/api/cart/items/{itemId}")
  public ResponseEntity pushCarItem(@PathVariable("itemId") int itemId, @CookieValue(value = "token", required = false) String token) {
      if(!jwtService.isValid(token)){
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
      }

      int memberId = jwtService.getId(token);
      Cart cart = cartRepository.findByMemberIdAndItemId(memberId, itemId);

      if(cart == null){
        Cart newCart = new Cart();
        newCart.setMemberId(memberId);
        newCart.setItemId(itemId);
        cartRepository.save(newCart);
      }

      return new ResponseEntity<>(HttpStatus.OK);
  }
  
}
