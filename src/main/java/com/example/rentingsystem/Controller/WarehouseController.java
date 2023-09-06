package com.example.rentingsystem.Controller;

import com.example.rentingsystem.Api.ApiResponse;
import com.example.rentingsystem.Model.Warehouse;
import com.example.rentingsystem.Service.WarehouseSerivce;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/warehouses")
@RequiredArgsConstructor
public class WarehouseController {
    private final WarehouseSerivce warehouseSerivce;

    @GetMapping("/")
    public ResponseEntity getWarehouses(){
        return ResponseEntity.status(HttpStatus.OK).body(warehouseSerivce.getWarehouses());
    }
    @PostMapping("/add")
    public ResponseEntity addWarehouse(@RequestBody @Valid Warehouse warehouse){
        warehouseSerivce.addWarehouse(warehouse);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("added"));
    }

    @PutMapping("/{warehouseId}")
    public ResponseEntity updateWarehouse(@RequestBody @Valid Warehouse warehouse, @PathVariable Integer warehouseId){
        warehouseSerivce.updateWarehouse(warehouse,warehouseId);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("updated"));
    }

    @DeleteMapping("/{warehouseId}")
    public ResponseEntity removeWarehouse(@PathVariable Integer warehouseId){
        warehouseSerivce.removeWarehouse(warehouseId);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("deleted"));
    }

}
