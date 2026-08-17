package com.kritsn.inventoryservice.presentation.rest.controller;

import com.kritsn.inventoryservice.domain.model.StockItem;
import com.kritsn.inventoryservice.domain.port.in.CreateStockItemUseCase;
import com.kritsn.inventoryservice.domain.port.in.GetStockItemUseCase;
import com.kritsn.inventoryservice.domain.port.in.ListStockItemsUseCase;
import com.kritsn.inventoryservice.presentation.rest.dto.CreateStockItemRequest;
import com.kritsn.inventoryservice.presentation.rest.dto.StockItemResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/inventory")
public class InventoryController {

    private final CreateStockItemUseCase createStockItemUseCase;
    private final GetStockItemUseCase getStockItemUseCase;
    private final ListStockItemsUseCase listStockItemsUseCase;

    public InventoryController(CreateStockItemUseCase createStockItemUseCase,
                                GetStockItemUseCase getStockItemUseCase,
                                ListStockItemsUseCase listStockItemsUseCase) {
        this.createStockItemUseCase = createStockItemUseCase;
        this.getStockItemUseCase = getStockItemUseCase;
        this.listStockItemsUseCase = listStockItemsUseCase;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public StockItemResponse create(@RequestBody CreateStockItemRequest request) {
        StockItem item = createStockItemUseCase.create(request.sku(), request.branch(), request.quantityAvailable());
        return toResponse(item);
    }

    @GetMapping("/{id}")
    public StockItemResponse getById(@PathVariable UUID id) {
        return toResponse(getStockItemUseCase.getById(id));
    }

    @GetMapping
    public List<StockItemResponse> listAll() {
        return listStockItemsUseCase.listAll().stream().map(this::toResponse).toList();
    }

    private StockItemResponse toResponse(StockItem item) {
        return new StockItemResponse(item.getId(), item.getSku(), item.getBranch(), item.getQuantityAvailable(),
                item.getCreatedAt());
    }
}
