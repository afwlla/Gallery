package com.example.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.backend.entity.Item;
import java.util.List;


public interface ItemRepository extends JpaRepository<Item, Integer>{
  List<Item> findByIdIn(List<Integer> ids);
}
