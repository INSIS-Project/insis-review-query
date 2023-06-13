package com.isep.acme.mappers;



import java.io.IOException;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.isep.acme.model.Product;

@Component
public class ProductMapper {
    
    public Product createProductFromMessage(String body){
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(body);
            String sku = jsonNode.get("sku").asText();
            
            Product product = new Product(sku);
            
            return product;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    
}
