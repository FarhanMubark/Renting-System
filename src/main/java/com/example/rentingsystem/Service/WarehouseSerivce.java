package com.example.rentingsystem.Service;

import com.example.rentingsystem.Api.ApiException;
import com.example.rentingsystem.Model.Warehouse;
import com.example.rentingsystem.Repository.WarehouseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WarehouseSerivce {
    private final WarehouseRepository warehouseRepository;

    public List<Warehouse> getWarehouses() {
        return warehouseRepository.findAll();
    }
    public void addWarehouse(Warehouse warehouse) {
        warehouseRepository.save(warehouse);
    }

    public void updateWarehouse(Warehouse warehouse,Integer warehouseId){
        Warehouse warehouse1 = warehouseRepository.findWarehouseById(warehouseId);
        if (warehouse1 == null){
            throw new ApiException("Could not find ticket");
        }
        warehouse1.setLocation(warehouse.getLocation());
        warehouse1.setSize(warehouse.getSize());
        warehouseRepository.save(warehouse1);
    }

    public void removeWarehouse(Integer warehouseId ) {
        Warehouse warehouse1 = warehouseRepository.findWarehouseById(warehouseId);
        if (warehouse1 == null){
            throw new ApiException("Could not find ticket");
        }
        warehouseRepository.delete(warehouse1);
    }

}
