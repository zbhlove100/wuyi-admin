package wuyi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import wuyi.model.Item;

public interface ItemRepository extends JpaRepository<Item, Integer> {

}
