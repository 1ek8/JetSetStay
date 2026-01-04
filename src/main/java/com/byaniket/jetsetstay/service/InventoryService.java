package com.byaniket.jetsetstay.service;

import com.byaniket.jetsetstay.entity.Room;

public interface InventoryService {

    void initializeRoomForAYear(Room room);

    void deleteAllInventories(Room room);

}
