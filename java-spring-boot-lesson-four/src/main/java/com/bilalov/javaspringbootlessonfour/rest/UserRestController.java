package com.bilalov.javaspringbootlessonfour.rest;

import com.bilalov.javaspringbootlessonfour.dto.ProductDTO;
import com.bilalov.javaspringbootlessonfour.dto.ProductMapper;
import com.bilalov.javaspringbootlessonfour.dto.UserMapper;
import com.bilalov.javaspringbootlessonfour.dto.UserDTO;
import com.bilalov.javaspringbootlessonfour.entities.User;
import com.bilalov.javaspringbootlessonfour.services.UsersService;
import com.bilalov.javaspringbootlessonfour.services.exceptions.NotFoundException;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Tag(name = "User API", description = "API to manipulate user resources")
@RestController
@RequestMapping("/api/v1/user")
public class UserRestController {

    private UsersService service;

    @Autowired
    public void setService(UsersService service) {
        this.service = service;
    }

    @GetMapping(path = "/{id}/id", produces = "application/json")
    public UserDTO findById(@PathVariable("id") Long id) {
        User user = service.findById(id).orElseThrow(NotFoundException::new);
        UserDTO userDTO = UserMapper.MAPPER.fromUser(user);
        List<ProductDTO> productDTOList = ProductMapper.MAPPER.fromProductList(user.getProducts());
        userDTO.setProductDTOS(productDTOList);
        userDTO.setNickname("little-" + user.getLogin());
        return userDTO;
    }

    @GetMapping(path = "/list", produces = "application/json")
    public List<User> findAll() {
        return service.findAll();
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public User createUser(@RequestBody User user) {
        service.createOrUpdate(user);
        return user;
    }

    @PutMapping(consumes = "application/json", produces = "application/json")
    public UserDTO save(@RequestBody UserDTO userDTO) {
        User user = UserMapper.MAPPER.toUser(userDTO);
        return UserMapper.MAPPER.fromUser(user);
    }

    @DeleteMapping("/{id}/id")
    public void deleteById(@PathVariable("id") Long id) {
        service.deleteById(id);
    }

    @ExceptionHandler
    public ResponseEntity<String> notFoundExceptionHandler(NotFoundException e) {
        return new ResponseEntity<>("Entity not found", HttpStatus.NOT_FOUND);
    }

//    private UserDTO toDTO(User user) {
//        return UserDTO.builder()
//                .id(user.getId())
//                .login(user.getLogin())
//                .nickname("little-" + user.getLogin())
//                .productDTOS(toDTOs(user.getProducts()))
//                .build();
//    }
//
//    private List<ProductDTO> toDTOs(List<Product> products) {
//        return products.stream().map(p -> toDTO(p)).collect(Collectors.toList());
//    }
//
//    private ProductDTO toDTO(Product product) {
//        return ProductDTO.builder()
//                .id(product.getId())
//                .title(product.getTitle())
//                .description(product.getDescription())
//                .price(product.getPrice())
//                .build();
//    }
}

